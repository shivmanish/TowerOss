package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentAddTaskInfoBinding
import com.smarthub.baseapplication.viewmodels.MainViewModel

class AddTaskInfoFragment : Fragment() {

    private var _binding: FragmentAddTaskInfoBinding? = null
    private lateinit var mainViewModel:MainViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        _binding = FragmentAddTaskInfoBinding.inflate(inflater, container, false)
        initView()
        val root: View = binding.root
        return root
    }

    private fun initView() {
        _binding!!.next.setOnClickListener{
            findNavController().navigate(
                R.id.actionToMoveSecondFrag
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}