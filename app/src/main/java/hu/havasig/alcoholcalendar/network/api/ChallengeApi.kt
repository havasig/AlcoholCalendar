package hu.havasig.alcoholcalendar.network.api

import hu.havasig.alcoholcalendar.models.Challenge
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ChallengeApi {
	@POST("")
	fun createChallenge(
		@Header("user-id") userId: UUID,
		@Body challenge: Challenge
	): Call<Challenge>

	@GET("/{id}")
	fun getChallenge(@Header("user-id") userId: UUID, @Path("id") challengeId: Int): Call<Challenge>

	@PUT("/{id}")
	fun updateChallenge(
		@Header("user-id") userId: UUID,
		@Path("id") challengeId: Int,
		@Body challenge: Challenge
	): Call<Challenge>

	@DELETE("/{id}")
	fun deleteChallenge(@Header("user-id") userId: UUID, @Path("id") challengeId: Int)

	@POST("{id}/apply")
	fun applyToChallenge(@Header("user-id") userId: UUID, @Path("id") challengeId: Int)

	@GET("/current")
	fun getCurrentChallenges(@Header("user-id") userId: UUID): Call<List<Challenge>>
}
