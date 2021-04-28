package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.Challenge
import retrofit2.http.*

interface ChallengeApi {
	@POST("challenge")
	suspend fun createChallenge(@Body challenge: Challenge)

	@GET("challenge/{id}")
	suspend fun getChallenge(@Path("id") challengeId: Int): Challenge

	@PUT("challenge/{id}")
	suspend fun updateChallenge(@Path("id") challengeId: Int, @Body challenge: Challenge): Challenge

	@DELETE("challenge/{id}")
	suspend fun deleteChallenge(@Path("id") challengeId: Int)

	@POST("challenge({id}/apply")
	suspend fun applyToChallenge(@Path("id") challengeId: Int)

	@GET("challenge/current")
	suspend fun getCurrentChallenges(): List<Challenge>
}
