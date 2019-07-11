package br.com.if91wallet.service

import br.com.if91wallet.vo.UserVo
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("users")
    fun getUsers(): Call<List<UserVo>>
}