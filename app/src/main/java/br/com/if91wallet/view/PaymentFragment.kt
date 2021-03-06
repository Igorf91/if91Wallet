package br.com.if91wallet.view

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.if91wallet.R
import br.com.if91wallet.util.SimpleTextWatcher
import br.com.if91wallet.util.getLoaderPlaceholder
import br.com.if91wallet.viewmodel.TransactionViewModel
import br.com.if91wallet.viewmodel.TransactionViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_payment.payment_button
import kotlinx.android.synthetic.main.fragment_payment.payment_card_description
import kotlinx.android.synthetic.main.fragment_payment.payment_currency
import kotlinx.android.synthetic.main.fragment_payment.payment_img
import kotlinx.android.synthetic.main.fragment_payment.payment_nickname
import kotlinx.android.synthetic.main.fragment_payment.payment_value_edit_text

class PaymentFragment : Fragment() {

    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val paymentArgs: PaymentFragmentArgs by navArgs()

        transactionViewModel = ViewModelProvider(this, TransactionViewModelFactory(paymentArgs.user))
                .get(TransactionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupLayout()
        setupListeners()
    }

    private fun setupLayout() {
        Glide
            .with(this)
            .load(transactionViewModel.user.img)
            .placeholder(getLoaderPlaceholder(requireContext()))
            .apply(RequestOptions.circleCropTransform())
            .into(payment_img)

        payment_nickname.text = transactionViewModel.user.username
        val last4Digits =
            SpannableString(getString(R.string.card_description, transactionViewModel.getCardCvv()))
        last4Digits.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.colorBrand)),
            last4Digits.lastIndexOf(" "),
            last4Digits.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        payment_card_description.text = last4Digits
    }


    private fun setupListeners() {
        setupPaymentValueListener()
        setupEditCardListener()
        setupPaymentButtonListener()
    }

    private fun setupPaymentValueListener() {
        payment_value_edit_text.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                val value = text.toString().replace(",", ".")
                if (value.isNotEmpty() && value.toDouble() > 0) {
                    val colorBrand = ContextCompat.getColor(requireContext(), R.color.colorBrand)
                    payment_value_edit_text.setTextColor(colorBrand)
                    payment_currency.setTextColor(colorBrand)
                    payment_button.isEnabled = true
                } else {
                    val colorText = ContextCompat.getColor(requireContext(), R.color.colorText)
                    payment_value_edit_text.setTextColor(colorText)
                    payment_currency.setTextColor(colorText)
                    payment_button.isEnabled = false
                }
            }
        })
    }

    private fun setupEditCardListener() {
        payment_card_description.setOnClickListener {
            val action = PaymentFragmentDirections.actionPaymentToEditCard(
                transactionViewModel.user,
                transactionViewModel.card
            )
            findNavController().navigate(action)
        }
    }

    private fun setupPaymentButtonListener() {
        transactionViewModel.getTransaction().observe(this, Observer {
            if (it.success) {
                val paySlipFragment = PaySlipFragment(transactionViewModel.getCardCvv(), it)
                paySlipFragment.show(parentFragmentManager, PaySlipFragment.TAG)
            } else
                Toast.makeText(
                    requireContext(),
                    "Transação Negada, \nTente novamente",
                    Toast.LENGTH_SHORT
                ).show()
        })

        payment_button.setOnClickListener {
            val value = payment_value_edit_text.text.toString().replace(",", ".").toFloat()

            transactionViewModel.pay(value)
        }
    }
}