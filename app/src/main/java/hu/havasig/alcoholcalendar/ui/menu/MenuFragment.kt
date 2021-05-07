package hu.havasig.alcoholcalendar.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R

@AndroidEntryPoint
class MenuFragment : Fragment() {

	companion object {
		fun newInstance() = MenuFragment()
	}

	private lateinit var menuViewModel: MenuViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		menuViewModel =
			ViewModelProvider(this).get(MenuViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_menu, container, false)
		val last7DaysDate: TextView = root.findViewById(R.id.last7DaysDate)
		val last7DaysBeer: TextView = root.findViewById(R.id.last7DaysBeerValue)
		val last7DaysWine: TextView = root.findViewById(R.id.last7DaysWineValue)
		val last7DaysCocktails: TextView = root.findViewById(R.id.last7DaysCocktailsValue)
		val last7DaysShots: TextView = root.findViewById(R.id.last7DaysShotsValue)
		menuViewModel.statistics.observe(viewLifecycleOwner, {
			if (it != null) {
				last7DaysDate.text = it.last7Days?.from.toString()
			} else {
				last7DaysDate.text = "(2021.02.02 - 2021.02.02)"
				last7DaysBeer.text = "5.5 l"
				last7DaysWine.text = "0.75 l"
				last7DaysCocktails.text = "1.2 l"
				last7DaysShots.text = "0.32 l"
			}
		})

		val fab: FloatingActionButton = root.findViewById(R.id.fab_add_drink)
		fab.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show()
		}

		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
		// TODO: Use the ViewModel
	}
}