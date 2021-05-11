package hu.havasig.alcoholcalendar.ui.addDrink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.data.model.DrinkType
import hu.havasig.alcoholcalendar.data.repository.DrinkRepository
import hu.havasig.alcoholcalendar.data.repository.DrinkTypeRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AddDrinkViewModel @Inject constructor(
	private val drinkTypeRepository: DrinkTypeRepository,
	private val drinkRepository: DrinkRepository
) : ViewModel() {
	val drinkTypes = drinkTypeRepository.myDrinkTypes
	val drinks = drinkRepository.myDrinks

	init {
		runBlocking {
			drinkRepository.updateDrinks()
			getDrinkTypesFromServer()
			updateMyDrinkTypes()
		}
	}

	fun updateMyDrinkTypes() {
		viewModelScope.launch {
			drinkTypeRepository.updateMyDrinkTypes()
		}
	}

	private fun getDrinkTypesFromServer() {
		viewModelScope.launch {
			drinkTypeRepository.getDrinkTypesFromServer()
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