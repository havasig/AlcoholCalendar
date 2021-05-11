package hu.havasig.alcoholcalendar.ui.challenges.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ChallengeDetailsFragment : Fragment() {

	companion object {
		fun newInstance() = ChallengeDetailsFragment()
	}

	private lateinit var challengeDetailsViewModel: ChallengeDetailsViewModel
	private var rootLayout: View? = null
	private val dateFormat = SimpleDateFormat("yyyy.MM.dd.", Locale.getDefault())


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		challengeDetailsViewModel =
			ViewModelProvider(this).get(ChallengeDetailsViewModel::class.java)
		rootLayout = inflater.inflate(R.layout.fragment_challenge_details, container, false)

		val challengeNameTv: TextView = rootLayout!!.findViewById(R.id.challengeNameTv)
		val challengeDescriptionTv: TextView =
			rootLayout!!.findViewById(R.id.challengeDescriptionTv)
		val challengeStartDateTv: TextView = rootLayout!!.findViewById(R.id.challengeStartDateTv)
		val challengeEndDateTv: TextView = rootLayout!!.findViewById(R.id.challengeEndDateTv)

		val challengeId = arguments?.getInt("challengeId")!!
		challengeDetailsViewModel.getChallenge(challengeId)
		challengeDetailsViewModel.challenge.observe(viewLifecycleOwner, {

			challengeNameTv.setText(it.name, TextView.BufferType.EDITABLE)
			challengeDescriptionTv.setText(it.description, TextView.BufferType.EDITABLE)
			challengeStartDateTv.setText(
				dateFormat.format(it.startDate),
				TextView.BufferType.EDITABLE
			)
			challengeEndDateTv.setText(dateFormat.format(it.endDate), TextView.BufferType.EDITABLE)
		})


		return rootLayout
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		challengeDetailsViewModel =
			ViewModelProvider(this).get(ChallengeDetailsViewModel::class.java)
		// TODO: Use the ViewModel
	}
}