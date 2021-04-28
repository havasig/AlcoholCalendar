package hu.havasig.alcoholcalendar.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.model.Statistic
import hu.havasig.alcoholcalendar.data.repository.StatisticRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val statisticRepository: StatisticRepository): ViewModel() {
	var statistics: MutableLiveData<Statistic?> = statisticRepository.statistics

	init {
		viewModelScope.launch {
			statisticRepository.getStatistics()
		}
	}
}