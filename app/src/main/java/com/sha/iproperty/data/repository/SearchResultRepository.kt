package com.sha.iproperty.data.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sha.iproperty.data.model.SearchResult
import com.sha.iproperty.data.utils.Utils

class SearchResultRepository (val app: Application) {

    fun getSearchResult() : SearchResult {
        val jsonString = Utils.jsonFromAssets(app.applicationContext, "search-results-page.json")
        val type = object : TypeToken<SearchResult>() {}.type
        return Gson().fromJson(jsonString, type)
    }

    fun getMoreSearchResult(pageNumber: String) : SearchResult {
        val jsonString = Utils.jsonFromAssets(app.applicationContext, "search-results-page${pageNumber}.json")
        val type = object : TypeToken<SearchResult>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}