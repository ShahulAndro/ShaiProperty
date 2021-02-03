package com.sha.iproperty.view.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import com.sha.iproperty.R
import com.sha.iproperty.view.adapter.RecentSearchItemsAdapter
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecentSearchItemsAdapter()
        recentSearchRecyclerView.adapter = adapter

        ivBackArrow.setOnClickListener{
            hideKeyboard()
            requireActivity().supportFragmentManager.popBackStack()
        }

        showKeyboard()
        etSearchContacts.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    private fun performSearch() {
        val fragment = SearchResultFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment, SearchResultFragment.tagFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showKeyboard() {
        etSearchContacts.requestFocus()
        val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun hideKeyboard() {
        etSearchContacts.clearFocus()
        val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(etSearchContacts.windowToken, 0)
    }

    companion object {
        const val tagFragment = "SEARCH_FRAGMENT"
    }
}