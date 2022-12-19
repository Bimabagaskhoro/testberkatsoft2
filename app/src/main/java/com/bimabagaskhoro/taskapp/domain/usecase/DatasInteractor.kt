package com.bimabagaskhoro.taskapp.domain.usecase

import com.bimabagaskhoro.taskapp.data.Resource
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModelById
import com.bimabagaskhoro.taskapp.domain.model.TaskModel
import com.bimabagaskhoro.taskapp.domain.repository.IDatasRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatasInteractor @Inject constructor(private val datasRepository: IDatasRepository) : DatasUseCase  {

    override fun getAllParticipant(): Flow<Resource<List<ParticipantModel>>> =
        datasRepository.getAllParticipant()

    override fun getByIdParticipant(id: String): Flow<Resource<List<ParticipantModelById>>> =
        datasRepository.getByIdParticipant(id)

    override fun postDataParticipant(data: ParticipantModel): Flow<Resource<List<String>>> =
        datasRepository.postDataParticipant(data)

    override fun postDataTask(data: TaskModel): Flow<Resource<List<String>>> =
        datasRepository.postDataTask(data)
}