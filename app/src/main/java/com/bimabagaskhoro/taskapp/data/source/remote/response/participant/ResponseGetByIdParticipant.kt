package com.bimabagaskhoro.taskapp.data.source.remote.response.participant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetByIdParticipant(
	val data: DataById,
	val status: String
) : Parcelable

@Parcelize
data class DataById(
	val kegiatan: List<KegiatanItem>,
	val peserta: Peserta
) : Parcelable

@Parcelize
data class KegiatanItem(
	val timeStart: String,
	val name: String,
	val id: String,
	val timeEnd: String,
	val day: String
) : Parcelable

@Parcelize
data class Peserta(
	val name: String,
	val id: String
) : Parcelable
