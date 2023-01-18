package com.demox.main.models

data class ServerResponse(
    val docs: List<MovieResponse>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)
