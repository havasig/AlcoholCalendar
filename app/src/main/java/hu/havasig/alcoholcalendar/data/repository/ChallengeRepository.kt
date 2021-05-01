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
			challenge.startDate.before(currentDate) && challenge.endDate.after(currentDate) && !challenge.isDeleted
		}
		currentChallenges.postValue(activeChallenges)
		try {
			val response = challengeService.updateChallenges(challenges)
			val activeChallengesFromServer = response.filter { challenge ->
				challenge.startDate.before(currentDate) && challenge.endDate.after(currentDate) && !challenge.isDeleted
			}
			currentChallenges.postValue(activeChallengesFromServer)
			challengeDao.save(response)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	suspend fun applyToChallenge(challenge: Challenge) {
		challenge.amIApplied = true
		try {
			challengeService.applyToChallenge(challenge.id)
		} catch (e: Exception) {
			e.printStackTrace()
		}
		challengeDao.save(challenge)
	}

	suspend fun createChallenge(challenge: Challenge): Boolean {
		return try {
			challengeService.createChallenge(challenge)
			challengeDao.save(challenge)
			true
		} catch (e: Exception) {
			e.printStackTrace()
			false
		}
	}

	suspend fun updateChallenge(challenge: Challenge): Boolean {
		return try {
			challengeService.updateChallenge(challenge.id, challenge)
			challengeDao.save(challenge)
			true
		} catch (e: Exception) {
			e.printStackTrace()
			false
		}
	}

	suspend fun deleteChallenge(challenge: Challenge): Boolean {
		challenge.isDeleted = true
		return try {
			challengeService.deleteChallenge(challenge.id)
			challengeDao.save(challenge)
			true
		} catch (e: Exception) {
			e.printStackTrace()
			false
		}
	}
}