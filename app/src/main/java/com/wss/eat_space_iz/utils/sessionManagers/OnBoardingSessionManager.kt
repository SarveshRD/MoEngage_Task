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
class OnBoardingSessionManager
@Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        context.getString(Strings.app_first_time_on_boarding_screen_pref),
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val prefEditor: SharedPreferences.Editor = prefs.edit()

    var chooseAFavouriteFood: Boolean
        get() = prefs.getBoolean(
            AppConstants.Prefs.FirstTimeOnBoarding.Choose_a_Favourite_Food,
            false
        )
        set(chooseAFavouriteFood) {
            prefEditor.putBoolean(
                AppConstants.Prefs.FirstTimeOnBoarding.Choose_a_Favourite_Food,
                chooseAFavouriteFood
            )
            prefEditor.apply()
        }

    var receiveTheGreatFood: Boolean
        get() = prefs.getBoolean(
            AppConstants.Prefs.FirstTimeOnBoarding.Receive_the_Great_Food,
            false
        )
        set(receiveTheGreatFood) {
            prefEditor.putBoolean(
                AppConstants.Prefs.FirstTimeOnBoarding.Receive_the_Great_Food,
                receiveTheGreatFood
            )
            prefEditor.apply()
        }

    var hotDeliveryToHome: Boolean
        get() = prefs.getBoolean(AppConstants.Prefs.FirstTimeOnBoarding.Hot_Delivery_to_Home, false)
        set(hotDeliveryToHome) {
            prefEditor.putBoolean(
                AppConstants.Prefs.FirstTimeOnBoarding.Hot_Delivery_to_Home,
                hotDeliveryToHome
            )
            prefEditor.apply()
        }

    fun clearPrefs() {
        prefs.all.forEach {
            prefEditor.remove(it.key)
        }
        prefEditor.apply()
    }

}