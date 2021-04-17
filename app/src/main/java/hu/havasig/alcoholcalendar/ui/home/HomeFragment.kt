package hu.havasig.alcoholcalendar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.ui.gallery.GalleryFragment
import hu.havasig.alcoholcalendar.ui.gallery.GalleryViewModel

class HomeFragment : Fragment() {

	companion object {
		fun newInstance() = HomeFragment()
	}

	private lateinit var homeViewModel: HomeViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		homeViewModel =
			ViewModelProvider(this).get(HomeViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_home, container, false)
		val textView: TextView = root.findViewById(R.id.text_home)
		homeViewModel.text.observe(viewLifecycleOwner, Observer {
			textView.text = it
		})
		return root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
		// TODO: Use the ViewModel
	}
}