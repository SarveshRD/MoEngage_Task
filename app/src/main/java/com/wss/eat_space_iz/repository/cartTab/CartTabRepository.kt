package com.wss.eat_space_iz.repository.cartTab

import com.wss.eat_space_iz.data.networkModels.cart.CartResponse
import com.wss.eat_space_iz.data.networkModels.favitems.FavItemsResponse
import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.ApiResult
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class CartTabRepository
@Inject constructor(private val apiCall: ApiService): BaseRepo(){

   /* suspend fun cart(
        onError: ((ApiResult<Any>) -> Unit)?,
    ): CartResponse? {
        return with(apiCall(executable = {
            apiCall.cart()
        })) {
            if (data == null)
                onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }
*/

}