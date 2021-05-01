package hu.havasig.alcoholcalendar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "drinks")
data class Drink(
    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("server_id")
    var serverId: Int? = null,
    @SerializedName("name")
    var name: String,
    @SerializedName("percentage")
    var percentage: Int? = null,
    @SerializedName("amount")
    var amount: Double? = null,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("last_update")
    var lastUpdate: Date,
    @SerializedName("is_deleted")
    var isDeleted: Boolean = false
)