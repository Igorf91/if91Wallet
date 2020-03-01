package br.com.if91wallet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.if91wallet.R
import br.com.if91wallet.adapter.UserAdapter
import br.com.if91wallet.repository.CardRepository
import br.com.if91wallet.util.SimpleTextWatcher
import br.com.if91wallet.viewmodel.UserViewModel
import br.com.if91wallet.viewmodel.UserViewModelFactory
import br.com.if91wallet.vo.UserVo
import kotlinx.android.synthetic.main.fragment_home.appBar
import kotlinx.android.synthetic.main.fragment_home.search_textinput
import kotlinx.android.synthetic.main.fragment_home.users_rv

class HomeFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userViewModel = ViewModelProviders
            .of(this, UserViewModelFactory())
            .get(UserViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupCall()
    }

    private fun setupView() {
        adapter = UserAdapter {
            callAction(it)
        }
        setupRecyclerView()
        setupSearchTextView()
    }

    private fun setupRecyclerView() {
        users_rv.adapter = adapter
        users_rv.layoutManager = LinearLayoutManager(requireContext())
        users_rv.isNestedScrollingEnabled = false

        userViewModel.getUsers().observe(this, Observer {
            adapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupSearchTextView() {
        search_textinput.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(text.toString())
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
        userViewModel.fetchUsersData()
    }

    private fun callAction(userVo: UserVo) {
        val action =
            if(CardRepository().hasCard())
                HomeFragmentDirections.actionHomeToPayment(userVo)
            else
                HomeFragmentDirections.actionHomeToAddCardSplash(userVo)

        findNavController().navigate(action)
    }
}
