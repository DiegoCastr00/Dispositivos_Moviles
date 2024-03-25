package com.proyecto.venus.Post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.proyecto.venus.HomeActivity
import com.proyecto.venus.Models.Post
import com.proyecto.venus.Models.User

import com.proyecto.venus.R
import com.proyecto.venus.databinding.ActivityPostBinding
import com.proyecto.venus.utils.POST
import com.proyecto.venus.utils.POST_FOLDER
import com.proyecto.venus.utils.USER_NODE
import com.proyecto.venus.utils.USER_PROFILE_FOLDER
import com.proyecto.venus.utils.uploadImage

class PostActivity : AppCompatActivity() {
    val binding by lazy { ActivityPostBinding.inflate(layoutInflater) }

    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl = url
                }

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }


        binding.postButton.setOnClickListener {

            Firebase.firestore.collection(USER_NODE).document().get()
                .addOnSuccessListener {
                    val postUrl = imageUrl ?: ""
                    val post: Post = Post(
                        postUrl = postUrl,
                        caption = binding.caption.editText?.text.toString(),
                        Uid = Firebase.auth.currentUser!!.uid,
                        time = System.currentTimeMillis().toString()
                    )


                    Firebase.firestore.collection(POST).document().set(post)
                        .addOnSuccessListener {
                            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid)
                                .document()
                                .set(post).addOnSuccessListener {
                                    Toast.makeText(this, "Post publicado", Toast.LENGTH_SHORT)
                                        .show()
                                    startActivity(
                                        Intent(
                                            this@PostActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Fallo ocurrido, intente más tarde",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                }.addOnFailureListener {
                    Log.i("PostActivity", "Error: ${it.message}")
                }



        }

    }

}