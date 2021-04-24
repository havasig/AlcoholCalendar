package hu.havasig.alcoholcalendar.models

import com.google.gson.annotations.SerializedName

data class DrinkType (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("percentage")
    val percentage: Int? = null,
    @SerializedName("amount")
    val amount: Double? = null
) {
}