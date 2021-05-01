package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.Statistic
import retrofit2.http.GET

interface StatisticApi {
	@GET("statistics")
	suspend fun getStatistics(): Statistic
}
