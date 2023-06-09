package com.smarthub.baseapplication.ui.fragments.sitedetail

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.model.taskModel.dropdown.GetTaskDataModel
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.model.taskModel.dropdown.UpdateTaskDataModel
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.SiteInfoRepo


class SiteDetailViewModel: ViewModel() {
    var changeStatusPopUp:PopupWindow?=null
    var isScrollUp = MutableLiveData<Boolean>()
    var isScroll = MutableLiveData<SetTabData>()

    var siteInfoRepo: SiteInfoRepo?=null
    var dropDownResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    var taskUiModelResoonse : SingleLiveEvent<Resource<GetTaskDataModel>>?=null
    var updateTaskUiModelResoonse : SingleLiveEvent<Resource<UpdateTaskDataModel>>?=null

    init {
        siteInfoRepo = SiteInfoRepo(APIInterceptor.get())
        dropDownResponse = siteInfoRepo?.dropDownResoonse
        taskUiModelResoonse = siteInfoRepo?.taskUiModelResoonse
        updateTaskUiModelResoonse = siteInfoRepo?.updateTaskUiModelResoonse
    }

    fun  getStrings(ctx:Context): Array<String> {
       return ctx.resources.getStringArray(R.array.tab_names)
    }
    fun getImageArray(ctx:Context): TypedArray {
        return ctx.resources.obtainTypedArray(R.array.random_imgs)
    }


    fun getDrawable(ctx:Context,istrue: Boolean):Drawable{
        if (!istrue){
           return ctx.resources.getDrawable(R.drawable.edit_fav_btn)
        }else{
            return ctx.resources.getDrawable(R.drawable.close)
        }
    }
    fun showStatusPopup(context: Activity,p: Point) {

        // Inflate the popup_layout.xml
//        val viewGroup = context.findViewById<View>(R.id.llStatusChangePopup) as LinearLayout
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = layoutInflater.inflate(R.layout.pop_window_custom, null)

        // Creating the PopupWindow
         val wrapper: Context = ContextThemeWrapper(context, R.style.PopupMenu)
         changeStatusPopUp = PopupWindow(wrapper)
        changeStatusPopUp?.contentView = layout
        changeStatusPopUp?.width = LinearLayout.LayoutParams.WRAP_CONTENT
        changeStatusPopUp?.height = LinearLayout.LayoutParams.WRAP_CONTENT
        changeStatusPopUp?.isFocusable = false


        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        val OFFSET_X = 400
        val OFFSET_Y = -320

        //Clear the default translucent background
        changeStatusPopUp?.setBackgroundDrawable(null)
//         changeStatusPopUp?.setBackgroundDrawable(
//             ColorDrawable(
//                 Color.TRANSPARENT)
//         )
         changeStatusPopUp?.elevation = 40f

        // Displaying the popup at the specified location, + offsets.
//        changeStatusPopUp.showAsDropDown(layout)
         Log.e("TagValues","x coordinate ${p.x}")
        changeStatusPopUp?.showAtLocation(layout, Gravity.NO_GRAVITY, p.x - OFFSET_X, p.y + OFFSET_Y)
    }
    fun openPopup(view: View,context: Activity){
        var p:Point = Point()
        p.x = view.x.toInt()
        p.y = view.y.toInt()
        showStatusPopup(context,p)
    }
    fun dismissPopub(){
        changeStatusPopUp?.dismiss()
    }

    fun setScrollViewUp(b: Boolean) {
       isScrollUp.value = b
    }
    fun setScrollValue(istrue: Boolean,b: Int) {
        var setTabData= SetTabData(istrue,b)
        isScroll.value = setTabData

    }

    data class SetTabData(
        var istrue: Boolean,
        var offset:Int
    )

    fun fetchDropDown() {
        siteInfoRepo?.siteInfoDropDown()
    }

    fun siteTaskUiModel(id:String) {
        siteInfoRepo?.siteTaskUiModel(id)
    }

    fun siteTaskUiUpdateModel(taskDropDownModel:TaskDropDownModel) {
        siteInfoRepo?.siteTaskUiUpdateModel(taskDropDownModel)
    }
}