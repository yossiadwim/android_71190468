package id.ac.ukdw.fti.datafilmcrud

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MovieAdapter(var context: Context,var listMovie: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        holder.bindMovie(listMovie[position])

        //val movie : Movie = listMovie.get(position)
//        holder.title.text = movie.title
//        holder.rating.text = movie.rating
//        holder.year.text = movie.year
//        holder.genre.text = movie.genre
//        holder.sinopsis.text = movie.sinopsis
//        holder.language.text = movie.lang
//        holder.title.setText(movie.judul)
//        holder.rating.setText(movie.rate)
//        holder.year.setText(movie.tahun)
//        holder.genre.setText(movie.jenis)
//        holder.sinopsis.setText(movie.sinop)
//        holder.language.setText(movie.bahasa)

    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    inner class MovieHolder (view:View):RecyclerView.ViewHolder(view) {

        var firestore = Firebase.firestore

        val title : TextView = view.findViewById(R.id.title)
        val rating : TextView = view.findViewById(R.id.rating)
        val year : TextView = view.findViewById(R.id.year)
        val sinopsis : TextView = view.findViewById(R.id.sinopsis)
        val genre: TextView = view.findViewById(R.id.genre)
        val language: TextView = view.findViewById(R.id.language)
        val menus:ImageView = view.findViewById(R.id.more)



        init {
            menus.setOnClickListener {
                popUpMenu(it)
            }
        }

        private fun popUpMenu(view: View?) {
            val popup = PopupMenu(context,view)
            val mv = ArrayList<MovieClass>()
            popup.inflate(R.menu.show_menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.edit ->{
                        val intent = Intent(context,UpdateActivity::class.java)
                        intent.putExtra("judul",title.text.toString())
                        intent.putExtra("rate",rating.text.toString())
                        intent.putExtra("tahun",year.text.toString())
                        intent.putExtra("jenis",genre.text.toString())
                        intent.putExtra("bahasa",language.text.toString())
                        intent.putExtra("sinop",sinopsis.text.toString())
                        context.startActivity(intent)
                        true
                    }
                    R.id.delete ->{
                        val judul = title.text
                        val pd = ProgressDialog(context)
                        pd.setMessage("Menghapus....")
                        pd.show()
                        firestore = FirebaseFirestore.getInstance()
                        firestore.collection("film").whereEqualTo("title",judul).get()
                            .addOnSuccessListener {
                                for(doc in it){
                                    firestore.collection("film").document(doc.id).delete()
                                }
                                listMovie.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                pd.dismiss()
                            }
                        true
                    }
                    else-> true
                }

            }
            popup.show()
        }

        fun bindMovie(movie: Movie){
            title.text = movie.title
            rating.text = movie.rating
            year.text = movie.year
            sinopsis.text = movie.sinopsis
            genre.text = movie.genre
            language.text = movie.lang

            itemView.setOnClickListener {
                val intent = Intent(context,DetailMovieActivity::class.java)
                intent.putExtra("judul",title.text.toString())
                intent.putExtra("rate",rating.text.toString())
                intent.putExtra("tahun",year.text.toString())
                intent.putExtra("jenis",genre.text.toString())
                intent.putExtra("bahasa",language.text.toString())
                intent.putExtra("sinop",sinopsis.text.toString())
                context.startActivity(intent)
            }
        }


    }

}




