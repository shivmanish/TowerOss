package com.smarthub.baseapplication.ui.alert

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertStatusBinding
import com.smarthub.baseapplication.model.home.alerts.AlertAllData
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.model.search.SearchListItem
import com.smarthub.baseapplication.ui.alert.adapter.AlertImageAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertStatusListAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertStatusListener
import com.smarthub.baseapplication.ui.alert.adapter.SelectCallBack
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.dialog.CreateAlertBottomSheet
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.search.SearchResultAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.widgets.CustomStringSpinner


class AlertStatusFragment : BaseFragment(), AlertStatusListener, AlertImageAdapter.ItemClickListener,SelectCallBack,SearchResultAdapter.SearchResultListener {

    lateinit var binding: AlertStatusBinding
    lateinit var viewmodel: AlertViewModel
    var searchCardView: TextView?=null
    var adapter: AlertStatusListAdapter? = null
    var list : List<String> = ArrayList()
    var item: SearchListItem?=null
    var loadingProgress: ProgressBar?=null
    lateinit var searchResultAdapter : SearchResultAdapter
    companion object{
        var itemNew = SearchListItem("Search Site","448")
        var homeAlertData:AlertAllData?=null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AlertStatusBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity())[AlertViewModel::class.java]
        observerData()
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultAdapter = SearchResultAdapter(requireContext(),this@AlertStatusFragment)
        var type = "custom"
        if (arguments!=null && arguments?.containsKey("type") == true){
            type = "${arguments?.getString("type")}"
        }
        adapter = AlertStatusListAdapter(this, this@AlertStatusFragment,type)
        binding.list.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    fun detailsItemClicked() {
        val bottomSheetDialogFragment = CreateAlertBottomSheet(R.layout.create_alert_dialog)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun itemClicked() {

    }

    private fun observerData() {
        if (viewmodel.sendAlertResponseLivedataNew.hasActiveObservers())
            viewmodel.sendAlertResponseLivedataNew.removeObservers(viewLifecycleOwner)
        viewmodel.sendAlertResponseLivedataNew.observe(viewLifecycleOwner) {
            //response will get here
            hideLoader()
            if (it?.data != null && it.data.data.isNotEmpty()) {
                findNavController().navigate(AlertStatusFragmentDirections.actionAlertStatusFragmentToSubmitAlertFragment(it.data.data[0].id))
            }else
                Toast.makeText(requireContext(),"empty data",Toast.LENGTH_SHORT).show()
        }

        if (viewmodel.searchListItem.hasActiveObservers())
            viewmodel.searchListItem.removeObservers(viewLifecycleOwner)
        viewmodel.searchListItem.observe(viewLifecycleOwner) {
            //response will get here
            if (it!=null){
                if (searchCardView!=null){
                    searchCardView?.text = it.name
//                    Toast.makeText(requireContext(),"${it.name} name",Toast.LENGTH_SHORT).show()
                }else Toast.makeText(requireContext(),"null fields",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"null value",Toast.LENGTH_SHORT).show()
            }

        }

        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            //response will get here
            hideLoader()
            if (it.data!=null){
                viewmodel.userDataList.clear()
                viewmodel.userDataList.addAll(it.data)
                val bm = AlertUserListBottomSheet(R.layout.alert_list_bottom_sheet, viewmodel, this)
                bm.show(childFragmentManager, "categoery")
            }

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
        viewmodel.getDepartments(DropdownParam(AppController.getInstance().ownerName,"department"))
        viewmodel.searchListItem.postValue(SearchListItem("Search Site","448"))
    }

    override fun itemAdded() {

    }

    override fun sendAlertData() {
        showLoader()
        viewmodel.sendAlertModel.sitename = "${itemNew.name}"
        viewmodel.sendAlertModel.siteid = "${itemNew.id}"
        viewmodel.sendAlert()
    }

    override fun getuser() {
        showLoader()
        if (customStringSpinner!=null)
            viewmodel.getDepartmentUsers(GetUserList(customStringSpinner!!.selectedValue, AppController.getInstance().ownerName))
        else
            viewmodel.getDepartmentUsers(GetUserList("D1",AppController.getInstance().ownerName))
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

    override fun setSearchEditBoxAdapter(searchCardView: TextView) {
        this.searchCardView = searchCardView
        searchCardView.text = itemNew.name
        searchCardView.setOnClickListener {
            findNavController().navigate(AlertStatusFragmentDirections.actionAlertStatusFragmentToSearchIdFragment())
        }
    }
    override fun onResume() {
        if (searchCardView!=null)
            searchCardView?.text = itemNew.name
        super.onResume()
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

//    override fun clickedChat() {
//
//    }

    override fun onSearchItemSelected(item: Any?) {

    }


}


