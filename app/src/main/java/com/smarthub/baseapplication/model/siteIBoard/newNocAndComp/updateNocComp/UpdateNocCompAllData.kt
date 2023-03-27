package com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp

import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocAuthorityDetail
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocAuthorityFeePaymentDetail
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocPODetail

data class UpdateNocCompAllData(
    var ApplicationInitial: ArrayList<NocApplicationInitial>?=null,
    var AuthorityDetail: ArrayList<NocAuthorityDetail>?=null,
    var AuthorityFeePaymentDetail: ArrayList<NocAuthorityFeePaymentDetail>?=null,
    var PODetail: ArrayList<NocPODetail>?=null,
    var id: Int?=null,

)