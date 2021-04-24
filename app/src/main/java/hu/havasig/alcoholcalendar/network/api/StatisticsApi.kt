package hu.havasig.alcoholcalendar.network.api

import hu.havasig.alcoholcalendar.models.Statistics
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface StatisticsApi {
    @GET("")
    fun getStatistics(@Header("user-id") userId: UUID): Call<List<Statistics>>
}
