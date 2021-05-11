package hu.havasig.alcoholcalendar.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import hu.havasig.alcoholcalendar.data.AppDatabase
import hu.havasig.alcoholcalendar.data.api.DrinkTypeApi
import hu.havasig.alcoholcalendar.data.api.ServiceBuilder
import hu.havasig.alcoholcalendar.data.dao.DrinkTypeDao
import hu.havasig.alcoholcalendar.data.model.DrinkType
import javax.inject.Inject

class DrinkTypeRepository @Inject constructor(
	application: Application
) {
	private val drinkTypeService = ServiceBuilder.buildService(DrinkTypeApi::class.java)

	private val drinkTypeDao: DrinkTypeDao = AppDatabase.getDatabase(application).drinkTypeDao()

	val myDrinkTypes = MutableLiveData<List<DrinkType>>()

	suspend fun updateMyDrinkTypes() {
		try {
			val currentDrinkTypes = drinkTypeDao.getAll()
			val response = drinkTypeService.updateType(currentDrinkTypes)
			drinkTypeDao.save(response)
			myDrinkTypes.postValue(
				drinkTypeDao.getAll().filter { drinkType ->
					!drinkType.isDeleted
				})
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	suspend fun getDrinkTypes() {
		val currentDrinkTypes = drinkTypeDao.getAll()
		myDrinkTypes.postValue(currentDrinkTypes.filter { drinkType -> !drinkType.isDeleted })
		try {
			val serverDrinkTypes = drinkTypeService.getDrinkTypes()
			serverDrinkTypes?.let {
				myDrinkTypes.postValue(it.filter { drinkType -> !drinkType.isDeleted })
				drinkTypeDao.save(it)
			}
		} catch (e: Exception) {
			e.printStackTrace()
			//no need to update without connection
		}
	}

	suspend fun deleteDrinkType(drinkType: DrinkType) {
		drinkType.isDeleted = true
		try {
			drinkTypeService.deleteDrinkType(drinkType.id!!)
		} catch (e: Exception) {
			e.printStackTrace()
		}
		drinkTypeDao.save(drinkType)
	}

	suspend fun createDrinkType(drinkType: DrinkType) {
		try {
			drinkType.id = drinkTypeDao.save(drinkType).toInt()
			val response = drinkTypeService.createDrinkType(drinkType)
			response?.let { drinkType.serverId = response.serverId }
			drinkTypeDao.save(drinkType)
			myDrinkTypes.postValue(
				drinkTypeDao.getAll().filter { drinkType -> !drinkType.isDeleted })
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}