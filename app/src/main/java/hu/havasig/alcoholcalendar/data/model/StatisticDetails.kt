package hu.havasig.alcoholcalendar.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class StatisticDetails (
    @SerializedName("from")
    val from: Date? = null,
    @SerializedName("to")
    val to: Date? = null,
    @SerializedName("beer")
    var beer: Int? = null,
    @SerializedName("wine")
    var wine: Int? = null,
    @SerializedName("cocktail")
    var cocktail: Int? = null,
    @SerializedName("shots")
    var shots: Int? = null
)