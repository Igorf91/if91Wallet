package br.com.if91wallet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.if91wallet.R
import br.com.if91wallet.repository.CardRepository
import br.com.if91wallet.util.validate
import br.com.if91wallet.vo.CardVo
import kotlinx.android.synthetic.main.fragment_card.card_number_edit_text
import kotlinx.android.synthetic.main.fragment_card.card_number_input_layout
import kotlinx.android.synthetic.main.fragment_card.cvv_edit_text
import kotlinx.android.synthetic.main.fragment_card.cvv_input_layout
import kotlinx.android.synthetic.main.fragment_card.expiration_edit_text
import kotlinx.android.synthetic.main.fragment_card.expiration_input_layout
import kotlinx.android.synthetic.main.fragment_card.name_edit_text
import kotlinx.android.synthetic.main.fragment_card.name_input_layout
import kotlinx.android.synthetic.main.fragment_card.save_card_btn

class CardFragment : Fragment() {

    private lateinit var cardRepository: CardRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardRepository = CardRepository()
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setupButton()
        setupValidateFields()
        val cardArgs: CardFragmentArgs by navArgs()
        cardArgs.card?.let {
            loadLayout(it)
        }
    }

    private fun setupButton() {
        save_card_btn.setOnClickListener {
            if (hasEmptyField())
                showMsg("Valores inválidos")
            else {
                val cardVo = CardVo(
                    cardNumber = card_number_edit_text.text.toString().toLong(),
                    name = name_edit_text.text.toString(),
                    expirationDate = expiration_edit_text.text.toString(),
                    cvv = cvv_edit_text.text.toString().toInt()
                )

                cardRepository.save(cardVo)
                showMsg("Salvo com sucesso")
                navigateToPayment()
            }
        }
    }

    private fun navigateToPayment() {
        val cardArgs: CardFragmentArgs by navArgs()
        val action = CardFragmentDirections.actionCardToPayment(cardArgs.user)
        findNavController().navigate(action)
    }

    private fun loadLayout(cardVo: CardVo) {
        card_number_edit_text.setText(cardVo.cardNumber.toString())
        name_edit_text.setText(cardVo.name)
        expiration_edit_text.setText(cardVo.expirationDate)
        cvv_edit_text.setText(cardVo.cvv.toString())
    }

    private fun hasEmptyField() = card_number_edit_text.text.isNullOrEmpty() ||
            name_edit_text.text.isNullOrEmpty() ||
            expiration_edit_text.text.isNullOrEmpty() ||
            cvv_edit_text.text.isNullOrEmpty()

    private fun setupValidateFields() {
        setupValidateCardNumber()
        setupValidateName()
        setupValidateExpiration()
        setupValidateCVV()
    }

    private fun setupValidateCardNumber() {
        validate(
            editText = card_number_edit_text,
            inputLayout = card_number_input_layout,
            errorMsg = "Cartão inválido",
            minSize = 16
        )
    }

    private fun setupValidateName() {
        validate(
            editText = name_edit_text,
            inputLayout = name_input_layout,
            errorMsg = "Nome inválido",
            minSize = 3
        )
    }

    private fun setupValidateExpiration() {
        validate(
            editText = expiration_edit_text,
            inputLayout = expiration_input_layout,
            errorMsg = "Data inválida",
            minSize = 4
        )
    }

    private fun setupValidateCVV() {
        validate(
            editText = cvv_edit_text,
            inputLayout = cvv_input_layout,
            errorMsg = "CVV inválido",
            minSize = 3
        )
    }

    private fun showMsg(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}