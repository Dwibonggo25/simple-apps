package com.example.customrecycler.repository

import com.example.customrecycler.api.ApiService
import com.example.customrecycler.db.UserDao
import com.example.customrecycler.model.SplashScreenResponse
import io.reactivex.Observable
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userDao: UserDao, private val apiservice: ApiService) {

    fun fetchALlData () : Observable <List<SplashScreenResponse>> {
        return apiservice.fetchALlData()
    }
}