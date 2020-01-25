package com.versilistyson.welldone.data.db.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM user_table WHERE id = :id")
    suspend fun getUserDetailsByLocalId(id: String) : Flow<UserDetailsData>
}