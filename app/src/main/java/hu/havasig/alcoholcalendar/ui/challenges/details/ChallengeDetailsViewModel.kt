package hu.havasig.alcoholcalendar.ui.challenges.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.havasig.alcoholcalendar.data.repository.ChallengeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChallengeDetailsViewModel @Inject constructor(
	private val challengeRepository: ChallengeRepository
) : ViewModel() {
	val challenge = challengeRepository.challenge

	fun getChallenge(challengeId: Int) {
		viewModelScope.launch {
			challengeRepository.getChallenge(challengeId)
		}
	}
}