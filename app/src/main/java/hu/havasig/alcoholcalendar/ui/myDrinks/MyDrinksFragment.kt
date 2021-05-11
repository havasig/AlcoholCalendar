package hu.havasig.alcoholcalendar.ui.myDrinks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.data.model.Drink
import hu.havasig.alcoholcalendar.ui.addDrink.AddDrinkFragment


@AndroidEntryPoint
class MyDrinksFragment :
	Fragment(),
	MyDrinksAdapter.OnDrinkClickedListener  {

	companion object {
		fun newInstance() = MyDrinksFragment()
	}

	private lateinit var myDrinksViewModel: MyDrinksViewModel
	private lateinit var myDrinksRecyclerView: RecyclerView
	private lateinit var noDrinkLL: LinearLayout
	private lateinit var myDrinksAdapter: MyDrinksAdapter
	private val myDrinks: ArrayList<Drink> = ArrayList()
	private var rootlayout: View? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		myDrinksViewModel =
			ViewModelProvider(this).get(MyDrinksViewModel::class.java)
		rootlayout = inflater.inflate(R.layout.fragment_my_drinks, container, false)
		myDrinksRecyclerView = rootlayout!!.findViewById(R.id.myDrinksRV)
		noDrinkLL = rootlayout!!.findViewById(R.id.noDrinkLL)
		myDrinksAdapter = MyDrinksAdapter(this@MyDrinksFragment, myDrinks)
		return rootlayout
	}

	override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
		super.onViewCreated(itemView, savedInstanceState)
		myDrinksRecyclerView.apply {
			layoutManager = LinearLayoutManager(activity)
			adapter = myDrinksAdapter
		}

		myDrinksViewModel.updateDrinks()
		myDrinksViewModel.drinks.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				myDrinks.clear()
				myDrinks.addAll(it)
				myDrinksAdapter.notifyDataSetChanged()
			} else {
				myDrinks.clear()
				myDrinksAdapter.notifyDataSetChanged()
				noDrinkLL.visibility = View.VISIBLE
			}
		})
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		myDrinksViewModel = ViewModelProvider(this).get(MyDrinksViewModel::class.java)
		// TODO: Use the ViewModel
	}

	override fun onDrinkSelected(drink: Drink) {
		Navigation.findNavController(rootlayout!!).navigate(R.id.nav_add_drink)

		val action = AddDrinkFragmentDirections.confirmationAction(drink.id)
		rootlayout!!.findNavController().navigate(action)
		Navigation.findNavController(rootlayout!!).navigate(R.id.nav_add_drink)
	}

	override fun onDrinkDelete(viewHolder: MyDrinksAdapter.ViewHolder, drink: Drink) {
		myDrinksViewModel.deleteDrink(drink)
		val viewHolderPosition = myDrinksAdapter.onDrinkRemove(viewHolder)
		Snackbar.make(rootlayout!!, "onDrinkDelete", Snackbar.LENGTH_LONG)
			.setAction(R.string.undo_string) {
				myDrinksAdapter.onDrinkRemoveUndo(viewHolderPosition)
				myDrinksViewModel.restoreDrink(drink.id!!)
			}
			.show()
	}
}