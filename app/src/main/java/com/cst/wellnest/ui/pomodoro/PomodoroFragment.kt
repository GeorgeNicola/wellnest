package com.cst.wellnest.ui.pomodoro

import android.app.AlertDialog
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cst.wellnest.R
import com.google.android.material.card.MaterialCardView
import java.lang.Exception
import java.util.Locale
import java.util.concurrent.TimeUnit

class PomodoroFragment : Fragment() {

    private var workTimeInMillis = 25 * 60 * 1000L
    private var timer: CountDownTimer? = null
    private var timeLeftInMillis = workTimeInMillis
    private var isTimerRunning = false

    private lateinit var timerTextView: TextView
    private lateinit var actionButton: Button
    private lateinit var timerCardView: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pomodoro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerTextView = view.findViewById(R.id.timerTextView)
        actionButton = view.findViewById(R.id.actionButton)
        timerCardView = view.findViewById(R.id.timerCardView)

        resetTimer()

        actionButton.setOnClickListener {
            if (isTimerRunning) {
                resetTimer()
            } else {
                startTimer()
            }
        }

        timerCardView.setOnClickListener {
            if (!isTimerRunning) {
                showSetTimeDialog()
            }
        }
    }

    private fun showSetTimeDialog() {
        val container = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            val padding = (24 * resources.displayMetrics.density).toInt()
            setPadding(padding, padding, padding, padding)
        }

        val secondsEditText = EditText(requireContext()).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            hint = getString(R.string.seconds_hint)
            gravity = Gravity.CENTER
            filters = arrayOf(InputFilter.LengthFilter(2))
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
        }

        val minutesEditText = EditText(requireContext()).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            hint = getString(R.string.minutes_hint)
            gravity = Gravity.CENTER
            filters = arrayOf(InputFilter.LengthFilter(2))
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )

            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 2) {
                        secondsEditText.requestFocus()
                    }
                }
            })
        }

        val separator = TextView(requireContext()).apply {
            text = getString(R.string.time_separator)
            textSize = 24f
            val margin = (8 * resources.displayMetrics.density).toInt()
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = margin
                marginEnd = margin
            }
        }

        container.addView(minutesEditText)
        container.addView(separator)
        container.addView(secondsEditText)

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.set_duration_title))
            .setView(container)
            .setPositiveButton(getString(R.string.dialog_button_ok)) { _, _ ->
                val minutesString = minutesEditText.text.toString()
                val secondsString = secondsEditText.text.toString()

                var minutes = minutesString.toLongOrNull() ?: 0L
                var seconds = secondsString.toLongOrNull() ?: 0L

                if (minutes > 99) {
                    minutes = 99
                }
                if (seconds > 59) {
                    seconds = 59
                }

                if (minutes > 0 || seconds > 0) {
                    workTimeInMillis = (minutes * 60 * 1000L) + (seconds * 1000L)
                    resetTimer()
                }
            }
            .setNegativeButton(getString(R.string.dialog_button_cancel)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    private fun startTimer() {
        isTimerRunning = true
        actionButton.text = getString(R.string.reset)
        actionButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.accent)
        timerCardView.isClickable = false

        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerDisplay()
            }
            override fun onFinish() {
                playSound()
                resetTimer()
            }
        }.start()
    }

    private fun resetTimer() {
        timer?.cancel()
        isTimerRunning = false
        timeLeftInMillis = workTimeInMillis
        actionButton.text = getString(R.string.start)
        actionButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.primary)
        timerCardView.isClickable = true
        updateTimerDisplay()
    }

    private fun updateTimerDisplay() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis)
        val seconds = timeLeftInMillis / 1000 % 60
        val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        timerTextView.text = timeFormatted
    }

    private fun playSound() {
        try {
            val notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(requireContext(), notificationUri)
            ringtone.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }
}