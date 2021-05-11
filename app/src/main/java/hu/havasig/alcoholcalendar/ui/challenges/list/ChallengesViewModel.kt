package hu.havasig.alcoholcalendar.ui.challenges.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.repository.ChallengeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(
	private val challengeRepository: ChallengeRepository
) : ViewModel() {
	val activeChallenges = challengeRepository.activeChallenges

	init {
		viewModelScope.launch {
			challengeRepository.getCurrentChallenges()
		}
	}
}