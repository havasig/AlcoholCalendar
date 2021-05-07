package hu.havasig.alcoholcalendar.data.model.dto

import hu.havasig.alcoholcalendar.data.model.DrinkType

class DrinkTypeDto(
	var id: Int,
	var name: String,
	var percentage: Int,
	var amount: Double,
	var isDeleted: Boolean
) {
	fun toDrinkType(): DrinkType {
		return DrinkType(
			id,
			name,
			percentage,
			amount,
			isDeleted
		)
	}
}