package com.maonamassa.ondetem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.maonamassa.ondetem.model.Project
import com.squareup.picasso.Picasso

class FullProjectActivity: AppCompatActivity() {
    private var project: Project? = null
    private var textTitle: TextView? = null
    private var textOwner: TextView? = null
    private var textPlace: TextView? = null
    private var textDateStart: TextView? = null
    private var textPeriod: TextView? = null
    private var textDescription: TextView? = null
    private var textMinAge: TextView? = null
    private var textMinSchool: TextView? = null
    private var textContactName: TextView? = null
    private var textContactEmail: TextView? = null
    private var textContactPhone: TextView? = null
    private var textContactSite: TextView? = null
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_project)
        project = intent.extras.getParcelable("project")
        Log.d("FullProject", project!!.title)
        textTitle = this.findViewById(R.id.textProjectTitle) as TextView
        textOwner = this.findViewById(R.id.textProjectOwner) as TextView
        textPlace = this.findViewById(R.id.textProjectLocal) as TextView
        textDateStart = this.findViewById(R.id.textProjectDateStart) as TextView
        textPeriod = this.findViewById(R.id.textProjectPeriod) as TextView
        textDescription = this.findViewById(R.id.textDescription) as TextView
        textMinAge = this.findViewById(R.id.textMinAge) as TextView
        textMinSchool = this.findViewById(R.id.textMinSchool) as TextView
        textContactName = this.findViewById(R.id.textContactName) as TextView
        textContactEmail = this.findViewById(R.id.textContactEmail) as TextView
        textContactPhone = this.findViewById(R.id.textContactPhone) as TextView
        textContactSite = this.findViewById(R.id.textContactSite) as TextView
        setImageAvatar()
        setTexts()


    }

    private fun setImageAvatar() {
        val avatar = this.findViewById(R.id.avatar) as ImageView
        imageUrl = project!!.imageUrl
        val placeholder = this.resources.getDrawable(R.mipmap.ic_launcher)

        if (imageUrl!!.isEmpty()) {
            imageUrl = null
        }

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(placeholder)
                .error(placeholder)
                .into(avatar)
    }

    private fun setTexts() {
        textTitle!!.text = project!!.title
        textOwner!!.text = project!!.owner
        textPlace!!.text = project!!.place
        textDateStart!!.text = project!!.dateStart
        textPeriod!!.text = project!!.period
        textDescription!!.text = project!!.description
        textMinAge!!.text = project!!.minAge!!.toString()
        textMinSchool!!.text = project!!.minSchool
        textContactName!!.text = project!!.contactName
        textContactEmail!!.text = project!!.contactEmail
        textContactPhone!!.text = project!!.contactPhone
        textContactSite!!.text = project!!.contactSite

    }
}
