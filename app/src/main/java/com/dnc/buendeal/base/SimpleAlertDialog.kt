package com.dnc.buendeal.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dnc.buendeal.R
import com.dnc.buendeal.core.extentions.dp
import org.koin.core.component.KoinComponent

class SimpleAlertDialog : DialogFragment() {

    private var title: String? = null
    private var message: CharSequence? = null

    private var successButtonMessage: String? = null
    private var negativeButtonMessage: String? = null

    private var successAction: (() -> Unit)? = null
    private var negativeAction: (() -> Unit)? = null

    private var dismissListener: (() -> Unit)? = null

    private var successButtonStyle: SuccessButtonStyle? = null
    private var successButtonLayoutParams: LinearLayout.LayoutParams? = null

    private var negativeButtonStyle: NegativeButtonStyle? = null
    private var negativeButtonLayoutParams: LinearLayout.LayoutParams? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_simple_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvSubtitle = view.findViewById<TextView>(R.id.tvSubtitle)
        val btnPositive = view.findViewById<TextView>(R.id.btnPositive)
        val btnNegative = view.findViewById<TextView>(R.id.btnNegative)
        val llButtonContainer = view.findViewById<LinearLayout>(R.id.llButtonContainer)

        title?.also {
            tvTitle.text = HtmlCompat.fromHtml(
                it, HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            tvTitle.isVisible = true
        }

        if (message is String) {
            message?.also {
                tvSubtitle.text = HtmlCompat.fromHtml(
                    it.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT
                )
                tvSubtitle.isVisible = true
            }
        } else {
            message.also {
                tvSubtitle.text = it
                tvSubtitle.isVisible = true
            }
        }

        successButtonMessage?.also {
            btnPositive.text = it
            llButtonContainer.isVisible = true
            btnPositive.isVisible = true
        }

        btnPositive.setOnClickListener {
            dismiss()
            successAction?.invoke()
        }

        negativeButtonMessage?.also {
            llButtonContainer.isVisible = true
            btnNegative.text = it
            btnNegative.isVisible = true
            btnNegative.setOnClickListener {
                negativeAction?.invoke()
                dismiss()
            }
        }

        successButtonLayoutParams?.also {
            btnPositive.layoutParams = it
        }

        negativeButtonLayoutParams?.let {
            btnNegative.layoutParams = it.apply {
                topMargin = 16.dp
            }
            btnNegative.setPadding(0, 9.dp, 0, 9.dp)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
    }

    private fun show(transaction: FragmentTransaction, tag: String?, allowStateLoss: Boolean): Int {
        transaction.add(this, tag)
        return try {
            if (allowStateLoss) transaction.commitAllowingStateLoss() else transaction.commit()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            -1
        }
    }

    companion object : KoinComponent {
        private const val DIALOG_TAG = "dialog_error_tag"

        fun show(
            fm: FragmentManager?,
            titleId: Int? = null,
            message: Int? = null,
            successButtonMessage: Int? = null,
            negativeButtonMessage: Int? = null,
            cancelable: Boolean = true,
            negativeAction: (() -> Unit)? = null,
            successAction: (() -> Unit)? = null,
            successButtonStyle: SuccessButtonStyle = SuccessButtonStyle.Standard,
            successButtonLayoutParams: LinearLayout.LayoutParams? = null,
            negativeButtonStyle: NegativeButtonStyle = NegativeButtonStyle.Standard,
            negativeButtonLayoutParams: LinearLayout.LayoutParams? = null,
        ) {
            fm ?: return
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag(DIALOG_TAG)
            if (prev != null) {
                ft.remove(prev)
                ft.commitAllowingStateLoss()
            }
            val dialog = getKoin().get<Context>().let { context ->
                newInstance(
                    titleId?.let { context.getString(it) },
                    message?.let { context.getString(it) },
                    successButtonMessage?.let { context.getString(it) },
                    negativeButtonMessage?.let { context.getString(it) },
                    cancelable,
                    negativeAction,
                    successAction,
                    successButtonStyle,
                    successButtonLayoutParams,
                    negativeButtonStyle,
                    negativeButtonLayoutParams
                )
            }
            dialog.show(ft, DIALOG_TAG, true)
        }

        fun show(
            fm: FragmentManager?,
            titleId: Int? = null,
            message: CharSequence? = null,
            successButtonMessage: Int? = null,
            negativeButtonMessage: Int? = null,
            cancelable: Boolean = true,
            negativeAction: (() -> Unit)? = null,
            successAction: (() -> Unit)? = null,
            successButtonStyle: SuccessButtonStyle = SuccessButtonStyle.Standard,
            successButtonLayoutParams: LinearLayout.LayoutParams? = null,
            negativeButtonStyle: NegativeButtonStyle = NegativeButtonStyle.Standard,
            negativeButtonLayoutParams: LinearLayout.LayoutParams? = null,
        ) {
            fm ?: return
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag(DIALOG_TAG)
            if (prev != null) {
                ft.remove(prev)
                ft.commitAllowingStateLoss()
            }
            val dialog = getKoin().get<Context>().let { context ->
                newInstance(
                    titleId?.let { context.getString(it) },
                    message,
                    successButtonMessage?.let { context.getString(it) },
                    negativeButtonMessage?.let { context.getString(it) },
                    cancelable,
                    negativeAction,
                    successAction,
                    successButtonStyle,
                    successButtonLayoutParams,
                    negativeButtonStyle,
                    negativeButtonLayoutParams
                )
            }
            dialog.show(ft, DIALOG_TAG, true)
        }

        fun show(
            fm: FragmentManager?,
            title: String? = null,
            message: String? = null,
            successButtonMessage: String? = null,
            negativeButtonMessage: String? = null,
            cancelable: Boolean = true,
            negativeAction: (() -> Unit)? = null,
            successAction: (() -> Unit)? = null,
            successButtonStyle: SuccessButtonStyle = SuccessButtonStyle.Standard,
            successButtonLayoutParams: LinearLayout.LayoutParams? = null,
            negativeButtonStyle: NegativeButtonStyle = NegativeButtonStyle.Standard,
            negativeButtonLayoutParams: LinearLayout.LayoutParams? = null,
        ) {
            fm ?: return
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag(DIALOG_TAG)
            if (prev != null) {
                ft.remove(prev)
                ft.commitAllowingStateLoss()
            }
            val dialog = newInstance(
                title,
                message,
                successButtonMessage,
                negativeButtonMessage,
                cancelable,
                negativeAction,
                successAction,
                successButtonStyle,
                successButtonLayoutParams,
                negativeButtonStyle,
                negativeButtonLayoutParams
            )
            dialog.show(ft, DIALOG_TAG, true)
        }

        sealed class SuccessButtonStyle {
            object Standard : SuccessButtonStyle()
            object BrandBlue : SuccessButtonStyle()
        }

        sealed class NegativeButtonStyle {
            object Standard : NegativeButtonStyle()
            object BrandBlueOutlined : NegativeButtonStyle()
        }

        private fun newInstance(
            titleId: String?,
            message: CharSequence?,
            successButtonMessage: String?,
            negativeButtonMessage: String?,
            cancelable: Boolean,
            negativeAction: (() -> Unit)? = null,
            successAction: (() -> Unit)? = null,
            successButtonStyle: SuccessButtonStyle,
            successButtonLayoutParams: LinearLayout.LayoutParams? = null,
            negativeButtonStyle: NegativeButtonStyle = NegativeButtonStyle.Standard,
            negativeButtonLayoutParams: LinearLayout.LayoutParams? = null,
            dismissAction: (() -> Unit)? = null
        ) = SimpleAlertDialog().apply {
            this.title = titleId
            this.message = message
            this.successButtonMessage = successButtonMessage
            this.negativeButtonMessage = negativeButtonMessage
            this.successAction = successAction
            this.negativeAction = negativeAction
            this.isCancelable = cancelable
            this.successButtonStyle = successButtonStyle
            this.successButtonLayoutParams = successButtonLayoutParams
            this.negativeButtonStyle = negativeButtonStyle
            this.negativeButtonLayoutParams = negativeButtonLayoutParams
            this.dismissListener = dismissAction
        }
    }
}
