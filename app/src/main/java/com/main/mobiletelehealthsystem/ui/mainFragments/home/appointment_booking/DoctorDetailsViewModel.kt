package com.main.mobiletelehealthsystem.ui.mainFragments.home.appointment_booking

import android.app.Application
import com.main.mobiletelehealthsystem.base.BaseViewModel
import com.main.mobiletelehealthsystem.model.User

class DoctorDetailsViewModel(application: Application) : BaseViewModel(application) {

    private var doctor: User = User()

    fun initDoctor(doctorDetails: User) {
        doctor = doctorDetails
    }

    fun getDoctor(): User {
        return doctor
    }

}
