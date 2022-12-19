package com.bimabagaskhoro.taskapp.utils

import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.DataItem
import com.bimabagaskhoro.taskapp.data.source.remote.response.participant.KegiatanItem
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModelById
import com.bimabagaskhoro.taskapp.domain.model.TaskModel

object MappingHelper {

    fun entitiesToDomain(data: List<DataItem>): List<ParticipantModel> =
        data.map {
            ParticipantModel(
                id = it.id,
                name = it.name
            )
        }

    fun entitiesToDomainById(data: List<KegiatanItem>): List<ParticipantModelById> =
        data.map {
            ParticipantModelById(
                timeStart = it.timeStart,
                name = it.name,
                id = it.id,
                timeEnd = it.timeEnd,
                day = it.day
            )
        }

    fun domainToEntity(data: ParticipantModel): ParticipantModel =
        ParticipantModel(
            id = data.id,
            name = data.name
        )

    fun domainToEntityTask(data: TaskModel): TaskModel =
        TaskModel(
            nameTask = data.nameTask,
            pesertaId = data.pesertaId,

            timeStart = data.timeStart,
            timeEnd = data.timeEnd,
            day = data.day,
        )
}