package com.smarthub.baseapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R

class RecyclerAdapter_faq : RecyclerView.Adapter<RecyclerAdapter_faq.ViewHolder>() {
    private val questions = arrayOf(
        "1. This is first question",
        "2. This is second question",
        "3. This is third question",
        "4. This is fourth question",
        "5. This is fifth question"
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var question1: TextView
        var answer: TextView
        var card: com.google.android.material.card.MaterialCardView?=null

        init{
            question1= itemView.findViewById(R.id.question1)
            answer=itemView.findViewById(R.id.answer)
            card=itemView.findViewById(R.id.base_cardview)

            question1.tag = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleritem_faq, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.question1.text=questions[position]

        holder.question1.setOnClickListener{
            var isExpanded = !(holder.question1.tag as Boolean)
            holder.question1.tag = isExpanded
            if (isExpanded){
                holder.answer.visibility = View.VISIBLE

//                change radius 10dp

            }else{
                holder.answer.visibility = View.GONE
//                change radius 100dp

            }
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}