package com.azhimkulov.data.utility.network.exception.parser

import com.azhimkulov.data.exception.BadRequestWithMessageException
import retrofit2.HttpException

/**
 * Created by azamat  on 2/22/21.
 */
private const val BAD_REQUEST_CODE = 400

fun parseBadRequestWithMessage(
    throwable: Throwable
): Throwable {

    if (throwable !is HttpException) {
        return throwable
    }

    if (throwable.code() != BAD_REQUEST_CODE) {
        return throwable
    }

    return try {
        val message = throwable.response()?.errorBody()?.string()?.replace("\"", "")
        if (!message.isNullOrBlank()) {
            BadRequestWithMessageException(message)
        } else {
            throwable
        }
    } catch (ex:Exception) {
        throwable
    }
}