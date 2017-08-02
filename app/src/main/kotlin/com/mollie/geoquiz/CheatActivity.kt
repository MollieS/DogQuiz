package com.mollie.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class CheatActivity : AppCompatActivity() {

    private lateinit var answerText: TextView
    private lateinit var showButton: Button
    private lateinit var cheatListener: CheatOnClick

    companion object {
        var EXTRA_ANSWER_IS_TRUE = "com.mollie.geoquiz.answer"
        val EXTRA_CHEAT_WAS_SHOWN = "com.mollie.geoquiz.cheat_was_shown"

        fun newIntent(context: Context, question: Question): Intent {
            var intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, question.isTrue)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerText = findViewById(R.id.answer_text) as TextView

        val intent: Intent = intent
        val isTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        cheatListener = CheatOnClick(this@CheatActivity, answerText, isTrue)

        showButton = findViewById(R.id.show_button) as Button
        showButton.setOnClickListener(cheatListener)
    }
}

class CheatOnClick(val activity: CheatActivity, val answerText: TextView, val isTrue: Boolean) : View.OnClickListener {

    var hasBeenClicked: Boolean = false

    override fun onClick(view: View) {
        if(hasBeenClicked) {
            goBack()
        } else {
            showAnswer(view as Button)
            hasBeenClicked = true
        }
    }

    fun showAnswer(button: Button) {
        answerText.text = isTrue.toString()
        button.setText(R.string.back)
    }

    fun goBack() {
        val intent: Intent = Intent()
        intent.putExtra(CheatActivity.EXTRA_CHEAT_WAS_SHOWN, hasBeenClicked)
        activity.setResult(Activity.RESULT_OK, intent)
        activity.finish()
    }
}
