package com.maonamassa.ondetem.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Roger on 27/05/2017.
 */

class Project: Parcelable {

    var title: String? = null
    var owner: String? = null
    var place: String? = null
    var dateStart: String? = null
    var period: String? = null
    var description: String? = null
    var minAge: Int? = null
    var minSchool: String? = null
    var mode: String? = null
    var contactPhone: String? = null
    var contactName: String? = null
    var contactEmail: String? = null
    var contactSite: String? = null
    var isApproved: Boolean = false
    var imageUrl: String? = null

    constructor() {}

    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        owner = `in`.readString()
        place = `in`.readString()
        dateStart = `in`.readString()
        period = `in`.readString()
        description = `in`.readString()
        minAge = if (`in`.readByte().toInt() == 0x00) null else `in`.readInt()
        minSchool = `in`.readString()
        mode = `in`.readString()
        contactPhone = `in`.readString()
        contactName = `in`.readString()
        contactEmail = `in`.readString()
        contactSite = `in`.readString()
        isApproved = `in`.readByte().toInt() != 0x00
        imageUrl = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(owner)
        dest.writeString(place)
        dest.writeString(dateStart)
        dest.writeString(period)
        dest.writeString(description)
        if (minAge == null) {
            dest.writeByte(0x00.toByte())
        } else {
            dest.writeByte(0x01.toByte())
            dest.writeInt(minAge!!)
        }
        dest.writeString(minSchool)
        dest.writeString(mode)
        dest.writeString(contactPhone)
        dest.writeString(contactName)
        dest.writeString(contactEmail)
        dest.writeString(contactSite)
        dest.writeByte((if (isApproved) 0x01 else 0x00).toByte())
        dest.writeString(imageUrl)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Project> = object: Parcelable.Creator<Project> {
            override fun createFromParcel(`in`: Parcel): Project {
                return Project(`in`)
            }

            override fun newArray(size: Int): Array<Project?> {
                return arrayOfNulls(size)
            }
        }
    }
}
