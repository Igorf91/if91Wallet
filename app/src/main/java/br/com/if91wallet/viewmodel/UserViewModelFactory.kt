package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.if91wallet.repository.UserRepository
import br.com.if91wallet.service.UserService
import br.com.if91wallet.util.RetrofitFactory

class UserViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val userService = RetrofitFactory().getRetrofit().create(UserService::class.java)
        return UserViewModel(UserRepository(userService)) as T
    }

}