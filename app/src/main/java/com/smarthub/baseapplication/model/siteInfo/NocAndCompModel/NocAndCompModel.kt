package com.smarthub.baseapplication.model.siteInfo.NocAndCompModel

import com.google.gson.annotations.SerializedName

class NocAndCompModel{
     @SerializedName("finaldata")
     var item : List<NocAllData>?=null

     @SerializedName("error")
     var error : Any?=null
 }
