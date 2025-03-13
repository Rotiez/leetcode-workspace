package edu.rotiez.lc.tools.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class LeetCodeSolutions(
    val logInfo: Boolean = true,
)
