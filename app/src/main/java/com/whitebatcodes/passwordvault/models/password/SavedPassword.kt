package com.whitebatcodes.passwordvault.models.password

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "password")
data class SavedPassword(
    var title: String = "",
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var login: String = "",
    var password: String = "",
    var siteUrl: String = "",
    var description: String = "",
    @ColumnInfo(name="created_at") var created: Long = Date().time,
    @ColumnInfo(name="updated_at") var updated: Long = Date().time
) : Serializable {
    fun mandatoryFieldsExist(): Boolean {
        return title.isNotEmpty() && login.isNotEmpty() && password.isNotEmpty()
    }
}
