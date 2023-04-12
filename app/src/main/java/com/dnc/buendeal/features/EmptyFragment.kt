package com.dnc.buendeal.features

import com.dnc.buendeal.R
import com.dnc.buendeal.base.BaseFragment
import com.dnc.buendeal.base.EmptyViewModel
import com.dnc.buendeal.databinding.FragmentEmptyBinding

class EmptyFragment : BaseFragment<EmptyViewModel, FragmentEmptyBinding>(
    R.layout.fragment_empty,
    FragmentEmptyBinding::bind
)
