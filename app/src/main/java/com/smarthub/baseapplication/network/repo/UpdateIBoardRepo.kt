package com.smarthub.baseapplication.network.repo

import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompModel
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.updateSiteInfo.UpdateSiteInfoResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc.UpdateSstSbcModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc.UpdateSstSbcResponseModel
import com.smarthub.baseapplication.network.APIClient
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
    var updateSiteInfoResponse: SingleLiveEvent<Resource<UpdateSiteInfoResponseModel>>? = null

    init {
        updateSiteAcqResponse=SingleLiveEvent<Resource<UpdateSiteAcqResponseModel>>()
        updateUtilityEquipResponse=SingleLiveEvent<Resource<UpdateUtilityResponseModel>>()
        updateNocCompResponse=SingleLiveEvent<Resource<UpdateNocCompResponseModel>>()
        updateSstSbcResponse=SingleLiveEvent<Resource<UpdateSstSbcResponseModel>>()
        updateSiteInfoResponse=SingleLiveEvent<Resource<UpdateSiteInfoResponseModel>>()
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


    
}