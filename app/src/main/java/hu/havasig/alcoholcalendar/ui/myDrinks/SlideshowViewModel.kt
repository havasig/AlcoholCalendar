package hu.havasig.alcoholcalendar.ui.myDrinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(): ViewModel() {
	private val _text = MutableLiveData<String>().apply {
		value = "This is my drinks Fragment"
	}
	val text: LiveData<String> = _text
// TODO: Implement the ViewModel
}