package io.ozupek.currencycalculator.network.responsemodel

import com.google.gson.annotations.SerializedName
import java.security.acl.Owner

/**
 * Created by VNGRS on 1.01.2019.
 */
data class RepositoryResponse(
    @SerializedName("total_count") var totalCount : Int?,
    var incomplete_results : Boolean?,
    var items : ArrayList<Repository>
)

data class Repository(
    var id : Int,
    var node_id : String,
    var name : String,
    @SerializedName("full_name") var fullName : String,
    var owner : RepositoryOwner,
    var language : String
)

data class RepositoryOwner(
    var login : String,
    @SerializedName("avatar_url") var avatarUrl : String?
)