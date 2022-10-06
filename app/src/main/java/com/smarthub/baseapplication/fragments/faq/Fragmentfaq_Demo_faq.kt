package com.smarthub.baseapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.RecyclerAdapter_faq

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragmentfaq_Demo_faq.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmentfaq_Demo_faq : Fragment() {
    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler_view = view.findViewById<RecyclerView>(R.id.faq_recycler_view)
        recycler_view.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=RecyclerAdapter_faq()
        }
        val scrollView = view.findViewById<ScrollView>(R.id.scrollfaq)
        scrollView.scrollTo(0, scrollView.getBottom());

    }
}