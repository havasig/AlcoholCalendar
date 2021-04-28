package hu.havasig.alcoholcalendar.ui.calendarView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hu.havasig.alcoholcalendar.R

@AndroidEntryPoint
class CalendarViewFragment : Fragment() {

	companion object {
		fun newInstance() = CalendarViewFragment()
	}

	private lateinit var calendarViewViewModel: CalendarViewViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		calendarViewViewModel =
			ViewModelProvider(this).get(CalendarViewViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_calendar_view, container, false)
		val textView: TextView = root.findViewById(R.id.text_calendar_view)
		calendarViewViewModel.text.observe(viewLifecycleOwner, Observer {
			textView.text = it
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		calendarViewViewModel = ViewModelProvider(this).get(CalendarViewViewModel::class.java)
		// TODO: Use the ViewModel
	}
}