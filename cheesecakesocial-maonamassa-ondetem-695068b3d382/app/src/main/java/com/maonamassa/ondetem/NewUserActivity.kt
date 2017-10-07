package com.maonamassa.ondetem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewUserActivity: AppCompatActivity() {

    private var userCompany: EditText? = null
    private var userName: EditText? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var userRePassword: EditText? = null
    private var userPhone: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        userCompany = findViewById(R.id.editTextCompany) as EditText
        userName = findViewById(R.id.editTextName) as EditText
        userEmail = findViewById(R.id.editTextEmail) as EditText
        userPassword = findViewById(R.id.editTextPassword) as EditText
        userRePassword = findViewById(R.id.editTextRePassword) as EditText
        userPhone = findViewById(R.id.editTextPhone) as EditText


        val createButton = findViewById(R.id.createButton) as Button
        createButton.setOnClickListener {
            val allValidFields = validateData()
            if (allValidFields!!) {
                createData()
            }
        }
    }

    internal fun validateData(): Boolean? {

        if (userCompany!!.text.toString().isEmpty()) {
            Toast.makeText(this@NewUserActivity, "Digite o nome da Entidade / Empresa", Toast.LENGTH_SHORT).show()
            return false
        }
        if (userName!!.text.toString().isEmpty()) {
            Toast.makeText(this@NewUserActivity, "Digite o seu nome completo", Toast.LENGTH_SHORT).show()
            return false
        }
        if (userEmail!!.text.toString().isEmpty()) {
            Toast.makeText(this@NewUserActivity, "Digite o e-mail", Toast.LENGTH_SHORT).show()
            return false
        }
        if (userPassword!!.text.toString().isEmpty()) {
            Toast.makeText(this@NewUserActivity, "Digite a sua senha!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (userRePassword!!.text.toString().isEmpty()) {
            Toast.makeText(this@NewUserActivity, "Confirme a sua senha!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!userRePassword!!.text.toString().isEmpty() && userRePassword!!.text.toString() !== userPassword!!.text.toString()) {
            Toast.makeText(this@NewUserActivity, "As senhas estão diferentes!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (userPhone!!.text.toString().isEmpty()) {
            Toast.makeText(this@NewUserActivity, "Digite o Telefone!", Toast.LENGTH_SHORT).show()
            return false
        }


        return true
    }

    internal fun createData() {

        //        Project project = new Project();
        //        project.setTitle(editTitle.getText().toString());
        //        project.setOwner(editOwner.getText().toString());
        //        project.setPlace(editPlace.getText().toString());
        //        project.setDescription(editDescription.getText().toString());
        //        if (editMinAge.getText() != null)
        //            project.setMinAge(Integer.parseInt(editMinAge.getText().toString()));
        //        project.setMinSchool(editMinSchool.getText().toString());
        //        project.setDateStart(editDateStart.getText().toString());
        //        project.setPeriod(editPeriod.getText().toString());
        //        project.setContactName(editContactName.getText().toString());
        //        project.setContactEmail(editContactEmail.getText().toString());
        //        project.setContactPhone(editContactPhone.getText().toString());
        //        project.setContactSite(editContactSite.getText().toString());
        //        project.setImageUrl(editImageURL.getText().toString());
        //
        //        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("projects");
        //        String key = databaseReference.push().getKey();
        //        databaseReference.child(key).setValue(project).addOnCompleteListener(new OnCompleteListener<Void>() {
        //            @Override
        //            public void onComplete(@NonNull Task<Void> task) {
        //                String success = task.isSuccessful() ? "Evento enviado! Aguarde a aprovação..." : "Ops! Ocorreu uma falha.";
        //                Toast.makeText(Create_Project.this, success, Toast.LENGTH_SHORT).show();
        //                Create_Project.this.finish();
        //            }
        //        });
    }
}
