package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.extentions.dp

class DiagonalStrikeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    private val paint by lazy {
        Paint().apply {
            color = context.getColor(R.color.red)
            strokeWidth = resources.getDimension(R.dimen.margin_tiny)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f, height.toFloat() - 4.dp, width.toFloat(), 4.5f.dp, paint)
    }
}
