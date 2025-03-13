package edu.rotiez.lc.tools.aop

import edu.rotiez.lc.tools.annotation.LeetCodeProblem
import edu.rotiez.lc.tools.annotation.LeetCodeSolutions
import edu.rotiez.lc.tools.client.LeetCodeClient
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature

@Aspect
class LeetCodeProblemAspect {

    private val client = LeetCodeClient()
    private val log = KotlinLogging.logger {}

    @Around(
        "execution(@edu.rotiez.lc.tools.annotation.LeetCodeProblem * *(..)) && " +
                "!within(LeetCodeProblemAspect) && " +
                "@within(edu.rotiez.lc.tools.annotation.LeetCodeSolutions)"
    )
    fun aroundAnnotatedMethod(joinPoint: ProceedingJoinPoint): Any? {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(LeetCodeProblem::class.java)

        val clazz = joinPoint.target.javaClass
        val solutionsAnnotation = clazz.getAnnotation(LeetCodeSolutions::class.java)

        if (solutionsAnnotation?.logInfo == false) return joinPoint.proceed()

        val problem = client.getProblem(annotation.id)
        log.info("\u001B[32mTitle:\u001B[0m ${problem.title}")
        log.info("\u001B[32mDifficulty:\u001B[0m ${problem.difficulty}")
        log.info("\u001B[32mTopics:\u001B[0m ${problem.topicTags.joinToString { it.name }}")
        println()

        return joinPoint.proceed()
    }
}
