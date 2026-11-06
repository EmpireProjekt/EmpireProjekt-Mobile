package com.makeevrserg.empireprojekt.mobile.core.log.mikro

import com.makeevrserg.empireprojekt.mobile.core.log.util.writeLogcatDebug
import com.makeevrserg.empireprojekt.mobile.core.log.util.writeLogcatError
import com.makeevrserg.empireprojekt.mobile.core.log.util.writeLogcatInfo
import com.makeevrserg.empireprojekt.mobile.core.log.util.writeLogcatVerbose
import com.makeevrserg.empireprojekt.mobile.core.log.util.writeLogcatWarn
import ru.astrainteractive.klibs.mikro.core.logging.Logger

actual class DefaultLogger actual constructor(tag: String) : Logger {
    override val TAG: String = tag

    override fun error(logMessage: () -> String) {
        writeLogcatError(
            tag = TAG,
            message = logMessage.invoke(),
            error = null
        )
    }

    override fun error(error: Throwable?, logMessage: () -> String) {
        writeLogcatError(
            tag = TAG,
            message = logMessage.invoke(),
            error = error
        )
    }

    override fun info(logMessage: () -> String) {
        writeLogcatInfo(TAG, logMessage.invoke())
    }

    override fun verbose(logMessage: () -> String) {
        writeLogcatVerbose(TAG, logMessage.invoke())
    }

    override fun warn(logMessage: () -> String) {
        writeLogcatWarn(TAG, logMessage.invoke())
    }

    override fun debug(logMessage: () -> String) {
        writeLogcatDebug(TAG, logMessage.invoke())
    }
}
