package com.jcinstagram.login.data

import com.jcinstagram.login.data.network.LoginService

/**
 * Clase encargada de gestionar la fuente a donde se consultarán los datos; BBDD, APIs
 */
class LoginRepository {

    private val api = LoginService()

    suspend fun doLogin(user: String, password: String): Boolean {
        return api.doLogin(user, password)
    }
}