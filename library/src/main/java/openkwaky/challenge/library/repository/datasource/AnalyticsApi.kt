package openkwaky.challenge.library.repository.datasource

import android.util.Log
import openkwaky.challenge.library.model.Tag
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException
import javax.inject.Inject

class AnalyticsApi @Inject constructor(private val service: AnalyticsService) {
    interface AnalyticsService {
        @POST("tags/new")
        fun sendTag(@Body tag: Tag): Call<Void>
    }

    fun sendTag(tag: Tag) : Boolean {
        try {
            service.sendTag(tag).execute()
            return true
        } catch (e: IOException) {
            Log.d("AnalyticsApi", "Network error : ${e.localizedMessage}")
        }
        return false
    }
}
