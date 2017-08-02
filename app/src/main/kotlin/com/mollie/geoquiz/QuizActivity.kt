package com.mollie.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton :Button
    private lateinit var question : TextView

    private val questions : List<Question> = listOf(
            Question(R.string.question_dog_1, true),
            Question(R.string.question_dog_2, true),
            Question(R.string.question_dog_3, false),
            Question(R.string.question_dog_4, false),
            Question(R.string.question_dog_5, true)
    )
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        question = findViewById(R.id.question_text) as TextView
        updateQuestion(questions[currentIndex])

        nextButton = findViewById(R.id.next_button) as Button
        setUpNextButton()

        falseButton = findViewById(R.id.false_button) as Button
        setUpButton(falseButton, false)

        trueButton = findViewById(R.id.true_button) as Button
        setUpButton(trueButton, true)
    }

    private fun updateCurrentIndex(): Int {
        currentIndex = if (currentIndex == questions.count() - 1) 0 else ++currentIndex
        return currentIndex
    }

    private fun setUpNextButton() {
        nextButton.setOnClickListener {
            updateQuestion(questions[updateCurrentIndex()])
        }
    }

    private fun updateQuestion(currentQuestion: Question) {
        question.setText(currentQuestion.id)
    }

    private fun checkAnswer(question: Question, buttonValue: Boolean): Int {
        if (question.isTrue == buttonValue) {
            return R.id.correct
        } else {
            return R.id.incorrect
        }
    }

    private fun setUpButton(button: Button, value: Boolean) {
        button.setOnClickListener {
            Toast.makeText(this@QuizActivity, checkAnswer(questions[currentIndex], value),
                    Toast.LENGTH_SHORT).show()
        }
    }
}
