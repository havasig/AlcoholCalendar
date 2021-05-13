package hu.havasig.alcoholcalendar.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

	companion object {
		fun newInstance() = StatisticsFragment()
	}

	private lateinit var statisticsViewModel: StatisticsViewModel
	private val dateFormat = SimpleDateFormat("yyyy.MM.dd.", Locale.getDefault())

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		statisticsViewModel =
			ViewModelProvider(this).get(StatisticsViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_statistics, container, false)
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

		val crashButton: Button = root.findViewById(R.id.crashButton)

		crashButton.setOnClickListener { throw RuntimeException("Crash") }

		statisticsViewModel.statistics.observe(viewLifecycleOwner, {
			if (it != null) {
				last7DaysDate.text =
					"(${dateFormat.format(it.last7Days?.from)} - ${dateFormat.format(it.last7Days?.to)})"
				last7DaysBeer.text = "${it.last7Days?.beer} l"
				last7DaysWine.text = "${it.last7Days?.wine} l"
				last7DaysCocktails.text = "${it.last7Days?.cocktail} l"
				last7DaysShots.text = "${it.last7Days?.shots} l"

				lastWeekDate.text =
					"(${dateFormat.format(it.lastWeek?.from)} - ${dateFormat.format(it.last7Days?.to)})"
				lastWeekBeer.text = "${it.lastWeek?.beer} l"
				lastWeekWine.text = "${it.lastWeek?.wine} l"
				lastWeekCocktails.text = "${it.lastWeek?.cocktail} l"
				lastWeekShots.text = "${it.lastWeek?.shots} l"

				lastMonthDate.text =
					"(${dateFormat.format(it.lastMonth?.from)} - ${dateFormat.format(it.last7Days?.to)})"
				lastMonthBeer.text = "${it.lastMonth?.beer} l"
				lastMonthWine.text = "${it.lastMonth?.wine} l"
				lastMonthCocktails.text = "${it.lastMonth?.cocktail} l"
				lastMonthShots.text = "${it.lastMonth?.shots} l"

				lastYearDate.text =
					"(${dateFormat.format(it.lastYear?.from)} - ${dateFormat.format(it.last7Days?.to)})"
				lastYearBeer.text = "${it.lastYear?.beer} l"
				lastYearWine.text = "${it.lastYear?.wine} l"
				lastYearCocktails.text = "${it.lastYear?.cocktail} l"
				lastYearShots.text = "${it.lastYear?.shots} l"

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
		statisticsViewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
		// TODO: Use the ViewModel
	}
}