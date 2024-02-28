package com.castres.breand.block6.p1.androidproject.data

import retrofit2.HttpException
import com.castres.breand.block6.p1.androidproject.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import java.lang.Exception

class UserRepositoryImpl (
    private val api: Api
): UserRepository{

    override suspend fun getUserList(): Flow<Result<List<User>>> {
        return flow {
           val usersFromApi = try {
               api.getUserList()

           }catch (e: IOException){
               e.printStackTrace()
               emit(Result.Error(message = "Error loading"))
               return@flow
           }catch (e: HttpException){
               e.printStackTrace()
               emit(Result.Error(message = "Error loading"))
               return@flow
           }catch (e:Exception){
               e.printStackTrace()
               emit(Result.Error(message = "Error loading"))
               return@flow
           }
            //emit(Result.Success(usersFromApi.))
        }
    }
}