package hu.havasig.alcoholcalendar.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.ui.home.HomeFragment
import hu.havasig.alcoholcalendar.ui.home.HomeViewModel

class SlideshowFragment : Fragment() {

	companion object {
		fun newInstance() = SlideshowFragment()
	}

	private lateinit var slideshowViewModel: SlideshowViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		slideshowViewModel =
			ViewModelProvider(this).get(SlideshowViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
		val textView: TextView = root.findViewById(R.id.text_slideshow)
		slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
			textView.text = it
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
		// TODO: Use the ViewModel
	}
}