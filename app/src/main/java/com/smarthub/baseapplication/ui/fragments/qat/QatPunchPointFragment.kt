package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentPunchPointQatBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.adapter.qat.QatPunchPointAdapter
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.model.qatcheck.punch_point.PunchPointUpdate
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
import com.smarthub.baseapplication.ui.dialog.qat.CreateQatPunchPointBottomSheet
import com.smarthub.baseapplication.ui.dialog.qat.PunchPointResolveDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QatPunchPointFragment : BaseFragment(), PunchPointListener {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter : QatPunchPointAdapter
    private lateinit var binding: FragmentPunchPointQatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val viewlay = inflater.inflate(R.layout.fragment_punch_point_qat, container, false)
        binding = FragmentPunchPointQatBinding.bind(viewlay)
        adapter = QatPunchPointAdapter(this,ArrayList())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        setRecyclerView()
    }

    private fun setRecyclerView() {
        if (viewmodel.QatModelResponse?.hasActiveObservers() == true){
            viewmodel.QatModelResponse?.removeObservers(this)
        }
        viewmodel.QatModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.item!=null && it.data.item?.isNotEmpty()==true){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                Toast.makeText(requireContext(),"Data updated successfully", Toast.LENGTH_SHORT).show()
            }else if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.itemNew!=null && it.data.itemNew?.isNotEmpty()==true){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                it.data.item = it.data.itemNew
                Toast.makeText(requireContext(),"Data updated successfully", Toast.LENGTH_SHORT).show()
                AppLogger.log("size :${it.data.itemNew?.size}")
            }else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.recyclerViewOpen.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOpen.adapter = adapter
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpenQatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    override fun addPunchPoint(punchPointUpdate : PunchPointUpdate) {

        val listPunch = ArrayList<PunchPointUpdate>()
        listPunch.add(punchPointUpdate)
        val updateData = QatPunchPointModel(listPunch, AppController.getInstance().siteid,AppController.getInstance().ownerName)
        viewmodel.qatLaunchMain(updateData)
    }

    override fun editPunchPoint(item : Checkpoint,pos:Int) {
        val bottomSheetDialogFragment = CreateQatPunchPointBottomSheet(object : CreateQatPunchPointBottomSheet.LaunchQatBottomSheetListener{
            override fun onPunchPointCreated(data: QatPunchPointModel) {
                showLoader()
                viewmodel.qatLaunchMain(data)
            }
        },item.QATItem_id)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun punchPointClicked() {
        var dialog = PunchPointResolveDialog(requireContext())
        dialog.show()
    }
}