package com.wss.eat_space_iz.repository.loginSignUpTab

import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class SignUpRepository
@Inject constructor(private val apiCall: ApiService): BaseRepo() {
}