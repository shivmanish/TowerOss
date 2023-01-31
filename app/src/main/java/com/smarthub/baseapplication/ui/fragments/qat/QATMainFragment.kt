package com.smarthub.baseapplication.ui.fragments.qat
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.adapter.qat.OpenQatAdapter
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QATMainLaunch
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.qat.adapter.QATListAdapter
import com.smarthub.baseapplication.ui.fragments.qat.adapter.QatMainAdapterListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class QATMainFragment (var id:String): BaseFragment(), QatMainAdapterListener {
    lateinit var viewmodel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    lateinit var  adapter:QATListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewlay = inflater.inflate(R.layout.fragment_open_qat, container, false)
        setRecyclerView(viewlay)
        return viewlay
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        viewmodel.qatMainRequestAll(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewmodel.QatModelResponse?.hasActiveObservers() == true){
            viewmodel.QatModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.QatModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.item!=null && it.data.item?.isNotEmpty()==true){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                adapter.setData(it.data.item!![0].QATMainLaunch)
                AppLogger.log("size :${it.data.item?.size}")
            }else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun setRecyclerView(view: View) {
        adapter = QATListAdapter(requireContext(),this,id)
        recyclerView = view.findViewById(R.id.recyclerView_open)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun clickedItem(data: QATMainLaunch?, Id: String, index: Int) {
        QatActivity.data = data?.Category
        Intent(requireContext(),QatActivity::class.java).apply { startActivity(this) }
    }
}