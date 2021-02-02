package com.sha.iproperty.data.model

data class SearchResult(
    var totalCount: Int?,
    var nextPageToken: String?,
    var items: List<PropertyItem>
)