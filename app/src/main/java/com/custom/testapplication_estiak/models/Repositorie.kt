package com.custom.testapplication_estiak.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Repositorie {
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null

    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null

    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
}