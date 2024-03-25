package com.proyecto.venus.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.proyecto.venus.Models.Post
import com.proyecto.venus.R
import com.proyecto.venus.adapters.MyPostRvAdapter
import com.proyecto.venus.databinding.FragmentMypostBinding

class MypostFragment : Fragment() {
    private lateinit var binding: FragmentMypostBinding
    private lateinit var postList: ArrayList<Post>
    private lateinit var adapter: MyPostRvAdapter
    private var dataLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMypostBinding.inflate(inflater, container, false)
        postList = arrayListOf<Post>()
        adapter = MyPostRvAdapter(requireContext(), postList)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = adapter
        binding.rv.setHasFixedSize(true)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        Log.d("MypostFragment", "onResume called")
        if (!dataLoaded) {
            loadData()
            dataLoaded = true
        }
    }

    private fun loadData() {
        Log.d("MypostFragment", "Loading data")
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                requireActivity().runOnUiThread {
                    var tempList = arrayListOf<Post>()
                    for (doc in it.documents) {
                        val post = doc.toObject<Post>()
                        post?.let { it1 -> tempList.add(it1) }
                    }
                    postList.clear()
                    postList.addAll(tempList)
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.e("MypostFragment", "Error loading data: ${exception.message}")
            }
    }
}