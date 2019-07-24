package br.com.if91wallet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.if91wallet.service.UserService
import br.com.if91wallet.util.RetrofitFactory
import br.com.if91wallet.util.callback
import br.com.if91wallet.vo.UserVo

class UserRepository (private val userService: UserService) {

    private var _usersList = MutableLiveData<List<UserVo>>()
    val usersList: LiveData<List<UserVo>> = _usersList

    companion object {
        private const val TAG = "UserRepository"
    }

    fun fetchUserList() {
        userService.getUsers().enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { _usersList.postValue(it) }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })
    }
}