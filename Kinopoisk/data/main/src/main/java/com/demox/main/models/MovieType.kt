package com.demox.main.models

enum class MovieType {
    ALL,
    MOVIE,
    SERIES,
    CARTOON
}

fun MovieType.toServerCall(): String {
    return when (this) {
        MovieType.ALL -> ""
        MovieType.MOVIE -> "1"
        MovieType.SERIES -> "2"
        MovieType.CARTOON -> "3"
    }
}

fun String.movieTypeForDataBaseCall(): String {
    return when (this) {
        "1" -> "movie"
        "2" -> "series"
        "3" -> "cartoon"
        else -> ""
    }
}
