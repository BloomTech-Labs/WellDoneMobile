package com.versilistyson.welldone.domain

import com.versilistyson.welldone.domain.common.Either
import org.junit.Test
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue

class EitherTest {
    @Test
    fun `Either right should return correct type`() {
        // SETUP
        val rightEither = Either.Right("Yeah that's right")
        // CHECk
        assertTrue(rightEither.isRight)
        assertFalse(rightEither.isLeft)
        rightEither.either(
            {},
            {right -> assertTrue(right == "Yeah that's right")}
        )
    }

    @Test
    fun `Either left should return correct type`() {
        // SETUP
        val leftEither = Either.Left("Left")
        // Check
        assertTrue(leftEither.isLeft)
        assertFalse(leftEither.isRight)
        leftEither.either({left -> assertTrue(left == "Left")}, {})
    }
}