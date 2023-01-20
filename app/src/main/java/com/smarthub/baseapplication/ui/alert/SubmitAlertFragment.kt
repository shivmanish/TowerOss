package com.smarthub.baseapplication.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.SubmitAlertBottomSheetBinding
import com.smarthub.baseapplication.ui.alert.model.UpdateAlertData
import com.smarthub.baseapplication.ui.alert.model.newData.NewData
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class SubmitAlertFragment: BaseFragment() {
    lateinit var binding: SubmitAlertBottomSheetBinding
    lateinit var viewmodel: AlertViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SubmitAlertBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    var reportedBy = "7269024641"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(requireActivity())[AlertViewModel::class.java]
        if (viewmodel.sendAlertResponseLivedata.hasActiveObservers())
            viewmodel.sendAlertResponseLivedata.removeObservers(viewLifecycleOwner)
        viewmodel.sendAlertResponseLivedata.observe(viewLifecycleOwner) {
            //response will get here
            hideLoader()
            if (it?.data != null) {
                reportedBy = it.data[0].ReportedBy
                mapUiData(it.data[0])
            }
        }

        binding.close.setOnClickListener {
            val update = UpdateAlertData("$action", Utils.getCurrentFormatedDate(),binding.detailedText.text.toString(),"${arguments?.getString("id")}")
            viewmodel.updateAlertDetails(update)
            findNavController().popBackStack()
        }
        binding.chat.setOnClickListener {
            arguments?.getString("id","93")?.let {
                findNavController().navigate(SubmitAlertFragmentDirections.actionSubmitAlertFragmentToChatFragment2(it,reportedBy))
            }
        }

        if (arguments!=null && arguments?.containsKey("id")==true){
            showLoader()
            AppLogger.log("id : ${arguments?.getString("id")}")
            viewmodel.getAlertDetails("${arguments?.getString("id")}")
        }

        binding.actionTracker.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.actionOngoing.isChecked = false
                binding.actionWorking.isChecked = false
                action = 1
            }
        }
        binding.actionWorking.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.actionOngoing.isChecked = false
                binding.actionTracker.isChecked = false
                action = 2
            }
        }
        binding.actionOngoing.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.actionTracker.isChecked = false
                binding.actionWorking.isChecked = false
                action = 3
            }
        }
    }
    var action = 0
    private fun mapUiData(data: NewData){
        when (data.ActionStatus) {
            "1" -> binding.actionTracker.isChecked = true
            "2" -> binding.actionWorking.isChecked = true
            "3" -> binding.actionOngoing.isChecked = true
        }
        binding.detailedText.text = data.FullDetails.toEditable()
        binding.troublemaker.text = data.SATroubleMaker
        binding.issuetype.text = data.SAIssueType
        binding.issuetypetwo.text = data.SAIssueType
        binding.severity.text = data.SASeverity
        binding.severitytwo.text = data.SASeverity
        binding.address.text = data.SADetails
        binding.name.text = data.sitename
        binding.senderName.text = data.sitename
        binding.sendDate.text = data.HeppenDateTime
        binding.date.text = data.HeppenDateTime
        binding.sendDate.text = "D1"
        var comma_string: String = ""
        for (dat in data.Sendalertsupportdata) {
            comma_string = if (comma_string.equals("")) {
                dat.SuRecepientusername
            } else {
                comma_string + "" + dat.SuRecepientusername
            }
        }

        binding.recipentaName.text = comma_string

    }
}