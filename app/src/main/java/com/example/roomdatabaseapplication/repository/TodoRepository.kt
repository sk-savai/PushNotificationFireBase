package com.example.roomdatabaseapplication.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabaseapplication.room.dao.TodoDao
import com.example.roomdatabaseapplication.room.entity.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val allTodo: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }


    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }


    suspend fun update(todo: Todo){
        todoDao.update(todo)
    }
}