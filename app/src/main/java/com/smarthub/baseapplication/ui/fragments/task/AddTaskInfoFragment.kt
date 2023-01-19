package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentAddTaskInfoBinding
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.viewmodels.MainViewModel

class AddTaskInfoFragment : BaseFragment() {

    lateinit var binding: FragmentAddTaskInfoBinding
    private lateinit var mainViewModel:MainViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        binding = FragmentAddTaskInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding.next.setOnClickListener{
            findNavController().navigate(
                R.id.actionToMoveSecondFrag
            )
        }
        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }
//        binding.startDate.setOnClickListener {
//            Toast.makeText(context,"Start Date Clicked",Toast.LENGTH_SHORT).show()
//        }
        binding.endDate.setOnClickListener {
            Toast.makeText(context,"end Date Clicked",Toast.LENGTH_SHORT).show()
        }
        setDatePickerView(binding.startDate)
//        setDatePickerView(binding.endDate)

        var list=ArrayList<DropDownItem>()
        list.add(DropDownItem("Yes","0"))
        list.add(DropDownItem("No","1"))
        binding.taskForASingleSite.setSpinnerData(list)
    }


}