package com.cst.wellnest.ui.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cst.wellnest.ApplicationController
import com.cst.wellnest.R
import com.cst.wellnest.models.User
import com.cst.wellnest.data.repositories.UserRepository
import com.cst.wellnest.utils.extensions.showToast
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RegisterFragment: Fragment() {
    private val args: RegisterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("TAG","onViewCreated - RegisterFragment")

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        emailEditText.setText(args.email)

        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val goToLoginButton = view.findViewById<TextView>(R.id.goToLoginText)




        registerButton?.setOnClickListener {
            val email = emailEditText.text.toString()
            Toast.makeText(requireContext(), "Logging in with $email", Toast.LENGTH_SHORT).show()

            // Validate email & password
            // Create user
            doRegister()

            goToLogin()
        }

        goToLoginButton?.setOnClickListener {
            Toast.makeText(requireContext(), "Navigate to Login Screen", Toast.LENGTH_SHORT).show()
            goToLogin()
        }
    }

    private fun goToLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(action)
    }


    private fun doRegister() {
        val email = view?.findViewById<EditText>(R.id.emailEditText)?.text?.toString()
        val password = view?.findViewById<EditText>(R.id.passwordEditText)?.text?.toString()
        val firstName = view?.findViewById<EditText>(R.id.emailEditText)?.text?.toString() // TODO add firstName edit field
        val lastName = view?.findViewById<EditText>(R.id.emailEditText)?.text?.toString() // TODO add lastName edit field

        if (email.isNullOrEmpty() || password.isNullOrEmpty() || firstName.isNullOrEmpty() || lastName.isNullOrEmpty()) {
            "Invalid credentials".showToast(requireContext())
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
//                    AuthenticationRepository.register(RegisterAPIRequestModel(email, password, firstName, lastName) )
                    val userRepository = UserRepository(requireContext().getSharedPreferences("app", Context.MODE_PRIVATE), Gson(), ApplicationController.instance?.appDatabase?.userDao())
                    userRepository.saveUser(User(email, firstName, lastName))
                }

                "Register success!".showToast(requireContext())
            } catch (e: IOException) {
                ("Please check your internet connection").showToast(requireContext())
            } catch (e: HttpException) {
                ("Server error: ${e.code()}").showToast(requireContext())
            } catch (e: Exception) {
                ("Unexpected error: ${e.localizedMessage}").showToast(requireContext())
            }
        }
    }
}