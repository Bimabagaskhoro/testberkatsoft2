package com.bimabagaskhoro.taskapp.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUpdateDelete(
	val message: String,
	val status: String
) : Parcelable
