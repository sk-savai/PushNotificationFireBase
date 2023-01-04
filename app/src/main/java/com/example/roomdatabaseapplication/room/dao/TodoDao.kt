package com.example.roomdatabaseapplication.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabaseapplication.room.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * from todo_table order by done desc")
    fun getAllTodos() : LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo : Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

/*    @Query("DELETE FROM todo_table")
    fun deleteAll()*/





}