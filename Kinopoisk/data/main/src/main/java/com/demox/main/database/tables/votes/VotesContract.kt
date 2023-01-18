package com.demox.main.database.tables.votes

object VotesContract {
    const val TABLE_NAME = "votes"

    object Colums {
        const val ID = "votes_id"
        const val AWAIT = "await"
        const val CRITICS = "filmCritics"
        const val IMDB = "imdb"
        const val KP = "kp"
        const val RUS_CRITICS = "russianFilmCritics"
    }
}
