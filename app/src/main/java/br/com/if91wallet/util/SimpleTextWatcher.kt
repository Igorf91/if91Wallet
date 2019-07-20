package br.com.if91wallet.util

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        //Not implemented
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //Not implemented
    }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        //Not implemented
    }
}