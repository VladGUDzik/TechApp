package entities

import android.text.TextUtils
import android.util.Patterns;
import java.util.regex.Pattern

class Admin(
    private var login: String,
    private var email: String,
    private var password: String
) {

    fun isDataValid(): Int {
        return if (TextUtils.isEmpty(getLogin()) || TextUtils.isEmpty(getEmail()))
            -1
        else if (!isEmailValid())
            -2
        else if (!isPasswordLengthValid())
            -3
        else
            1
    }

    fun setLogin(login: String) {
        this.login = login
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getLogin(): String {
        return login
    }

    fun getPassword(): String {
        return password;
    }

    fun getEmail(): String {
        return email
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()
    }

    private fun isPasswordLengthValid(): Boolean {
        val passwordPattern = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=!])" +
                "(?=\\S+$)" +
                ".{8,}" +
                "$"
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(password)

        return matcher.matches()
    }
}