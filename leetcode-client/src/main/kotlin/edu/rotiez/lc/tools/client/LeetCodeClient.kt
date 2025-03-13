package edu.rotiez.lc.tools.client

import com.squareup.moshi.Types
import edu.rotiez.lc.tools.client.model.GraphqlResponse
import edu.rotiez.lc.tools.client.model.ProblemsetQuestionList
import edu.rotiez.lc.tools.client.util.GraphqlQueries.PROBLEMSET_QUESTION_LIST_OPERATION
import edu.rotiez.lc.tools.client.util.GraphqlQueries.PROBLEMSET_QUESTION_LIST_QUERY
import edu.rotiez.lc.tools.client.util.GraphqlUtils.prepareRequestBody

class LeetCodeClient {

    companion object {
        private const val LEETCODE_URL = "https://leetcode.com"
        private const val GRAPHQL_URL = "$LEETCODE_URL/graphql"
    }

    private val httpClient = CustomHttpClient()

    fun getProblemList(problemId: Int, limit: Int): ProblemsetQuestionList {
        val variables = mapOf(
            "categorySlug" to "all-code-essentials",
            "skip" to 0,
            "limit" to limit,
            "filters" to mapOf("searchKeywords" to problemId.toString())
        )

        val responseType = Types.newParameterizedType(GraphqlResponse::class.java, ProblemsetQuestionList::class.java)
        val response: GraphqlResponse<ProblemsetQuestionList> = httpClient.post(
            GRAPHQL_URL,
            prepareRequestBody(PROBLEMSET_QUESTION_LIST_QUERY, variables, PROBLEMSET_QUESTION_LIST_OPERATION),
            responseType
        )

        return response.data
    }

    fun getProblem(problemId: Int): ProblemsetQuestionList.ProblemQuestion {
        return getProblemList(problemId, 1).problemsetQuestionList.questions.first()
    }
}
