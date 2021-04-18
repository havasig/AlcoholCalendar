package hu.havasig.alcoholcalendar.ui.addDrink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.havasig.alcoholcalendar.R

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
		val textView: TextView = root.findViewById(R.id.text_add_drink)
		addDrinkViewModel.text.observe(viewLifecycleOwner, Observer {
			textView.text = it
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		addDrinkViewModel = ViewModelProvider(this).get(AddDrinkViewModel::class.java)
		// TODO: Use the ViewModel
	}
}