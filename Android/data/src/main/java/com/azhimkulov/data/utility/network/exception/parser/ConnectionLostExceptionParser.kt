package com.azhimkulov.data.utility.network.exception.parser

import com.azhimkulov.data.exception.ConnectionLostException
import java.net.UnknownHostException

fun parseConnectionLostException(
    throwable: Throwable
): Throwable {

    return if (throwable is UnknownHostException) {
        ConnectionLostException()
    } else {
        throwable
    }
}