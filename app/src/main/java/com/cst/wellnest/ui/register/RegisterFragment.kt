package com.cst.wellnest.ui.register

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
import androidx.navigation.fragment.navArgs
import com.cst.wellnest.R

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
            val password = passwordEditText.text.toString()
            Toast.makeText(requireContext(), "Logging in with $email", Toast.LENGTH_SHORT).show()

            // Validate email & password
            // Create user

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


}