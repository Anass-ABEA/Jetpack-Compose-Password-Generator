package com.whitebatcodes.passwordvault.models.password

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SavedPasswordRepo(private val savedPasswordDao : SavedPasswordDao) {

    @WorkerThread
    suspend fun insert(password : SavedPassword){
        withContext(Dispatchers.IO){
            savedPasswordDao.insert(password)
        }
    }

}