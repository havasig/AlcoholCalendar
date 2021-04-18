package hu.havasig.alcoholcalendar.ui.calendarView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewViewModel @Inject constructor() : ViewModel() {
	private val _text = MutableLiveData<String>().apply {
		value = "This is calendar view Fragment"
	}
	val text: LiveData<String> = _text
	// TODO: Implement the ViewModel
}