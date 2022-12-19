package com.bimabagaskhoro.taskapp.data.source.remote.network

import com.bimabagaskhoro.taskapp.data.source.remote.response.ResponseUpdateDelete
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.DataById
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.ResponseGetAllParticipant
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.ResponsePostParticipant
import com.bimabagaskhoro.taskapp.data.source.remote.response.task.ResponseGetAllTask
import com.bimabagaskhoro.taskapp.data.source.remote.response.task.ResponseGetByIdTask
import com.bimabagaskhoro.taskapp.data.source.remote.response.task.ResponsePostTask
import retrofit2.http.*

interface ApiService {

    @GET("peserta")
    suspend fun getAllParticipant(): ResponseGetAllParticipant

    @GET("peserta/{id}")
    suspend fun getParticipantById(
        @Path("id") id: String
    ): DataById

    @POST("peserta")
    suspend fun postParticipant(
        @Body req: HashMap<String, String>
    ): ResponsePostParticipant

    @PUT("peserta/{id}")
    suspend fun putPParticipant(
        @Path("id") id: String,
        @Body param: Map<String, String>
    ): ResponseUpdateDelete

    @DELETE("peserta/{id}")
    suspend fun deleteParticipant(
        @Path("id") id: String
    ): ResponseUpdateDelete

    //=======================//

    @GET("kegiatan")
    suspend fun getAllKegiatan(): ResponseGetAllTask

    @GET("kegiatan/{id}")
    suspend fun getKegiatanById(
        @Path("id") id: String
    ): ResponseGetByIdTask

    @POST("kegiatan")
    suspend fun postKegiatan(
        @Body req: HashMap<String, String>
    ): ResponsePostTask

    @PUT("kegiatan/{id}")
    suspend fun putKegiatan(
        @Path("id") id: String,
        @Body param: Map<String, String>
    ): ResponseUpdateDelete

    @DELETE("kegiatan/{id}")
    suspend fun deleteKegiatan(
        @Path("id") id: String
    ): ResponseUpdateDelete
}