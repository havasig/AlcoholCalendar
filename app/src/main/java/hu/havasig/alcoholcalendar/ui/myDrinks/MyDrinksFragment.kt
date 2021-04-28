package hu.havasig.alcoholcalendar.ui.myDrinks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R

@AndroidEntryPoint
class MyDrinksFragment : Fragment() {

	companion object {
		fun newInstance() = MyDrinksFragment()
	}

	private lateinit var myDrinksViewModel: MyDrinksViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		myDrinksViewModel =
			ViewModelProvider(this).get(MyDrinksViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_my_drinks, container, false)
		val textView: TextView = root.findViewById(R.id.text_my_drinks)
		myDrinksViewModel.getMyDrinks()
		myDrinksViewModel.drinks.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				it.forEach { drink -> textView.text = "${textView.text} ${drink.name}" }
				textView.text = it[0].name
			} else
				textView.text = "You have no drink"
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		myDrinksViewModel = ViewModelProvider(this).get(MyDrinksViewModel::class.java)
		// TODO: Use the ViewModel
	}
}