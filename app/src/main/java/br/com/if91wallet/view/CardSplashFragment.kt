package br.com.if91wallet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.if91wallet.R
import kotlinx.android.synthetic.main.fragment_card_splash.btn_add_card_splash

class CardSplashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_card_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        btn_add_card_splash.setOnClickListener {
            val action = CardSplashFragmentDirections.actionSplashToAddCard()
            findNavController().navigate(action)
        }
    }
}