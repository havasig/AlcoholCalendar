package hu.havasig.alcoholcalendar.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Drink (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("percentage")
    val percentage: Int? = null,
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("date")
    val date: LocalDate? = null
)