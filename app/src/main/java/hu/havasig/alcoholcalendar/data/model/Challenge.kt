package hu.havasig.alcoholcalendar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "challenges")
data class Challenge (
    @SerializedName("id")
    @PrimaryKey
    var id: Int? = null,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("image_url")
    var imageUrl: String? = null,
    @SerializedName("start_date")
    var startDate: Date,
    @SerializedName("end_date")
    var endDate: Date,
    @SerializedName("am_i_applied")
    var amIApplied: Boolean,
    @SerializedName("is_deleted")
    var isDeleted: Boolean
)