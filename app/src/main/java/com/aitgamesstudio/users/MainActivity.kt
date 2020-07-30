package com.aitgamesstudio.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() , LoaderManager.LoaderCallbacks<String>{

    val LOADER_ID = 21
    private lateinit var mUsersData: ArrayList<User>
    private lateinit  var rec_view: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var user_ : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportLoaderManager.initLoader(LOADER_ID, null, this)
        makeRequest()

        val gridColumnCount = 1

        rec_view = findViewById(R.id.recyclerview)

        rec_view.layoutManager = GridLayoutManager(this, gridColumnCount)

        mUsersData = ArrayList<User>()

        adapter = UserAdapter(this, mUsersData!!)
        rec_view.adapter = adapter

    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            val jsonObject = JSONObject(data)

            // Get the JSONArray of book items.
            val arr = jsonObject.getJSONArray("results")

            var user = arr.getJSONObject(0)
            var objectName = user.getJSONObject("name")
            var first = objectName.getString("first")
            var last = objectName.getString("last")
            var objectDoB = user.getJSONObject("dob")
            var email = user.getString("email")
            var age = objectDoB.getInt("age")
            var phone = user.getString("phone")
            var objectPhoto = user.getJSONObject("picture")
            var photo = objectPhoto.getString("thumbnail")
            var photoMed = objectPhoto.getString("medium")
//            Toast.makeText(this, photo + photoMed, Toast.LENGTH_SHORT).show()
            user_= User(first,last,photo,age,phone,email,"",photoMed)
            if(mUsersData != null)
            initializeData()

        } catch (e: Exception) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            Toast.makeText(
                    this,
                    "No url is specified. Check internet connection",
                    Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): BookLoader {
        return BookLoader(this)
    }

    private fun makeRequest() {

        // this will try to fetch a Loader with ID = LOADER_ID
        val loader: Loader<Long>? = supportLoaderManager.getLoader(LOADER_ID)
        if (loader == null) {
            /* if the Loader with the loaderID not found,
            * Initialize a New Loader with ID = LOADER_ID
            * Also pass the necessary callback which is 'this' because we've implemented it on our activity
            */
            supportLoaderManager.initLoader(LOADER_ID, Bundle.EMPTY, this)
        } else {
            /* If the Loader was found with ID = LOADER_ID,
            * Stop whatever it may be doing
            * Restart it
            * Also pass the necessary callback which is 'this' because we've implemented it on our activity
            */
            supportLoaderManager.restartLoader(LOADER_ID, Bundle.EMPTY, this)
        }
    }

    override fun onLoaderReset(loader: Loader<String>) {
        TODO("Not yet implemented")
    }

    private fun initializeData() {
        // Get the resources from the XML file.
        if(mUsersData.size <= 1) {
            for (i in 1..20) {
                mUsersData?.add(user_)
            }
            adapter.notifyDataSetChanged()
        }
    }
}