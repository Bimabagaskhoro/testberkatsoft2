package com.bimabagaskhoro.taskapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskModel(
    val nameTask: String,
    val pesertaId: String,
    val timeStart: String,
    val timeEnd: String,
    val day: String
) : Parcelable
