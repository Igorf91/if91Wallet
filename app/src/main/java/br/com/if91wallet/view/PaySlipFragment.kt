package br.com.if91wallet.view

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.if91wallet.R
import br.com.if91wallet.util.getLoaderPlaceholder
import br.com.if91wallet.vo.TransactionVo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_card
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_card_value
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_datetime
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_img
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_nickname
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_total_value
import kotlinx.android.synthetic.main.fragment_pay_slip.pay_slip_transaction_id
import java.text.SimpleDateFormat
import java.util.*

class PaySlipFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "Recibo"
        private const val TRANSACTION = "TRANSACTION"
        private const val CARD = "CARD"

        operator fun invoke(card: String, transaction: TransactionVo): BottomSheetDialogFragment {
            val bundle = Bundle()
            bundle.putString(CARD, card)
            bundle.putParcelable(TRANSACTION, transaction)

            return PaySlipFragment()
                .also {
                    it.arguments = bundle
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pay_slip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadPaySlip()
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadPaySlip() {
        val transaction = getTransaction()
        Glide
            .with(this)
            .load(transaction.destinationUser.img)
            .placeholder(getLoaderPlaceholder(requireContext()))
            .apply(RequestOptions.circleCropTransform())
            .into(pay_slip_img)

        pay_slip_nickname.text = transaction.destinationUser.username

        pay_slip_transaction_id.text =
            getString(R.string.pay_slip_transaction, transaction.id.toString())


        val formatter = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm")
        pay_slip_datetime.text = formatter.format( Date(transaction.timestamp))

        pay_slip_card.text = getString(R.string.pay_slip_card, "Master", getCard())

        pay_slip_card_value.text =
            getString(R.string.default_currency, transaction.value.toString())

        pay_slip_total_value.text =
            getString(R.string.default_currency, transaction.value.toString())
    }

    private fun getCard() = arguments!!.getString(CARD)
    private fun getTransaction(): TransactionVo = arguments!!.getParcelable(TRANSACTION)!!

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        findNavController().navigateUp()
    }
}