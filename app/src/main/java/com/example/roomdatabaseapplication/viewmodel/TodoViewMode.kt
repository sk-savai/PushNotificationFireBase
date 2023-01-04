package com.example.roomdatabaseapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseapplication.repository.TodoRepository
import com.example.roomdatabaseapplication.room.database.TodoRoomDatabase
import com.example.roomdatabaseapplication.room.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoViewModel (application: Application) : AndroidViewModel(application) {
    private val allNotes : LiveData<List<Todo>>
    private val repository : TodoRepository

    init {
        val dao = TodoRoomDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(dao)
        allNotes = repository.allTodo
    }

    fun deleteNote (todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }

    fun updateNote(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }

    fun addNote(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }
}
