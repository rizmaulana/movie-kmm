package id.rizmaulana.moviekmm.di

import id.rizmaulana.moviekmm.data.MovieRepository
import id.rizmaulana.moviekmm.data.mapper.MovieDataMapper
import id.rizmaulana.moviekmm.data.source.MovieRemoteDataSource
import id.rizmaulana.moviekmm.domain.interactor.MovieInteractor
import id.rizmaulana.moviekmm.domain.interactor.MovieInteractorImpl
import id.rizmaulana.moviekmm.platformModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        appModule()
        platformModule()
    }
}

fun appModule() = module {
    single {
        MovieDataMapper()
    }
    single {
        MovieRemoteDataSource(get())
    }

    single {
        MovieRepository(get(), get())
    }

    single<MovieInteractor> {
        MovieInteractorImpl(get())
    }


    single {
        Json { isLenient = true; ignoreUnknownKeys = true }
    }


    single {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(get())
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}
