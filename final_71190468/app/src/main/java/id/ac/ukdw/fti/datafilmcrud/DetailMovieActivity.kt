package id.ac.ukdw.fti.datafilmcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val title = findViewById<TextView>(R.id.title)
        val rating = findViewById<TextView>(R.id.rating)
        val year = findViewById<TextView>(R.id.year)
        val genre = findViewById<TextView>(R.id.genre)
        val language = findViewById<TextView>(R.id.language)
        val sinopsis = findViewById<TextView>(R.id.sinopsis)

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

    }
}