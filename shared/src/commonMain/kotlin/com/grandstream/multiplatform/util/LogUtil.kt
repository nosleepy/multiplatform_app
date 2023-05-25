package com.grandstream.multiplatform.util

import com.grandstream.multiplatform.tag
import io.github.aakira.napier.Napier

object LogUtil {
    fun d(content: String) {
        Napier.d(tag = tag()) { content }
    }

    fun i(content: String) {
        Napier.i(tag = tag()) { content }
    }

    fun w(content: String) {
        Napier.w(tag = tag()) { content }
    }

    fun e(content: String) {
        Napier.e(tag = tag()) { content }
    }
}