package com.bimabagaskhoro.taskapp.data.source.remote.response.participant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePostParticipant(
    val data: DataPostParticipant,
    val message: String,
    val status: String
) : Parcelable

@Parcelize
data class DataPostParticipant(
	val name: String,
	val pesertaId: String
) : Parcelable
