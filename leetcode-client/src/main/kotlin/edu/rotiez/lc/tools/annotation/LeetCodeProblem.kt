package edu.rotiez.lc.tools.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LeetCodeProblem(
    val id: Int,
)
