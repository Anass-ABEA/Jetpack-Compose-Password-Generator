package com.whitebatcodes.passwordvault.models.password

import java.util.Date

data class SavedPassword(
    var title: String = "",
    var id: Int = 0,
    var login: String = "",
    var password: String = "",
    var siteUrl: String = "",
    var description: String = "",
    var created: Long = Date().time,
    var updated: Long = Date().time
) {
    fun mandatoryFieldsExist(): Boolean {
        return title.isNotEmpty() && login.isNotEmpty() && password.isNotEmpty()
    }
}
