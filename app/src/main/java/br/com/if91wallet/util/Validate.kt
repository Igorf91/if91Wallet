package br.com.if91wallet.util

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun validate(
    editText: TextInputEditText,
    inputLayout: TextInputLayout,
    errorMsg: String,
    minSize: Int
) {
    editText.addTextChangedListener(object : SimpleTextWatcher() {
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            if (text.isNullOrEmpty() || text.length < minSize) {
                inputLayout.isErrorEnabled = true
                inputLayout.error = errorMsg
            } else
                inputLayout.isErrorEnabled = false
        }
    })
}