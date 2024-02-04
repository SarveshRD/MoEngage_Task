package com.wss.eat_space_iz.utils

import android.content.Context
import android.util.Patterns
import android.view.View
import android.widget.Toast
import java.util.regex.Pattern

/**
 * Created by pchub on 29-09-2017.
 *
 * A Simple ValidationUtil class that can validate your inputs
 * and can display the error messages on its own so that you
 * don't have to bother adding multiple if-else-if statements
 * and Toasts for your Validations.
 *
 * Of course you can modify this file according to your needs
 * and implement more methods here to improve the validation of this class.
 *
 * <b>*Note*</b>
 * <pre>
 *     Do not save your activity's context in this class as it will lead to memory leak.
 *     Only pass your activity's context to the parameters and avoid keeping reference
 *     of the activity stored inside this class.
 * </pre>
 */
object ValidationUtil {

    const val FUll_NAME_PATTERN = "^[a-zA-z]+([\\s][a-zA-Z]+)*\$"
    const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"


    private fun showToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    private fun isNullOrEmpty(input: String?): Boolean = input == null || input.isEmpty()

    fun isValidEmail(
        context: Context, view: View? = null, email: String?,
    ): Boolean {
        when {
            isNullOrEmpty(email) -> if (view != null) ToastUtil.errorSnackbar(
                "Please enter Email first.", view.rootView, null
            )
            else showToast(context, "Please enter Email first.")
            !Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> if (view != null) ToastUtil.errorSnackbar(
                "Please enter a valid Email address.", view.rootView, null
            )
            else showToast(context, "Please enter a valid Email address.")
            else -> return true
        }
        return false
    }

    fun isValidMobile(
        context: Context, view: View? = null, mobile: String?, regex: String = "[0-9]{10}",
    ): Boolean {
        when {
            isNullOrEmpty(mobile) -> {
                if (view != null) ToastUtil.errorSnackbar(
                    "Please enter Mobile number first.", view.rootView, null
                )
                else showToast(context, "Please enter Mobile number first.")
            }
            !Pattern.matches(regex, mobile) -> {
                if (view != null) ToastUtil.errorSnackbar(
                    "Please enter a valid Mobile number.", view.rootView, null
                )
                else showToast(context, "Please enter a valid Mobile number.")
            }
            else -> return true
        }
        return false
    }

    /*private fun isValidUsername(
        context: Context,
        username: String?,
        regex: String = "^[a-zA-Z0-9._-]{3,20}$"
    ): Boolean {
        when {
            isNullOrEmpty(username) -> showToast(context, "Please enter User name first.")
            !Pattern.matches(regex, username) -> showToast(
                context,
                "Please enter a valid User name."
            )
            else -> return true
        }
        return false
    }*/

    /*fun isValidPassword(
        context: Context,
        view: View? = null,
        password: String?,
        callback: ((Boolean) -> Unit)?
    ): Boolean {
        when {
            isNullOrEmpty(password) -> showToast(context, "Please enter Password first.")
            password!!.length < 6 -> showToast(
                context,
                "Password length should not be less than 6 characters"
            )
            password.length > 30 -> showToast(
                context,
                "Password length should not be greater than 30 characters"
            )
            else -> return true
        }
        return false
    }*/

   /* fun isValidPassword(
        context: Context,
        view: View? = null,
        password: String?,
        fieldMassage: String,
    ): Boolean {
        when {
            isValidatePassword(password) -> {
                if (view != null)
                    ToastUtil.errorSnackbar(fieldMassage, view.rootView, null)
                else showToast(context, fieldMassage)
            }
        }
        return false
    }*/

    fun isValidatePassword(password: String?): Boolean {
        password?.let {
            val passwordPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }

    fun isValidFullName(context: Context, view: View? = null, fieldName: String?): Boolean {
        when {
            isNullOrEmpty(fieldName) -> {
                if (view != null)
                    ToastUtil.errorSnackbar("Please enter Full Name first", view.rootView, null)
                else
                    showToast(context, "Please enter Full Name first")
            }
            fieldName?.length ?: 0 < 4 -> {
                if (view != null)
                    ToastUtil.errorSnackbar(
                        "Please enter Full Name Minimum 4 Character",
                        view.rootView,
                        null
                    )
                else
                    showToast(context, "Please enter Full Name Minimum 4 Character")
            }
            else -> return true
        }
        return false
    }

    fun isValidateField(
        context: Context,
        view: View? = null,
        fieldName: String?,
        fieldMassage: String,
    ): Boolean {
        when {
            isNullOrEmpty(fieldName) -> {
                if (view != null)
                    ToastUtil.errorSnackbar(fieldMassage, view.rootView, null)
                else
                    showToast(context, fieldMassage)
            }
            else -> return true
        }
        return false
    }

    fun isValidateFieldEqual(
        context: Context,
        view: View? = null,
        fieldFirst: String,
        fieldSecond: String,
        fieldMassage: String,
    ): Boolean {
        if (fieldFirst != fieldSecond) {
            if (view != null)
                ToastUtil.errorSnackbar(fieldMassage, view.rootView, null)
            else
                showToast(context, fieldMassage)
        } else return true
        return false
    }
}