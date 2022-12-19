package com.bimabagaskhoro.taskapp.data.source.remote.response.participant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetAllParticipant(
	val data: List<DataItem>,
	val status: String
) : Parcelable

@Parcelize
data class DataItem(
	val name: String,
	val id: String
) : Parcelable
