package id.ac.ukdw.fti.datafilmcrud

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdateActivity : AppCompatActivity() {
    var firestore = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val title : EditText = findViewById(R.id.updateTitle)
        val rating : EditText = findViewById(R.id.updateRating)
        val year : EditText = findViewById(R.id.updateYear)
        val genre : EditText = findViewById(R.id.updateGenre)
        val language: EditText = findViewById(R.id.updatelanguage)
        val sinopsis : EditText = findViewById(R.id.updateSinopsis)
        val btnUpdte : Button = findViewById(R.id.btnUpdate)

        val judul = intent.getStringExtra("judul")
        val jenis = intent.getStringExtra("jenis")
        val tahun = intent.getStringExtra("tahun")
        val rate = intent.getStringExtra("rate")
        val sinop = intent.getStringExtra("sinop")
        val bhs = intent.getStringExtra("bahasa")


        title.setText(judul.toString())
        rating.setText(rate.toString())
        year.setText(tahun.toString())
        genre.setText(jenis.toString())
        language.setText(bhs.toString())
        sinopsis.setText(sinop.toString())

        btnUpdte.setOnClickListener{
            var judulBaru = title.text
            var ratingBaru = rating.text
            var yearBaru = year.text
            var genreBaru = genre.text
            var languageBaru = language.text
            var sinopsisBaru = sinopsis.text

            update(genreBaru.toString(),languageBaru.toString(),ratingBaru.toString(),sinopsisBaru.toString(),judulBaru.toString(),yearBaru.toString())

        }






    }

    fun update(genre:String,language:String,rating:String,sinopsis:String,judul:String,year:String){

        if(!genre.isEmpty() || language.isEmpty() || !rating.isEmpty() || !sinopsis.isEmpty() || !judul.isEmpty() || !year.isEmpty()){

            val pd = ProgressDialog(this)
            pd.setMessage("Memperbarui data...")
            pd.show()

            val titleMovie = intent.getStringExtra("judul")
            var query = titleMovie.toString()

            val data = MovieClass(genre,language,rating,sinopsis,judul,year)

            firestore = FirebaseFirestore.getInstance()
            firestore.collection("film").whereEqualTo("title",query).get()
                .addOnSuccessListener {
                    for(doc in it){
                        firestore.collection("film").document(doc.id).set(data)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    pd.dismiss()
                                    Toast.makeText(this,"Berhasil memperbarui data",Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this,MainActivity::class.java)
                                    startActivity(intent)

                                }
                        }
                            .addOnFailureListener {
                                Toast.makeText(this,"Gagal memperbarui data",Toast.LENGTH_SHORT).show()
                            }
                    }
                }

        }
        else{
            Toast.makeText(this,"Field tidak boleh kosong", Toast.LENGTH_LONG).show()
        }
    }
}