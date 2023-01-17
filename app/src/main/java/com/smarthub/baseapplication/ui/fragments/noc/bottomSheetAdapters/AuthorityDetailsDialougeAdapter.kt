package com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocAuthorityDetailsDialougeLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.AuthorityDetails
import com.smarthub.baseapplication.utils.DropDowns

class AuthorityDetailsDialougeAdapter(
    contentLayoutId: Int,
    var authorityDetails: AuthorityDetails?
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: NocAuthorityDetailsDialougeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }

        AppPreferences.getInstance().setDropDown(
            binding.authorityPrefferedLanguage,
            DropDowns.ApplicationInitialPreferredLaungauge.name,
            authorityDetails?.ApplicationInitialPreferredLaungauge
        )

        binding.authorityName.setText(authorityDetails?.Name)
        binding.authorityContactPerson.setText(authorityDetails?.ContactPerson)
        binding.authorityContactNumber.setText(authorityDetails?.ContactNumber)
        binding.authorityEmail.setText(authorityDetails?.EmailId)
        binding.authorityLandmark.setText(authorityDetails?.LandMark)
        binding.update.setOnClickListener {
            authorityDetails.let {
                it?.Name = binding.authorityName.text.toString()
                it?.ContactPerson = binding.authorityContactPerson.text.toString()
                it?.ContactNumber = binding.authorityContactNumber.text.toString()
                it?.EmailId = binding.authorityEmail.text.toString()
                it?.LandMark = binding.authorityLandmark.text.toString()



            }


        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NocAuthorityDetailsDialougeLayoutBinding.inflate(inflater)
        return binding.root
    }
}