package com.smarthub.baseapplication.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.register.RegisterData
import com.smarthub.baseapplication.model.taskModel.Processtemplatecallmanual
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object Utils {

    fun log(message: String) {
        Log.i("TAG", message)
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getSelectedSpinner(selsteddata: String, list: List<DropDownItem>): DropDownItem {
        var returndata:DropDownItem? = null
        println("data123 "+selsteddata)
        println("data123 "+list.size)

        for(i in list){
            if(i.name.equals(selsteddata,true)){
                println("data123 "+i.name)
                returndata = i
                break
            }
        }
        return returndata!!
    }

    fun clearBackStack(activity: FragmentActivity) {
        val name: String? = activity.supportFragmentManager.getBackStackEntryAt(0).name
        activity.supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    }

    fun log(tag: String, message: String) {
        Log.i(tag, message)
    }

    fun flog(message: String) {
        Log.e("asdfasdf", ":-: $message :-:")
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)

        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // Collapse speed of 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun expand(v: View) {
        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = v.measuredHeight

        v.layoutParams.height = 1
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                v.visibility = View.VISIBLE
                v.layoutParams.height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // Expansion speed of 1dp/ms
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun validatePass(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true

    }

    // the function which triggered when the VALIDATE button is clicked
    // which validates the email address entered by the user
    fun emailValidator(emailToText: String): Boolean {
        if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true
        }
        return false
    }

    fun addFragment(fragment: Fragment?, activity: AppCompatActivity, resId: Int) {
        val backStateName: String = activity.supportFragmentManager.javaClass.name
        val manager = activity.supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.add(resId, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    fun addFragmenttab(fragment: Fragment?, activity: AppCompatActivity, resId: Int) {
        val manager = activity.supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(resId, fragment!!)
        transaction.commit()
    }


    fun replaceFragmentFragment(fragment: Fragment?, activity: AppCompatActivity, resId: Int) {
        val backStateName: String = activity.supportFragmentManager.javaClass.name
        val manager = activity.supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(resId, fragment!!)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    fun getRegistrationDummyData(): RegisterData {
        var registerData = RegisterData(
            save = "NA",
            ownername = "NA",
            username = "NA",
            last_name = "",
            phone = "NA",
            email = "NA",
            rolestxt = "NA",
            departmenttxt = "M",
            rolegeographytxt = "NA",
            managername = "NA",
            manageremail= "NA",
            managerphone= "NA",
            password = ""
        )
        return registerData
    }

    fun getCreateNewTaskDummyData(): Processtemplatecallmanual {
        var processTemplatemanual = Processtemplatecallmanual(
            "",true,"",true,
            "",true,0,0,"","",
            "","","","", true,"","",true,
            "","","","","","")
        return processTemplatemanual
    }

    fun compareDate(date : String) : Int{
        AppLogger.log("compareDate:$date")
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            val firstDate: Date =  Calendar.getInstance().time
            val secondDate: Date = sdf.parse(date)
            val cmp = firstDate.compareTo(secondDate)
            AppLogger.log("cmp :$cmp")
            return cmp
        }catch (e:java.lang.Exception){
            AppLogger.log("compareDate error :${e.localizedMessage}")
        }
        return 0
    }
    fun getShortForm(currentDate: String?,format:String): String? {
        var dateInString = currentDate
        if(dateInString?.contains(" ")==true)
            dateInString=dateInString.split(" ")[0]
        val sdf = SimpleDateFormat(format)
        val c = Calendar.getInstance()
        try {
            c.time = sdf.parse(dateInString)
        } catch (e:java.lang.Exception){
            AppLogger.log("getShortForm error :${e.localizedMessage}")
        }
        val resultdate = Date(c.timeInMillis)
        dateInString = sdf.format(resultdate)
        return dateInString
    }

    fun getCurrentFormatedDate() : String{
        try {
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val current = formatter.format(time)
            return current
        }catch (e : Exception){
            AppLogger.log("compareDate error :${e.localizedMessage}")
        }
        return ""
    }

    fun getFormatedDate(d : String,format:String) : String{
        var date=d
        if(date.contains(" "))
            date=date.split(" ")[0]
        AppLogger.log("getformatedDate:$date")
        try {
            val sdf = SimpleDateFormat(format)
            val secondDate: Date = sdf.parse(date)

            date=secondDate.toString()
            AppLogger.log("date :$date")
        }catch (e:java.lang.Exception){
            AppLogger.log("compareDate error :${e.localizedMessage}")
        }
        return date
    }

    fun isValid(value: String):Boolean {
        return !value.trim().equals("") && !value.equals("Na", ignoreCase = true) && !(value.length<3)
    }


    fun dateDiffrence(firstdate : String,secondDate : String): Int {
        AppLogger.log("dateDiffrence:$firstdate, $secondDate")
        try {
            val date1: Date
            val date2: Date
            val dates = SimpleDateFormat("yyyy-MM-dd")
            date1 = dates.parse(firstdate)
            date2 = dates.parse(secondDate)
            AppLogger.log("dateDiffrence:$date1, $date2")
            val difference: Long = date2.time - date1.time
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            AppLogger.log("date diffrence error :${differenceDates}")
            return differenceDates.toInt()
        }catch (e:java.lang.Exception){
            AppLogger.log("date diffrence error :${e.message}")

        }
        return 0
    }
    fun dialogYesOrNo(
        context: Context,
        title: String,
        message: String,
        listener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes", listener
        )
        builder.setNegativeButton("No", listener)
        val alert = builder.create()
        alert.setTitle(title)
        alert.window?.setBackgroundDrawableResource(R.drawable.round_corner_background)
        alert.setMessage(message)
        alert.setCancelable(false)
        alert.show()
    }
}
