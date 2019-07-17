package br.com.if91wallet.viewmodel

import androidx.lifecycle.ViewModel
import br.com.if91wallet.repository.UserRepository

class HomeViewModel (private val userRepository : UserRepository ) : ViewModel() {

    fun getUsers() = userRepository.usersList
    fun fetchUsersData() = userRepository.fetchUserList()
}