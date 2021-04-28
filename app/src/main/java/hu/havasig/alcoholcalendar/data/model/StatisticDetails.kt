package hu.havasig.alcoholcalendar.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class StatisticDetails (
    @SerializedName("id")
    val from: Date? = null,
    @SerializedName("id")
    val to: Date? = null,
    @SerializedName("drink_types")
    var drinkTypes: MutableList<DrinkType> = mutableListOf()
)