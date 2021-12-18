package id.ac.ukdw.fti.datafilmcrud

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var listMovie: ArrayList<Movie>
    private lateinit var movieAdapter: MovieAdapter
    var firestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_custom))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        auth = FirebaseAuth.getInstance()
        val btnCari = findViewById<ImageButton>(R.id.btnSearch)
        val cari: EditText = findViewById(R.id.search)
        val status :TextView = findViewById(R.id.status)

        val scroll:NestedScrollView =  findViewById(R.id.scrollMain)

        firestore = Firebase.firestore
        recyclerView = findViewById(R.id.rcyFilm)
        recyclerView.isNestedScrollingEnabled = false
        listMovie = ArrayList()


        btnCari.setOnClickListener {
            val pd = ProgressDialog(this)
            pd.setMessage("Loading...")
            pd.show()

            listMovie.clear()
            val query = cari.text.toString().trim()
            firestore = FirebaseFirestore.getInstance()
            firestore.collection("film").get().addOnSuccessListener {

                for (doc in it) {
                    var check = true
                    val data = Movie(
                        "${doc["genre"]}",
                        "${doc["langueage"]}",
                        "${doc["rating"]}",
                        "${doc["sinopsis"]}",
                        "${doc["title"]}",
                        "${doc["year"]}"
                    )
                    if ("${doc["title"]}".equals(query) && check) {
                        check = false
                        listMovie.add(data)

                    }
                    if ("${doc["language"]}".equals(query) && check) {
                        check = false
                        listMovie.add(data)

                    }
                    if ("${doc["rating"]}".equals(query)) {
                        check = false
                        listMovie.add(data)

                    }
                    if ("${doc["year"]}".equals(query)) {
                        check = false
                        listMovie.add(data)

                    }

                }
                pd.dismiss()
            }
            Handler().postDelayed({
                if(listMovie.size == 0){
                    recyclerView.visibility = View.GONE
                    status.visibility = View.VISIBLE
                    status.setText("$query tidak ditemukan")
                }
                else{
                    var movieAdapter = MovieAdapter(this, listMovie)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = movieAdapter
                    }
                }

            }, 1000)

        }

        getData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_custom,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId){
        R.id.logOut ->{
            auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.addData ->{
            val intent = Intent(this,TambahActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.refresh ->{
            firestore.collection("film").orderBy("title",Query.Direction.ASCENDING).get().addOnSuccessListener {
                var listMovie:ArrayList<Movie> = ArrayList()
                listMovie.clear()

                for(doc in it){
                    listMovie.add((Movie(
                        doc.data.get("genre").toString(),
                        doc.data.get("language").toString(),
                        doc.data.get("rating").toString(),
                        doc.data.get("sinopsis").toString(),
                        doc.data.get("title").toString(),
                        doc.data.get("year").toString()
                    )))

                }

                var movieAdapter = MovieAdapter(this,listMovie)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = movieAdapter
                }

            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            true
        }

        else ->{
            super.onOptionsItemSelected(item)
        }
    }

    fun getData(){
        val pd =ProgressDialog(this)
        pd.setMessage("Loading")
        pd.show()
        firestore.collection("film").orderBy("title",Query.Direction.ASCENDING).get()
            .addOnSuccessListener {
            var listMovie:ArrayList<Movie> = ArrayList()
            listMovie.clear()

            for(doc in it){
                listMovie.add((Movie(
                    doc.data.get("genre").toString(),
                    doc.data.get("language").toString(),
                    doc.data.get("rating").toString(),
                    doc.data.get("sinopsis").toString(),
                    doc.data.get("title").toString(),
                    doc.data.get("year").toString()
                )))

            }
            pd.dismiss()

            var movieAdapter = MovieAdapter(this,listMovie)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

        }
    }

}