package openkwaky.challenge.library.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import openkwaky.challenge.library.core.AnalyticsInteractor
import openkwaky.challenge.library.model.Tag

class AnalyticsController constructor(private val interactor: AnalyticsInteractor) {
    fun addTag(tag: Tag) {
        CoroutineScope(Dispatchers.IO).launch {
            interactor.addTag(tag)
        }
    }

    fun scheduleSendTags() {
        CoroutineScope(Dispatchers.IO).launch {
            interactor.scheduleSendTags()
        }
    }

    fun stopSchedule() {
        interactor.stopSchedule()
    }
}
