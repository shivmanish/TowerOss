package com.smarthub.baseapplication.ui.fragments.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.search.SearchListItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.mapui.MapActivity
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SearchFragment : BaseFragment(), SearchResultAdapter.SearchResultListener, SearchCategoryAdapter.SearchCategoryListener, SearchChipAdapter.SearchChipAdapterListner {

    var fetchedData = ""
    var isDataFetched = true
    var item: SearchListItem?=null
    var searchHistoryList=SearchList()
    var selectedCategory: String="name"
    private lateinit var binding: SearchFragmentBinding
    lateinit var homeViewModel : HomeViewModel
    lateinit var searchResultAdapter : SearchResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        searchHistoryList=AppPreferences.getInstance().searchList
        return binding.root
    }

    fun clearResult(){
        searchResultAdapter.list.clear()
        searchResultAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disableButton()
        val searchChipAdapter = SearchChipAdapter(requireContext(),this@SearchFragment)
        binding.chipLayout.adapter = searchChipAdapter
        searchChipAdapter.updateList(searchHistoryList)
        val chipsLayoutManager = ChipsLayoutManager.newBuilder(requireContext()) //set vertical gravity for all items in a row. Default = Gravity.CENTER_VERTICAL
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
        binding.chipLayout.setHasFixedSize(true)

        val searchCategoryAdapter = SearchCategoryAdapter(requireContext(),this@SearchFragment)
        binding.categoryList.adapter = searchCategoryAdapter
        binding.categoryList.setHasFixedSize(true)

        searchResultAdapter = SearchResultAdapter(requireContext(),this@SearchFragment)
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

                    if (item!=null && (item?.siteID==fetchedData|| item?.id==fetchedData || fetchedData.isEmpty())) {

                    }else {
                        it.data?.let { it1 -> searchResultAdapter.updateList(it1) }
                        if (fetchedData.isNotEmpty())
                            homeViewModel.fetchSiteSearchData("siteID",fetchedData)
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
                if (fetchedData.isNotEmpty() && isDataFetched) {
                    AppLogger.log("fetchedData :$fetchedData,item?.Siteid:" +
                            "${item?.siteID},item?.id:${item?.id}")
                    if (item!=null && (item?.siteID==fetchedData|| item?.id==fetchedData)) {
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
                        homeViewModel.fetchSiteSearchData("siteID",fetchedData)
                    }
//                    fetchedData = ""
                }
                else if(fetchedData.isEmpty()){
                    Toast.makeText(requireContext(),"Input can't be empty",Toast.LENGTH_SHORT).show()
                    disableButton()
                }
            }
        })

        binding.viewOnIbo.setOnClickListener {
            if(searchHistoryList.contains(SearchListItem(item?.siteID,item?.id))){
              searchHistoryList.remove(SearchListItem(item?.siteID,item?.id))}
            searchHistoryList.add(0,SearchListItem(item?.siteID,item?.id))
            if(searchHistoryList.size>=10)
                searchHistoryList.subList(10,searchHistoryList.size).clear()
            AppPreferences.getInstance().saveSearchList(searchHistoryList)
            searchChipAdapter.updateList(searchHistoryList)
//            var searchHistory1=Gson().fromJson(searchhistoryJson,SearchList::class.java)

            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSiteDetailFragment("${item?.id}"))

        }
        if (homeViewModel.siteInfoResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel.siteInfoResponse?.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.status == Resource.Status.SUCCESS) {
                    if (homeViewModel.siteInfoResponse?.hasActiveObservers() == true)
                        homeViewModel.siteInfoResponse?.removeObservers(viewLifecycleOwner)
                    AppLogger.log("Site data fetched")
                    Toast.makeText(requireContext(),"Site data fetched",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSiteDetailFragment("${it.data?.item!![0].id}"))
                }else{
                    Toast.makeText(requireContext(),"Request failed",Toast.LENGTH_SHORT).show()
                    AppLogger.log("Request failed e :${it.message}")
                }
            } else {
                AppLogger.log("Something went wrong")
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
        binding.viewOnMap.setOnClickListener {
            val intent = Intent(requireContext(), MapActivity::class.java)
            startActivity(intent)

        }

        binding.notificationLayout.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSiteBoardToNotificationsFragment2())
            Log.d("notification Nvigate","navigated from home to navigation fragment")
        }

//        fun navigatefromhistory(){
//            historyItem=
//        }

    }

    override fun onSearchItemSelected(item: SearchListItem?) {
        this.item = item
        if (item!=null){
            binding.searchCardView.text = if (item.siteID!=null) item.siteID.toEditable() else item.id?.toEditable()
            binding.searchCardView.setSelection(binding.searchCardView.text.toString().length)
            enableButton()
        } else {
            disableButton()
        }
    }

//   fun returnSiteIdTonavigate(historyItem: SearchListItem?): SearchListItem? {
//       return historyItem
//   }
    fun disableButton() {
        binding.viewOnIbo.alpha = 0.2f
        binding.viewOnMap.alpha = 0.2f
        binding.viewOnIbo.isEnabled = false
        binding.viewOnMap.isEnabled = false

    }

    private fun enableButton() {
        binding.viewOnIbo.alpha = 1.0f
        binding.viewOnMap.alpha = 1.0f
        binding.viewOnIbo.isEnabled = true
        binding.viewOnMap.isEnabled = true

    }

    override fun selectedCategory(item: String) {
        this.selectedCategory = item
        Log.d("status", "selectedCategory:$item")
    }
//    var historyItemSite: SearchListItem?=null
    override fun clickedSearchHistoryItem(historyItem: SearchListItem?) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSiteDetailFragment("448"))
    }

}