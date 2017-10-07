package com.maonamassa.ondetem.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.maonamassa.ondetem.FullProjectActivity
import com.maonamassa.ondetem.R
import com.maonamassa.ondetem.model.Project
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by Roger on 27/05/2017.
 */


class EventsAdapter: RecyclerView.Adapter<EventsViewHolder>() {

    var projects = ArrayList<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cell_list, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val project = projects[position]
        holder.setProject(project)
    }

    override fun getItemCount(): Int {
        return projects.size
    }
}

class EventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private var project: Project? = null
    private val avatar: ImageView
    private val title: TextView
    private val owner: TextView
    private val place: TextView
    private val period: TextView
    private val dateStart: TextView
    private var imageUrl: String? = null
    private val inflater: LayoutInflater? = null
    private val context: Context

    init {
        avatar = itemView.findViewById(R.id.avatar) as ImageView
        title = itemView.findViewById(R.id.textProjectTitle) as TextView
        owner = itemView.findViewById(R.id.textProjectOwner) as TextView
        place = itemView.findViewById(R.id.textProjectLocal) as TextView
        period = itemView.findViewById(R.id.textProjectPeriod) as TextView
        dateStart = itemView.findViewById(R.id.textProjectDateStart) as TextView
        itemView.isClickable = true
        itemView.setOnClickListener(this)
        context = itemView.context
    }

    fun setProject(project: Project) {
        this.project = project
        title.text = project.title
        owner.text = project.owner
        place.text = project.place
        period.text = project.period
        dateStart.text = project.dateStart

        // add avatar
        imageUrl = project.imageUrl
        val placeholder = context.resources.getDrawable(R.mipmap.ic_launcher)
        if (imageUrl!!.isEmpty()) {
            imageUrl = null
        }

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(placeholder)
                .error(placeholder)
                .into(avatar)
    }

    override fun onClick(v: View) {
        val intent = Intent(context, FullProjectActivity::class.java)
        intent.putExtra("project", project)
        context.startActivity(intent)
    }

}
