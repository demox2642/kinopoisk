package com.demox.main.models

import android.os.Parcel
import android.os.Parcelable

data class Rating(
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

    companion object CREATOR : Parcelable.Creator<Rating> {
        override fun createFromParcel(parcel: Parcel): Rating {
            return Rating(parcel)
        }

        override fun newArray(size: Int): Array<Rating?> {
            return arrayOfNulls(size)
        }
    }
}

fun Rating.toUIString(): String {
    return "await:${this.await} ; filmCritics:${this.filmCritics} ; imdb:${this.imdb} ; kp:${this.kp} ; russianFilmCritics:${this.russianFilmCritics}"
}
