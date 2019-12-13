package com.versilistyson.welldone.data.remote.dto

data class PumpResponse(
    val commune_name: String,
    val district_name: String,
    val headquarter_city: String,
    val latitude: Double,
    val longitude: Double,
    val org_name: String,
    val province_name: String,
    val village_name: String
)