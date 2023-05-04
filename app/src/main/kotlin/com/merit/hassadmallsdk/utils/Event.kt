package com.merit.hassadmallsdk.utils

/**
@author Salman Aziz
created on 8/27/21
 **/

open class Event<out T>(private val content: T) {

    var hasbeenHandeled = false
        private set

    fun getContentIfNotHandeled(): T? {
        return if (hasbeenHandeled) {
            null
        } else {
            hasbeenHandeled = true
            content
        }
    }

    fun peakContent() = content
}