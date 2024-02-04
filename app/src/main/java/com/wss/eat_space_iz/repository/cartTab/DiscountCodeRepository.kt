package com.wss.eat_space_iz.repository.cartTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.ApiResult
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class DiscountCodeRepository
@Inject constructor(private val apiCall: ApiService): BaseRepo(){

   /* suspend fun viewAdminOffer(
        onError: ((ApiResult<Any>) -> Unit)?,
    ): Any? {
        return with(apiCall(executable = {
            apiCall.topLink()
        })) {
            if (data == null)
                onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/

}