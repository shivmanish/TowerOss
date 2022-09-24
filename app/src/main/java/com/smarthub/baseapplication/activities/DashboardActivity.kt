package com.smarthub.baseapplication.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R

class DashboardActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var datasource:ArrayList<String>
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var rv1Adapter: DashboardActivityAdapter1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        rv = findViewById<RecyclerView>(R.id.DashboardRecyclerView1)


    }
    class DashboardActivityAdapter1  constructor(val context: Context, val datalist: ArrayList<String> =arrayListOf<String>(), private var data:ArrayList<String>): RecyclerView.Adapter<DashboardActivityAdapter1.DashboardActivityHolder1>() {

        constructor(context: Context, datalist:ArrayList<String>) : this(context, data = datalist)
        class DashboardActivityHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {



        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DashboardActivityHolder1 {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rv1_item,parent,false) as View
            return DashboardActivityHolder1(view);
        }

        override fun onBindViewHolder(holder: DashboardActivityHolder1, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
           return data.size
        }
    }
}


