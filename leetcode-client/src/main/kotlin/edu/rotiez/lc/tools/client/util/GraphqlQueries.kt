package edu.rotiez.lc.tools.client.util

object GraphqlQueries {

    val PROBLEMSET_QUESTION_LIST_OPERATION = "problemsetQuestionList"
    val PROBLEMSET_QUESTION_LIST_QUERY = """
        query problemsetQuestionList(
            ${'$'}categorySlug: String, 
            ${'$'}limit: Int, 
            ${'$'}skip: Int, 
            ${'$'}filters: QuestionListFilterInput
        ) {
            problemsetQuestionList: questionList(
                categorySlug: ${'$'}categorySlug
                limit: ${'$'}limit
                skip: ${'$'}skip
                filters: ${'$'}filters
            ) {
                total: totalNum
                questions: data {
                    acRate
                    difficulty
                    freqBar
                    frontendQuestionId: questionFrontendId
                    isFavor
                    paidOnly: isPaidOnly
                    status
                    title
                    titleSlug
                    topicTags {
                        name
                        id
                        slug
                    }
                    hasSolution
                    hasVideoSolution
                }
            }
        }
    """.trimIndent()
}