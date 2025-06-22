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
import androidx.navigation.fragment.findNavController
import com.cst.wellnest.MainAppActivity
import com.cst.wellnest.R


class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("TAG","onViewCreated - LoginFragment")


        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val goToRegisterButton = view.findViewById<TextView>(R.id.goToRegisterText)

        loginButton?.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            Toast.makeText(requireContext(), "Logging in with $email", Toast.LENGTH_SHORT).show()

            // Check email & password
            // Save to local idk

            goToMainAppActivity()
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
        val intent = Intent(requireContext(), MainAppActivity::class.java)
        startActivity(intent)

    }
}