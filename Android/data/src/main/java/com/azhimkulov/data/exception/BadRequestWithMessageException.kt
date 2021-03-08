package com.azhimkulov.data.exception

import java.lang.Exception

class BadRequestWithMessageException(val errorMessage: String): Exception(errorMessage)