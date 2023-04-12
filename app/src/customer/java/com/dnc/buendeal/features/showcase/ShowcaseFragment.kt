package com.dnc.buendeal.features.showcase

import android.os.Bundle
import android.view.View
import com.dnc.buendeal.R
import com.dnc.buendeal.base.BaseFragment
import com.dnc.buendeal.base.EmptyViewModel
import com.dnc.buendeal.core.extentions.enable
import com.dnc.buendeal.databinding.FragmentShowcaseBinding

class ShowcaseFragment : BaseFragment<EmptyViewModel,
    FragmentShowcaseBinding>(
    R.layout.fragment_showcase,
    FragmentShowcaseBinding::bind
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardChevronLeft.setOnClickListener { }
        binding.cardChevronRight.setOnClickListener { }
        binding.cardShare.setOnClickListener { }
        binding.cardHeart.setOnClickListener { }
        binding.cardElements.setOnClickListener { }
        binding.threeStateBtnHeart.setOnClickListener { }
        binding.threeStateBtnCompare.setOnClickListener { }

        binding.textAreaInputLayout1.setError("Maximum number of characters is 500")
        binding.textInputLayoutPsw1.setError("Minimum password length 6 characters")

        binding.dropdownMenu3.enable(false, binding.dropdownMenu3)
    }

    override fun onResume() {
        super.onResume()
        binding.dropdownMenu1.setupTestAdapter()
        binding.dropdownMenu2.setupTestAdapter()
    }
}
