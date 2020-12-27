package openkwaky.challenge.analytics.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import openkwaky.challenge.analytics.BuildConfig
import openkwaky.challenge.library.Analytics
import openkwaky.challenge.library.model.Configuration


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(NetworkFlipperPlugin())
            client.addPlugin(SharedPreferencesFlipperPlugin(this))
            client.start()
        }
        analytics = Analytics(
            Configuration.Builder().withDelay(30).withUrl("http://localhost:3000").build(),
            this
        )
    }

    companion object {
        lateinit var analytics: Analytics
            private set
    }
}