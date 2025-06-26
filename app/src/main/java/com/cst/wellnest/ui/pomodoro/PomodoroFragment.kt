package com.cst.wellnest.ui.pomodoro

import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cst.wellnest.R
import java.lang.Exception
import java.util.Locale
import java.util.concurrent.TimeUnit

class PomodoroFragment : Fragment() {

    private val workTimeInMillis = 10 * 1000L

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis = workTimeInMillis
    private var isTimerRunning = false

    private lateinit var timerTextView: TextView
    private lateinit var actionButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pomodoro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerTextView = view.findViewById(R.id.timerTextView)
        actionButton = view.findViewById(R.id.actionButton)

        resetTimer()

        actionButton.setOnClickListener {
            if (isTimerRunning) {
                resetTimer()
            } else {
                startTimer()
            }
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        actionButton.text = getString(R.string.reset)
        actionButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.accent)

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