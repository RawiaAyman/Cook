package com.example.myapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.R
import com.example.myapplication.Data.local.database.AppDatabase
import com.example.myapplication.Data.model.user
import com.example.myapplication.databinding.FragmentSignUpBinding
import com.example.myapplication.ui.utils.PreferenceHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SignupFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toggle password visibility 👁️
        binding.eyeToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            binding.passwordInput.inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            binding.eyeToggle.setImageResource(if (isPasswordVisible) R.drawable.eye else R.drawable.hide)
            binding.passwordInput.setSelection(binding.passwordInput.text.length)
        }

        // Sign Up button logic
        binding.signupBtn.setOnClickListener {
            val username = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showSnackbar("Please fill in all fields")
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val userDao = AppDatabase.getInstance(requireContext()).userDao()
                    val existingUser = userDao.getUser(username)

                    if (existingUser != null) {
                        showSnackbar("User already exists, try logging in")
                    } else {
                        val newUser = user(username = username, password = password)
                        userDao.insertUser(newUser)
                        PreferenceHelper.setLoggedIn(requireContext(), true)
                        PreferenceHelper.setUserName(requireContext(), username)


                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    showSnackbar("Sign up failed: ${e.localizedMessage}")
                }
            }
        }

        // Back to Login
        binding.loginRedirect.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(resources.getColor(R.color.dishdash_red, null))
            .setTextColor(resources.getColor(android.R.color.white, null))
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
