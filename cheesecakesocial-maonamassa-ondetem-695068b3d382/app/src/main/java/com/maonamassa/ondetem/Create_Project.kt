package com.maonamassa.ondetem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.maonamassa.ondetem.model.Project

class Create_Project: AppCompatActivity() {

    private var editTitle: EditText? = null
    private var editOwner: EditText? = null
    private var editPlace: EditText? = null
    private var editDescription: EditText? = null
    private var editMinAge: EditText? = null
    private var editMinSchool: EditText? = null
    private var editDateStart: EditText? = null
    private var editPeriod: EditText? = null
    private var editContactName: EditText? = null
    private var editContactPhone: EditText? = null
    private var editContactEmail: EditText? = null
    private var editContactSite: EditText? = null
    private var editImageURL: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_project)
        editTitle = findViewById(R.id.editTextTitle) as EditText
        editOwner = findViewById(R.id.editTextOwner) as EditText
        editPlace = findViewById(R.id.editTextPlace) as EditText
        editDescription = findViewById(R.id.editTextDescription) as EditText
        editMinAge = findViewById(R.id.editTextMinAge) as EditText
        editMinSchool = findViewById(R.id.editTextMinSchool) as EditText
        editDateStart = findViewById(R.id.editTextDateStart) as EditText
        editPeriod = findViewById(R.id.editTextPeriod) as EditText
        editContactName = findViewById(R.id.editTextContactName) as EditText
        editContactEmail = findViewById(R.id.editTextContactEmail) as EditText
        editContactPhone = findViewById(R.id.editTextContactPhone) as EditText
        editContactSite = findViewById(R.id.editTextContactSite) as EditText
        editImageURL = findViewById(R.id.editTextImageURL) as EditText

        val createButton = findViewById(R.id.createButton) as Button
        createButton.setOnClickListener {
            val allValidFields = validateData()
            if (allValidFields!!) {
                createData()
            }
        }
    }

    internal fun validateData(): Boolean? {

        if (editTitle!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite o Título do Projeto", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editOwner!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite o Criador do Projeto", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editPlace!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite o Local Do Projeto", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editDescription!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite a Descrição", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editMinAge!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite a Idade Minima", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editDateStart!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite a Data de Inicio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editPeriod!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Escolha um Periodo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editContactName!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite o Nome do Contato", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editContactPhone!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite o Telefone do Contato", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editContactEmail!!.text.toString().isEmpty()) {
            Toast.makeText(this@Create_Project, "Digite o Email do Contato", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    internal fun createData() {

        val project = Project()
        project.title = editTitle!!.text.toString()
        project.owner = editOwner!!.text.toString()
        project.place = editPlace!!.text.toString()
        project.description = editDescription!!.text.toString()
        if (editMinAge!!.text != null)
            project.minAge = Integer.parseInt(editMinAge!!.text.toString())
        project.minSchool = editMinSchool!!.text.toString()
        project.dateStart = editDateStart!!.text.toString()
        project.period = editPeriod!!.text.toString()
        project.contactName = editContactName!!.text.toString()
        project.contactEmail = editContactEmail!!.text.toString()
        project.contactPhone = editContactPhone!!.text.toString()
        project.contactSite = editContactSite!!.text.toString()
        project.imageUrl = editImageURL!!.text.toString()

        val databaseReference = FirebaseDatabase.getInstance().reference.child("projects")
        val key = databaseReference.push().key
        databaseReference.child(key).setValue(project).addOnCompleteListener { task ->
            val success = if (task.isSuccessful) "Evento enviado! Aguarde a aprovação..." else "Ops! Ocorreu uma falha."
            Toast.makeText(this@Create_Project, success, Toast.LENGTH_SHORT).show()
            this@Create_Project.finish()
        }
    }

}
