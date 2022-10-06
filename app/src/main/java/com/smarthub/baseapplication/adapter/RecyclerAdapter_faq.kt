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
        var question: TextView
        var question1: TextView
        var answer: TextView
        var invisiblegroup: Group
        init{
            question= itemView.findViewById(R.id.question)
            question1= itemView.findViewById(R.id.question1)
            answer=itemView.findViewById(R.id.answer)
            invisiblegroup=itemView.findViewById(R.id.group_collapse)

            question1.setOnClickListener{
                invisiblegroup.visibility=View.VISIBLE
                question1.visibility=View.GONE
            }
            question.setOnClickListener{
                invisiblegroup.visibility=View.GONE
                question1.visibility=View.VISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleritem_faq, parent, false)
        val group = v.findViewById<Group>(R.id.group_collapse)
        group.visibility=View.GONE
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.question.text=questions[position]
        holder.question1.text=questions[position]

    }

    override fun getItemCount(): Int {
        return questions.size
    }
}