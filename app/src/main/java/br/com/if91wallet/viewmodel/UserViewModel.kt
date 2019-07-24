package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import br.com.if91wallet.repository.UserRepository
import br.com.if91wallet.vo.UserVo
import java.lang.Exception

class UserViewModel (private val userRepository : UserRepository ) : ViewModel() {

    private var user: UserVo? = null
    fun requireUser() = user?: throw IllegalStateException("User null.")

    fun getUsers() = userRepository.usersList
    fun fetchUsersData() = userRepository.fetchUserList()
}