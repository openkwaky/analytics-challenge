package openkwaky.challenge.library

import android.content.Context
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import openkwaky.challenge.library.controller.AnalyticsController
import openkwaky.challenge.library.di.ServiceLocator
import openkwaky.challenge.library.model.Configuration
import openkwaky.challenge.library.model.Tag

class Analytics(configuration: Configuration, appContext: Context) : LifecycleObserver {

    var controller: AnalyticsController

    init {
        internalConfiguration = configuration
        internalAppContext = appContext
        controller = ServiceLocator.provideAnalyticsController()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun addTag(tag: Tag) {
        controller.addTag(tag)
    }

    @OnLifecycleEvent(Event.ON_STOP)
    fun onStop(source: LifecycleOwner?) {
        controller.stopSchedule()
    }

    @OnLifecycleEvent(Event.ON_START)
    fun onStart(source: LifecycleOwner?) {
        controller.scheduleSendTags()
    }

    companion object {
        lateinit var internalConfiguration: Configuration
        lateinit var internalAppContext: Context
    }

}
