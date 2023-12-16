package com.app.recipeapp.pojo.network

import android.os.Parcel
import android.os.Parcelable

data class Images(
    val THUMBNAIL: Thumbnail?,
    val SMALL: Thumbnail?,
    val REGULAR: Thumbnail?,
    val LARGE: Thumbnail?
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Thumbnail::class.java.classLoader),
        parcel.readParcelable(Thumbnail::class.java.classLoader),
        parcel.readParcelable(Thumbnail::class.java.classLoader),
        parcel.readParcelable(Thumbnail::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(THUMBNAIL, flags)
        parcel.writeParcelable(SMALL, flags)
        parcel.writeParcelable(REGULAR, flags)
        parcel.writeParcelable(LARGE, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}
