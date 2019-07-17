package br.com.if91wallet.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.if91wallet.R
import br.com.if91wallet.adapter.UserAdapter
import br.com.if91wallet.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.appBar
import kotlinx.android.synthetic.main.fragment_home.search_textinput
import kotlinx.android.synthetic.main.fragment_home.users_rv

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val adapter = UserAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders
            .of(this)
            .get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupCall()
    }

    private fun setupView() {
        setupRecyclerView()
        setupSearchTextView()
    }

    private fun setupRecyclerView() {
        users_rv.adapter = adapter
        users_rv.layoutManager = LinearLayoutManager(requireContext())
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

        search_textinput.onFocusChangeListener =
                (View.OnFocusChangeListener { _, _ -> hideAppBar() })
    }

    private fun hideAppBar() {
        appBar.setExpanded(false, true)
    }

    private fun setupCall() {
        homeViewModel.fetchUsersData()
    }
}
