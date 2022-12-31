package com.smarthub.baseapplication.model.siteInfo.NocAndCompModel

import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem

class NocAllData (
    val NOCCompliance: ArrayList<OpcoDataItem>,
    val id: Int,
    val isActive: Boolean
    )