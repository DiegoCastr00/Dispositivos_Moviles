package com.proyecto.venus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.proyecto.venus.Models.Post
import com.proyecto.venus.Models.User
import com.proyecto.venus.R
import com.proyecto.venus.databinding.PostRvBinding
import com.proyecto.venus.utils.USER_NODE

class PostAdapter (var context: Context, var postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.MyHolder>() {
    inner class MyHolder(var binding : PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding = PostRvBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        try {
            Firebase.firestore.collection(USER_NODE).document(postList.get(position).Uid).get()
                .addOnSuccessListener {

                    var user=it.toObject<User>()
                    Glide.with(context).load(user!!.image).placeholder(R.drawable.example).into(holder.binding.profileImage)
                    holder.binding.name.text=user.name
                }

        }catch (e:Exception){
            e.printStackTrace()
        }


        Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.heart1).into(holder.binding.postImage)
        holder.binding.time.text= postList.get(position).time
        holder.binding.caption.text= postList.get(position).caption
        holder.binding.like.setOnClickListener {
            holder.binding.like.setImageResource(R.drawable.like)

        }


    }
}
