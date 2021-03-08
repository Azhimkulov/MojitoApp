package com.azhimkulov.mojitoapp.exception

import com.azhimkulov.data.exception.BadRequestWithMessageException

class ErrorMessageFactory {

    companion object {
        fun create(throwable: Throwable): String? {
            if (throwable is BadRequestWithMessageException) {
                return throwable.errorMessage
            }

            return null
        }
    }
}