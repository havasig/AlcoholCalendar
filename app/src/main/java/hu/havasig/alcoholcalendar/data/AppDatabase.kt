package hu.havasig.alcoholcalendar.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.havasig.alcoholcalendar.data.dao.ChallengeDao
import hu.havasig.alcoholcalendar.data.dao.DrinkDao
import hu.havasig.alcoholcalendar.data.dao.DrinkTypeDao
import hu.havasig.alcoholcalendar.data.model.Challenge
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.data.model.DrinkType


@Database(
	entities = [Drink::class, DrinkType::class, Challenge::class],
	version = 16,
	exportSchema = false
)
@TypeConverters(DateConverter::class, DrinkTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun drinkDao(): DrinkDao
	abstract fun drinkTypeDao(): DrinkTypeDao
	abstract fun challengeDao(): ChallengeDao

	companion object {
		private var INSTANCE: AppDatabase? = null

		fun getDatabase(context: Context): AppDatabase {
			if (INSTANCE == null) {
				synchronized(AppDatabase::class.java) {
					if (INSTANCE == null) {
						INSTANCE = Room.databaseBuilder(
							context.applicationContext as Application,
							AppDatabase::class.java, "alcohol_calendar_database"
						)
							.fallbackToDestructiveMigration()
							.build()
					}
				}
			}
			return INSTANCE!!
		}
	}
}