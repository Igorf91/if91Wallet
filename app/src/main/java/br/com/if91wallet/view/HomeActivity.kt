package br.com.if91wallet.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.if91wallet.R
import br.com.if91wallet.adapter.UserAdapter
import br.com.if91wallet.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.appBar
import kotlinx.android.synthetic.main.activity_home.search_textinput
import kotlinx.android.synthetic.main.activity_home.users_rv

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private val adapter = UserAdapter()

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
        setupRecyclerView()
        setupSearchTextView()
    }

    private fun setupRecyclerView() {
        users_rv.adapter = adapter
        users_rv.layoutManager = LinearLayoutManager(this)
        users_rv.isNestedScrollingEnabled = false

        homeViewModel.getUsers().observe(this, Observer {
            adapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupSearchTextView() {
        search_textinput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //empty
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //empty
            }

            override fun onTextChanged(newText: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(newText.toString())
                hideAppBar()
            }
        })

        search_textinput.onFocusChangeListener = (object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                hideAppBar()
            }
        })
    }

    private fun hideAppBar() {
        appBar.setExpanded(false, true)
    }

    private fun setupCall() {
        homeViewModel.fetchUsersData()
    }
}
