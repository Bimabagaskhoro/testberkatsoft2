package com.bimabagaskhoro.taskapp.data.source.remote.response.task

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetAllTask(
	val data: DataGetAllTask,
	val status: String
) : Parcelable

@Parcelize
data class DataGetAllTask(
	val kegiatan: List<KegiatanItem>
) : Parcelable

@Parcelize
data class KegiatanItem(
	val nameTask: String,
	val pesertaId: String,
	val timeStart: String,
	val timeEnd: String,
	val day: String
) : Parcelable
