package hu.havasig.alcoholcalendar.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import hu.havasig.alcoholcalendar.data.model.DrinkType
import java.util.*


object DateConverter {
	@TypeConverter
	fun toDate(dateLong: Long?): Date? {
		return if (dateLong == null) null else Date(dateLong)
	}

	@TypeConverter
	fun fromDate(date: Date?): Long? {
		return date?.time
	}
}

@ProvidedTypeConverter
object DrinkTypeConverter {
	var gson = Gson()

	@TypeConverter
	fun toDrinkType(typeString: String?): DrinkType? {
		return if (typeString == null) null else gson.fromJson(typeString, DrinkType::class.java)
	}

	@TypeConverter
	fun fromDrinkType(drinkType: DrinkType?): String? {
		return gson.toJson(drinkType)
	}
}