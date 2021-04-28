package hu.havasig.alcoholcalendar.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
	private var sharedPreferences: SharedPreferences? = null

	fun setup(context: Context) {
		sharedPreferences = context.getSharedPreferences("alcoholcalendar.sharedprefs", MODE_PRIVATE)
	}

	var userId: String?
		get() = Key.USERID.getString()
		set(value) = Key.USERID.setString(value)

	private enum class Key {
		USERID;

		fun getString(): String? =
			if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(
				name,
				""
			) else null

		fun setString(value: String?) =
			value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

		fun remove() = sharedPreferences!!.edit { remove(name) }
	}
}