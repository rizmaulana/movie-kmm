package id.rizmaulana.moviekmm.android

import android.app.Application
import id.rizmaulana.moviekmm.di.initKoin

class KMMMovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}