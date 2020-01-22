package com.versilistyson.welldone.data.db.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM user_table WHERE id = :localId")
    fun getUserDetailsByLocalId(localId: String) : LiveData<UserDetailsData>
}