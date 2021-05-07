package hu.havasig.alcoholcalendar.ui.myDrinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import hu.havasig.alcoholcalendar.R
import hu.havasig.alcoholcalendar.data.model.Drink


class MyDrinksAdapter(
	private var listener: OnDrinkClickedListener,
	private val myDrinkList: ArrayList<Drink>
) :
	RecyclerView.Adapter<MyDrinksAdapter.ViewHolder>() {

	interface OnDrinkClickedListener {
		fun onDrinkEdit(drink: Drink)
		fun onDrinkDelete(viewHolder: ViewHolder, drink: Drink)
	}

	interface DeleteUndoListener : View.OnClickListener {

	}


	//this method is returning the view for each item in the list
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val v =
			LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
		return ViewHolder(v)
	}

	//this method is binding the data on the list
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindItems(myDrinkList[position])
	}

	//this method is giving the size of the list
	override fun getItemCount(): Int {
		return myDrinkList.size
	}

	private var lastDeletedDrink: Drink? = null

	fun onDrinkRemove(viewHolder: RecyclerView.ViewHolder): Int {
		val adapterPosition = viewHolder.layoutPosition
		val mDrink = myDrinkList[adapterPosition]
		myDrinkList.removeAt(adapterPosition)
		notifyItemRemoved(adapterPosition)
		lastDeletedDrink = mDrink
		return adapterPosition
	}

	fun onDrinkRemoveUndo(adapterPosition: Int) {
		myDrinkList.add(adapterPosition, lastDeletedDrink!!)
		notifyItemRemoved(adapterPosition)
		lastDeletedDrink = null
	}

	//the class is hodling the list view
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bindItems(myDrink: Drink) {
			val nameTv: TextView = itemView.findViewById(R.id.drinkNameTV)
			val percentageTv: TextView = itemView.findViewById(R.id.drinkPercentageTV)
			val amountTv: TextView = itemView.findViewById(R.id.drinkAmountTV)
			val dateTv: TextView = itemView.findViewById(R.id.drinkDateTV)
			val imageIv: ImageView = itemView.findViewById(R.id.drinkIv)
			val editBtn: ImageButton = itemView.findViewById(R.id.editDrinkBtn)
			val deleteBtn: ImageButton = itemView.findViewById(R.id.deleteDrinkBtn)

			nameTv.text = myDrink.name
			percentageTv.text =
				if (myDrink.percentage != null) "(${myDrink.percentage.toString()}%)" else ""
			amountTv.text = if (myDrink.amount != null) "${myDrink.amount.toString()} Liter" else ""
			dateTv.text = myDrink.date?.toString() ?: ""
			when (myDrink.type.name) {
				"beer" -> imageIv.setImageResource(R.drawable.ic_beer)
				"wine" -> imageIv.setImageResource(R.drawable.ic_wine)
				"cocktail" -> imageIv.setImageResource(R.drawable.ic_cocktail)
				"shot" -> imageIv.setImageResource(R.drawable.ic_shot)
			}
			editBtn.setOnClickListener { listener.onDrinkEdit(myDrink) }
			deleteBtn.setOnClickListener {
				listener.onDrinkDelete(this, myDrink)
			}
		}
	}
}