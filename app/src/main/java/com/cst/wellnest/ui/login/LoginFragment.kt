package com.cst.wellnest.ui.login

import android.content.Intent
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
import com.cst.wellnest.AppActivity
import com.cst.wellnest.R
import com.cst.wellnest.data.repositories.UserRepository
import com.cst.wellnest.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("TAG","onViewCreated - LoginFragment")

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val goToRegisterButton = view.findViewById<TextView>(R.id.goToRegisterText)

        loginButton?.setOnClickListener {
            val email = emailEditText.text.toString()
            Toast.makeText(requireContext(), "Logging in with $email", Toast.LENGTH_SHORT).show()

            // Check email & password
            doLogin()
        }

        goToRegisterButton?.setOnClickListener {
            Toast.makeText(requireContext(), "Navigate to Register Screen", Toast.LENGTH_SHORT).show()
            goToRegister(emailEditText.text.toString())
        }
    }

    private fun goToRegister(email: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(email)
        findNavController().navigate(action)
    }

    private fun goToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToNavigationBottom()
        findNavController().navigate(action)
    }

    private fun goToMainAppActivity() {
        val intent = Intent(requireContext(), AppActivity::class.java)
        startActivity(intent)

    }

    private fun doLogin() {
        val email = view?.findViewById<EditText>(R.id.emailEditText)?.text?.toString()
        val password = view?.findViewById<EditText>(R.id.passwordEditText)?.text?.toString()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            "Invalid credentials".showToast(requireContext())
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val user = UserRepository.authenticate(email, password)
                val result = withContext(Dispatchers.IO) {
                    // network validation call
                    // AuthenticationRepository.login(email, password)
                }

                if (user != null) {
                    "Login successful".showToast(requireContext())
                    goToMainAppActivity()
                } else {
                    "Invalid email or password".showToast(requireContext())
                }

                withContext(Dispatchers.IO) {
                    // token returned by network call
                    //SharedPrefsManager.saveAuthToken(result.getOrNull()!!.token)
                }

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