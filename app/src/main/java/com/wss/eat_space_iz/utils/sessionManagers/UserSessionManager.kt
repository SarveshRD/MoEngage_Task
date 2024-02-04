package com.wss.eat_space_iz.utils.sessionManagers

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.wss.eat_space_iz.utils.Strings
import com.wss.eat_space_iz.utils.constants.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager
@Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        context.getString(Strings.app_user_pref),
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val prefEditor: SharedPreferences.Editor = prefs.edit()

    var isUserLogin: Boolean
        get() = prefs.getBoolean(AppConstants.Prefs.UserPrefs.IS_USER_LOGIN, false)
        set(isUserLogin) {
            prefEditor.putBoolean(AppConstants.Prefs.UserPrefs.IS_USER_LOGIN, isUserLogin)
            prefEditor.apply()
        }

    var userToken: String?
        get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_TOKEN, null)
        set(userToken) {
            prefEditor.putString(AppConstants.Prefs.UserPrefs.USER_TOKEN, userToken)
            prefEditor.apply()
        }

    var userId: String?
        get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_ID, null)
        set(userId) {
            prefEditor.putString(AppConstants.Prefs.UserPrefs.USER_ID, userId)
            prefEditor.apply()
        }

    var userProfilePic: String?
        get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_PROFILE_PIC, null)
        set(userProfilePic) {
            prefEditor.putString(AppConstants.Prefs.UserPrefs.USER_PROFILE_PIC, userProfilePic)
            prefEditor.apply()
        }

    var userProfileDetails: String?
        get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_PROFILE_DETAILS, null)
        set(userProfileDetails) {
            prefEditor.putString(
                AppConstants.Prefs.UserPrefs.USER_PROFILE_DETAILS,
                userProfileDetails
            )
            prefEditor.apply()
        }

    /*  var userHomeLateLong: String?
          get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_HOME_LATE_LONG, null)
          set(userHomeLateLong) {
              prefEditor.putString(
                  AppConstants.Prefs.UserPrefs.USER_HOME_LATE_LONG,
                  userHomeLateLong
              )
              prefEditor.apply()
          }

      var userWorkLateLong: String?
          get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_WORK_LATE_LONG, null)
          set(userWorkLateLong) {
              prefEditor.putString(
                  AppConstants.Prefs.UserPrefs.USER_WORK_LATE_LONG,
                  userWorkLateLong
              )
              prefEditor.apply()
          }

      var userCurrentLatLong: String?
          get() = prefs.getString(AppConstants.Prefs.UserPrefs.USER_CURRENT_LATE_LONG, null)
          set(userCurrentLatLng) {
              prefEditor.putString(
                  AppConstants.Prefs.UserPrefs.USER_CURRENT_LATE_LONG,
                  userCurrentLatLng
              )
              prefEditor.apply()
          }

      var userSelectedPaymentMode: String?
          get() = prefs.getString(
              AppConstants.Prefs.UserPrefs.USER_SELECTED_PAYMENT_MODE,
              AppConstants.Prefs.PaymentMode.CASH_MODE
          )
          set(userSelectedPaymentMode) {
              prefEditor.putString(
                  AppConstants.Prefs.UserPrefs.USER_SELECTED_PAYMENT_MODE,
                  userSelectedPaymentMode
              )
              prefEditor.apply()
          }*/

    fun clearPrefs() {
        prefs.all.forEach {
            prefEditor.remove(it.key)
        }
        prefEditor.clear()
        prefEditor.apply()
    }
}