package com.smarthub.baseapplication.ui.alert.dialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ChatDialogBinding
import com.smarthub.baseapplication.ui.alert.adapter.ChatAdapter
import com.smarthub.baseapplication.ui.alert.model.chat.ChatModelData
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils


class ChatFragment: BaseFragment() {
    lateinit var binding : ChatDialogBinding
    lateinit var viewmodel: AlertViewModel
    var id = "111"
    var reportedBy = "7269024641"

    fun updateList(list : ArrayList<ChatModelData>){
        hideLoader()
        if (binding.list.adapter is ChatAdapter)
            (binding.list.adapter as ChatAdapter).updateList(list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity())[AlertViewModel::class.java]
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.99).toInt()
        if (arguments!=null && arguments?.containsKey("reportedBy")==true){
            showLoader()
            reportedBy = "${arguments?.getString("reportedBy")}"
            AppLogger.log("reportedBy : $reportedBy")
        }
        val chatAdapter = ChatAdapter(ArrayList(),requireContext(),reportedBy)
        binding.list.adapter = chatAdapter

        if (viewmodel.sendAlertResponseLivedata.hasObservers())
            viewmodel.sendAlertResponseLivedata.removeObservers(viewLifecycleOwner)
        viewmodel.sendAlertResponseLivedata.observe(viewLifecycleOwner) {
            //response will get here
            hideLoader()
            if (it?.data != null) {
                if (it.data[0].chats!=null && it.data[0].chats?.isNotEmpty() == true){
                    updateList(ArrayList(it.data[0].chats))
                    AppLogger.log("ChatFragment chat size :${it.data[0].chats?.size}")
                }else AppLogger.log("ChatFragment chat size empty")
            }
            else Toast.makeText(context,"ChatFragment null data", Toast.LENGTH_SHORT).show()
        }

        binding.editChatBx.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.editChatBx.text.toString().isNotEmpty()){
                    binding.sendMsg.isEnabled = true
                    binding.sendMsg.setImageResource(R.drawable.ic_enter_message)
                }else {
                    binding.sendMsg.isEnabled = false
                    binding.sendMsg.setImageResource(R.drawable.ic_enter_message_gray)
                }
            }

        })
        binding.sendMsg.setOnClickListener {
            showLoader()
            viewmodel.sendSms(id,binding.editChatBx.text.toString())
            binding.editChatBx.text = "".toEditable()
            Utils.hideKeyboard(requireContext(),binding.editChatBx)
        }
        binding.sendMsg.isEnabled = false
        binding.sendMsg.setImageResource(R.drawable.ic_enter_message_gray)
        if (arguments!=null && arguments?.containsKey("id")==true){
            showLoader()
            id = "${arguments?.getString("id")}"
            AppLogger.log("id : $id")
        }

        showLoader()
        viewmodel.getAlertDetails(id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ChatDialogBinding.inflate(inflater)
        return binding.root
    }

}