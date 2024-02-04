package com.wss.eat_space_iz.utils.constants

class AppConstants {

    object App {




    }

    object Prefs {

        object FirstTimeOnBoarding {
            const val Choose_a_Favourite_Food = "Choose_a_Favourite_Food"
            const val Receive_the_Great_Food = "Receive_the_Great_Food"
            const val Hot_Delivery_to_Home = "Hot_Delivery_to_Home"
        }

        object UserPrefs {
            const val IS_USER_LOGIN = "isUserLogin"
            const val USER_ID = "userId"
            const val USER_TOKEN = "userToken"
            const val USER_PROFILE_DETAILS = "userProfileDetails"
            const val USER_PROFILE_PIC = "userProfilePic"
            const val USER_HOME_LATE_LONG = "userHomeLateLong"
            const val USER_WORK_LATE_LONG = "userWorkLateLong"
            const val USER_CURRENT_LATE_LONG = "userCurrentLateLong"
            const val USER_SELECTED_PAYMENT_MODE = "userSelectedPaymentMethod"
            const val USER_EMAIL = "userEmail"
            const val USER_MOBILE_NO = "userMobileNo"
            const val USER_FULL_NAME = "userFullName"
        }

        object PaymentMode {
            const val CASH_MODE = "cash"
            const val SPACE_IZ_WALLET = "space-iz wallet"
            const val STRIPE_MODE = "strip payment"
        }
    }

    object Api {



        object BaseUrl {



            //Chat Base URL
            const val CHAT_BASE_SOCKET_URL =
                ""
            const val CHAT_BASE_URL =
                ""
            const val CHAT_HEADER = ""
            const val CHAT_BASE_URL_LOCATION =
                ""
            const val MAP_BASE_URL = ""


        }

        object UserEndUrl {



            const val MOENGAGE = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

        }

    }



}