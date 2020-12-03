package com.mzd.myapp.ui.base

import com.mzd.myapp.R


class Dialog(
    val payload: Any?,
    val titleRes: Int,
    val messageRes: Int,
    val positiveButtonRes: Int,
    val neutralButtonRes: Int,
    val negativeButtonRes: Int,
    val cancelable: Boolean,
    val showButtons: Int
) {

    companion object {
        const val POSITIVE = 0x01
        const val NEUTRAL = 0x02
        const val NEGATIVE = 0x04
    }

    data class Builder(
        var payload: Any? = null,
        var titleRes: Int = 0,
        var messageRes: Int = 0,
        var positiveButtonRes: Int = R.string.dialog_default_positive,
        var neutralButtonRes: Int = R.string.dialog_default_neutral,
        var negativeButtonRes: Int = R.string.dialog_default_negative,
        var cancelable: Boolean = true,
        var showButtons: Int = POSITIVE
    ) {

        @Suppress("unused")
        fun payload(payload: Any?) = apply { this.payload = payload }
        fun titleRes(titleRes: Int) = apply { this.titleRes = titleRes }
        fun messageRes(messageRes: Int) = apply { this.messageRes = messageRes }
        @Suppress("unused")
        fun positiveButtonRes(positiveButtonRes: Int) = apply { this.positiveButtonRes = positiveButtonRes }
        @Suppress("unused")
        fun negativeButtonRes(negativeButtonRes: Int) = apply { this.negativeButtonRes = negativeButtonRes }
        @Suppress("unused")
        fun neutralButtonRes(neutralButtonRes: Int) = apply { this.neutralButtonRes = neutralButtonRes }
        @Suppress("unused")
        fun cancelable(cancellable: Boolean) = apply { this.cancelable = cancellable }
        fun showButtons(showButtons: Int) = apply { this.showButtons = showButtons }

        fun build() = Dialog(
            payload,
            titleRes,
            messageRes,
            positiveButtonRes,
            neutralButtonRes,
            negativeButtonRes,
            cancelable,
            showButtons
        )
    }

}