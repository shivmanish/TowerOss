package com.smarthub.baseapplication.ui.fragments.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchIdFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.search.*
import com.smarthub.baseapplication.ui.alert.AlertStatusFragment
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger

class SearchIdFragment : BaseFragment(), SearchResultAdapter.SearchResultListener, SearchCategoryAdapter.SearchCategoryListener, SearchChipAdapter.SearchChipAdapterListner {

    var fetchedData = ""
    var isDataFetched = true

    companion object{
        var item = SearchListItem("Site Id","")
    }

    var selectedCategory: String="siteID"
    private lateinit var binding: SearchIdFragmentBinding
    lateinit var homeViewModel : AlertViewModel
    lateinit var searchResultAdapter : SearchResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SearchIdFragmentBinding.inflate(layoutInflater)
        homeViewModel = ViewModelProvider(this)[AlertViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disableButton()

        searchResultAdapter = SearchResultAdapter(requireContext(),this@SearchIdFragment)
        binding.searchResult.adapter = searchResultAdapter
        binding.searchResult.setHasFixedSize(true)
        if (homeViewModel.siteSearchResponse?.hasActiveObservers() == true){
            homeViewModel.siteSearchResponse?.removeObservers(viewLifecycleOwner)
        }

        homeViewModel.siteSearchResponse?.observe(viewLifecycleOwner){
            if (binding.loadingProgress.visibility ==View.VISIBLE)
                binding.loadingProgress.visibility = View.INVISIBLE
            binding.searchCardView.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.search,0)
            isDataFetched = true
            if (it!=null){
                if (it.status == Resource.Status.SUCCESS){

                    if (item!=null && (item?.name==fetchedData|| item?.id==fetchedData || fetchedData.isEmpty())) {

                    }else {
                        it.data?.let { it1 -> searchResultAdapter.updateList(it1) }
                        if (fetchedData.isNotEmpty()) {
                            if (selectedCategory.isNotEmpty()){
                                homeViewModel.fetchSiteSearchData(selectedCategory,fetchedData)
                            }else {
                                homeViewModel.fetchSiteSearchData("name",fetchedData)
                            }
                        }
                        fetchedData = ""
                    }
//                    Toast.makeText(requireContext(),"data fetched",Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(),"error :${it.message}",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(requireContext(),"error in fetching data",Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchCardView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                fetchedData = binding.searchCardView.text.toString()
                if (fetchedData.isNotEmpty() && fetchedData.length>=3 && isDataFetched) {
                    AppLogger.log("fetchedData :$fetchedData,item?.Siteid:" +
                            "${item?.name},item?.id:${item?.id}")
                    if (item!=null && (item?.name==fetchedData|| item?.id==fetchedData)) {
                        AppLogger.log("return : $fetchedData")
                        return
                    }
                    isDataFetched = false
                    if (binding.loadingProgress.visibility != View.VISIBLE)
                        binding.loadingProgress.visibility = View.VISIBLE
                    binding.searchCardView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)

                    if (selectedCategory.isNotEmpty()){
                        homeViewModel.fetchSiteSearchData(selectedCategory,fetchedData)
                    }else {
                        homeViewModel.fetchSiteSearchData("name",fetchedData)
                    }
//                    fetchedData = ""
                }
                else if(fetchedData.isEmpty()){
                    Toast.makeText(requireContext(),"Input can't be empty",Toast.LENGTH_SHORT).show()
                    disableButton()
                }

            }
        })

    }

    override fun onSearchItemSelected(i: Any?) {

        if (i!=null && i is SearchListItem){
            item = SearchListItem(i.name,i.id)
            binding.searchCardView.text = item.name?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        }
        else if (i!=null && i is SearchSiteIdItem){
            item = SearchListItem(i.siteID,item.id)
            binding.searchCardView.text = i.siteID?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        }
        else if (i!=null && i is SearchSiteNameItem){
            item = SearchListItem(i.siteName,i.id)
            binding.searchCardView.text = i.siteName?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        }
        else if (i!=null && i is SearchAliasNameItem){
            item = SearchListItem(i.aliasName,i.id)
            binding.searchCardView.text = i.aliasName?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        }
        else if (i!=null && i is SearchSiteOpcoName){
            item = SearchListItem(i.OpcoName,item.id)
            binding.searchCardView.text = i.OpcoName?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        }
        else if (i!=null && i is SearchSiteOpcoSiteId){
            item = SearchListItem(i.OpcoSiteID,item.id)
            binding.searchCardView.text = i.OpcoSiteID?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        }
        else {
            disableButton()
        }
        homeViewModel.updateSearchListItem(item)
        AlertStatusFragment.itemNew = item
        AppLogger.log("new name :${item.name}")
        findNavController().popBackStack()
    }


    fun disableButton() {
        binding.lnButtonLayout.visibility=View.GONE

        binding.viewOnMap.alpha = 0.2f

        binding.viewOnMap.isEnabled = false

    }

    private fun enableButton() {
        binding.lnButtonLayout.visibility=View.VISIBLE

        binding.viewOnMap.alpha = 1.0f

        binding.viewOnMap.isEnabled = true

    }

    override fun selectedCategory(item: String) {
        this.selectedCategory = item
        Log.d("status", "selectedCategory:$item")
    }
    override fun clickedSearchHistoryItem(historyItem: SearchListItem?) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSiteDetailFragment(historyItem?.id.toString()))
    }

}