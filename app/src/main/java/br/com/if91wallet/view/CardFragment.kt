package br.com.if91wallet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.if91wallet.R
import kotlinx.android.synthetic.main.add_card_splash_layout.btn_add_card_splash
import kotlinx.android.synthetic.main.fragment_card.add_card_splash

class CardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        setupCardSplashScreen()
    }

    private fun setupCardSplashScreen() {
        add_card_splash.visibility = View.VISIBLE

        btn_add_card_splash.setOnClickListener {
            add_card_splash.visibility = View.GONE
        }
    }
}