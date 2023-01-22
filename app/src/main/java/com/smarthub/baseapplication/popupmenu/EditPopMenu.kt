package com.smarthub.baseapplication.popupmenu

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.smarthub.baseapplication.R

class EditPopMenu {
    var pop:PopupMenu? = null

    fun EditPopMenu(context: Context){
        pop?.dismiss()
    }
    fun EditPopMenu(context: Context, view: View) {
        pop = PopupMenu(context, view)

        pop?.inflate(R.menu.edit_popup_menu)

        pop?.setOnMenuItemClickListener {
            when (it!!.itemId) {
                R.id.id_edit -> {
//                    Toast.makeText(context, "item Edite", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.id_lifecycle -> {
//                    Toast.makeText(context, "item lifecycle", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.id_escalation -> {
//                    Toast.makeText(context, "item escalation", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.id_viewonmap -> {
//                    Toast.makeText(context, "item viewonmap", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false

            }
        }

        try {

            val fieldMpopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMpopup.isAccessible= true
            val mPopup = fieldMpopup.get(pop)
            mPopup.javaClass
                .getDeclaredMethod("setFoeceShowIcon",Boolean::class.java)
                .invoke(mPopup,true)

        }catch (e:Exception){

        }finally {
            pop?.show()
        }

    }

}