package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.model.Drink
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.time.LocalDate
import java.util.*

interface DrinkApi {
    //create one drink
    @POST("drink")
    suspend fun createDrink(@Body drink: Drink): Int

    //sync from server
    @PUT("drink")
    suspend fun updateDrinks(@Body drinks: List<Drink>) : List<Drink>

    //update after edit
    @PUT("drink/{id}")
    suspend fun updateDrink(@Path("{id}") drinkId: Int, @Body drink: Drink)

    @DELETE("drink/{id}")
    suspend fun deleteDrink(@Path("{id}") drinkId: Int)



    //probably no need
    @GET("drink/{id}")
    suspend fun getDrinkById(@Path("{id}") drinkId: Int): Drink

    //probably no need
    @GET("drink/find-by-date")
    suspend fun findDrinksByDate(date: LocalDate): Call<List<Drink>>

    //probably no need, get all drinks (sync is enough?)
    @GET("drink")
    suspend fun getMyDrinks(): List<Drink>
}
