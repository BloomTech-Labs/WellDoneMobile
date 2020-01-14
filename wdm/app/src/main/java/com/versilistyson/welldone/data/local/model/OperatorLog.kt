package com.versilistyson.welldone.data.local.model

import android.graphics.drawable.Drawable
import java.io.Serializable

data class OperatorLog(val date_filed: String, val last_modified: String, val status: Drawable, val comment: String): Serializable
