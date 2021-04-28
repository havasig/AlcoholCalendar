package hu.havasig.alcoholcalendar.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import hu.havasig.alcoholcalendar.data.AppDatabase
import hu.havasig.alcoholcalendar.data.api.DrinkApi
import hu.havasig.alcoholcalendar.data.api.ServiceBuilder
import hu.havasig.alcoholcalendar.data.dao.DrinkDao
import hu.havasig.alcoholcalendar.data.model.Drink
import javax.inject.Inject

class DrinkRepository @Inject constructor(
	application: Application
) {
	private val drinkService = ServiceBuilder.buildService(DrinkApi::class.java)
	private val drinkDao: DrinkDao = AppDatabase.getDatabase(application).drinkDao()

	val myDrinks = MutableLiveData<List<Drink>>()

	suspend fun getMyDrinks() {
		myDrinks.postValue(drinkDao.getAll())
		try {
			val drinkFromServer = drinkService.getMyDrinks()
			drinkDao.save(drinkFromServer)
			myDrinks.postValue(drinkDao.getAll())
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	suspend fun updateDrinkList() {
		try {
			val currentDrinks = drinkDao.getAll()
			drinkService.updateDrink(currentDrinks)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	suspend fun createDrink(drink: Drink) {
		try {
			val serverId = drinkService.createDrink(drink)
			drink.serverId = serverId
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
			drinkDao.save(drink)
		}
	}

	suspend fun updateDrink(drinkId: Int, drink: Drink) {
		try {
			drinkService.updateDrink(drinkId, drink)
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}