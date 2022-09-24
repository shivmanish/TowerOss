@file:Suppress("DEPRECATION")

package com.smarthub.baseapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.smarthub.baseapplication.activities.LoginActivity

import com.smarthub.baseapplication.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegFragment_2.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegFragment_2 : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_reg_2, container, false)
        val regFragment3 = RegFragment_3()
        view.findViewById<Button>(R.id.next2).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView,regFragment3)
                ?.commit()
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = view.findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                it.startActivity(intent)
            }
        }
//        val spinner: Spinner = view.findViewById<Spinner>(R.id.job_role)
// Create an ArrayAdapter using the string array and a default spinner layout

    }


}