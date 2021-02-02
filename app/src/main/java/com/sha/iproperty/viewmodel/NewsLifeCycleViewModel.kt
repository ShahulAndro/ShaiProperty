package com.sha.iproperty.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sha.iproperty.data.model.NewsLifeCycle
import com.sha.iproperty.data.repository.NewsLifeStyleRepository


class NewsLifeCycleViewModel(private val app: Application) : AndroidViewModel(app) {
    private var news: MutableLiveData<List<NewsLifeCycle>> = MutableLiveData()
    private var lifeStyles: MutableLiveData<List<NewsLifeCycle>> = MutableLiveData()

    private val repository: NewsLifeStyleRepository by lazy {
        NewsLifeStyleRepository(app)
    }

    fun getNews(): LiveData<List<NewsLifeCycle>> {
        news.value = repository.getNews()
        return news
    }

    fun getLifeStyles(): LiveData<List<NewsLifeCycle>> {
        lifeStyles.value = repository.getLifeStyles()
        return lifeStyles
    }
}