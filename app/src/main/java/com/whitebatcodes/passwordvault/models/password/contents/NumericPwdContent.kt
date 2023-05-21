package com.whitebatcodes.passwordvault.models.password.contents

data class NumericPwdContentImpl(override var content: String = "1234567890") : PasswordContent

object NumericPwdContent {
    private var instance : PasswordContent = NumericPwdContentImpl()

    fun getInstance() : PasswordContent{
        return instance
    }
}