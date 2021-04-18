package hu.havasig.alcoholcalendar.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(): ViewModel() {
	private val _text = MutableLiveData<String>().apply {
		value = "This is menu Fragment"
	}
	val text: LiveData<String> = _text
	// TODO: Implement the ViewModel
}