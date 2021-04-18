package hu.havasig.alcoholcalendar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is home Fragment, but it is going to be menu fragment"
	}
	val text: LiveData<String> = _text
}