package com.smarthub.baseapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.qat.AtpAddImageListAdapter
import com.smarthub.baseapplication.databinding.FragmentGABinding
import com.smarthub.baseapplication.databinding.SpinnerTextBinding
import com.smarthub.baseapplication.listeners.AddImageListener
import com.smarthub.baseapplication.ui.gat.GAViewModel


class GADynamicAdapter(var list: ArrayList<Any>,var listener: AddImageListener) :
    RecyclerView.Adapter<GADynamicAdapter.ViewHold>() {
    private lateinit var gaViewModel: GAViewModel

    open class ViewHold(var view: View) : RecyclerView.ViewHolder(view)

    class SpinerViewHold(view: View) : ViewHold(view) {
        var spinerBinding = SpinnerTextBinding.bind(view)
    }

    class TextFieldViewHold(view: View) : ViewHold(view) {
        //    var dynamicTextfieldLayoutBinding = DynamicTextfieldLayoutBinding.bind(view)
    }

    class MainScreenHold(view: View, var listener: AddImageListener) : ViewHold(view) {
        var fragmentGABinding = FragmentGABinding.bind(view)
        lateinit var list : ArrayList<String>
        var Adapter= AtpAddImageListAdapter(listener)
      var addimge=view.findViewById<RecyclerView>(R.id.rvImageAttachment)

        init{


            /*var list : ArrayList<Any>, var listener: AddImageListener*/
           addimge.adapter=Adapter
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && (list[position] as String) == "spiner_item")
            return 1
        if (list[position] is String && (list[position] as String) == "text_filed_items")
            return 2
        if (list[position] is String && (list[position] as String) == "main_layout")
            return 3

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {

        return when (viewType) {
            1 -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dynamic_spiner, parent, false)
                SpinerViewHold(view)
            }
            2 -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dynamic_textfield_layout, parent, false)
                TextFieldViewHold(view)
            }
            3 -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_ga_list_layout, parent, false)
                MainScreenHold(view, listener)
            }
            else -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pro_single_item_view, parent, false)
                return ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is SpinerViewHold) {
            Log.d("status", "SpinerViewHold")
        } else if (holder is TextFieldViewHold) {
            Log.d("status", "TextFieldViewHold")
        }
        else if (holder is MainScreenHold) {

            Log.d("status", "MainScreenHold")
            Log.d(
                "status",
                holder.view.findViewById<RecyclerView>(R.id.rvImageAttachment).toString()

            )
            holder.Adapter.UpdateList(ArrayList())
          //  val addimge=holder.view.findViewById<RecyclerView>(R.id.rvImageAttachment).toString()
   /*         holder.addimge.setOnClickListener {
               // Toast.makeText(holder.itemView.context,"Clicked!",Toast.LENGTH_LONG).show()
                val dialog = BottomSheetDialog(holder.itemView.context)
                val view= LayoutInflater.from(holder.itemView.context).inflate(R.layout.upload_pic_bottom_sheet,null)
                val layoutCamera= view.findViewById<LinearLayout>(R.id.layoutCamera);
                val layoutCamera2= view.findViewById<LinearLayout>(R.id.layoutCamera2);



               dialog.show()
            }
*/
        } else {
            Log.d("status", "default")
        }
    }

    override fun getItemCount(): Int {
        return 5
    }
}

