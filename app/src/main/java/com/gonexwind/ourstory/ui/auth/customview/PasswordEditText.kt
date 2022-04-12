package com.gonexwind.ourstory.ui.auth.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.gonexwind.ourstory.R

class CustomEditText : AppCompatEditText {
    private lateinit var errorButtonImage: Drawable
    private lateinit var passwordEditText: EditText

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        errorButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_error) as Drawable
        passwordEditText = findViewById(R.id.passwordEditText)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length < 6) showErrorButton() else hideErrorButton()
            }

            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    private fun showErrorButton() {
        setButtonDrawables(endOfTheText = errorButtonImage)
        passwordEditText.error = resources.getString(R.string.password_error)
    }

    private fun hideErrorButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = getDrawable(context, R.drawable.ic_password),
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }
}