package br.com.if91wallet.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.if91wallet.R
import br.com.if91wallet.adapter.UserAdapter
import br.com.if91wallet.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.users_rv

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProviders
            .of(this)
            .get(HomeViewModel::class.java)

        setupView()
        setupCall()
    }

    private fun setupView() {
        val adapter = UserAdapter()

        users_rv.adapter = adapter
        users_rv.layoutManager = LinearLayoutManager(this)
        users_rv.isNestedScrollingEnabled = false

        homeViewModel.getUsers().observe(this, Observer {
            adapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupCall() {
        homeViewModel.fetchUsersData()
    }
}
