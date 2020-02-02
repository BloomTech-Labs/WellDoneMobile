package com.versilistyson.welldone.data.db.user

import androidx.room.*
import com.versilistyson.welldone.data.util.Mappable
import com.versilistyson.welldone.domain.framework.entity.Entity

@androidx.room.Entity(
    tableName = "user_table",
    indices = [Index(value = ["id"], unique = true)]
)
class UserDetailsData(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val userId: Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email_address") val emailAddress: String?,
    @ColumnInfo(name = "mobile_number") val phoneNumber: String?
): Mappable<Entity.UserDetails> {
    override fun map() =
        Entity.UserDetails(
            userId = userId,
            firstName = firstName,
            lastName = lastName,
            emailAddress = emailAddress,
            phoneNumber = phoneNumber
        )
}