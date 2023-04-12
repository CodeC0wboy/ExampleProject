package com.dnc.buendeal.features.search.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.dnc.buendeal.R
import com.dnc.buendeal.base.BaseFragment
import com.dnc.buendeal.base.BaseViewModel
import com.dnc.buendeal.core.base.recycler.RecyclerDelegationAdapter
import com.dnc.buendeal.core.core.ui.view.BottomItem
import com.dnc.buendeal.core.extentions.*
import com.dnc.buendeal.databinding.FragmentSearchBinding
import com.dnc.buendeal.databinding.PopupSearchScreenBinding
import com.dnc.buendeal.features.products.ui.list.ProductsInnerRecyclerAdapterDelegate
import com.dnc.buendeal.features.search.ui.list.*
import com.dnc.buendeal.features.search.ui.model.RecentSearchItemModel

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    R.layout.fragment_search,
    FragmentSearchBinding::bind
) {

    private val adapter by lazy {
        RecyclerDelegationAdapter(requireContext()).apply {
            addDelegate(
                RecentSearchAdapterDelegate(requireContext()) {
                    val selectedRecentSearch = it as RecentSearchItemModel
                    viewModel.performSearch(selectedRecentSearch.title)
                    binding.search.setText(selectedRecentSearch.title)
                    hideKeyboard()
                }
            )
            addDelegate(TitleSearchAdapterDelegate(requireContext()))
            addDelegate(
                CategorySearchAdapterDelegate(requireContext()) {
                    showToast(R.string.under_construction, Toast.LENGTH_SHORT)
                }
            )
        }
    }

    private val searchResultsAdapter by lazy {
        RecyclerDelegationAdapter(requireContext()).apply {
            addDelegate(
                ProductsInnerRecyclerAdapterDelegate(
                    requireContext(),
                    itemListener = {
                        showToast(R.string.under_construction)
                    },
                    heartBtnListener = {
                    },
                    compareBtnListener = {

                    }
                )
            )
            addDelegate(
                SearchResultsFilterSortAdapterDelegate(
                    requireContext(),
                    filterListener = {
//                        viewModel.navigate(
//                            SearchFragmentDirections.actionSearchFragmentToFiltersFragment()
//                        )
                    },
                    categoriesFilterListener = {
                        viewModel
                    }
                )
            )
        }
    }

    private val searchPopup by lazy {
        setUpPopupLayout().apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            isOutsideTouchable = false
            isFocusable = false
            inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
            setOnDismissListener {
                binding.imageViewSearchScreen.visible()
                binding.textTitleSearchScreen.visible()
                binding.textDescriptionSearchScreen.visible()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.requestFocus()

        showKeyboard(binding.search)

        // Fixes search edit text not responding to taps on API <= O_MR1
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1)
            binding.search.clearFocus()

        binding.searchScreenBackBtn.setOnClickListener {
            searchPopup.dismiss()
            viewModel.navigateBack()
        }

        binding.searchResultsList.adapter = searchResultsAdapter

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let { viewModel.search(it) }
                searchPopup.showAsDropDown(binding.dividerSearch)
                if (searchPopup.isShowing) {
                    hideRecentSearchesPlaceholder()
                    binding.searchResultsList.gone()
                    binding.goUpButton.gone()
                }
            }
        })

        binding.search.setOnEditorActionListener { textView, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.performSearch(textView.text)
                viewModel.saveSearch(textView.text.toString())
            }
            false
        }

        binding.goUpButton.setOnClickListener {
            binding.searchResultsList.smoothScrollToPosition(0)
        }

        setupUpButtonVisibilityListener(binding.searchResultsList, binding.goUpButton)
    }

    override fun observeLiveData() {
        super.observeLiveData()
        subscribe(viewModel.listSearchScreen) {
            adapter.setItems(it)
        }


        subscribe(viewModel.searchResults) {
            searchPopup.dismiss()
            hideRecentSearchesPlaceholder()
            binding.searchResultsList.visible()
            searchResultsAdapter.setItems(it)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                searchPopup.dismiss()
                viewModel.navigateBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun setUpPopupLayout(): PopupWindow {
        val binding: PopupSearchScreenBinding =
            PopupSearchScreenBinding.inflate(LayoutInflater.from(context), null, false)

        binding.popupSearchRecycler.addItemDecoration(SearchScreenItemOffsetDecorator())
        binding.popupSearchRecycler.adapter = adapter

        return PopupWindow(
            binding.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
    }

    private fun hideRecentSearchesPlaceholder() {
        binding.imageViewSearchScreen.gone()
        binding.textTitleSearchScreen.gone()
        binding.textDescriptionSearchScreen.gone()
    }
}
