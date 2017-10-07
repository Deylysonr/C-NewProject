package com.maonamassa.ondetem

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.maonamassa.ondetem.adapter.EventsAdapter
import com.maonamassa.ondetem.admin.LoginActivity
import com.maonamassa.ondetem.model.Project
import io.fabric.sdk.android.Fabric
import java.util.*

class MainActivity: AppCompatActivity() {
    private var adapter: EventsAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var newProjectButton: FloatingActionButton? = null
    private var admin: Boolean = false

    private var mAuth: FirebaseAuth? = null
    // ...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_main)
        //        try {
        //            FirebaseAuth.getInstance().signOut();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        recyclerView = findViewById(R.id.initList) as RecyclerView
        adapter = EventsAdapter()
        recyclerView!!.adapter = adapter
        newProjectButton = findViewById(R.id.newProjectButton) as FloatingActionButton
        fetchData()
        newProjectButton!!.setOnClickListener {
            //Intent intent = new Intent(MainActivity.this, Create_Project.class);
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            this@MainActivity.startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            admin = true

            Log.d("User", "+++++" + currentUser.email!!.toString())
        }
        // Check if user is signed in (non-null) and update UI accordingly.


    }

    internal fun fetchData() {
        Toast.makeText(this@MainActivity, "Aguarde... Listando atividades.", Toast.LENGTH_LONG).show()

        val databaseReference = FirebaseDatabase.getInstance().reference.child("projects")
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return
                }
                val iterator = dataSnapshot.children.iterator()
                val projects = ArrayList<Project>()
                while (iterator.hasNext()) {
                    val project = iterator.next().getValue<Project>(Project::class.java)// cria novo objeto apartir de cada objeto do json
                    projects.add(project) // adiciona na lista de projetos
                }
                val filteredProjects = ArrayList<Project>()

                for (project in projects) {
                    if (admin) {
                        filteredProjects.add(project)
                    } else {

                        if (project.isApproved) filteredProjects.add(project)
                    }
                }
                adapter!!.projects = filteredProjects
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }


}
