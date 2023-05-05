package com.smarthub.baseapplication.ui.fragments.rfequipment.pojo

import com.smarthub.baseapplication.utils.AppController


class RfMainResponse{
   var RfSurvey: ArrayList<RfSurvey>? = null
   var ownername:String? = AppController.getInstance().ownerName
   var id:String? = AppController.getInstance().siteid
}