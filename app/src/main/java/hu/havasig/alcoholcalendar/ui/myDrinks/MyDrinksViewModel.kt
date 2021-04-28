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

	init {
		viewModelScope.launch {
			drinkRepository.updateDrinkList()
		}
	}

	fun getMyDrinks() {
		viewModelScope.launch {
			drinkRepository.getMyDrinks()
		}
	}
}