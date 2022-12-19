package com.bimabagaskhoro.taskapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParticipantModel(
    val name: String,
    val id: String
) : Parcelable

@Parcelize
data class ParticipantModelById(
    val timeStart: String,
    val name: String,
    val id: String,
    val timeEnd: String,
    val day: String
) : Parcelable
