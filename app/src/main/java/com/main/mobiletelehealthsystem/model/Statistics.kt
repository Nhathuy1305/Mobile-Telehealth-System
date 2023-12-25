package com.main.mobiletelehealthsystem.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthData(
    var name: String? = null,
    var tests: List<TestResult>? = null,
    var healthId: String? = null
) : Parcelable

@Parcelize
data class TestResult(
    var result: String? = null,
    var dateTime: String? = null
) : Parcelable