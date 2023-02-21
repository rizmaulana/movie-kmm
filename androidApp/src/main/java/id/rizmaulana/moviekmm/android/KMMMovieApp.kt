package id.rizmaulana.moviekmm.android

import android.app.Application
import id.rizmaulana.moviekmm.di.initKoin
import org.koin.android.ext.koin.androidContext

class KMMMovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@KMMMovieApp)

        }
    }
}