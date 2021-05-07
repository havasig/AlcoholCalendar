package hu.havasig.alcoholcalendar.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import hu.havasig.alcoholcalendar.data.model.Drink

@Dao
interface DrinkDao {
	@Query("SELECT * FROM drinks")
	suspend fun getAll(): List<Drink>

	@Query("SELECT * FROM drinks WHERE id IN (:drinkId)")
	suspend fun getById(drinkId: Int): Drink?

	@Insert(onConflict = REPLACE)
	suspend fun save(drinks: List<Drink>)

	@Insert(onConflict = REPLACE)
	suspend fun save(drink: Drink)
}