package openkwaky.challenge.library.core

import openkwaky.challenge.library.model.Tag
import openkwaky.challenge.library.repository.database.TagRepository
import openkwaky.challenge.library.timer.Timer

class AnalyticsInteractor constructor(private val repository: TagRepository, private val timer: Timer) {
    fun addTag(tag: Tag) {
        repository.addTag(tag)
    }

    fun scheduleSendTags(){
        timer.startTimer()
    }

    fun stopSchedule(){
        timer.stopTimer()
    }
}
