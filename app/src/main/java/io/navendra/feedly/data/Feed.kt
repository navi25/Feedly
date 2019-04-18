package io.navendra.feedly.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import io.navendra.feedly.AppConstants.Room as RoomConstants
import java.util.*

@Parcelize
@Entity(tableName = RoomConstants.FEED_TABLE)
data class Feed(

    @PrimaryKey
    @Json(name="id") val id: String,
    @Json(name="feed") val feed:String,
    @Json(name = "user") val user:String,
    @Json(name = "user_id") val userId:Long,
    @Json(name = "client_id") val clientId:Long,
    @Json(name = "created_at") val createdAt : Date

) : Parcelable