package com.whitebatcodes.passwordvault.models.password

import com.whitebatcodes.passwordvault.models.password.contents.CustomPwdContent
import com.whitebatcodes.passwordvault.models.password.contents.LowerPwdContent
import com.whitebatcodes.passwordvault.models.password.contents.NumericPwdContent
import com.whitebatcodes.passwordvault.models.password.contents.PasswordContent
import com.whitebatcodes.passwordvault.models.password.contents.UpperPwdContent

data class PasswordGenerator(
    var passwordSize: Int,
    var passwordContent: ArrayList<PasswordContent>
) {

    fun generatePassword() : String{
        var result = ""

        for(i in 0..passwordSize){
            val index = (Math.random()*passwordContent.size).toInt()
            result+= passwordContent[index].getRandom()
        }
        return result
    }

    class Builder{

        private var instance = PasswordGenerator(8, ArrayList())

        fun addUpper(hasUpper : Boolean) : Builder{
            if(hasUpper) instance.passwordContent.add(UpperPwdContent.getInstance())
            return this
        }
        fun addLower(hasLower : Boolean) : Builder{
            if(hasLower) instance.passwordContent.add(LowerPwdContent.getInstance())
            return this
        }
        fun addNumeric(hasNumeric : Boolean) : Builder{
            if(hasNumeric) instance.passwordContent.add(NumericPwdContent.getInstance())
            return this
        }
        fun addCustom(hasCustom : Boolean, custom : CustomPwdContent) : Builder{
            if(hasCustom) instance.passwordContent.add(custom)
            return this
        }

        fun setSize(pwdSize : Int): Builder{
            instance.passwordSize = pwdSize
            return this
        }

        fun build() : PasswordGenerator{
            return instance
        }


    }


}