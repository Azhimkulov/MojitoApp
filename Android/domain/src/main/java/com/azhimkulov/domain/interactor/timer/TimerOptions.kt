package com.azhimkulov.domain.interactor.timer

import java.util.concurrent.TimeUnit

class TimerOptions(
    val delay: Long,
    val updatePeriod: Long,
    val timeUnit: TimeUnit
)