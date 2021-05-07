package hu.havasig.alcoholcalendar.ui.myDrinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.data.repository.DrinkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyDrinksViewModel @Inject constructor(private val drinkRepository: DrinkRepository) : ViewModel() {
	var drinks: LiveData<List<Drink>> = drinkRepository.myDrinks

	fun updateDrinks() {
		viewModelScope.launch {
			drinkRepository.updateDrinks()
		}
	}

	fun deleteDrink(drink: Drink) {
		viewModelScope.launch {
			drinkRepository.deleteDrink(drink)
		}
	}
	fun restoreDrink(drinkId: Int) {
		viewModelScope.launch {
			drinkRepository.restoreDrink(drinkId)
		}
	}
}