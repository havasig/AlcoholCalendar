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

	suspend fun getMyDrinkTypes() {
		myDrinkTypes.postValue(drinkTypeDao.getAll())
		try {
			val response = drinkTypeService.getDrinkTypes()
			myDrinkTypes.postValue(response)
			drinkTypeDao.save(response)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}