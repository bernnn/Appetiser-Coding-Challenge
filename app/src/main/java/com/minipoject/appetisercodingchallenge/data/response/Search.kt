package com.minipoject.appetisercodingchallenge.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    val resultCount : Int,
    val results : ArrayList<ResultData>
) : Parcelable

@Parcelize
data class ResultData(
    val trackName: String?,
    val artworkUrl100: String?,
    val trackPrice: Float,
    val currency: String,
    val primaryGenreName: String,
    val longDescription: String
): Parcelable