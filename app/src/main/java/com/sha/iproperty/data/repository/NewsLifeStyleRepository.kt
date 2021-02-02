package com.sha.iproperty.data.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sha.iproperty.data.model.NewsLifeCycle
import com.sha.iproperty.data.utils.Utils
import java.lang.reflect.Type

class NewsLifeStyleRepository (val app: Application) {

    fun getNews(): List<NewsLifeCycle> {
        val jsonString = Utils.jsonFromAssets(app.applicationContext, "news.json")
        val newsList: Type = object : TypeToken<List<NewsLifeCycle>>() {}.type
        return Gson().fromJson<List<NewsLifeCycle>>(jsonString, newsList)
    }

    fun getLifeStyles(): List<NewsLifeCycle> {
        val jsonString = Utils.jsonFromAssets(app.applicationContext, "lifestyles.json")
        val lifeStyleList: Type = object : TypeToken<List<NewsLifeCycle>>() {}.type
        return Gson().fromJson<List<NewsLifeCycle>>(jsonString, lifeStyleList)
    }
}