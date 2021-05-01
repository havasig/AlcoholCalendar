package hu.havasig.alcoholcalendar.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import hu.havasig.alcoholcalendar.data.AppDatabase
import hu.havasig.alcoholcalendar.data.api.DrinkApi
import hu.havasig.alcoholcalendar.data.api.ServiceBuilder
import hu.havasig.alcoholcalendar.data.dao.DrinkDao
import hu.havasig.alcoholcalendar.data.model.Drink
import java.util.*
import javax.inject.Inject

class DrinkRepository @Inject constructor(
	application: Application
) {
	private val drinkService = ServiceBuilder.buildService(DrinkApi::class.java)
	private val drinkDao: DrinkDao = AppDatabase.getDatabase(application).drinkDao()

	val myDrinks = MutableLiveData<List<Drink>>()

	suspend fun updateDrinks() {
		myDrinks.postValue(drinkDao.getAll())
		try {
			val currentDrinks = drinkDao.getAll()
			for (drink in currentDrinks) {
				if (drink.serverId == null) {
					createDrink(drink)
				}
			}
			val serverDrinks = drinkService.updateDrinks(currentDrinks)
			drinkDao.save(serverDrinks)
			myDrinks.postValue(drinkDao.getAll())
		} catch (e: Exception) {
			e.printStackTrace()
			//no need to update without connection
		}
	}

	suspend fun createDrink(drink: Drink) {
		try {
			val serverId = drinkService.createDrink(drink)
			drink.serverId = serverId
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
			//save to db without server id
			drinkDao.save(drink)
		}
	}

	suspend fun deleteDrink(drink: Drink) {
		drink.isDeleted = true
		try {
			drink.serverId?.let {
				drinkService.deleteDrink(it)
			}
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
			//save to db without server update
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