package com.demox.main.database.tables.rating

object RatingContract {
    const val TABLE_NAME = "rating"

    object Colums {
        const val ID = "rating_id"
        const val AWAIT = "await"
        const val CRITICS = "filmCritics"
        const val IMDB = "imdb"
        const val KP = "kp"
        const val RUS_CRITICS = "russianFilmCritics"
    }
}
