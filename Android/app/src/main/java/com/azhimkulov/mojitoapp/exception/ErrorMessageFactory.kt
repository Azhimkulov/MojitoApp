package com.azhimkulov.mojitoapp.exception

import com.azhimkulov.data.exception.BadRequestWithMessageException
import com.azhimkulov.data.exception.ConnectionLostException

class ErrorMessageFactory {

    companion object {
        fun create(throwable: Throwable): String? {
            if (throwable is BadRequestWithMessageException) {
                return throwable.errorMessage
            }

            if (throwable is ConnectionLostException) {
                return "Проверьте подключение к интернету"
            }

            return null
        }
    }
}