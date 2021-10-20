package com.amirhusseinsoori.invalidPassword.util.builderInvalidPass

import com.amirhusseinsoori.invalidPassword.util.Pattern
import com.amirhusseinsoori.invalidPassword.util.emptyString
import com.amirhusseinsoori.invalidPassword.util.textPersian

class InvalidPassword constructor(
    var input: String? = null,
    var state: Int? = null
) {
    private constructor(builder: Builder) : this(
        builder.input,
        builder.state
    )

    class Builder() : InvalidPasswordStatus {
        var input: String? = emptyString
            private set
        var state: Int = 0
            private set

        override fun inputValue(st: String): InvalidPassword.Builder = apply {
            input = st
        }

        override fun hasNumber(): InvalidPassword.Builder = apply {
            if (input!!.matches(Pattern.UseNumber.rex)) state += 1
        }

        override fun hasUpperCase(): InvalidPassword.Builder = apply {
            if (input!!.matches(Pattern.UpperCase.rex)) state += 1
        }

        override fun hasDownCase(): InvalidPassword.Builder = apply {
            if (input!!.matches(Pattern.DownCase.rex)) state += 1
        }
        override fun atLeast8(): InvalidPassword.Builder = apply {
            if (input!!.length > 7) state += 1
        }

        override fun hasSymbol(): InvalidPassword.Builder = apply {
            if (input!!.matches(Pattern.Symbol.rex)) state += 1
        }

        override fun hasNoSpace(): InvalidPassword.Builder = apply {
            if (!(input!!.matches(Pattern.NoSpace.rex))) state = 0
        }
        override fun hasPersian(): InvalidPassword.Builder = apply {
            if (input!!.textPersian()) state = 0
        }



        fun build() = InvalidPassword(this)
    }

}


