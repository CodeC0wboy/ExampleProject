package com.dnc.buendeal.features.mainscreen.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dnc.buendeal.R
import com.dnc.buendeal.base.BaseFragment
import com.dnc.buendeal.core.base.recycler.RecyclerDelegationAdapter
import com.dnc.buendeal.core.core.ui.list.BannerAdapterDelegate
import com.dnc.buendeal.core.core.ui.list.InnerRecyclerDelegate
import com.dnc.buendeal.core.core.ui.list.SearchAdapterDelegate
import com.dnc.buendeal.core.extentions.setupUpButtonVisibilityListener
import com.dnc.buendeal.core.extentions.showToast
import com.dnc.buendeal.core.extentions.subscribe
import com.dnc.buendeal.databinding.FragmentMainScreenBinding
import com.dnc.buendeal.features.mainscreen.ui.list.ItemOffsetDecorator
import com.dnc.buendeal.features.mainscreen.ui.list.TitleSectionAdapterDelegate
import com.dnc.buendeal.features.products.ui.list.ProductsInnerRecyclerAdapterDelegate

class MainScreenFragment :
    BaseFragment<MainScreenViewModel, FragmentMainScreenBinding>(
        R.layout.fragment_main_screen,
        FragmentMainScreenBinding::bind
    ) {

    private val adapter by lazy {
        RecyclerDelegationAdapter(requireContext()).apply {

            addDelegate(
                InnerRecyclerDelegate(requireContext(), itemListener = {
                    //Navigate to product info fragment logic
                }, heartBtnListener = {

                }, compareBtnListener = {
                    viewModel.addProductToCompareList(it.id)
                })
            )
            addDelegate(
                TitleSectionAdapterDelegate(requireContext()) {
                    showToast(R.string.under_construction, Toast.LENGTH_SHORT)
                }
            )
            addDelegate(
                ProductsInnerRecyclerAdapterDelegate(
                    requireContext(),
                    itemListener = {
                        //Navigate to product info fragment logic
                    },
                    heartBtnListener = {

                    },
                    compareBtnListener = {

                    }
                )
            )

            addDelegate(
                SearchAdapterDelegate(requireContext()) {
                    viewModel.navigate(MainScreenFragmentDirections.actionMainScreenFragmentToSearchFragment())
                }
            )
            addDelegate(BannerAdapterDelegate(requireContext()))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerMainScreen.addItemDecoration(ItemOffsetDecorator())
        binding.recyclerMainScreen.adapter = adapter

        binding.btnUp.setOnClickListener {
            binding.recyclerMainScreen.smoothScrollToPosition(0)
        }

        setupUpButtonVisibilityListener(binding.recyclerMainScreen, binding.btnUp)
    }

    override fun observeLiveData() {
        super.observeLiveData()
        subscribe(viewModel.listProducts) {
            adapter.setItems(it)
        }
    }
}
