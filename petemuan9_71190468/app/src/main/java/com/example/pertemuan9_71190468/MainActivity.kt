package com.example.pertemuan9_71190468

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setSubtitle("DAAAAA")

        //viewpager
        val listFragment = arrayListOf<Fragment>(FragmentHome(),FragmentSatu(),FragmentDua(),FragmentTiga())
        val pager = findViewById<ViewPager2>
        (R.id.pager)
        val pagerAdapter = PagerAdapter(this,listFragment)
        pager.adapter = pagerAdapter
        //viewpager
    }
    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>): FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return listFragment.size
        }

        override fun createFragment(position: Int): Fragment {
            return listFragment.get(position)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_default,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_profile -> {
                val intent = Intent(this,Profile::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_send -> {
                val intent = Intent(this,Send::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_favorite -> {
                val intent = Intent(this,Favorite::class.java)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId){
//
//        R.id.menu_profile -> {
//            Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
//            this.startActivity(Intent(this,FragmentSatu::class.java))
//            true
//        }
//        R.id.menu_send -> {
//            Toast.makeText(this,"Send Message",Toast.LENGTH_SHORT).show()
//            true
//        }
//        R.id.menu_favorite ->{
//            Toast.makeText(this,"Favorite",Toast.LENGTH_SHORT).show()
//            true
//        }
//        else ->{
//            super.onOptionsItemSelected(item)
//        }
//    }
}