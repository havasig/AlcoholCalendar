package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.DrinkType
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface DrinkTypeApi {
	@GET("drink-type")
	suspend fun getDrinkTypes(): List<DrinkType>

	@POST("drink-type")
	suspend fun addDrinkType(@Body drinkType: DrinkType)

	@DELETE("drink-type/{id}")
	suspend fun deleteDrinkType(@Path("id") drinkTypeId: Int)
}
