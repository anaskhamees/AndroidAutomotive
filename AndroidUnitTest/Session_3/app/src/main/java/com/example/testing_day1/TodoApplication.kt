

package com.example.testing_day1

import android.app.Application



/**
 * An application that lazily provides a repository. Note that this Service Locator pattern is
 * used to simplify the sample. Consider a Dependency Injection framework.
 *
 * Also, sets up Timber in the DEBUG BuildConfig. Read Timber's documentation for production setups.
 */
class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
