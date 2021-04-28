package hu.havasig.alcoholcalendar.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import hu.havasig.alcoholcalendar.data.AppDatabase
import hu.havasig.alcoholcalendar.data.api.ChallengeApi
import hu.havasig.alcoholcalendar.data.api.ServiceBuilder
import hu.havasig.alcoholcalendar.data.model.Challenge
import java.util.*
import javax.inject.Inject

class ChallengeRepository @Inject constructor(
	application: Application
) {
	private val challengeService = ServiceBuilder.buildService(ChallengeApi::class.java)
	private val challengeDao = AppDatabase.getDatabase(application).challengeDao()

	val currentChallenges = MutableLiveData<List<Challenge>>()

	suspend fun getCurrentChallenges() {
		val challenges = challengeDao.getAll()
		val currentDate = Calendar.getInstance().time
		val activeChallenges = challenges.filter { challenge ->
			challenge.startDate.before(currentDate) && challenge.endDate.after(currentDate)
		}
		currentChallenges.postValue(activeChallenges)
		try {
			val response = challengeService.getCurrentChallenges()
			currentChallenges.postValue(response)
			challengeDao.save(response)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	suspend fun createChallenge(challenge: Challenge) {
		try {
			challengeService.createChallenge(challenge)
			challengeDao.save(challenge)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}