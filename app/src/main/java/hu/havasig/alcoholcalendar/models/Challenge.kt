package hu.havasig.alcoholcalendar.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Challenge (
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("start_date")
    val startDate: LocalDate? = null,
    @SerializedName("end_date")
    val endDate: LocalDate? = null
)