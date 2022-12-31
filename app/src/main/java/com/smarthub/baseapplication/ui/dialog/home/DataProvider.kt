package com.smarthub.baseapplication.ui.dialog.home

import com.smarthub.baseapplication.model.dropdown.DropDownItem

object DataProvider {

    fun getSiteCode():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("SL","0"))
        datalist.add(DropDownItem("RL","1"))
        datalist.add(DropDownItem("AM","2"))
        datalist.add(DropDownItem("PR","3"))
        return datalist
    }

    fun getCityCode():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("MP","0"))
        datalist.add(DropDownItem("OR","1"))
        datalist.add(DropDownItem("HR","2"))
        datalist.add(DropDownItem("SM","3"))
        return datalist
    }
    fun getSiteType():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("001","0"))
        datalist.add(DropDownItem("002","1"))
        datalist.add(DropDownItem("003","2"))
        datalist.add(DropDownItem("004","3"))
        return datalist
    }
    fun getSiteClass():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("01","0"))
        datalist.add(DropDownItem("02","1"))
        datalist.add(DropDownItem("03","2"))
        datalist.add(DropDownItem("04","3"))
        return datalist
    }
    fun getNational():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("INDIA","0"))
        return datalist
    }
    fun getRegion():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("South Zone","0"))
        datalist.add(DropDownItem("East Zone","1"))
        datalist.add(DropDownItem("West Zone","2"))
        datalist.add(DropDownItem("North Zone","3"))
        return datalist
    }
    fun getState():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("OD","0"))
        datalist.add(DropDownItem("MP","1"))
        datalist.add(DropDownItem("HR","2"))
        datalist.add(DropDownItem("MH","3"))
        return datalist
    }
    fun getMentainancePoint():ArrayList<DropDownItem>{
        val datalist = arrayListOf<DropDownItem>()
        datalist.add(DropDownItem("OD","0"))
        datalist.add(DropDownItem("MP","1"))
        datalist.add(DropDownItem("HR","2"))
        datalist.add(DropDownItem("MH","3"))
        return datalist
    }

}