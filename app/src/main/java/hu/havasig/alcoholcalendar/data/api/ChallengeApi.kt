package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.Challenge
import retrofit2.http.*

interface ChallengeApi {
	//ADMIN, only on connection
	@POST("challenge")
	suspend fun createChallenge(@Body challenge: Challenge)

	//ADMIN, only on connection
	@PUT("challenge/{id}")
	suspend fun updateChallenge(@Path("id") challengeId: Int, @Body challenge: Challenge)

	//ADMIN, only on connection
	@DELETE("challenge/{id}")
	suspend fun deleteChallenge(@Path("id") challengeId: Int)

	@POST("challenge/{id}/apply")
	suspend fun applyToChallenge(@Path("id") challengeId: Int)

	@PUT("challenge")
	suspend fun updateChallenges(@Body challenges: List<Challenge>): List<Challenge>

	//probably no need
	@GET("challenge/{id}")
	suspend fun getChallenge(@Path("id") challengeId: Int): Challenge
}
