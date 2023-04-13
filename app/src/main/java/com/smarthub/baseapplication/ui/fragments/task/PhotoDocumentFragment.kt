package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.PhotoDocumentFragmentBinding
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger

class PhotoDocumentFragment(var taskInfo : TaskInfoItem?): BaseFragment() {
    lateinit var binding:PhotoDocumentFragmentBinding
    lateinit var viewmodel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PhotoDocumentFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        setPreviousData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if(requireActivity().intent.hasExtra("data")){
//            var data = Gson().fromJson(requireActivity().intent.getStringExtra("data"),MyTeamTask::class.java)
//            viewmodel.getTaskById(data.id1)
//            taskInfoObserver()
//        }
        if(taskInfo!=null){
            binding.pictureBox.isChecked= taskInfo?.pictures=="True"
            binding.documentBox.isChecked= taskInfo?.documents=="True"
        }
        binding.pictureBox.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.pictures=isChecked
        }
        binding.documentBox.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.pictures=isChecked
        }
        binding.Remark.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val changedText=binding.Remark.text
                viewmodel.processTemplatemanual.remark=changedText.toString()
            }
        })
        binding.next.setOnClickListener {
            viewmodel.processTemplatemanual.pictures=binding.pictureBox.isChecked
            viewmodel.processTemplatemanual.documents=binding.documentBox.isChecked
            viewmodel.processTemplatemanual.remark=binding.Remark.text.toString()
            viewmodel.processTemplatemanual.Where= null
            AppLogger.log("all data: ${viewmodel.processTemplatemanual}")
            nextClicked()
        }
    }


    private fun nextClicked() {
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }

    fun taskInfoObserver(){
        if (viewmodel.getTaskInfoResponse!!.hasActiveObservers())
            viewmodel.getTaskInfoResponse!!.removeObservers(viewLifecycleOwner)
        viewmodel.getTaskInfoResponse!!.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                try {
                    taskInfo=it.data.get(0)
                }catch (e:Exception){
                    AppLogger.log("Somthing went wront to fetch task info ${e.localizedMessage}")
                }
            }else AppLogger.log("Department not fetched")

        })
    }

    fun setPreviousData(){
        if (viewmodel.processTemplatemanual.pictures!=null)
            binding.pictureBox.isChecked= viewmodel.processTemplatemanual.pictures == true
        if (viewmodel.processTemplatemanual.documents!=null)
            binding.documentBox.isChecked= viewmodel.processTemplatemanual.documents == true
        if (viewmodel.processTemplatemanual.remark!=null)
            binding.Remark.setText(viewmodel.processTemplatemanual.remark)
    }

}