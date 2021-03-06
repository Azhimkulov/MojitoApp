package com.azhimkulov.data.executor

import com.azhimkulov.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class JobExecutor @Inject constructor() : ThreadExecutor {

    companion object {
        private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
        private const val KEEP_ALIVE_TIME = 1
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS

        private class JobThreadFactory : ThreadFactory {
            private var counter = 0

            override fun newThread(runnable: Runnable): Thread {
                return Thread(runnable, "android_" + this.counter++)
            }
        }
    }

    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        NUMBER_OF_CORES,
        NUMBER_OF_CORES * 2,
        KEEP_ALIVE_TIME.toLong(),
        KEEP_ALIVE_TIME_UNIT,
        LinkedBlockingQueue<Runnable>(),
        JobThreadFactory()
    )

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }
}