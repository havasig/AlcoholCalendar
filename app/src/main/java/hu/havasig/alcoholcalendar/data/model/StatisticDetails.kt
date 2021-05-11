package hu.havasig.alcoholcalendar.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class StatisticDetails (
    @SerializedName("from")
    val from: Date,
    @SerializedName("to")
    val to: Date,
    @SerializedName("beer")
    var beer: Double? = null,
    @SerializedName("wine")
    var wine: Double? = null,
    @SerializedName("cocktail")
    var cocktail: Double? = null,
    @SerializedName("shots")
    var shots: Double? = null
)