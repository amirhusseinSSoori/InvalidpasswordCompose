package com.amirhusseinsoori.invalidPassword.util.builderInvalidPass

interface InvalidPasswordStatus {
    fun inputValue(st: String): InvalidPassword.Builder
    fun hasNumber(): InvalidPassword.Builder
    fun hasUpperCase(): InvalidPassword.Builder
    fun hasDownCase(): InvalidPassword.Builder
    fun hasNoSpace():InvalidPassword.Builder
    fun hasPersian():InvalidPassword.Builder
    fun hasSymbol():InvalidPassword.Builder
    fun atLeast8(): InvalidPassword.Builder
}