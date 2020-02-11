package com.leo.coroutine.entity

data class IndexRumorNews(
    val body: String,
    val id: Int,
    val mainSummary: String,
    val rumorType: Int,
    val score: Int,
    val sourceUrl: String,
    val summary: String,
    val title: String
)