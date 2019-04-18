package io.navendra.feedly

object AppConstants {


    const val BASE_URL = "http://10.152.72.167:2508/"
    const val FEEDS_ENDPOINT = "feeds"
    const val FEED_ID = "feedId"
    const val FEED_ITEM_ENDPOINT = "feeds/$FEED_ID"

    object Room{
        const val FEED_DATABASE = "feedDatabase"
        const val FEED_TABLE = "feedTable"
        const val GET_ALL_FEED = "SELECT * FROM $FEED_TABLE"
    }

}