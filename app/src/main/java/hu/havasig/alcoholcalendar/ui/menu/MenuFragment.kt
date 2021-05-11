package hu.havasig.alcoholcalendar.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

		val lastWeekDate: TextView = root.findViewById(R.id.lastWeekDate)
		val lastWeekBeer: TextView = root.findViewById(R.id.lastWeekBeerValue)
		val lastWeekWine: TextView = root.findViewById(R.id.lastWeekWineValue)
		val lastWeekCocktails: TextView = root.findViewById(R.id.lastWeekCocktailsValue)
		val lastWeekShots: TextView = root.findViewById(R.id.lastWeekShotsValue)

		val lastMonthDate: TextView = root.findViewById(R.id.lastMonthDate)
		val lastMonthBeer: TextView = root.findViewById(R.id.lastMonthBeerValue)
		val lastMonthWine: TextView = root.findViewById(R.id.lastMonthWineValue)
		val lastMonthCocktails: TextView = root.findViewById(R.id.lastMonthCocktailsValue)
		val lastMonthShots: TextView = root.findViewById(R.id.lastMonthShotsValue)

		val lastYearDate: TextView = root.findViewById(R.id.lastYearDate)
		val lastYearBeer: TextView = root.findViewById(R.id.lastYearBeerValue)
		val lastYearWine: TextView = root.findViewById(R.id.lastYearWineValue)
		val lastYearCocktails: TextView = root.findViewById(R.id.lastYearCocktailsValue)
		val lastYearShots: TextView = root.findViewById(R.id.lastYearShotsValue)

		val statisticsSV: ScrollView = root.findViewById(R.id.statisticsSV)
		val noStatisticsLL: LinearLayout = root.findViewById(R.id.noStatisticsLL)

		menuViewModel.statistics.observe(viewLifecycleOwner, {
			if (it != null) {
				last7DaysDate.text = "(${it.last7Days?.from.toString()} - ${it.last7Days?.from.toString()})"
				last7DaysBeer.text = "${it.last7Days?.beer.toString()} l"
				last7DaysWine.text = "${it.last7Days?.wine.toString()} l"
				last7DaysCocktails.text = "${it.last7Days?.cocktail.toString()} l"
				last7DaysShots.text = "${it.last7Days?.shots.toString()} l"

				lastWeekDate.text = "(${it.lastWeek?.from.toString()} - ${it.last7Days?.from.toString()})"
				lastWeekBeer.text = "${it.lastWeek?.beer.toString()} l"
				lastWeekWine.text = "${it.lastWeek?.wine.toString()} l"
				lastWeekCocktails.text = "${it.lastWeek?.cocktail.toString()} l"
				lastWeekShots.text = "${it.lastWeek?.shots.toString()} l"

				lastMonthDate.text = "(${it.lastMonth?.from.toString()} - ${it.last7Days?.from.toString()})"
				lastMonthBeer.text = "${it.lastMonth?.beer.toString()} l"
				lastMonthWine.text = "${it.lastMonth?.wine.toString()} l"
				lastMonthCocktails.text = "${it.lastMonth?.cocktail.toString()} l"
				lastMonthShots.text = "${it.lastMonth?.shots.toString()} l"

				lastYearDate.text = "(${it.lastYear?.from.toString()} - ${it.last7Days?.from.toString()})"
				lastYearBeer.text = "${it.lastYear?.beer.toString()} l"
				lastYearWine.text = "${it.lastYear?.wine.toString()} l"
				lastYearCocktails.text = "${it.lastYear?.cocktail.toString()} l"
				lastYearShots.text = "${it.lastYear?.shots.toString()} l"

				noStatisticsLL.visibility = View.GONE
				statisticsSV.visibility = View.VISIBLE

			} else {
				noStatisticsLL.visibility = View.VISIBLE
				statisticsSV.visibility = View.GONE
			}
		})

		val fab: FloatingActionButton = root.findViewById(R.id.fab_add_drink)
		fab.setOnClickListener { root.findNavController().navigate(R.id.nav_add_drink) }

		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
		// TODO: Use the ViewModel
	}
}