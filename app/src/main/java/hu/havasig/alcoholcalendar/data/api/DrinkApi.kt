package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.Drink
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.time.LocalDate
import java.util.*

interface DrinkApi {
    @GET("drink")
    suspend fun getMyDrinks(): List<Drink>

    @POST("drink")
    suspend fun createDrink(@Body drink: Drink): Int

    @PUT("drink")
    suspend fun updateDrink(@Body drinks: List<Drink>)

    @GET("drink/{id}")
    suspend fun getDrinkById(@Path("{id}") drinkId: Int): Drink

    @PUT("drink/{id}")
    suspend fun updateDrink(@Path("{id}") drinkId: Int, @Body drink: Drink)

    @DELETE("drink/{id}")
    suspend fun deleteDrink(@Path("{id}") drinkId: Int)

    @GET("drink/find-by-date")
    suspend fun findDrinksByDate(date: LocalDate): Call<List<Drink>>
}
