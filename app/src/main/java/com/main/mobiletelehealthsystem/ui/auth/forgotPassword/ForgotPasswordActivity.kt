package com.main.mobiletelehealthsystem.ui.auth.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.main.mobiletelehealthsystem.base.ViewModelFactory
import com.main.mobiletelehealthsystem.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForgotPasswordBinding
    private val forgotPasswordViewModael by viewModels<ForgotPasswordViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        initViews()
    }

    private fun initViews() {
        binding.run {
            forgotPasswordButton.setOnClickListener {
                forgotPasswordViewModel.sendResetLinkMail()
            }
            emailEditText.setUserInputListener {
                forgotPasswordViewModel.setEmail(it)
            }
        }
    }

    private fun initObservers() {
        forgotPasswordViewModel.run {
            errorLiveData.observe(this@ForgotPasswordActivity) {
                Toast.makeText(this@ForgotPasswordActivity, it, Toast.LENGTH_SHORT).show()
            }
            enableSendMailButton.observe(this@ForgotPasswordActivity) {
                binding.forgotPasswordButton.isEnabled = it
                binding.forgotPasswordButton.setButtonEnabled(it)
            }
            forgotPasswordLiveData.observe(this@ForgotPasswordActivity) {
                Toast.makeText(this@ForgotPasswordActivity, "Reset link sent to ${getEmail()}", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}