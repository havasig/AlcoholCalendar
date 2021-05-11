package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.Challenge
import retrofit2.http.*

interface ChallengeApi {
	@GET("challenge")
	suspend fun getChallenges(): List<Challenge>

	@POST("challenge/{id}/apply")
	suspend fun applyToChallenge(@Path("id") challengeId: Int)

	@PUT("challenge/list")
	suspend fun updateChallenges(@Body challenges: List<Challenge>): List<Challenge>

	@GET("challenge/{id}")
	suspend fun getChallenge(@Path("id") challengeId: Int): Challenge

	//ADMIN, only on connection
	@POST("challenge")
	suspend fun createChallenge(@Body challenge: Challenge)

	//ADMIN, only on connection
	@PUT("challenge/{id}")
	suspend fun updateChallenge(@Path("id") challengeId: Int, @Body challenge: Challenge)

	//ADMIN, only on connection
	@DELETE("challenge/{id}")
	suspend fun deleteChallenge(@Path("id") challengeId: Int)
}
