package com.castres.breand.block6.p1.androidproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.castres.breand.block6.p1.androidproject.data.Result
import com.castres.breand.block6.p1.androidproject.data.UserRepository
import com.castres.breand.block6.p1.androidproject.data.model.User
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(
    private val usersRepository: UserRepository
): ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            usersRepository.getUserList().collectLatest { result ->
                when(result){
                    is Result.Error ->{
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { users ->
                            _users.update { users }
                        }
                    }
                }
            }
        }
    }
}