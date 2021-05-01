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
		val currentDrinkTypes = drinkTypeDao.getAll()
		myDrinkTypes.postValue(currentDrinkTypes.filter { drinkType -> !drinkType.isDeleted })
		try {
			val response = drinkTypeService.updateType(currentDrinkTypes)
			myDrinkTypes.postValue(currentDrinkTypes.filter { drinkType -> !drinkType.isDeleted })
			drinkTypeDao.save(response)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	suspend fun deleteDrinkType(drinkType: DrinkType) {
		drinkType.isDeleted = true
		try {
			drinkTypeService.deleteDrinkType(drinkType.id)
		} catch (e: Exception) {
			e.printStackTrace()
		}
		drinkTypeDao.save(drinkType)
	}
}