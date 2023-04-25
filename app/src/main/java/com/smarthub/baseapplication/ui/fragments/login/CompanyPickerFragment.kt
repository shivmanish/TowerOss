package com.smarthub.baseapplication.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.CompanyPickerFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.profile.CompanyData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class CompanyPickerFragment : BaseFragment() , CompanyPickerAdapter.CompanyPickerAdapterListner {

    var historyItem: CompanyData?=null
    private var loginViewModel : LoginViewModel?=null
    var binding : CompanyPickerFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.company_picker_fragment, container, false)
        binding = CompanyPickerFragmentBinding.bind(view)
        return view
    }

    fun processSelection(){
        if (historyItem!=null){
            AppController.getInstance().ownerName = historyItem?.ownercode
            AppPreferences.getInstance().saveString("company",historyItem?.ownercode)

            AppPreferences.getInstance().saveLong("loginTime",System.currentTimeMillis())
            val intent = Intent (requireContext(), DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }else Toast.makeText(requireContext(),"company not selected",Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding?.login?.setOnClickListener {
            processSelection()
        }
        binding?.login?.post {
            showLoader()
        }
        val adapter = CompanyPickerAdapter(requireContext(),this)
        binding?.list?.adapter = adapter
        if (loginViewModel?.loginResponse?.hasActiveObservers() == true){
            loginViewModel?.loginResponse?.removeObservers(viewLifecycleOwner)
        }
        loginViewModel?.profileResponse?.observe(viewLifecycleOwner) {
            hideLoader()
            if (it != null && it.status==Resource.Status.SUCCESS) {
                if (it.data?.isNotEmpty()==true) {
                    val data = it.data[0]
                    if (data.ownernameall!=null){
                        if (data.ownernameall.size==1){
                            historyItem = data.ownernameall[0]
                            processSelection()
                        }
                        adapter.updateList(data.ownernameall)
                    }
                }
            }else{
                Toast.makeText(requireContext(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        loginViewModel?.getProfileData()
    }

    override fun clickedSearchHistoryItem(historyItem: CompanyData?) {
        this.historyItem = historyItem
    }
}


