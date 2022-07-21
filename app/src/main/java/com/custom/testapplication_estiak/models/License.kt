package com.custom.testapplication_estiak.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class License {
    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("spdx_id")
    @Expose
    var spdxId: String? = null

    @SerializedName("url")
    @Expose
    var url: Any? = null

    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null
}