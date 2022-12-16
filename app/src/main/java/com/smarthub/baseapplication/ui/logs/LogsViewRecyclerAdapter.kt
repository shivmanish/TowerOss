package com.smarthub.baseapplication.ui.logs

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R

class LogsViewRecyclerAdapter : RecyclerView.Adapter<LogsViewRecyclerAdapter.ViewHolder>(){
    private val arr = arrayOf(
       "Grand Manualty1",
        "Grand Manualty2",
        "Grand Manualty3",
        "Grand Manualty4",
        "Grand Manualty5",
        "Grand Manualty6",
        "Grand Manualty7",
        "Grand Manualty8",
        "Grand Manualty9"
    )
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView
        val titleLayout: LinearLayout
        val sideImage: ImageView
        init{
            textViewTitle = view.findViewById(R.id.log_item_title_text)
            titleLayout = view.findViewById(R.id.log_item_title_layout)
            sideImage = view.findViewById(R.id.log_item_side_image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.logs_card_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.textViewTitle.text= arr[position]
            if(position%3==0)
            {
                holder.titleLayout.setBackgroundColor(Color.parseColor("#FFF3CD"))
                holder.sideImage.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_3)
                holder.sideImage.setImageResource(R.drawable.vector__4_);

            }
            if(position%3==1)
            {
                holder.titleLayout.setBackgroundColor(Color.parseColor("#D4EDDA"))
                holder.sideImage.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_4)
                holder.sideImage.setImageResource(R.drawable.vector__5_);
            }
            if(position%3==2)
            {
                holder.titleLayout.setBackgroundColor(Color.parseColor("#FFDEDE"))
                holder.sideImage.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_5);
                holder.sideImage.setImageResource(R.drawable.vector__6_);
            }

    }

    override fun getItemCount(): Int {
       return arr.size
    }

}