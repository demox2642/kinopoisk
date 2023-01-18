package com.demox.main.models

import android.os.Parcel
import android.os.Parcelable

data class Votes(
    val _id: String,
    val await: Double,
    val filmCritics: Double,
    val imdb: Double,
    val kp: Double,
    val russianFilmCritics: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeDouble(await)
        parcel.writeDouble(filmCritics)
        parcel.writeDouble(imdb)
        parcel.writeDouble(kp)
        parcel.writeDouble(russianFilmCritics)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Votes> {
        override fun createFromParcel(parcel: Parcel): Votes {
            return Votes(parcel)
        }

        override fun newArray(size: Int): Array<Votes?> {
            return arrayOfNulls(size)
        }
    }
}

fun Votes.toUIString(): String {
    return "await:${this.await} ; filmCritics:${this.filmCritics} ; imdb:${this.imdb} ; kp:${this.kp} ; russianFilmCritics:${this.russianFilmCritics}"
}
