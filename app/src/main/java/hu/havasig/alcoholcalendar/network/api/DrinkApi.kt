package hu.havasig.alcoholcalendar.network.api

import hu.havasig.alcoholcalendar.models.Drink
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate
import java.util.*

interface DrinkApi {
    @GET("")
    fun getDrinks(@Header("user-id") userId: UUID): Call<List<Drink>>

    @POST("")
    fun addDrink(@Header("user-id") userId: UUID, @Body drink: Drink)

    @GET("/{id}")
    fun getDrinkById(@Header("user-id") userId: UUID, @Path("{id}") drinkId: Int): Call<Drink>

    @PUT("/{id}")
    fun updateDrink(@Header("user-id") userId: UUID, @Path("{id}") drinkId: Int, @Body drink: Drink)

    @DELETE("/{id}")
    fun deleteDrink(@Header("user-id") userId: UUID, @Path("{id}") drinkId: Int)

    @GET("/find-by-date")
    fun findDrinksByDate(@Header("user-id") userId: UUID, date: LocalDate): Call<List<Drink>>
}
