package hu.havasig.alcoholcalendar.ui.addDrink

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
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
	private var rootlayout: View? = null
	private lateinit var firstRadioGroup: RadioGroup
	private lateinit var secondRadioGroup: RadioGroup
	private lateinit var saveDrinkTypeBtn: Button
	private lateinit var saveDrinkBtn: Button
	private lateinit var nameEt: EditText
	private lateinit var percentageEt: EditText
	private lateinit var amountEt: EditText
	private lateinit var datePicker: DatePicker
	private var setType = false
	private var currentDrinkType: DrinkType? = null

	private val textWatcher = object : TextWatcher {
		override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
		override fun afterTextChanged(p0: Editable?) {}
		override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
			if (!setType) selectCustomDrinkType()
		}
	}

	private fun selectCustomDrinkType() {
		val count: Int = firstRadioGroup.childCount
		for (i in 0 until count) {
			val o: View = firstRadioGroup.getChildAt(i)
			if (o is RadioButton && o.text == resources.getString(R.string.custom)) {
				o.isChecked = true
			}
		}
		val count2: Int = secondRadioGroup.childCount
		for (i in 0 until count2) {
			val o: View = secondRadioGroup.getChildAt(i)
			if (o is RadioButton && o.text == resources.getString(R.string.custom)) {
				o.isChecked = true
			}
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		addDrinkViewModel =
			ViewModelProvider(this).get(AddDrinkViewModel::class.java)
		rootlayout = inflater.inflate(R.layout.fragment_add_drink, container, false)
		firstRadioGroup = rootlayout!!.findViewById(R.id.firstRadioGroup)
		secondRadioGroup = rootlayout!!.findViewById(R.id.secondRadioGroup)
		saveDrinkTypeBtn = rootlayout!!.findViewById(R.id.saveDrinkTypeBtn)
		saveDrinkBtn = rootlayout!!.findViewById(R.id.saveDrinkBtn)

		nameEt = rootlayout!!.findViewById(R.id.drinkNameEditText)
		percentageEt = rootlayout!!.findViewById(R.id.drinkPercentageEditText)
		amountEt = rootlayout!!.findViewById(R.id.drinkAmountEditText)
		datePicker = rootlayout!!.findViewById(R.id.datePicker)

		firstRadioGroup.clearCheck()
		secondRadioGroup.clearCheck()
		firstRadioGroup.setOnCheckedChangeListener(listener1)
		secondRadioGroup.setOnCheckedChangeListener(listener2)

		nameEt.addTextChangedListener(textWatcher)
		percentageEt.addTextChangedListener(textWatcher)
		amountEt.addTextChangedListener(textWatcher)

		val calendar = Calendar.getInstance()
		datePicker.updateDate(
			calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(
				Calendar.DAY_OF_MONTH
			)
		)

		saveDrinkTypeBtn.setOnClickListener { saveDrinkType() }
		saveDrinkBtn.setOnClickListener { saveDrink() }

		return rootlayout
	}

	private val listener1: RadioGroup.OnCheckedChangeListener =
		RadioGroup.OnCheckedChangeListener { _, checkedId ->
			if (checkedId != -1) {
				secondRadioGroup.setOnCheckedChangeListener(null)
				secondRadioGroup.clearCheck()
				secondRadioGroup.setOnCheckedChangeListener(listener2)
				onChangeType()
			}
		}

	private val listener2: RadioGroup.OnCheckedChangeListener =
		RadioGroup.OnCheckedChangeListener { _, checkedId ->
			if (checkedId != -1) {
				firstRadioGroup.setOnCheckedChangeListener(null)
				firstRadioGroup.clearCheck()
				firstRadioGroup.setOnCheckedChangeListener(listener1)
				onChangeType()
			}
		}

	private fun onChangeType() {
		val chkId1: Int = firstRadioGroup.checkedRadioButtonId
		val chkId2: Int = secondRadioGroup.checkedRadioButtonId
		val realCheck = if (chkId1 == -1) chkId2 else chkId1
		val pickedBtn = rootlayout!!.findViewById(realCheck) as RadioButton

		saveDrinkTypeBtn.isEnabled = pickedBtn.text == resources.getString(R.string.custom)

		addDrinkViewModel.drinkTypes.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				it.find { drinkType -> drinkType.name == pickedBtn.text }?.let { chosenType ->
					setType = true
					currentDrinkType = chosenType
					nameEt.setText(chosenType.name, TextView.BufferType.EDITABLE)
					percentageEt.setText(
						chosenType.percentage.toString(),
						TextView.BufferType.EDITABLE
					)
					amountEt.setText(chosenType.amount.toString(), TextView.BufferType.EDITABLE)
					setType = false
				}
			}
		})
	}

	private fun addRadioButtons(drinkTypes: List<DrinkType>) {
		firstRadioGroup.removeAllViews()
		secondRadioGroup.removeAllViews()
		var even = true
		drinkTypes.forEach { drinkType ->
			val newBtn = RadioButton(context)
			newBtn.id = View.generateViewId()
			newBtn.text = drinkType.name
			if (even) {
				firstRadioGroup.addView(newBtn)
			} else {
				secondRadioGroup.addView(newBtn)
			}
			even = !even
		}
		val newBtn = RadioButton(context)
		newBtn.id = View.generateViewId()
		newBtn.text = resources.getString(R.string.custom)
		if (even) {
			firstRadioGroup.addView(newBtn)
		} else {
			secondRadioGroup.addView(newBtn)
		}
		newBtn.isChecked = true
	}

	private fun saveDrink() {
		if (nameEt.text.toString() == "") {
			showSnackbar("Name must not be empty")
			return
		}
		try {
			val percentage = getPercentage()
			val amount = getAmount()
			val date = getDate()
			addDrinkViewModel.createDrink(
				Drink(
					null,
					null,
					nameEt.text.toString(),
					percentage,
					amount,
					date,
					currentDrinkType,
					Calendar.getInstance().time
				)
			)
			showSnackbar("Drink saved")
		} catch (ex: NumberFormatException) {
			showSnackbar("Percentage and amount must be number")
			return
		} catch (ex: Exception) {
			showSnackbar(ex.message!!)
			return
		}
	}

	private fun saveDrinkType() {
		if (nameEt.text.toString() == "") {
			showSnackbar("Name must not be empty")
			return
		}
		try {
			val percentage = getPercentage()
			val amount = getAmount()
			if (amount == null) {
				showSnackbar("Amount cannot be empty")
				return
			}
			if (percentage == null) {
				showSnackbar("Percentage cannot be empty")
				return
			}
			addDrinkViewModel.createDrinkType(
				DrinkType(
					null,
					null,
					nameEt.text.toString(),
					percentage,
					amount
				)
			)
			nameEt.setText("", TextView.BufferType.EDITABLE)
			amountEt.setText("", TextView.BufferType.EDITABLE)
			percentageEt.setText("", TextView.BufferType.EDITABLE)
			showSnackbar("New type saved")
			loadDrinkTypes()
		} catch (ex: NumberFormatException) {
			showSnackbar("Percentage and amount must be number")
			return
		} catch (ex: Exception) {
			showSnackbar(ex.message!!)
			return
		}
	}

	private fun loadDrinkTypes() {
		addDrinkViewModel.getDrinkTypes()
		//addDrinkViewModel.updateMyDrinkTypes()
		addDrinkViewModel.drinkTypes.observe(viewLifecycleOwner, {
			if (it.isNotEmpty()) {
				addRadioButtons(it)
			} else {
				Log.e("MY_LOG", "empty drink types")
			}
		})
	}

	private fun showSnackbar(text: String) {
		Snackbar.make(rootlayout!!, text, Snackbar.LENGTH_SHORT).show()
	}

	override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
		super.onViewCreated(itemView, savedInstanceState)

		loadDrinkTypes()
	}

	@Throws(Exception::class)
	private fun getPercentage(): Double? {
		if (percentageEt.text.toString() != "") {
			val percentage = percentageEt.text.toString().toDouble()
			if (percentage <= 0 || percentage > 100) {
				throw Exception("Percentage must be between 0 and 100")
			}
			return percentage
		}
		return null
	}

	@Throws(Exception::class)
	private fun getAmount(): Double? {
		if (amountEt.text.toString() != "") {
			val amount = amountEt.text.toString().toDouble()
			if (amount <= 0) {
				throw Exception("Amount must be bigger than zero")
			}
			return amount
		}
		return null
	}

	private fun getDate(): Date {
		val cal = Calendar.getInstance()
		cal[Calendar.YEAR] = datePicker.year
		cal[Calendar.MONTH] = datePicker.month
		cal[Calendar.DAY_OF_MONTH] = datePicker.dayOfMonth
		return cal.time
	}


	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		addDrinkViewModel = ViewModelProvider(this).get(AddDrinkViewModel::class.java)
		// TODO: Use the ViewModel
	}
}