package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EquipmentRoomDialougeLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignEquipRoomEquipmentRoom
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns


class EquipmentRoomDialouge(var data: PlanningAndDesignEquipRoomEquipmentRoom?) : DialogFragment() {
lateinit var binding: EquipmentRoomDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = EquipmentRoomDialougeLayoutBinding.inflate(inflater)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.setLayout(width, height)
        dialog!!.window!!.setGravity(Gravity.BOTTOM)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setWindowAnimations(R.style.DialogAnimation)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.FullDialog
    }

    override fun isCancelable(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener{
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.canecelText.setOnClickListener{
            dialog!!.dismiss()
            dialog!!.cancel()
        }

        try {
            binding.editroomSizeL.setText(data?.SizeL)
            binding.editroomSizeW.setText(data?.SizeB)
            binding.editRoomSizeH.setText(data?.SizeH)
            binding.editFoundationSizeL.setText(data?.FoundationSizeL)
            binding.editFoundationSizeW.setText(data?.FoundationSizeB)
            binding.editFoundationSizeH.setText(data?.FoundationSizeH)
            binding.remark.setText(data?.Remark)

            AppPreferences.getInstance().setDropDown(binding.foundationType,
                DropDowns.FoundationType.name,data?.FoundationType.toString())
            AppPreferences.getInstance().setDropDown(binding.type,
                DropDowns.EquipmentType.name,data?.Type.toString())

        }catch (e:java.lang.Exception){
            AppLogger.log("PlanDesign Equip Room Adapter error : ${e.localizedMessage}")
            Toast.makeText(context,"PlanDesign Equip Room Adapter error :${e.localizedMessage}",
                Toast.LENGTH_LONG).show()

        }

    }


}