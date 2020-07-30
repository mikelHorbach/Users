package com.aitgamesstudio.users

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class Profile : AppCompatActivity() {
    private lateinit var name_view : TextView
    private lateinit var cell_view : TextView
    private lateinit var age_view : TextView
    private lateinit var email_view : EditText
    private  var image_view : ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val name = intent.getStringExtra("name")
        val last_name =intent.getStringExtra("last_name")
        val photo =intent.getStringExtra("photo")
        val age =intent.getIntExtra("age",0)
        val phone =intent.getStringExtra("phone")
        var email =intent.getStringExtra("email")
        val skype =intent.getStringExtra("skype")
        val photo_med =intent.getStringExtra("photo_med")
        //Toast.makeText(this, photo_med, Toast.LENGTH_SHORT).show()

        name_view = findViewById(R.id.name_profile)
        age_view = findViewById(R.id.years)
        email_view = findViewById(R.id.email)
        cell_view = findViewById(R.id.cell_phone)
        image_view = findViewById(R.id.image_med)
        image_view?.let {
            Glide.with(this).load(photo_med)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
        name_view.text = "$name $last_name"
        age_view.text = "$age years old"
        cell_view.text = phone
        email_view.text = Editable.Factory.getInstance().newEditable(email)

    }


    fun phone(view: View) {
        val phone: String = (view as TextView).text as String
        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show()

        val intent = Intent(Intent.ACTION_CALL)

        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }
}