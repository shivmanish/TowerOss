package com.smarthub.baseapplication.model.siteInfo.NocAndCompModel

import com.google.gson.annotations.SerializedName
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData.OpcoInfoAllDataNew

class NocAndCompModel{
     @SerializedName("finaldata")
     var item : List<OpcoInfoAllDataNew>?=null

     @SerializedName("error")
     var error : Any?=null
 }
