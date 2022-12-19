package com.bimabagaskhoro.taskapp.data.source

import android.content.ContentValues
import android.util.Log
import com.bimabagaskhoro.taskapp.utils.MappingHelper
import com.bimabagaskhoro.taskapp.data.Resource
import com.bimabagaskhoro.taskapp.data.source.remote.RemoteDataSource
import com.bimabagaskhoro.taskapp.data.source.remote.network.ApiResponse
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModelById
import com.bimabagaskhoro.taskapp.domain.model.TaskModel
import com.bimabagaskhoro.taskapp.domain.repository.IDatasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatasRepository @Inject constructor(private val remoteDataSource: RemoteDataSource): IDatasRepository{
    private val TAG = DatasRepository::class.java.simpleName

    override fun getAllParticipant(): Flow<Resource<List<ParticipantModel>>> {
        return flow {
            emit(Resource.Loading(null))
            when (val apiResponse = remoteDataSource.getAllParticipant().first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MappingHelper.entitiesToDomain(apiResponse.data!!)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.message!!))
                }
                is ApiResponse.Empty -> {
                    Log.d(TAG, "getAllParticipant: Empty Data")
                }
            }
        }
    }

    override fun getByIdParticipant(id: String): Flow<Resource<List<ParticipantModelById>>> {
        return flow {
            emit(Resource.Loading(null))
            when (val apiResponse = remoteDataSource.getByIdParticipant(id).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(MappingHelper.entitiesToDomainById(apiResponse.data!!)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.message!!))
                }
                is ApiResponse.Empty -> {
                    Log.d(TAG, "getByIdParticipant: Empty Data")
                }
            }
        }
    }

    override fun postDataParticipant(data: ParticipantModel): Flow<Resource<List<String>>> {
        val entity = MappingHelper.domainToEntity(data)
        return flow {
            emit(Resource.Loading(null))
            when(val apiResponse = remoteDataSource.postParticipant(entity).first()){
                is ApiResponse.Success -> {
                    emit(Resource.Success(null,"success"))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.message!!))
                }
                is ApiResponse.Empty -> {
                    Log.d(ContentValues.TAG, "postDataParticipant: on task null")
                }
            }
        }
    }

    override fun postDataTask(data: TaskModel): Flow<Resource<List<String>>> {
        val entity = MappingHelper.domainToEntityTask(data)
        return flow {
            emit(Resource.Loading(null))
            when(val apiResponse = remoteDataSource.postTask(entity).first()){
                is ApiResponse.Success -> {
                    emit(Resource.Success(null,"success"))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.message!!))
                }
                is ApiResponse.Empty -> {
                    Log.d(ContentValues.TAG, "postDataTask: on task null")
                }
            }
        }
    }
}