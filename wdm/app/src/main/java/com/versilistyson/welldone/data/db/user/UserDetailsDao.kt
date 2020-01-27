package com.versilistyson.welldone.data.db.user

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserDetailsByLocalId(id: String) : Flow<UserDetailsData>
}