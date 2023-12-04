package com.itinergo.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.itinergo.R

class EditTextPlan : AppCompatEditText {
    private lateinit var editTextBackground: Drawable
    private lateinit var errorBackground: Drawable
    private var isError = false

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        background = editTextBackground
        background = if (isError) errorBackground else editTextBackground
    }

    private fun init() {
        editTextBackground = ContextCompat.getDrawable(context, R.drawable.bg_edit_text_plan) as Drawable
        errorBackground =
            ContextCompat.getDrawable(context, R.drawable.bg_edit_text_plan_error) as Drawable

    }
}