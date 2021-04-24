package hu.havasig.alcoholcalendar.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class StatisticDetails (
    @SerializedName("id")
    val from: LocalDate? = null,
    @SerializedName("id")
    val to: LocalDate? = null,
    @SerializedName("drink_types")
    var drinkTypes: MutableList<DrinkType> = mutableListOf()
)