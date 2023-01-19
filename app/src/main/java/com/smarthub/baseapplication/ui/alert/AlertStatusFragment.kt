package com.smarthub.baseapplication.ui.alert

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertStatusBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.model.search.SearchListItem
import com.smarthub.baseapplication.ui.alert.adapter.AlertImageAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertStatusListAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertStatusListener
import com.smarthub.baseapplication.ui.alert.adapter.SelectCallBack
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.dialog.CreateAlertBottomSheet
import com.smarthub.baseapplication.ui.alert.dialog.SubmitAletrBottomSheet
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.search.SearchResultAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomStringSpinner


class AlertStatusFragment : BaseFragment(), AlertStatusListener, AlertImageAdapter.ItemClickListener, SubmitAletrBottomSheet.SubmitAlertBottomSheetListener,SelectCallBack {

    var fetchedData = ""
    var isDataFetched = true
    lateinit var binding: AlertStatusBinding
    lateinit var viewmodel: AlertViewModel
    lateinit var searchCardView: EditText
    var adapter: AlertStatusListAdapter? = null
    var list : List<String> = ArrayList()
    var item: SearchListItem?=null
    var loadingProgress: ProgressBar?=null
    lateinit var searchResultAdapter : SearchResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AlertStatusBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        observerData()
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AlertStatusListAdapter(this, viewmodel, this@AlertStatusFragment)
        binding.list.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        if (viewmodel.siteSearchResponse?.hasActiveObservers() == true){
            viewmodel.siteSearchResponse?.removeObservers(viewLifecycleOwner)
        }

        viewmodel.siteSearchResponse?.observe(viewLifecycleOwner){
//            if (binding.loadingProgress.visibility ==View.VISIBLE)
//                binding.loadingProgress.visibility = View.INVISIBLE
            searchCardView.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.search,0)
            isDataFetched = true
            if (it!=null){
                if (it.status == Resource.Status.SUCCESS){

                    if (item!=null && (item?.name==fetchedData|| item?.id==fetchedData || fetchedData.isEmpty())) {

                    }else {
                        it.data?.let { it1 -> searchResultAdapter.updateList(it1) }
                        if (fetchedData.isNotEmpty()) {
                            viewmodel.fetchSiteSearchData("siteID",fetchedData)
                        }
                        fetchedData = ""
                    }
                }else {
                    Toast.makeText(requireContext(),"error :${it.message}",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(requireContext(),"error in fetching data",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun detailsItemClicked() {
        val bottomSheetDialogFragment = CreateAlertBottomSheet(R.layout.create_alert_dialog)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun itemClicked() {

    }

    private fun observerData() {
        if (viewmodel.sendAlertResponseLivedata.hasActiveObservers())
            viewmodel.sendAlertResponseLivedata.removeObservers(viewLifecycleOwner)
        viewmodel.sendAlertResponseLivedata.observe(viewLifecycleOwner) {
            //response will get here
            hideLoader()
            if (it?.data != null) {
                val bm = SubmitAletrBottomSheet(R.layout.submit_allert_bottom_sheet, it.data.data[0],this@AlertStatusFragment)
                bm.show(childFragmentManager, "categoery")
            }
        }
        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            //response will get here
            hideLoader()
            viewmodel.userDataList.clear()
            viewmodel.userDataList.addAll(it.data!!)
            val bm = AlertUserListBottomSheet(R.layout.alert_list_bottom_sheet, viewmodel, this)
            bm.show(childFragmentManager, "categoery")

        })

        if (viewmodel.departmentDropdown.hasActiveObservers())
            viewmodel.departmentDropdown.removeObservers(viewLifecycleOwner)
        viewmodel.departmentDropdown.observe(viewLifecycleOwner) {
            //response will get here
            hideLoader()
            if (it?.data != null) {
                list = it.data.department
                customStringSpinner?.setSpinnerData(it.data.department)
                customStringSpinner1?.setSpinnerData(it.data.department)
            }else Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()
        }
        viewmodel.getDepartments(DropdownParam("SMRT","department"))
    }

    override fun itemAdded() {

    }

    override fun sendAlertData() {
        showLoader()
        viewmodel.sendAlert()
    }

    override fun getuser() {
        showLoader()
        if (customStringSpinner!=null)
            viewmodel.getUser(GetUserList(customStringSpinner!!.selectedValue))
        else
            viewmodel.getUser(GetUserList("D1"))
    }
    var customStringSpinner: CustomStringSpinner?=null
    var customStringSpinner1: CustomStringSpinner?=null

    override fun setCustomArrayAdapter(customStringSpinner: CustomStringSpinner) {
        this.customStringSpinner = customStringSpinner
        customStringSpinner.setSpinnerData(list)
    }

    override fun setCustomArrayAdapter1(customStringSpinner: CustomStringSpinner) {
        this.customStringSpinner1 = customStringSpinner
        customStringSpinner.setSpinnerData(list)
    }

    override fun setSearchEditBoxAdapter(searchCardView: EditText) {
        this.searchCardView = searchCardView
        this.searchCardView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                fetchedData = searchCardView.text.toString()
                if (fetchedData.isNotEmpty() && fetchedData.length>=3 && isDataFetched) {
                    AppLogger.log("fetchedData :$fetchedData,item?.Siteid:" +
                            "${item?.name},item?.id:${item?.id}")
                    if (item!=null && (item?.name==fetchedData|| item?.id==fetchedData)) {
                        AppLogger.log("return : $fetchedData")
                        return
                    }
                    isDataFetched = false
                    if (loadingProgress?.visibility != View.VISIBLE)
                        loadingProgress?.visibility = View.VISIBLE
                    searchCardView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                        viewmodel.fetchSiteSearchData("siteID",fetchedData)

                }
                else if(fetchedData.isEmpty()){
                    Toast.makeText(requireContext(),"Input can't be empty",Toast.LENGTH_SHORT).show()

                }

            }
        })
    }

    override fun setSearchEditProgress(progress: ProgressBar) {
        this.loadingProgress = progress
    }

    override fun onSelectUser(position: Int, isadd: Boolean) {

        if (isadd) {
            viewmodel.selecteduserposition.add(position)
        } else {
            viewmodel.selecteduserposition.remove(position)
        }
        adapter?.setusercount(viewmodel.selecteduserposition.size)

    }

    override fun clickedChat() {
        findNavController().navigate(AlertStatusFragmentDirections.actionAlertStatusFragmentToChatFragment())
    }


}


