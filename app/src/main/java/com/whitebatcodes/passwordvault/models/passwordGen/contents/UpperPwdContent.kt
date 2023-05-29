package com.whitebatcodes.passwordvault.models.passwordGen.contents

data class UpperPwdContentImpl(override var content: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ") : PasswordContent


object UpperPwdContent {
    private var instance : PasswordContent = UpperPwdContentImpl()

    fun getInstance() : PasswordContent{
        return instance
    }
}