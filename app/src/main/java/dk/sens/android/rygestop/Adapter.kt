package dk.sens.android.rygestop


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class Adapter(val textList: ArrayList<Text>, val clickListener: (Text) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.txtName?.text = textList[position].title
        holder?.txtTitle?.text = textList[position].des

        (holder as ViewHolder).bind(textList[position], clickListener)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return textList.size
    }





    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)

        fun bind(text: Text, clickListener: (Text) -> Unit) {
            itemView.txtName.text = text.title
            itemView.txtTitle.text = text.des
            itemView.setOnClickListener { clickListener(text)}
        }



    }

}