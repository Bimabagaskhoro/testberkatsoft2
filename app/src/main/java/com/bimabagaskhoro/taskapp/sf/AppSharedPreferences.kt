package com.bimabagaskhoro.taskapp.sf

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPreferences @Inject constructor(context: Context) {
    companion object {
        const val KEY_ID_PARTICIPANT = "isParticipantId"
        const val KEY_ID = "id_peserta"
    }

    private var pref: SharedPreferences = context.getSharedPreferences("Session", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = pref.edit()

    fun createIdParticipant() {
        editor.putBoolean(KEY_ID_PARTICIPANT, true)
            .commit()
    }

    fun clearData() {
        editor.clear()
        editor.commit()
    }

    val isParticipantId: Boolean = pref.getBoolean(KEY_ID_PARTICIPANT, false)

    fun saveToPreference(key: String, value: String) = editor.putString(key, value).commit()

    fun getFromPreference(key: String) = pref.getString(key, "")


}