package hu.havasig.alcoholcalendar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.havasig.alcoholcalendar.data.model.DrinkType

@Dao
interface DrinkTypeDao {
	@Query("SELECT * FROM drink_types")
	suspend fun getAll(): List<DrinkType>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(drinks: List<DrinkType>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(drinks: DrinkType)
}