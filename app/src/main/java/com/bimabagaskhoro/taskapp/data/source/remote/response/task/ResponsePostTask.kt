package com.bimabagaskhoro.taskapp.data.source.remote.response.task

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePostTask(
    val data: DataPostTask,
    val status: String
) : Parcelable

@Parcelize
data class DataPostTask(
	val kegiatanId: String
) : Parcelable
