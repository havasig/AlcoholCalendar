package hu.havasig.alcoholcalendar.network.api

import hu.havasig.alcoholcalendar.models.DrinkType
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface DrinkTypesApi {
	@GET("")
	fun getDrinkTypes(@Header("user-id") userId: UUID): Call<List<DrinkType>>

	@POST("")
	fun addDrinkType(@Header("user-id") userId: UUID, @Body drinkType: DrinkType)

	@DELETE("/{id}")
	fun deleteDrinkType(@Header("user-id") userId: UUID, @Path("id") drinkTypeId: Int)
}
