package hu.havasig.alcoholcalendar.ui.weeklyChallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.havasig.alcoholcalendar.R

class WeeklyChallengeFragment : Fragment() {

	companion object {
		fun newInstance() = WeeklyChallengeFragment()
	}

	private lateinit var weeklyChallengeViewModel: WeeklyChallengeViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		weeklyChallengeViewModel =
			ViewModelProvider(this).get(WeeklyChallengeViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_weekly_challenge, container, false)
		val textView: TextView = root.findViewById(R.id.text_weekly_challenge)
		weeklyChallengeViewModel.text.observe(viewLifecycleOwner, Observer {
			textView.text = it
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		weeklyChallengeViewModel = ViewModelProvider(this).get(WeeklyChallengeViewModel::class.java)
		// TODO: Use the ViewModel
	}
}