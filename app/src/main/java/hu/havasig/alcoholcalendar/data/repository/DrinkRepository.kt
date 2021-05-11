package hu.havasig.alcoholcalendar.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import hu.havasig.alcoholcalendar.data.AppDatabase
import hu.havasig.alcoholcalendar.data.api.DrinkApi
import hu.havasig.alcoholcalendar.data.api.DrinkTypeApi
import hu.havasig.alcoholcalendar.data.api.ServiceBuilder
import hu.havasig.alcoholcalendar.data.dao.DrinkDao
import hu.havasig.alcoholcalendar.data.dao.DrinkTypeDao
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.data.model.DrinkType
import javax.inject.Inject

class DrinkRepository @Inject constructor(
	application: Application
) {
	private val drinkService = ServiceBuilder.buildService(DrinkApi::class.java)
	private val drinkDao: DrinkDao = AppDatabase.getDatabase(application).drinkDao()

	val myDrinks = MutableLiveData<List<Drink>>()

	suspend fun createDrink(drink: Drink) {
		try {
			drink.id = drinkDao.save(drink).toInt()
			drink.serverId = drinkService.createDrink(drink)?.serverId
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
			//save to db without server id
			drinkDao.save(drink)
		}
	}

	suspend fun updateDrink(drink: Drink) {
		try {
			drink.serverId = drinkService.updateDrink(drink)?.id
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
			//save to db without server update
			drinkDao.save(drink)
		}
	}

	suspend fun updateDrinks() {
		val currentDrinks = drinkDao.getAll()
		myDrinks.postValue(currentDrinks.filter { drink -> !drink.isDeleted })
		try {
			val serverDrinks =
				drinkService.updateDrinks(currentDrinks)
			myDrinks.postValue(drinkDao.getAll().filter { drink -> !drink.isDeleted })
			serverDrinks?.let { drinkDao.save(it) }
		} catch (e: Exception) {
			e.printStackTrace()
			//no need to update without connection
		}
	}

	suspend fun deleteDrink(drink: Drink) {
		Log.d("MY_LOG", "deleteDrink")
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

	suspend fun restoreDrink(drinkId: Int) {
		Log.d("MY_LOG", "restoreDrink")
		val drink = drinkDao.getById(drinkId) ?: return
		drink.isDeleted = false
		try {
			drink.serverId?.let {
				drinkService.restoreDrink(it)
			}
			drinkDao.save(drink)
		} catch (e: Exception) {
			e.printStackTrace()
			//save to db without server update
			drinkDao.save(drink)
		}
	}
}