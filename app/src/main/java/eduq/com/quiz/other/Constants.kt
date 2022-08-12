package eduq.com.quiz.other

import java.util.ArrayList

object Constants {

    var FIRST_TIME_TOGGLE: String = "FIRST_TIME_TOGGLE"

    const val ACTION_SHOW_ORDER_DETAIL_FRAGMENT = "ACTION_SHOW_ORDER_DETAIL_FRAGMENT"
    const val ACTION_SHOW_DIALOG_NOTIFICATION = "ACTION_SHOW_DIALOG_NOTIFICATION"


    const val REQUEST_CODE_LOCATION_PERMISSION = 0

    const val CART_DATABASE_NAME = "cart_db"

    const val FIELD_USER_ID = "userId"
    const val FIELD_USER_NAME = "userName"
    const val FIELD_PHONE = "phone"
    const val FIELD_RATING_VALUE = "ratingValue"
    const val FIELD_RATING_COUNT = "ratingCount"
    const val FIELD_CATEGORY_ID = "categoryId"
    const val FIELD_BRAND_ID = "brandId"
    const val FIELD_RESTAURANT_ID = "restaurantId"
    const val FIELD_FULL_NAME = "fullName"
    const val FIELD_FOOD_NAME = "name"
    const val FIELD_QUERY_FIELD = "queryField"
    const val FIELD_ORDER_DETAILS = "orderDetails"


    const val REF_USERS = "Users"
    const val REF_LEVEL = "Level"
    const val REF_CLASS = "Class"
    const val REF_MATERIAL_STUDIES = "Subject"
    const val REF_RESULT = "Result"
    const val REF_QUESTIONS = "Questions"
    const val REF_LESSONS = "Lesones"

    const val FOLDER_PROFILE_IMAGE = "profile_image"
    const val FOLDER_CART_IMAGE = "cart_image"

    //For Messagee
    const val FOLDER_FILES = "messages_files"
    const val TYPE_MESSAGE_IMAGE = "image"
    const val TYPE_MESSAGE_TEXT = "text"
    const val TYPE_MESSAGE_VOICE ="voice"
    const val TYPE_MESSAGE_FILE = "file"

    const val BUNDLE_CURRENT_RESTAURANT = "CURRENT_RESTAURANT"
    const val DEFAULT = "Default" // For Size

    //Status Order
    const val ORDER_STATUS_REQUEST = "requested" //Placed //STATUS_PLACED
    const val ORDER_STATUS_DELIVERING = "delivering" //     const val STATUS_SHIPPING = "Shipping"
    const val ORDER_STATUS_DELIVERED = "delivered" //     const val STATUS_SHIPPED = "Shipped"
    const val ORDER_STATUS_CANCELLED = "cancelled"
    const val ORDER_STATUS_REJECTED = "rejected"
    const val ORDER_STATUS_PREPARE = "prepare"
    const val ORDER_STATUS_ASSIGNED_TO_PILOT = "assignedtopilot"
    const val ORDER_STATUS_WAITING_PILOT = "waitingpilot"

    const val SHARED_PREFERENCES_NAME = "sharedPref"

    const val KEY_RESTAURANT_MODEL_JSON = "KEY_RESTAURANT_MODEL_JSON"
    const val KEY_RESTAURANT_ID = "KEY_RESTAURANT_ID"
    const val KEY_RESTAURANT_DELIVERY_COAST = "KEY_RESTAURANT_DELIVERY_COAST"
    const val KEY_USER_TOKEN_FCM = "KEY_USER_TOKEN_FCM"
    const val KEY_LESSONS_MODEL_JSON = "KEY_LESSONS_MODEL_JSON"

    const val KEY_USER_MODEL_JSON = "KEY_USER_MODEL_JSON"
    const val KEY_CITIES_JSON = "KEY_CITIES_JSON"
    const val KEY_ORDER_MODEL_JSON = "KEY_ORDER_MODEL_JSON"
    const val KEY_USER_ID = "KEY_USER_ID"
    const val KEY_PILOT_ID = "KEY_PILOT_ID"
    const val KEY_IS_GUEST = "KEY_IS_GUEST"
    const val KEY_ENLARGE = "KEY_ENLARGE"

    const val KEY_SHOW_FETCH_CART = "KEY_SHOW_FETCH_CART" // At Home Store
    const val KEY_SHOW_CASE_CART = "KEY_SHOW_CASE_CART" // At Add item in cart


    const val KEY_NOT_EXISTS = "KEY_NOT_EXISTS"
    const val KEY_USER_IS_BLOCK = "KEY_USER_IS_BLOCK"
    const val KEY_NEWS_SYSTEM = "KEY_NEWS_SYSTEM2"
    const val KEY_FCM_TOKEN = "KEY_FCM_TOKEN"

    const val SEARCH_FOOD_TIME_DELAY = 500L

    //Set const FIELD for query
    //For User
    const val QUERY_FIELD_USER_ID_RESTAURANT_ID = "query_userId_restaurantId"
    const val QUERY_FIELD_USER_ID = "userId"
    const val QUERY_FIELD_USER_ID_RESTAURANT_ID_ORDER_STATUS = "query_userId_restaurantId_orderStatus"
    //For Restaurant Manger
    const val QUERY_FIELD_RESTAURANT_ID_ORDER_STATUS = "query_restaurantId_orderStatus"
    const val QUERY_FIELD_RESTAURANT_ID_ORDER_STATUS_CREATE_AT = "query_restaurantId_orderStatus_createAt"
    const val QUERY_FIELD_RESTAURANT_ID_CREATE_AT = "query_restaurantId_createAt"

    //Remote
    const val UNDER_MAINTENANCE = "under_maintenance"
    const val IS_COMING_SOON = "isComingSoon"
    const val APP_VERSION = "app_version_user"
    const val COUNT_DOWNDATE = "countDownDate"


    //NOTIFICATION TYPES
    const val NOTIFICATION_TYPE_URL = "URL"
    const val NOTIFICATION_TYPE_ORDER_STATUS = "SELLER_ORDER_STATUS"
    const val NOTIFICATION_TYPE_NEWS_SYSTEM = "NEWS_SYSTEM"
    const val NOTIFICATION_TYPE_NEW_MESSAGE = "NEW_MESSAGE"

    const val NOTIFICATION_TYPE_NEW_ARRIVAL = "NEW_ARRIVAL"

    //NOTIFICATION TOPICS
    const val NOTIFICATION_TOPIC_GLOBAL = "global"

    //NOTIFICATION CONSTANTS
    const val NOTIFICATION_TITLE = "title"
    const val NOTIFICATION_MESSAGE = "message"
    const val NOTIFICATION_TYPE = "type"
    const val NOTIFICATION_DATA = "data"
    const val NOTIFICATION_ORDER_STATUS = "orderStatus"
    const val NOTIFICATION_ORDER_ID = "orderId"
    const val NOTIFICATION_IMAGE_URL = "imgUrl"
    const val NOTIFICATION_IS_HAVE_IMAGE = "haveImage"

    //For Contact
    var FACEBOOK_URL = "https://www.facebook.com/DelivercoApp"
    var FACEBOOK_PAGE_ID = "DelivercoApp"

    //Value Freelance
    const val FREELANCE = "freelance"
    const val STORE_TYPE_ID = "STORE_TYPE_ID"


    //Key For Tracking
    const val KEY_LOG = "tracking/log" // At Add item in cart
    const val KEY_STORE = "tracking/store" // At Add item in cart
    const val KEY_CART = "tracking/cart" // At Add item in cart
    const val KEY_CALL = "tracking/call" // At Add item in cart
    const val KEY_CHAT = "tracking/chat" // At Add item in cart



//    fun getQuestions(): ArrayList<QuestionModel> {
//        val questionsList = ArrayList<QuestionModel>()
//
//        val question1 = QuestionModel(
//            1,
//            "Which internet company began life as an online bookstore called 'Cadabra' ?",
//            "ebay",
//            "Shopify",
//            "Amazon",
//            "Overstock",
//            3
//        )
//        questionsList.add(question1)
//
//        val question2 = QuestionModel(
//            1,
//            "Which of the following languages is used as a scripting language in the Unity 3D game engine?",
//            "Java",
//            "C#",
//            "C++",
//            "Objective-C",
//            2
//        )
//        questionsList.add(question2)
//
//        val question3 = QuestionModel(
//            1,
//            "Which of these people was NOT a founder of Apple Inc?",
//            "Jonathan Ive",
//            "Steve Jobs",
//            "Ronald Wayne",
//            "Steve Wozniak",
//            1
//        )
//        questionsList.add(question3)
//
//        val question4 = QuestionModel(
//            1,
//            "What does the term GPU stand for?",
//            "Graphite Producing Unit",
//            "Gaming Processor Unit",
//            "Graphical Proprietary Unit",
//            "Graphics Processing Unit",
//            4
//        )
//        questionsList.add(question4)
//
//        val question5 = QuestionModel(
//            1,
//            "Moore's law originally stated that the number of transistors on a microprocessor chip would double every...",
//            "Year",
//            "Four Years",
//            "Two Years",
//            "Eight Years",
//            1
//        )
//        questionsList.add(question5)
//
//        val question6 = QuestionModel(
//            1,
//            "What five letter word is the motto of the IBM Computer company?",
//            "Click",
//            "Logic",
//            "Pixel",
//            "Think",
//            4
//        )
//        questionsList.add(question6)
//
//        val question7 = QuestionModel(
//            1,
//            "In programming, the ternary operator is mostly defined with what symbol(s)?",
//            "??",
//            "if then",
//            "?:",
//            "?",
//            3
//        )
//        questionsList.add(question7)
//
//        val question8 = QuestionModel(
//            1,
//            "On which computer hardware device is the BIOS chip located?",
//            "Motherboard",
//            "Hard Disk Drive",
//            "Central Processing Unit",
//            "Graphics Processing Unit",
//            1
//        )
//        questionsList.add(question8)
//
//        val question9 = QuestionModel(
//            1,
//            "What did the name of the Tor Anonymity Network orignially stand for?",
//            "The Only Router",
//            "The Orange Router",
//            "The Ominous Router",
//            "The Onion Router",
//            4
//        )
//        questionsList.add(question9)
//
//        val question10 = QuestionModel(
//            1,
//            "What was the first Android version specifically optimized for tablets?",
//            "Eclair",
//            "Honeycomb",
//            "Marshmellow",
//            "Froyo",
//            2
//        )
//        questionsList.add(question10)
//        return questionsList
//    }
}