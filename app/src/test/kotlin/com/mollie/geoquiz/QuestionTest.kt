package com.mollie.geoquiz

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec


class QuestionTest : StringSpec() {

    init {
        "it knows if it is true" {
            var question = Question(0, true)

            question.isTrue shouldBe true
        }
        "it knows if it is false" {
            var question = Question(1, false)

            question.isTrue shouldBe false
        }
    }
}