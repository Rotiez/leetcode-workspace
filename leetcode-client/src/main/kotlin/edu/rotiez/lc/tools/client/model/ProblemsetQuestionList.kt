package edu.rotiez.lc.tools.client.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProblemsetQuestionList(
    val problemsetQuestionList: ProblemsetQuestion
) {

    @JsonClass(generateAdapter = true)
    data class ProblemsetQuestion(
        val questions: List<ProblemQuestion>
    )

    @JsonClass(generateAdapter = true)
    data class ProblemQuestion(
        val acRate: Double,
        val difficulty: String,
        val paidOnly: Boolean,
        val title: String,
        val topicTags: List<TopicTag>,
    )

    @JsonClass(generateAdapter = true)
    data class TopicTag(
        val name: String
    )
}
