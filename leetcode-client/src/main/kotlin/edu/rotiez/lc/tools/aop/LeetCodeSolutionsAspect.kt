package edu.rotiez.lc.tools.aop

import edu.rotiez.lc.tools.annotation.LeetCodeProblem
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect

@Aspect
class LeetCodeSolutionsAspect {

    private val checkedClasses = mutableSetOf<Class<*>>()

    // 1. Измените pointcut, чтобы параметр аннотации был явно привязан
    @After("initialization((@edu.rotiez.lc.tools.annotation.LeetCodeSolutions *).new(..)) && this(target)")
    fun checkDuplicateProblemIds(joinPoint: JoinPoint, target: Any) {
        val clazz = target.javaClass

        if (checkedClasses.contains(clazz)) return
        checkedClasses.add(clazz)

        // 2. Проверка дубликатов остается без изменений
        val problemIds = clazz.declaredMethods
            .mapNotNull { it.getAnnotation(LeetCodeProblem::class.java)?.id }

        val duplicates = problemIds.groupingBy { it }.eachCount().filter { it.value > 1 }
        if (duplicates.isNotEmpty()) {
            throw IllegalStateException(
                "Duplicate LeetCodeProblem IDs in class ${clazz.simpleName}: ${duplicates.keys}"
            )
        }
    }
}