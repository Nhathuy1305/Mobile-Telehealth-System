package com.main.mobiletelehealthsystem.ui.mainFragments.appointments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.main.mobiletelehealthsystem.base.BaseViewModel
import com.main.mobiletelehealthsystem.model.DoctorAppointment
import com.main.mobiletelehealthsystem.model.Rating
import com.main.mobiletelehealthsystem.utils.Constants
import com.main.mobiletelehealthsystem.utils.Logger
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PatientQueueViewModel(application: Application) : BaseViewModel(application) {

    val doctorUserID = MutableLiveData<String>()
    val selectedDate = MutableLiveData<String>()
    private val hideDateSelector = MutableLiveData<Boolean>()
    val patientsListLiveData = MutableLiveData<List<DoctorAppointment>>()


    fun setPassedData(
        doctorUserID: String,
        selectedDate: String,
        hideDateSelector: Boolean = false
    ) {
        this.doctorUserID.value = doctorUserID
        this.selectedDate.value = selectedDate
        this.hideDateSelector.value = hideDateSelector
    }

    fun getPatientsListFromFirebase() {
        val patientsList = mutableListOf<DoctorAppointment>()
        FirebaseDatabase.getInstance("https://kotlin-telehealth-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(Constants.Users).child(doctorUserID.value.toString())
            .child("DoctorsAppointments").child(selectedDate.value.toString()).orderByChild("TotalPoints")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (appointmentSnapshot in snapshot.children) {
                            val appointment =
                                appointmentSnapshot.getValue(DoctorAppointment::class.java)
                            Logger.debugLog("Appointment: ${appointment!!}")

                            patientsList.add(appointment)
                            patientsList.sortWith(compareBy { it.TotalPoints })
                            patientsList.reverse()
                            Logger.debugLog("PatientsList: $patientsList")
                        }
                        patientsListLiveData.postValue(patientsList as ArrayList<DoctorAppointment>?)
                    } else {
                        patientsListLiveData.postValue(patientsList as ArrayList<DoctorAppointment>?)
                        Logger.debugLog("No appointments found for the date: ${selectedDate.value} and doctor: ${doctorUserID.value}")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }


    fun updateRating(rating: Rating) {
        try {
            FirebaseDatabase.getInstance("https://kotlin-telehealth-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference(Constants.Users).child(rating.patientId).get()
                .addOnSuccessListener { snapShot ->

                    var total = 0f
                    var size = 0

                    snapShot.child("ratings").children.forEach { ratingSnapshot ->
                        total += ratingSnapshot.child("rating").value.toString().toFloat()
                        size++
                    }

                    val totalRatingSize: Int = if (size == 0) 2 else size + 1

                    if (total == 0f)
                        total = 5f

                    val totalRatingGiven = total + rating.rating
                    val newRating = totalRatingGiven / totalRatingSize

                    Logger.debugLog("Total: $total")
                    Logger.debugLog("Size: $size")
                    Logger.debugLog("TotalRatingSize: $totalRatingSize")
                    Logger.debugLog("TotalRatingGiven: $totalRatingGiven")
                    Logger.debugLog("NewRating: $newRating")

                    snapShot.child("totalRating").ref.setValue(newRating)
                    snapShot.child("ratings").ref.push().setValue(rating)
                }

            Logger.debugLog("Rating: $rating")
        } catch (e: Exception) {
            Logger.debugLog("Rating: $e")
        }
    }


}