package com.smarthub.baseapplication.ui.fragments.sitedetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseBinding
import com.smarthub.baseapplication.ui.adapter.AddMoreCustomerListAdapter
import com.smarthub.baseapplication.ui.adapter.AddMoreSiteLeaseAdaptere
import com.smarthub.baseapplication.ui.fragments.sitedetail.viewmodel.SiteleaseViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SiteLeaseF.newInstance] factory method to
 * create an instance of this fragment.
 */
class SiteLeaseF : Fragment() {
    private var _binding: FragmentSiteLeaseBinding? = null
    private lateinit var siteleaseViewModel: SiteleaseViewModel
    private val binding get() = _binding!!
    var list : ArrayList<Any> = ArrayList()

    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        siteleaseViewModel = ViewModelProvider(requireActivity())[SiteleaseViewModel::class.java]

        _binding = FragmentSiteLeaseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        list.add("spiner_item")
        list.add("text_filed_items")

        layoutManager.orientation = LinearLayoutManager.VERTICAL
        _binding?.rvSiteLease.apply {
            this?.layoutManager = layoutManager
            this?.adapter = AddMoreSiteLeaseAdaptere(list)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}