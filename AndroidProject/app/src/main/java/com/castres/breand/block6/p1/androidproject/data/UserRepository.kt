package com.castres.breand.block6.p1.androidproject.data

import com.castres.breand.block6.p1.androidproject.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserList(): Flow<Result<List<User>>>
}