package hu.havasig.alcoholcalendar.ui.addDrink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.data.model.DrinkType
import java.util.*

@AndroidEntryPoint
class AddDrinkFragment : Fragment() {

	companion object {
		fun newInstance() = AddDrinkFragment()
	}

	private lateinit var addDrinkViewModel: AddDrinkViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		addDrinkViewModel =
			ViewModelProvider(this).get(AddDrinkViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_add_drink, container, false)
		val textView: TextView = root.findViewById(R.id.liter)
		val drinkType = DrinkType(1, "beer", 4, 4.6, false)
		val drinkType2 = DrinkType(2, "jagerbomb", 2, 4.1, false)
		addDrinkViewModel.createDrink(
			Drink(
				1,
				null,
				"beer",
				30,
				2.0,
				null,
				drinkType,
				Calendar.getInstance().time
			)
		)
		addDrinkViewModel.createDrink(
			Drink(
				2,
				null,
				"Jagerbomba",
				30,
				2.0,
				null,
				drinkType2,
				Calendar.getInstance().time
			)
		)
		addDrinkViewModel.updateMyDrinkTypes()
		addDrinkViewModel.drinkTypes.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				textView.text = it[0].name
			} else
				textView.text = "Liter"
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		addDrinkViewModel = ViewModelProvider(this).get(AddDrinkViewModel::class.java)
		// TODO: Use the ViewModel
	}
}