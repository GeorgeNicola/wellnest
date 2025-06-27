package com.cst.wellnest.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cst.wellnest.ApplicationController
import com.cst.wellnest.AuthActivity
import com.cst.wellnest.R
import com.cst.wellnest.data.repositories.UserRepository
import com.cst.wellnest.managers.SharedPrefsManager
import com.cst.wellnest.utils.extensions.showToast
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prefs = ApplicationController.instance!!.sharedPrefs
        val email = prefs.getString("email", null)
            ?: run {
                goToLogin()
                return
            }

        val firstEt = view.findViewById<EditText>(R.id.firstNameOnProfileEditText)
        val lastEt = view.findViewById<EditText>(R.id.lastNameOnProfileEditText)
        val saveBtn = view.findViewById<Button>(R.id.saveButton)
        val logoutBtn = view.findViewById<Button>(R.id.logoutButton)

        viewLifecycleOwner.lifecycleScope.launch {
            UserRepository.getUserByEmail(email)?.let { user ->
                firstEt.setText(user.firstName)
                lastEt.setText(user.lastName)
            }
        }

        saveBtn.setOnClickListener {
            val first = firstEt.text.toString().trim()
            val last = lastEt.text.toString().trim()
            if (first.isEmpty() || last.isEmpty()) {
                "Both fields are required".showToast(requireContext())
                return@setOnClickListener
            }
            viewLifecycleOwner.lifecycleScope.launch {
                val ok = UserRepository.updateUserNameByEmail(email, first, last)
                if (ok) "Profile updated".showToast(requireContext())
                else "Update failed".showToast(requireContext())
            }
        }

        logoutBtn.setOnClickListener {
            SharedPrefsManager.logoutUser()
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(requireContext(), AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}