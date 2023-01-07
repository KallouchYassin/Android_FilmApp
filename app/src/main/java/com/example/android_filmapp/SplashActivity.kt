package com.example.android_filmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android_filmapp.Adapter.MovieAdapter
import com.example.android_filmapp.IntanceClient.RClient
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.databinding.ActivityDetailMovieBinding
import com.example.android_filmapp.databinding.ActivitySplashBinding
import com.example.android_filmapp.modeldata.Movie
import com.example.android_filmapp.modeldata.MovieData
import com.example.android_filmapp.modeldata.MovieDetailData
import com.example.android_filmapp.modeldata.SearchData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks,
    EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERM = 123


    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySplashBinding.inflate(layoutInflater);
        setContentView(binding.root)
        readStorageTask()

       binding.btnGetStarted.setOnClickListener {
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

     private fun clearDataBase() {
        launch {
            this.let {
                MovieDatabase.getDatabase(this@SplashActivity).movieDao().clearDb()
            }
        }
    }

    private fun readStorageTask() {
        if (hasReadStoragePermission()) {
clearDataBase()
            getMovies()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage,",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }

    }
    fun getMovies() {


        val s="jurassic"
        val apikey="6690459f"

        RClient.instances.getMovies(s,apikey).enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                insertDataIntoRoomDb(response.body())

            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                binding.loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    private fun insertDataIntoRoomDb(body: SearchData?) {
        launch {
            this.let {

                for (arr in body!!.data!!) {
                    MovieDatabase.getDatabase(this@SplashActivity)
                        .movieDao().insert(arr)
                }
            }
        }
    }


    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }
}