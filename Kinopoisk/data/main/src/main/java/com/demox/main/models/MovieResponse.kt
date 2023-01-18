package com.demox.main.models

data class MovieResponse(
    val alternativeName: String?,
    val description: String?,
    val id: Int,
    val name: String?,
    val poster: Poster?,
    val rating: Rating,
    val shortDescription: String?,
    val type: String,
    val votes: Votes,
    val year: Int
)
fun MovieResponse.mapToMovie(): Movie =
    Movie(
        id = id,
        alternativeName = alternativeName,
        description = description,
        poster = poster?.previewUrl,
        name = name,
        rating = rating,
        shortDescription = shortDescription,
        type = type,
        votes = votes,
        year = year
    )
