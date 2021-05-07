package hu.havasig.alcoholcalendar.data.model.dto

import hu.havasig.alcoholcalendar.data.model.Drink
import java.util.*

class DrinkDto(
	var id: Int? = null,
	var clientId: Int,
	var name: String,
	var percentage: Int? = null,
	var amount: Double? = null,
	var date: Date? = null,
	var type: DrinkTypeDto,
	var isDeleted: Boolean = false
) {
	fun toDrink(): Drink {
		return Drink(
			clientId,
			id,
			name,
			percentage,
			amount,
			date,
			type.toDrinkType(),
			Calendar.getInstance().time,
			isDeleted
		)
	}
}