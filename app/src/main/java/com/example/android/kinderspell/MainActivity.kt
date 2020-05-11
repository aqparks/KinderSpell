package com.example.android.kinderspell

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var submitButton: Button
    private lateinit var skipButton: Button
    private lateinit var questionImageView: ImageView
    private lateinit var guessEditText: EditText
    private lateinit var speechBox: SpeechBox

    private val questions = listOf(
        Question(R.drawable.cat, "cat"),
        Question(R.drawable.baby, "baby"),
        Question(R.drawable.horse, "horse"),
        Question(R.drawable.ice, "ice"),
        Question(R.drawable.spider, "spider"),
        Question(R.drawable.lion, "lion"),
        Question(R.drawable.fish, "fish"),
        Question(R.drawable.dolphin, "dolphin"),
        Question(R.drawable.bee, "bee"),
        Question(R.drawable.ant, "ant"),
        Question(R.drawable.cookie, "cookie"),
        Question(R.drawable.bug, "bug"),
        Question(R.drawable.pug, "pug"),
        Question(R.drawable.dog, "dog")
    )

    private var currentIndex = 0
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.submit_button)
        skipButton = findViewById(R.id.skip_button)
        questionImageView = findViewById(R.id.question_image_view)
        guessEditText = findViewById(R.id.guess)
        speechBox = SpeechBox(assets)
        speechBox.loadSounds()

        questionImageView.setOnClickListener {
            speechBox.play(questions[currentIndex].answer)
        }

        submitButton.setOnClickListener {
            val guess = guessEditText.text.toString()
            val answer = questions[currentIndex].answer
            Log.wtf("onSubmit", "guess:" + guess)
            Log.wtf("onSubmit", "answer:" + answer)
            Log.wtf("onSubmit", "guess:" + guess.javaClass.canonicalName)
            Log.wtf("onSubmit", "answer:" + answer.javaClass.canonicalName)
            if (guess.equals(answer)) {
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
                hideKeyboard(this)
                nextQuestion()
            } else {
                Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
            }

        }
        skipButton.setOnClickListener {
            nextQuestion()
        }

        val questionImageResId = questions[currentIndex].drawableResId
        questionImageView.setImageResource(questionImageResId)
    }

    fun nextQuestion() {
        currentIndex = (currentIndex + 1)
        if (currentIndex >= questions.size) {
            currentIndex = 0
        }
        val questionImageResId = questions[currentIndex].drawableResId
        questionImageView.setImageResource(questionImageResId)
        guessEditText.setText("")
        guessEditText.setHint("Spell It")
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}
