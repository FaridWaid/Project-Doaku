package com.faridwaid.doaku.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.faridwaid.doaku.R
import de.hdodenhof.circleimageview.CircleImageView

class AboutDeveloperActivity : AppCompatActivity() {

    //title
    private var title: String = "Informasi Developer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_developer)

        //action bar
        val actionBar = supportActionBar
        actionBar!!.title = title

        //back button
        actionBar.setDisplayHomeAsUpEnabled(true)

        //set untuk link ke github
        val urlGithub: CircleImageView = findViewById(R.id.github)
        urlGithub.setOnClickListener {
            val uri: Uri = Uri.parse("https://github.com/FaridWaid")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        //set untuk link ke instagram
        val urlInstagram: CircleImageView = findViewById(R.id.instagram)
        urlInstagram.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.instagram.com/farid.waid/")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        //set untuk link ke facebook
        val urlFacebook: CircleImageView = findViewById(R.id.facebook)
        urlFacebook.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.facebook.com/mu111/")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}