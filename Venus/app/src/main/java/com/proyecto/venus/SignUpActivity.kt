package com.proyecto.venus

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.proyecto.venus.Models.User
import com.proyecto.venus.databinding.ActivitySignUpBinding
import com.proyecto.venus.utils.USER_NODE
import com.proyecto.venus.utils.USER_PROFILE_FOLDER
import com.proyecto.venus.utils.uploadImage
import com.squareup.picasso.Picasso


class SignUpActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User
    private val launcher= registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER){
                if (it==null){

                }else{
                    user.image=it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val text = "<font color=#FF000000>Ya tienes una cuenta:</font> <font color=#1E88E5>ingresa</font>"
        binding.login.setText(Html.fromHtml(text))

        user = User()

        if (intent.hasExtra("MODE")) {
            if(intent.getIntExtra("MODE",-1)==1){
                    binding.signUpBtn.text = "Actualizar"
                    binding.login.text = ""
                    Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                        .addOnSuccessListener {
                            user = it.toObject<User>()!!
                            binding.Nombre.editText?.setText(user.name)
                            binding.Email.editText?.setText(user.email)
                            binding.Password.editText?.setText(user.password)
                            if (user.image != null) {
                                Picasso.get().load(user.image).into(binding.profileImage)
                            }
                        }
                }
        }

        binding.signUpBtn.setOnClickListener {
            if (binding.Nombre.editText?.text.toString().equals("") or
                binding.Email.editText?.text.toString().equals("") or
                binding.Password.editText?.text.toString().equals("")
            ) {
                Toast.makeText(
                    this@SignUpActivity,
                    "Por favor llena toda la información",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.Email.editText?.text.toString(),
                    binding.Password.editText?.text.toString()
                ).addOnCompleteListener { result ->

                    if (result.isSuccessful) {
                        user.name = binding.Nombre.editText?.text.toString()
                        user.email = binding.Email.editText?.text.toString()
                        user.password = binding.Password.editText?.text.toString()
                        Firebase.firestore.collection(USER_NODE)
                            .document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))

                            }
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            result.exception?.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        binding.addImage.setOnClickListener{
            launcher.launch("image/*")
        }
        binding.login.setOnClickListener{
            startActivity(Intent(this@SignUpActivity,LoginActivity::class.java))
            finish()
        }
    }
}