package com.sha.iproperty.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sha.iproperty.data.model.PropertyItem
import com.sha.iproperty.data.model.SearchResult
import com.sha.iproperty.data.repository.SearchResultRepository


class SearchResultViewModel(private val app: Application) : AndroidViewModel(app) {
    private var searchResult: MutableLiveData<SearchResult> = MutableLiveData()
    private var searchResultMore: MutableLiveData<SearchResult> = MutableLiveData()
    private var propertyItemData: MutableLiveData<PropertyItem> = MutableLiveData()
    private var nextPageToken = "1"

    private val repository: SearchResultRepository by lazy {
        SearchResultRepository(app)
    }

    fun getSearchResult(): LiveData<SearchResult> {
        searchResult.value = repository.getSearchResult()
        searchResult.value?.nextPageToken?.let {
            nextPageToken = it
        }
        return searchResult
    }

    fun getMoreSearchResult(): LiveData<SearchResult> {
        searchResultMore.value = repository.getMoreSearchResult(nextPageToken)
        searchResultMore.value?.nextPageToken?.let {
            nextPageToken = it
        }
        return searchResultMore
    }

    fun getPropertyItem(): LiveData<PropertyItem> {
        return propertyItemData
    }

    fun setPropertyItem(propertyItem: PropertyItem) {
        propertyItemData.value = propertyItem
    }
}