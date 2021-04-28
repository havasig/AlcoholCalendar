package hu.havasig.alcoholcalendar.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import hu.havasig.alcoholcalendar.data.api.ServiceBuilder
import hu.havasig.alcoholcalendar.data.api.StatisticApi
import hu.havasig.alcoholcalendar.data.model.Statistic
import javax.inject.Inject

class StatisticRepository @Inject constructor() {
	private val statisticService = ServiceBuilder.buildService(StatisticApi::class.java)

	val statistics = MutableLiveData<Statistic?>()

	suspend fun getStatistics() {
		statistics.postValue(null)
		try {
			val response = statisticService.getStatistics()
			statistics.postValue(response)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}