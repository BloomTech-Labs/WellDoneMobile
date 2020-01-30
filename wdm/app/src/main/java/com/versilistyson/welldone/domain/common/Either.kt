package com.versilistyson.welldone.domain.common

// https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/master/app/src/main/kotlin/com/fernandocejas/sample/core/functional/Either.kt

sealed class Either<out L, out R> {
    data class Left<out L>(val left: L): Either<L, Nothing>()
    data class Right<out R>(val right: R): Either<Nothing, R>()

    val isRight get(): Boolean = this is Right<R>

    val isLeft get(): Boolean = this is Left<L>

    fun <L> left(left: L): Left<L> = Left(left)

    fun <R> right(right: R): Right<R> = Right(right)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
    when(this) {
        is Left -> fnL(left)
        is Right -> fnR(right)
    }
}