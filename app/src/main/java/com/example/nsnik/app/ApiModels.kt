package com.example.nsnik.app

import com.google.gson.annotations.SerializedName

data class DomainModel(@SerializedName("domain") val domainName: String)

data class SearchResultModel(@SerializedName("domains") val domains: List<DomainModel>)
