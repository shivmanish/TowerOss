package com.smarthub.baseapplication.ui.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.smarthub.baseapplication.databinding.SearchFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.search.SearchListItem
import com.smarthub.baseapplication.ui.dialog.adapter.SearchChipAdapter
import com.smarthub.baseapplication.ui.dialog.adapter.SearchResultAdapter
import com.smarthub.baseapplication.viewmodels.BasicInfoDetailViewModel

class SearchFragment : Fragment(), SearchResultAdapter.SearchResultListener {

    private lateinit var binding: SearchFragmentBinding
    lateinit var siteViewModel : BasicInfoDetailViewModel
    lateinit var searchResultAdapter : SearchResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        siteViewModel = ViewModelProvider(this)[BasicInfoDetailViewModel::class.java]

        return binding.root
    }

    var isDataFetched = true
    var fetchedData = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchChipAdapter = SearchChipAdapter(requireContext())
        binding.chipLayout.adapter = searchChipAdapter
        val chipsLayoutManager =
            ChipsLayoutManager.newBuilder(requireContext()) //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL
                .setChildGravity(Gravity.TOP) //whether RecyclerView can scroll. TRUE by default
                .setScrollingEnabled(true) //set maximum views count in a particular row
                .setMaxViewsInRow(5) //set gravity resolver where you can determine gravity for item in position.
                //This method have priority over previous one
                .setGravityResolver { Gravity.CENTER } //you are able to break row due to your conditions. Row breaker should return true for that views
                .setRowBreaker { position -> position == 6 || position == 11 || position == 2 } //a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
                .setOrientation(ChipsLayoutManager.HORIZONTAL) // row strategy for views in completed row, could be STRATEGY_DEFAULT, STRATEGY_FILL_VIEW,
                //STRATEGY_FILL_SPACE or STRATEGY_CENTER
                .setRowStrategy(ChipsLayoutManager.STRATEGY_FILL_SPACE) // whether strategy is applied to last row. FALSE by default
                .withLastRow(true)
                .build()
        binding.chipLayout.layoutManager = chipsLayoutManager

        searchResultAdapter = SearchResultAdapter(requireContext(),this@SearchFragment)
        binding.searchResult.adapter = searchResultAdapter

        siteViewModel.siteSearchResponse?.observe(viewLifecycleOwner){
            isDataFetched = true
            if (it!=null){
                if (it.status == Resource.Status.SUCCESS){
                    it.data?.let { it1 -> searchResultAdapter.updateList(it1) }
                    if (fetchedData.isNotEmpty())
                        siteViewModel.fetchSiteSearchData(fetchedData)
                    fetchedData = ""
                    Toast.makeText(requireContext(),"data fetched",Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(),"error :${it.message}",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(),"error in fetching data",Toast.LENGTH_SHORT).show()
            }
        }
        binding.searchCardView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                fetchedData = binding.searchCardView.text.toString()
                if (fetchedData.isNotEmpty() && isDataFetched) {
                    isDataFetched = false
                    siteViewModel.fetchSiteSearchData(fetchedData)
                    fetchedData = ""
                }
                else if(fetchedData.isEmpty()){
                    Toast.makeText(requireContext(),"Input can't be empty",Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.viewOnIbo.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSiteDetailFragment())
        }
    }

    override fun onItemSelected(item: SearchListItem?) {
        if (item!=null){

        }
    }

}