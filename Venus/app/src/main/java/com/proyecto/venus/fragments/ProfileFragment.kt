package com.proyecto.venus.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.proyecto.venus.Models.User
import com.proyecto.venus.SignUpActivity
import com.proyecto.venus.adapters.ViewPagerAdapter
import com.proyecto.venus.databinding.FragmentProfileBinding
import com.proyecto.venus.utils.USER_NODE
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editProfile.setOnClickListener {
            val intent = Intent(activity, SignUpActivity::class.java)
            intent.putExtra("MODE", 1)
            activity?.startActivity(intent)
            activity?.finish()
        }
/*
        if (!::viewPagerAdapter.isInitialized) {
            Log.d("ProfileFragment", "Initializing adapter")
            viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
            viewPagerAdapter.addFragment(MypostFragment(), "Mis publicaciones")
            viewPagerAdapter.addFragment(MyservicesFragment(), "Mis servicios")
            binding.viewPager.adapter = viewPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }*/


        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user: User = it.toObject<User>()!!
                binding.name.text = user.name
                binding.bio.text = user.email
                if (user.image != null) {
                    Picasso.get().load(user.image).into(binding.profileImage)
                }
            }.addOnFailureListener {
                Log.i("PostActivity", "Error: ${it.message}")
            }
    }
}

