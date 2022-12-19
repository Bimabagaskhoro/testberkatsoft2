package com.bimabagaskhoro.taskapp.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bimabagaskhoro.taskapp.domain.model.ParticipantModel
import com.bimabagaskhoro.taskapp.domain.model.TaskModel
import com.bimabagaskhoro.taskapp.domain.usecase.DatasUseCase
import com.bimabagaskhoro.taskapp.sf.AppSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DatasViewModel @Inject constructor(val useCase: DatasUseCase,val sharedPreferences: AppSharedPreferences) : ViewModel() {
    fun getAllParticipant() = useCase.getAllParticipant().asLiveData()
    fun getByIdParticipant(id: String) = useCase.getByIdParticipant(id).asLiveData()
    fun postParticipant(data: ParticipantModel) = useCase.postDataParticipant(data).asLiveData()
    fun postTask(data: TaskModel) = useCase.postDataTask(data).asLiveData()

    private var _idPost = sharedPreferences

    fun idValue(idPeserta: String) {
        _idPost.createIdParticipant()
        _idPost.saveToPreference(AppSharedPreferences.KEY_ID, idPeserta)
    }

    fun getDatas() = _idPost.getFromPreference(AppSharedPreferences.KEY_ID)

    fun clearData() = _idPost.clearData()

}