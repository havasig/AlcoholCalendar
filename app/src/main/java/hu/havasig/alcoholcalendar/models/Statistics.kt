package hu.havasig.alcoholcalendar.models

import com.google.gson.annotations.SerializedName

data class Statistics (
    @SerializedName("last_7_days")
    val last7Days: StatisticDetails? = null,
    @SerializedName("last_week")
    val lastWeek: StatisticDetails? = null,
    @SerializedName("last_month")
    val lastMonth: StatisticDetails? = null,
    @SerializedName("last_year")
    val lastYear: StatisticDetails? = null
)