package com.smarthub.baseapplication.fragments.sitedetail.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.fragments.sitedetail.CustomeDetailsFragment
import com.smarthub.baseapplication.utils.Utils

class CustomerDataAdapter(var context: Context, var array: ArrayList<String>) :
    Adapter<CustomerDataViewHolder>() {
    var clicked_positon: Int = -1
    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDataViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return CustomerDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerDataViewHolder, position: Int) {

        if (clicked_positon == position) {
            Utils.expand(holder.binding.mainCollaps)
            Utils.addFragmenttab(
                CustomeDetailsFragment(),
                context as AppCompatActivity, holder.binding.placeholder.id
            )
            holder.binding.titel.setTextColor(Color.WHITE)
            holder.binding.customerId.setTextColor(Color.WHITE)
            holder.binding.date.setTextColor(Color.WHITE)
            holder.binding.datetext.setTextColor(Color.WHITE)
            holder.binding.menu.visibility = View.VISIBLE
            holder.binding.top.background =
                context.resources.getDrawable(R.drawable.customer_top_background_selected)

        } else {
            Utils.collapse(holder.binding.mainCollaps)
            holder.binding.titel.setTextColor(Color.BLACK)
            holder.binding.customerId.setTextColor(Color.BLACK)
            holder.binding.date.setTextColor(Color.BLACK)
            holder.binding.datetext.setTextColor(Color.BLACK)
            holder.binding.menu.visibility = View.GONE
            holder.binding.top.background =
                context.resources.getDrawable(R.drawable.customer_top_background)

        }

        holder.binding.top.setOnClickListener {
            if (clicked_positon != position) {
                clicked_positon = position
                notifyDataSetChanged()
            } else {
                Utils.collapse(holder.binding.mainCollaps)
                holder.binding.titel.setTextColor(Color.BLACK)
                holder.binding.customerId.setTextColor(Color.BLACK)
                holder.binding.date.setTextColor(Color.BLACK)
                holder.binding.datetext.setTextColor(Color.BLACK)
                holder.binding.menu.visibility = View.GONE
                holder.binding.top.background =
                    context.resources.getDrawable(R.drawable.customer_top_background)
                clicked_positon = -1
            }
        }

//var id_placeholder = View.generateViewId()
//        holder.binding.placeholder.id = id_placeholder

        holder.binding.rdogrp.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                holder.binding.btn1.id -> {
                    println("Button one is called")
                    Utils.addFragmenttab(
                        CustomeDetailsFragment(),
                        context as AppCompatActivity, holder.binding.placeholder.id
                    )
                }
                holder.binding.btn2.id -> {
                    println("Button two is called")
                    Utils.addFragmenttab(
                        CustomeDetailsFragment(),
                        context as AppCompatActivity, holder.binding.placeholder.id
                    )
                }
                holder.binding.btn3.id -> {
                    println("Button three is called")
                    Utils.addFragmenttab(
                        CustomeDetailsFragment(),
                        context as AppCompatActivity, holder.binding.placeholder.id
                    )
                }

            }

        }


    }

    override fun getItemCount(): Int {
        return array.size
    }
}

class CustomerDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}