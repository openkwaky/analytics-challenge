package openkwaky.challenge.library.timer

import android.util.Log
import openkwaky.challenge.library.model.Configuration
import openkwaky.challenge.library.repository.TagRepository
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class Timer @Inject constructor(private val repository: TagRepository, private val configuration: Configuration) {

    private val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private val isRunning: AtomicBoolean = AtomicBoolean(false)
    private var task: ScheduledFuture<*>? = null

    fun startTimer() {
        Log.d("Timer", "started")
        if (!isRunning.get()) {
            task = scheduler.scheduleAtFixedRate(
                { repository.sendTags() },
                0,
                configuration.delay!!.toLong(),
                TimeUnit.SECONDS
            )
            isRunning.set(true)
        }
    }

    fun stopTimer() {
        Log.d("Timer", "stopped")
        task?.cancel(false)
        isRunning.set(false)
    }
}
