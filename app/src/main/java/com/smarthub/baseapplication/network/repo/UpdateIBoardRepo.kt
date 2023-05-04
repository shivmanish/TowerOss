package com.smarthub.baseapplication.network.repo

import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompModel
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.updatePowerFuel.UpdatePowerFuelResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.updateSiteInfo.UpdateSiteInfoResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerCivilAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.updateTwrCivil.UpdateTwrCivilInfraResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc.UpdateSstSbcModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc.UpdateSstSbcResponseModel
import com.smarthub.baseapplication.network.APIClient
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfBasicResponse
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfMainResponse
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateIBoardRepo(private var apiClient: APIClient) {

    val TAG = "UpdateSiteIBoardRepo"
    var updateSiteAcqResponse: SingleLiveEvent<Resource<UpdateSiteAcqResponseModel>>? = null
    var updateUtilityEquipResponse: SingleLiveEvent<Resource<UpdateUtilityResponseModel>>? = null
    var updateNocCompResponse: SingleLiveEvent<Resource<UpdateNocCompResponseModel>>? = null
    var updateSstSbcResponse: SingleLiveEvent<Resource<UpdateSstSbcResponseModel>>? = null
    var rfBasicResponselivedata: SingleLiveEvent<Resource<RfBasicResponse>>? = null
    var updateSiteInfoResponse: SingleLiveEvent<Resource<UpdateSiteInfoResponseModel>>? = null
    var updatePowerFuelResponse: SingleLiveEvent<Resource<UpdatePowerFuelResponseModel>>? = null
    var updateTwrCivilInfraResponse: SingleLiveEvent<Resource<UpdateTwrCivilInfraResponseModel>>? = null

    init {
        updateSiteAcqResponse=SingleLiveEvent<Resource<UpdateSiteAcqResponseModel>>()
        updateUtilityEquipResponse=SingleLiveEvent<Resource<UpdateUtilityResponseModel>>()
        updateNocCompResponse=SingleLiveEvent<Resource<UpdateNocCompResponseModel>>()
        updateSstSbcResponse=SingleLiveEvent<Resource<UpdateSstSbcResponseModel>>()
        updateSiteInfoResponse=SingleLiveEvent<Resource<UpdateSiteInfoResponseModel>>()
        updatePowerFuelResponse=SingleLiveEvent<Resource<UpdatePowerFuelResponseModel>>()
        updateTwrCivilInfraResponse=SingleLiveEvent<Resource<UpdateTwrCivilInfraResponseModel>>()
        rfBasicResponselivedata = SingleLiveEvent<Resource<RfBasicResponse>>()
    }



    fun updateSiteAcqData(data: UpdateSiteAcqModel?) {
        AppLogger.log("updateSiteAcqData==> : ${Gson().toJson(data)}")
        apiClient.updateSiteAcqRequest(data!!).enqueue(object : Callback<UpdateSiteAcqResponseModel> {
            override fun onResponse(
                call: Call<UpdateSiteAcqResponseModel?>,
                response: Response<UpdateSiteAcqResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdateSiteAcqResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdateSiteAcqResponseModel?>) {
                if (response.body() != null) {
                    updateSiteAcqResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateSiteAcqResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null) updateSiteAcqResponse?.postValue(
                    Resource.error(iThrowableLocalMessage, null, 500)
                ) else updateSiteAcqResponse?.postValue(
                    Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }

    fun updateUtilityEquipData(data: UpdateUtilityEquipmentModel?) {
        AppLogger.log("updateUtilityEquipData==> : ${Gson().toJson(data)}")
        apiClient.updateUtilitiesEquipRequest(data!!).enqueue(object : Callback<UpdateUtilityResponseModel> {
            override fun onResponse(
                call: Call<UpdateUtilityResponseModel?>,
                response: Response<UpdateUtilityResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdateUtilityResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdateUtilityResponseModel?>) {
                if (response.body() != null) {
                    updateUtilityEquipResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateUtilityEquipResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null) updateSiteAcqResponse?.postValue(
                    Resource.error(iThrowableLocalMessage, null, 500)
                ) else updateUtilityEquipResponse?.postValue(
                    Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }

    fun updateSiteInfoData(data: AllsiteInfoDataModel?) {
        AppLogger.log("updateSiteInfoData==> : ${Gson().toJson(data)}")
        apiClient.updateSiteInfoRequest(data!!).enqueue(object : Callback<UpdateSiteInfoResponseModel> {
            override fun onResponse(
                call: Call<UpdateSiteInfoResponseModel?>,
                response: Response<UpdateSiteInfoResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdateSiteInfoResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdateSiteInfoResponseModel?>) {
                if (response.body() != null) {
                    updateSiteInfoResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateSiteInfoResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null)
                    updateSiteInfoResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500)
                ) else
                    updateSiteInfoResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }

    fun updateNocCompData(data: UpdateNocCompModel?) {
        AppLogger.log("updateNocCompData==> : ${Gson().toJson(data)}")
        apiClient.updateNocCompRequest(data!!).enqueue(object : Callback<UpdateNocCompResponseModel> {
            override fun onResponse(
                call: Call<UpdateNocCompResponseModel?>,
                response: Response<UpdateNocCompResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdateNocCompResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdateNocCompResponseModel?>) {
                if (response.body() != null) {
                    updateNocCompResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateNocCompResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null) updateSiteAcqResponse?.postValue(
                    Resource.error(iThrowableLocalMessage, null, 500)
                ) else updateNocCompResponse?.postValue(
                    Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }

    fun updateSstSbcData(data: UpdateSstSbcModel?) {
        AppLogger.log("updateSstSbcData==> : ${Gson().toJson(data)}")
        apiClient.updateSstSbcRequest(data!!).enqueue(object : Callback<UpdateSstSbcResponseModel> {
            override fun onResponse(
                call: Call<UpdateSstSbcResponseModel?>,
                response: Response<UpdateSstSbcResponseModel?>
            ) {
                AppLogger.log("updateSstSbcData onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdateSstSbcResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log( "updateSstSbcData"+ " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdateSstSbcResponseModel?>) {
                if (response.body() != null) {
                    updateSstSbcResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateSstSbcResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null)
                    updateSstSbcResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500)
                ) else updateSstSbcResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }

    fun updateRfData(data: RfMainResponse?) {
        AppLogger.log("updateSstSbcData==> : ${Gson().toJson(data)}")
        apiClient.updateRfServey(data).enqueue(object : Callback<RfBasicResponse> {
            override fun onResponse(
                call: Call<RfBasicResponse?>,
                response: Response<RfBasicResponse?>
            ) {
                AppLogger.log("updateSstSbcData onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<RfBasicResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log( "updateSstSbcData"+ " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<RfBasicResponse?>) {
                if (response.body() != null) {
                    rfBasicResponselivedata?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateSstSbcResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null)
                    updateSstSbcResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500)
                    ) else updateSstSbcResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }


    fun updateTwrCivilData(data: TowerCivilAllDataModel?) {
        AppLogger.log("updateSiteInfoData==> : ${Gson().toJson(data)}")
        apiClient.updateTwrCivilInfraRequest(data!!).enqueue(object : Callback<UpdateTwrCivilInfraResponseModel> {
            override fun onResponse(
                call: Call<UpdateTwrCivilInfraResponseModel?>,
                response: Response<UpdateTwrCivilInfraResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdateTwrCivilInfraResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdateTwrCivilInfraResponseModel?>) {
                if (response.body() != null) {
                    updateTwrCivilInfraResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updateTwrCivilInfraResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null)
                    updateTwrCivilInfraResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500)
                    ) else
                    updateTwrCivilInfraResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }

    fun updatePowerFuelData(data: PowerFuelAllDataModel?) {
        AppLogger.log("updateSiteInfoData==> : ${Gson().toJson(data)}")
        apiClient.updatePowerFuelRequest(data!!).enqueue(object : Callback<UpdatePowerFuelResponseModel> {
            override fun onResponse(
                call: Call<UpdatePowerFuelResponseModel?>,
                response: Response<UpdatePowerFuelResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdatePowerFuelResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdatePowerFuelResponseModel?>) {
                if (response.body() != null) {
                    updatePowerFuelResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updatePowerFuelResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null)
                    updatePowerFuelResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500)
                    ) else
                    updatePowerFuelResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }


    
}