package com.bimabagaskhoro.taskapp.data.source.remote

import android.util.Log
import com.bimabagaskhoro.taskapp.data.source.remote.network.ApiResponse
import com.bimabagaskhoro.taskapp.data.source.remote.network.ApiService
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.DataItem
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.KegiatanItem
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.ResponsePostParticipant
import com.bimabagaskhoro.taskapp.data.source.remote.response.task.ResponsePostTask
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    private val TAG = RemoteDataSource::class.java.simpleName

    suspend fun getAllParticipant() : Flow<ApiResponse<List<DataItem>>> {
        return flow {
            try {
                val response = apiService.getAllParticipant()
                val data = response.data
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.message))
                Log.d(TAG, "getAllPengguna: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getByIdParticipant(id : String) : Flow<ApiResponse<List<KegiatanItem>>> {
        return flow {
            try {
                val response = apiService.getParticipantById(id)
                val data = response.kegiatan
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.kegiatan))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.message))
                Log.d(TAG, "getAllPengguna: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postParticipant(data: ParticipantModel): Flow<ApiResponse<ResponsePostParticipant>> {
        return flow {
            val body = HashMap<String, String>()
            body["name"] = data.name

            val response = apiService.postParticipant(body)
            when (response.status) {
                "success" -> emit(ApiResponse.Success(response))
                "fail" -> emit(ApiResponse.Error("failed"))
                else -> emit(ApiResponse.Empty)
            }
        }
    }

    suspend fun postTask(data: TaskModel): Flow<ApiResponse<ResponsePostTask>> {
        return flow {
            val body = HashMap<String, String>()

            body["name_task"] = data.nameTask
            body["day"] = data.day
            body["time_start"] = data.timeStart
            body["time_end"] = data.timeEnd
            body["pesertaId"] = data.pesertaId

            val response = apiService.postKegiatan(body)
            when (response.status) {
                "success" -> emit(ApiResponse.Success(response))
                "fail" -> emit(ApiResponse.Error("failed"))
                else -> emit(ApiResponse.Empty)
            }
        }
    }

}