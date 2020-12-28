package openkwaky.challenge.library

import android.content.Context
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import openkwaky.challenge.library.controller.AnalyticsController
import openkwaky.challenge.library.di.AnalyticsComponent
import openkwaky.challenge.library.model.Configuration
import openkwaky.challenge.library.model.Tag
import javax.inject.Inject

class Analytics(val configuration: Configuration, val appContext: Context) : LifecycleObserver {

    @Inject lateinit var controller: AnalyticsController

    init {
        AnalyticsComponent.Initializer.init(this).inject(this)
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

}
