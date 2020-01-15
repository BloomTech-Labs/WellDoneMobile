package com.versilistyson.welldone.domain.entity

import android.graphics.drawable.Drawable
import java.io.Serializable

sealed class Entity {
    data class AuthenticatedUser(
        val token: String,
        val userId: Int
    ) : Entity()
    data class SensorRecentResponse(
        val bluetooth: Int?,
        val cellular: Int?,
        val commune_name: String,
        val count: Int?,
        val created_at: String,
        val data_finished: String,
        val date: String?,
        val depth: Int,
        val district_name: String,
        val headquarter_city: String,
        val history_index: Int,
        val kind: String?,
        val latitude: Double,
        val longitude: Double,
        val org_id: Int,
        val org_name: String,
        val pad_count_0: Double?,
        val pad_count_1: Double?,
        val pad_count_2: Double?,
        val pad_count_3: Double?,
        val pad_seconds_0: Double?,
        val pad_seconds_1: Double?,
        val pad_seconds_2: Double?,
        val pad_seconds_3: Double?,
        val physical_id: Int,
        val province_name: String,
        val pump_index: Int,
        val quality: String?,
        val remark: String?,
        val reported_percent: Double?,
        val sensor_id: Int,
        val sensor_index: Int,
        val sensor_pid: Int,
        val static: Double?,
        val status: Int?,
        val total: Double?,
        val training: String?,
        val type: String?,
        val village_name: String,
        val yield: Double?
    ): Entity()
    data class OperatorLog(
        val date_filed: String,
        val last_modified: String,
        val status: Drawable,
        val comment: String
    ) : Entity()
}