package com.bimabagaskhoro.taskapp.domain.usecase

import com.bimabagaskhoro.taskapp.data.Resource
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModelById
import com.bimabagaskhoro.taskapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface DatasUseCase {
    fun getAllParticipant(): Flow<Resource<List<ParticipantModel>>>
    fun getByIdParticipant(id: String): Flow<Resource<List<ParticipantModelById>>>
    fun postDataParticipant(data : ParticipantModel): Flow<Resource<List<String>>>
    fun postDataTask(data : TaskModel): Flow<Resource<List<String>>>
}