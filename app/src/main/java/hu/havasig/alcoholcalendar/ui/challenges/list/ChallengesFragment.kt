package hu.havasig.alcoholcalendar.ui.challenges.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.data.model.Challenge

@AndroidEntryPoint
class ChallengesFragment : Fragment(),
	ChallengeAdapter.OnChallengeClickedListener {

	companion object {
		fun newInstance() = ChallengesFragment()
	}

	private lateinit var challengesViewModel: ChallengesViewModel
	private lateinit var challengesRecyclerView: RecyclerView
	private lateinit var noChallengeLL: LinearLayout
	private lateinit var challengesAdapter: ChallengeAdapter
	private val challenges: ArrayList<Challenge> = ArrayList()
	private var rootLayout: View? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		challengesViewModel =
			ViewModelProvider(this).get(ChallengesViewModel::class.java)
		rootLayout = inflater.inflate(R.layout.fragment_challenges, container, false)
		challengesAdapter = ChallengeAdapter(this@ChallengesFragment, challenges)

		challengesRecyclerView = rootLayout!!.findViewById(R.id.challengesRV)
		noChallengeLL = rootLayout!!.findViewById(R.id.noChallengeLL)

		return rootLayout
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		challengesRecyclerView.apply {
			layoutManager = LinearLayoutManager(activity)
			adapter = challengesAdapter
		}

		challengesViewModel.activeChallenges.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				challenges.clear()
				challenges.addAll(it)
				challengesAdapter.notifyDataSetChanged()
				noChallengeLL.visibility = View.GONE
			} else {
				challenges.clear()
				challengesAdapter.notifyDataSetChanged()
				noChallengeLL.visibility = View.VISIBLE
			}
		})
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		challengesViewModel = ViewModelProvider(this).get(ChallengesViewModel::class.java)
		// TODO: Use the ViewModel
	}

	override fun onChallengeSelected(challenge: Challenge) {
		val bundle = bundleOf("challengeId" to challenge.id)
		rootLayout!!.findNavController().navigate(R.id.nav_challenge_details, bundle)
	}
}