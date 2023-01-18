package com.demox.main.models

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val id: Int,
    val alternativeName: String?,
    val description: String?,
    val poster: String?,
    val name: String?,
    val rating: Rating,
    val shortDescription: String?,
    val type: String,
    val votes: Votes,
    val year: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Rating::class.java.classLoader)!!,
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readParcelable(Votes::class.java.classLoader)!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(alternativeName)
        parcel.writeString(description)
        parcel.writeString(poster)
        parcel.writeString(name)
        parcel.writeParcelable(rating, flags)
        parcel.writeString(shortDescription)
        parcel.writeString(type)
        parcel.writeParcelable(votes, flags)
        parcel.writeInt(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
