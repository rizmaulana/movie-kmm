package id.rizmaulana.moviekmm

import id.rizmaulana.moviekmm.viewmodel.MovieViewModel
import io.ktor.client.engine.android.Android
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {

    single {
        Android.create()
    }

    viewModel {
        MovieViewModel(get())
    }
}