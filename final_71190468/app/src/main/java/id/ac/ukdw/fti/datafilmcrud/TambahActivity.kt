package id.ac.ukdw.fti.datafilmcrud

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class TambahActivity : AppCompatActivity() {

    var firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        val title :EditText = findViewById(R.id.tmbhTitle)
        val rating:EditText = findViewById(R.id.tmbhRating)
        val year:EditText = findViewById(R.id.tmbhYear)
        val genre:EditText = findViewById(R.id.tmbhGenre)
        val sinopsis:EditText = findViewById(R.id.tmbhSinopsis)
        val language:EditText = findViewById(R.id.language)
        val btnSave:Button = findViewById(R.id.btnSave)

        firestore = FirebaseFirestore.getInstance();


        btnSave.setOnClickListener {
            var judul = title.text
            var rate = rating.text
            var tahun = year.text
            var jenis = genre.text
            var sinop = sinopsis.text
            var bahasa = language.text
            var id = UUID.randomUUID().toString()
            
            simpanKeFirestore(id,jenis.toString(),bahasa.toString(),rate.toString(),sinop.toString(),judul.toString(),tahun.toString())

        }

    }

    private fun simpanKeFirestore(id:String,jenis: String, bahasa: String, rate: String, sinop: String, judul: String, tahun: String) {
        val pd = ProgressDialog(this)
        pd.setMessage("Menyimpan ke Firestore")
        pd.show()
        if(!jenis.isEmpty() || bahasa.isEmpty() || !rate.isEmpty() || !sinop.isEmpty() || !judul.isEmpty() || !tahun.isEmpty()){
            val data = MovieClass(jenis,bahasa,rate,sinop,judul,tahun)
            firestore.collection("film")?.document(id)?.set(data).addOnCompleteListener {
                if(it.isSuccessful){
                    pd.dismiss()
                    Toast.makeText(this,"Sukses menambahkan data",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }
            }.addOnFailureListener {
                Toast.makeText(this,"Gagal menambahkan data",Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this,"Field tidak boleh kosong",Toast.LENGTH_LONG).show()
        }

    }
}