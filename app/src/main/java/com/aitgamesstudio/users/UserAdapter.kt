package com.aitgamesstudio.users

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*


class UserAdapter(
    private val context: Context,
    userList: ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.WordViewHolder?>() {
    private val mUsersList: ArrayList<User> = userList
    private val mInflater: LayoutInflater = LayoutInflater.from(context)





    inner class WordViewHolder(
        itemView: View,
        adapter: UserAdapter
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView? = null
        var image: ImageView? = null
        val mAdapter: UserAdapter
        override fun onClick(v: View) {
            // Get the position of the item that was clicked.
            val mPosition: Int = layoutPosition
            // Use that to access the affected item in mWordList.
            val user = mUsersList[mPosition]
            // Change the word in the mWordList.

            val detailedIntent = Intent(context, Profile::class.java)
            detailedIntent.putExtra("name", user.name)
            detailedIntent.putExtra("last_name", user.last_name)
            detailedIntent.putExtra("photo", user.phone)
            detailedIntent.putExtra("age", user.age)
            detailedIntent.putExtra("phone", user.phone)
            detailedIntent.putExtra("email", user.email)
            detailedIntent.putExtra("skype", user.skype)
            detailedIntent.putExtra("photo_med", user.photo_med)

            context.startActivity(detailedIntent)

        }

        init {
            name = itemView.findViewById(R.id.user)
            image = itemView.findViewById(R.id.user_image)
            mAdapter = adapter
            itemView.setOnClickListener(this)
        }

        fun bind(user: User) {
            name?.text = user.name + " " + user.last_name
            image?.let {
                Glide.with(context).load(user.photo)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(it)
            };
            //image?.let { Glide.with(context).load(R.drawable.man).into(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val mItemView: View = mInflater.inflate(
            R.layout.user_item,
            parent, false
        )
        return WordViewHolder(mItemView, this)
    }

    override fun getItemCount(): Int {
        return mUsersList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentUser: User = mUsersList[position]
        holder.bind(currentUser)
    }


}