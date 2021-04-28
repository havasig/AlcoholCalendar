package hu.havasig.alcoholcalendar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.havasig.alcoholcalendar.data.model.Challenge

@Dao
interface ChallengeDao {
	@Query("SELECT * FROM challenges")
	suspend fun getAll(): List<Challenge>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(challenges: List<Challenge>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun save(challenge: Challenge)
}