package com.sha.iproperty.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sha.iproperty.R
import com.sha.iproperty.data.model.SearchResult
import com.sha.iproperty.view.adapter.SearchResultItemsAdapter
import com.sha.iproperty.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.search_result_fragment.*

class SearchResultFragment : Fragment() {

    private val viewModel: SearchResultViewModel by lazy {
        ViewModelProviders.of(this.requireActivity()).get(SearchResultViewModel::class.java)
    }

    private var isLoading: Boolean = false
    private lateinit var searchResult: SearchResult
    private lateinit var adapter: SearchResultItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_result_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSearchResult().observe(this, Observer { searchResult ->
            this.searchResult = searchResult
            adapter = SearchResultItemsAdapter()
            adapter.setSearchResultItems(searchResult.items)
            searchResultRecyclerView.adapter = adapter
            adapter.onItemClick = { propertyItem ->
                val fragment = PropertyItemDetailsFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.flFragment, fragment, PropertyItemDetailsFragment.tagFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                viewModel.setPropertyItem(propertyItem)
            }
            initScrollListener()
        })

        ivBackArrow.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        tvSearch.setOnClickListener{
            val fragment = SearchFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flFragment, fragment, SearchFragment.tagFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initScrollListener() {
        searchResultRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    val count = searchResult.items.size!! - 1
                    if (linearLayoutManager != null && (linearLayoutManager.findLastCompletelyVisibleItemPosition() == count)) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        adapter.addLoadMore()

        viewModel.getMoreSearchResult().observe(this, Observer { searchResult ->
            adapter.removeLoadMore()

            searchResultRecyclerView.post {
                adapter.addMoreSearchResultItems(searchResult.items)
            }
            isLoading = false
        })
    }

    companion object {
        const val tagFragment = "SearchResultFragment"
    }

}