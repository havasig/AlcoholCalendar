package hu.havasig.alcoholcalendar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "drink_types")
data class DrinkType (
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("percentage")
    var percentage: Int,
    @SerializedName("amount")
    var amount: Double,
    @SerializedName("is_deleted")
    var isDeleted: Boolean
)