package hu.havasig.alcoholcalendar.ui.challenges.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.data.model.Challenge
import java.util.*


class ChallengeAdapter(
	private var listener: OnChallengeClickedListener,
	private val challengeList: ArrayList<Challenge>
) : RecyclerView.Adapter<ChallengeAdapter.ViewHolder>() {

	interface OnChallengeClickedListener {
		fun onChallengeSelected(challenge: Challenge)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v =
			LayoutInflater.from(parent.context).inflate(R.layout.item_challenge, parent, false)
		return ViewHolder(v)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindItems(challengeList[position])
	}

	override fun getItemCount(): Int {
		return challengeList.size
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bindItems(challenge: Challenge) {
			val nameTv: TextView = itemView.findViewById(R.id.nameTV)
			val descriptionTv: TextView = itemView.findViewById(R.id.descriptionTV)

			nameTv.text = challenge.name
			descriptionTv.text = challenge.description

			itemView.setOnClickListener { listener.onChallengeSelected(challenge) }
		}
	}
}