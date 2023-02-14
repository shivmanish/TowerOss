package com.smarthub.baseapplication.ui.fragments.plandesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.*
import com.smarthub.baseapplication.ui.fragments.plandesign.tableAdapters.SmpsTableAdapter
import com.smarthub.baseapplication.utils.AppLogger

class UtilityEquipAdapter(
    var context: Context,
    var listener: ItemClicListiner,
    allData: List<UtilityEquip>?
) : RecyclerView.Adapter<UtilityEquipAdapter.ViewHold>() {
    var currentOpened = -1
    private var data: UtilityEquip? = null
    var list: ArrayList<String> = ArrayList()
    var type1 = "SMPS"
    var type2 = "Battery Bank"
    var type3 = "DG"
    var type4 = "AC"
    var type5 = "Fire Extinguisher "
    var type6 = "Surge Protection Device"
    var type7 = "DCDB"
    var type8 = "Attachments"

    init {
        list.add(type1)
        list.add(type2)
        list.add(type3)
        list.add(type4)
        list.add(type5)
        list.add(type6)
        list.add(type7)
//        list.add(type8)
        try {
            data = allData?.get(0)
        } catch (e: java.lang.Exception) {
            AppLogger.log("PlanDesign Utility Equip Adapter error :${e.localizedMessage}")
//            Toast.makeText(
//                context,
//                "PlanDesign Utility Equip Adapter error :${e.localizedMessage}",
//                Toast.LENGTH_LONG
//            ).show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position].equals( type1))
            return 1
        else if ( list[position].equals(  type2,true))
            return 2
        else if ( list[position] .equals(  type3,true))
            return 3
        else if (list[position] .equals(  type4,true))
            return 4
        else if (list[position] .equals(  type5,true))
            return 5
        else if (list[position] .equals(  type6,true))
            return 6
        else if (list[position] .equals(  type7,true))
            return 7
        else if (list[position] .equals(  type8,true))
            return 8
        return 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.smps_view, parent, false)
                return SmpsViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.battery_bank_view, parent, false)
                return BatteryBankViewHolder(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dg_view, parent, false)
                return DgViewHolder(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ac_view, parent, false)
                return AcViewHolder(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fire_exit_view, parent, false)
                return FireExiViewHolder(view)
            }
            6 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.spd_view, parent, false)
                return SpdViewHolder(view)
            }
            7 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dcdb_view, parent, false)
                return DcdbViewHolder(view)
            }


        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        println("this is called")
        when (holder) {
            is SmpsViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.smpsclicked(data!!.SMPS.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseSmps.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseSmps.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseSmps.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.SMPS.get(0).let {
                        holder.binding.make.text = it.Make
                        holder.binding.modal.text = it.Model
                        holder.binding.RatingCapacityKw.text = it.RatingAndCapacity
                        holder.binding.cabinetsizel.text = it.CabinetSizeL
                        holder.binding.cabinetsizeh.text = it.CabinetSizeH
                        holder.binding.cabinetsizel.text = it.CabinetSizeL
                        holder.binding.overallWeightKg.text = it.OverallWeight
                        holder.binding.ownerCompany.text = ""
                        holder.binding.userCompany.text = ""

                        holder.binding.smpsPlanLeadTable.adapter =
                            SmpsTableAdapter(context, data!!.SMPS.get(0).PlannedLoad,object : TableCallback {
                                override fun editItem(obj: Any?) {
//                            val dalouge= SmpsPlannedTableEditDialouge()
//                            dalouge.show(childFragmentManager, "")
                                }

                                override fun viewItem(obj: Any?) {
//                            val dalouge= SmpsPlannedTableEditDialouge ()
//                            dalouge.show(childFragmentManager, "")
                                }
                            })
                    }
                }catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }



            }
            is BatteryBankViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.batterybankclicked(data!!.BatteryBank.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseBBank.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseBBank.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseBBank.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.BatteryBank.get(0).let {
                        holder.binding.batteryBankMake.text = it.Make
                        holder.binding.batteryBankModal.text = it.Model
                        holder.binding.batteryBankRatingCapacityKw.text = it.RatingAndCapacity
                        holder.binding.batteryBankCabinetsizel.text = it.CabinetSizeL
                        holder.binding.batteryBankCabinetsizeh.text = it.CabinetSizeH
                        holder.binding.batteryBankCabinetsizeb.text = it.CabinetSizeB
                        holder.binding.batteryBankOverallWeightKg.text = it.OverallWeight
                        holder.binding.batteryBankUserCompany.text = ""
                        holder.binding.batteryBankOwnerCompany.text = ""
                    }
/*
                    holder.binding.bBankTable.adapter =SmpsTableAdapter(
                        context,
                        data!!.SMPS.get(0).PlannedLoad,
                        object : TableCallback {
                            override fun editItem(obj: Any?) {
    //                        val dalouge= SmpsRectifierTableEditDialouge()
    //                        dalouge.show(childFragmentManager, "")
                            }

                            override fun viewItem(obj: Any?) {
    //                        val dalouge= SmpsRectifierTableEditDialouge ()
    //                        dalouge.show(childFragmentManager, "")
                            }
                        })
*/
                }catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }


            }
            is DgViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.dgClicked(data!!.DG.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseDg.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseDg.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseDg.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.DG.get(0).let {
                        holder.binding.dgMake.text = it.Make
                        holder.binding.dgModal.text = it.Model
                        holder.binding.dgRatingCapacityKw.text = it.RatingAndCapacity
                        holder.binding.dgCabinetsizel.text = it.CabinetSizeL
                        holder.binding.dgCabinetsizeh.text = it.CabinetSizeH
                        holder.binding.dgCabinetsizeb.text = it.CabinetSizeB
                        holder.binding.dgOverallWeightKg.text = it.OverallWeight
                        holder.binding.dgPlatformSize.text = it.PlatformSize
                        holder.binding.dgFuelType.text = ""
                        holder.binding.dgFuelConsumptionPerHour.text = it.FuelConsumptionPerHour
                    }
/*
                    holder.binding.dgAdditionalEditTable.adapter =
                        SmpsTableAdapter(context, data.SMPS.get(0).PlannedLoad, object : TableCallback {
                            override fun editItem(obj: Any?) {
                                */
/*val dalouge= SmpsRectifierTableEditDialouge()
                                dalouge.show(childFragmentManager, "")*//*

                            }

                            override fun viewItem(obj: Any?) {
                                */
/* val dalouge= SmpsRectifierTableEditDialouge ()
                                 dalouge.show(childFragmentManager, "")*//*

                            }
                        })
*/
                }catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }


            }
            is AcViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.acClicked(data!!.AC.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseAc.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseAc.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseAc.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.AC.get(0).let {
                        holder.binding.acMake.text = it.Make
                        holder.binding.acModal.text = it.Model
                        holder.binding.acRatingCapacityKw.text = it.RatingAndCapacity
                        holder.binding.acCabinetsizel.text = it.CabinetSizeL
                        holder.binding.acCabinetsizeh.text = it.CabinetSizeH
                        holder.binding.acCabinetsizeb.text = it.CabinetSizeB
                        holder.binding.acIndoreUnitSizeh.text = it.IndoorUnitSizeH
                        holder.binding.acIndoreUnitSizeb.text = it.IndoorUnitSizeB
                        holder.binding.acIndoreUnitSizel.text = it.IndoorUnitSizeL
                        holder.binding.acOutdoorUnitWeight.text = it.OutdoorUnitWeight
                        holder.binding.acOverallWeight.text = it.OverallWeight
                        holder.binding.acOwnerCompany.text = ""
                        holder.binding.acUserCompany.text = ""
                    }
                }
                catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }
            }
            is FireExiViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.fireExtinguisherClicked( data!!.FireExtinguisher.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseFire.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseFire.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseFire.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.FireExtinguisher.get(0).let {
                        holder.binding.fireMake.text = it.Make
                        holder.binding.fireModal.text = it.Model
                        holder.binding.fireRatingCapacityKw.text = ""
                        holder.binding.fireCabinetsizel.text = it.CabinetSizeL
                        holder.binding.fireCabinetsizeh.text = it.CabinetSizeH
                        holder.binding.fireCabinetsizeb.text = it.CabinetSizeB
                        holder.binding.fireUnitSizeh.text = it.UnitSizeH
                        holder.binding.fireUnitSizeb.text = it.UnitSizeB
                        holder.binding.fireUnitSizel.text = it.UnitSizeL
                        holder.binding.fireUnitWeight.text = ""
                        holder.binding.fireExtinguisherType.text = ""
                        holder.binding.fireFuelConsuptionHour.text = it.FuelConsumptionPerHour
                        holder.binding.fireOwnerCompany.text = ""
                        holder.binding.fireUserCompany.text = ""
                    }
                }catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }

            }
            is SpdViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.surgeProtectedClicked(data!!.SurgeProtectionDevice.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseSurgeProctetor.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseSurgeProctetor.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseSurgeProctetor.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.SurgeProtectionDevice.get(0).let {
                        holder.binding.surgeProctetorMake.text = it.Make
                        holder.binding.surgeProctetorModal.text = it.Model
                        holder.binding.surgeProctetorRatingCapacityKw.text = ""
                        holder.binding.surgeProctetorSpdType.text = ""
                        holder.binding.surgeProctetorOwnerCompany.text = ""
                        holder.binding.surgeProctetorUserCompany.text = ""
                    }
                }catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }

            }
            is DcdbViewHolder -> {
                holder.binding.smpsEdit.setOnClickListener {
                    listener.dcdbClicked(data!!.DCDB.get(0))
                }
                if (currentOpened == position) {
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapseDcdb.visibility = View.VISIBLE
                    holder.binding.smpsEdit.visibility = View.VISIBLE
                } else {
                    holder.binding.itemCollapseDcdb.tag = false
                    holder.binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.root.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapseDcdb.visibility = View.GONE
                    holder.binding.smpsEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayoutSmps.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleSmps.text = list[position]
                try {
                    data!!.DCDB.get(0).let {
                        holder.binding.dcdbMake.text = it.Make
                        holder.binding.dcdbModal.text = it.Model
                        holder.binding.dcdbRatingCapacityKw.text = it.RatingAndCapacity
                        holder.binding.dcdbUnitSizeb.text = it.UnitSizeB
                        holder.binding.dcdbUnitSizeh.text = it.UnitSizeH
                        holder.binding.dcdbUnitSizel.text = it.UnitSizeL
                        holder.binding.dcdbUnitWeight.text = it.UnitWeight
                        holder.binding.dcdbRatingCapacity.text = it.RatingAndCapacity
                        holder.binding.dcdbOwnerCompany.text = ""
                        holder.binding.dcdbRemarks.text = ""
                        holder.binding.dcdbUserCompany.text = ""
                    }
                }catch (e : Exception){
                    AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
                }

            }
        }
    }

    override fun getItemCount(): Int {
        println("item count prinit size is "+list.size)
        return list.size
    }

    var recyclerView: RecyclerView? = null
    fun updateList(position: Int) {
        currentOpened = if (currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView != null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface ItemClicListiner {
        fun smpsclicked(get: SMPS)
        fun batterybankclicked(get: BatteryBank)
        fun dgClicked(get: DG)
        fun acClicked(get: AC)
        fun fireExtinguisherClicked(get: FireExtinguisher)
        fun surgeProtectedClicked(get: SurgeProtectionDevice)
        fun dcdbClicked(data: DCDB)
        fun attachmentClicked()

    }


    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class SmpsViewHolder(itemView: View) : ViewHold(itemView) {
        var binding: SmpsViewBinding = SmpsViewBinding.bind(itemView)
        init {
            binding.itemCollapseSmps.tag = false
            if ((binding.itemCollapseSmps.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            }
            else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }


        }

    }

    class BatteryBankViewHolder(itemView: View) : ViewHold(itemView) {
        val binding: BatteryBankViewBinding =
            BatteryBankViewBinding.bind(itemView)

        init {
            binding.itemCollapseBBank.tag = false

            if ((binding.itemCollapseBBank.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class DgViewHolder(itemView: View) : ViewHold(itemView) {
        val binding: DgViewBinding =
            DgViewBinding.bind(itemView)

        init {
            binding.itemCollapseDg.tag = false

            if ((binding.itemCollapseDg.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class AcViewHolder(itemView: View) : ViewHold(itemView) {
        val binding: AcViewBinding =
            AcViewBinding.bind(itemView)

        init {
            binding.itemCollapseAc.tag = false

            if ((binding.itemCollapseAc.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class FireExiViewHolder(itemView: View) : ViewHold(itemView) {
        val binding: FireExitViewBinding =
            FireExitViewBinding.bind(itemView)

        init {
            binding.itemCollapseFire.tag = false

            if ((binding.itemCollapseFire.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class SpdViewHolder(itemView: View) : ViewHold(itemView) {
        val binding: SpdViewBinding =
            SpdViewBinding.bind(itemView)

        init {
            binding.itemCollapseSurgeProctetor.tag = false

            if ((binding.itemCollapseSurgeProctetor.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class DcdbViewHolder(itemView: View) : ViewHold(itemView) {
        val binding: DcdbViewBinding =
            DcdbViewBinding.bind(itemView)

        init {
            binding.itemCollapseDcdb.tag = false

            if ((binding.itemCollapseDcdb.tag as Boolean)) {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_up)
                binding.smpsRoot.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.smpsArrow.setImageResource(R.drawable.ic_arrow_down_black)
                binding.smpsRoot.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }


}



