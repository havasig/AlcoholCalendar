package hu.havasig.alcoholcalendar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "drink_types")
data class DrinkType (
    @SerializedName("id")
    @PrimaryKey
    val id: Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("percentage")
    val percentage: Int? = null,
    @SerializedName("amount")
    val amount: Double? = null
)