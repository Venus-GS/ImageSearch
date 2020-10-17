package devenus.rodi.imagesearch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ImageSearchApplication : Application() {

    companion object {
        private var instance: ImageSearchApplication? = null

        fun getInstance() : ImageSearchApplication {
            return if (instance == null) ImageSearchApplication() else instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}