package hu.havasig.alcoholcalendar.ui.weeklyChallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.repository.ChallengeRepository
import hu.havasig.alcoholcalendar.data.repository.DrinkRepository
import hu.havasig.alcoholcalendar.data.repository.DrinkTypeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyChallengeViewModel @Inject constructor(
	private val challengeRepository: ChallengeRepository
) : ViewModel() {
	val currentChallenges = challengeRepository.currentChallenges

	init {
		viewModelScope.launch {
			challengeRepository.getCurrentChallenges()
		}
	}
}