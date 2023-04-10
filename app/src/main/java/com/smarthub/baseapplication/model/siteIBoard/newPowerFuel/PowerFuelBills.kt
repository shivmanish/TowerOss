package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

import com.smarthub.baseapplication.model.siteIBoard.Attachments

class PowerFuelBills{
    var Amount: String?=null
    var BillMonth: String?=null
    var BillNumber: String?=null
    var DueDate: String?=null
    var PaymentStatus: List<Int>?=null
    var StatusDate: String?=null
    var UnitConsumed: Int?=null
    var created_at: String?=null
    var attachment: ArrayList<Attachments>?=null
    var id: Int?=null
    var isActive: Boolean?=null
    var modified_at: String?=null
}