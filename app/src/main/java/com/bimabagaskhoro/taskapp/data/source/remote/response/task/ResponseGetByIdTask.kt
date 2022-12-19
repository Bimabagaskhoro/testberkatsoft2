package com.bimabagaskhoro.taskapp.data.source.remote.response.task

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetByIdTask(
	val data: DataGetByIdTask,
	val status: String
) : Parcelable

@Parcelize
data class KegiatanByIdItem(
	val nameTask: String,
	val pesertaId: String,
	val timeStart: String,
	val timeEnd: String,
	val day: String
) : Parcelable

@Parcelize
data class DataGetByIdTask(
	val kegiatan: List<KegiatanByIdItem>
) : Parcelable
