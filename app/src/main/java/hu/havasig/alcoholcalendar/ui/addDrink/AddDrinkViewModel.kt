package hu.havasig.alcoholcalendar.ui.addDrink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.data.model.DrinkType
import hu.havasig.alcoholcalendar.data.repository.DrinkRepository
import hu.havasig.alcoholcalendar.data.repository.DrinkTypeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDrinkViewModel @Inject constructor(
	private val drinkTypeRepository: DrinkTypeRepository,
	private val drinkRepository: DrinkRepository
) : ViewModel() {
	val drinkTypes = drinkTypeRepository.myDrinkTypes

	fun updateMyDrinkTypes() {
		viewModelScope.launch {
			drinkTypeRepository.updateMyDrinkTypes()
		}
	}

	fun getDrinkTypes() {
		viewModelScope.launch {
			drinkTypeRepository.getDrinkTypes()
		}
	}

	fun createDrink(drink: Drink) {
		viewModelScope.launch {
			drinkRepository.createDrink(drink)
		}
	}

	fun updateDrink(drink: Drink) {
		viewModelScope.launch {
			drinkRepository.updateDrink(drink)
		}
	}

	fun deleteDrink(drink: Drink) {
		viewModelScope.launch {
			drinkRepository.deleteDrink(drink)
		}
	}

	fun createDrinkType(drinkType: DrinkType) {
		viewModelScope.launch {
			drinkTypeRepository.createDrinkType(drinkType)
		}
	}
}