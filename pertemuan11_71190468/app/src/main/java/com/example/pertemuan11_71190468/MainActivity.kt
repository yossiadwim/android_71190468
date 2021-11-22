package com.example.pertemuan11_71190468

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var firestore:FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()



        val nama = findViewById<EditText>(R.id.etNama)
        val nim = findViewById<EditText>(R.id.etNim)
        val ipk = findViewById<EditText>(R.id.etIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val txtOutput = findViewById<TextView>(R.id.txtOutput)
        val btnAsc = findViewById<RadioButton>(R.id.btnAsc)
        val btnDesc = findViewById<RadioButton>(R.id.btnDesc)
        val mainButton = findViewById<RadioGroup>(R.id.mainButton)


        var valueButtonSort = ""

        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(nama.text.toString(),nim.text.toString(),ipk.text.toString().toDouble())
            nama.setText("")
            nim.setText("")
            ipk.setText("")
            firestore?.collection("mahasiswa")?.add(mahasiswa)
        }

        mainButton.setOnCheckedChangeListener {
            group, checkedId ->
                valueButtonSort = when(checkedId){
                    R.id.btnAsc -> "Ascending"
                    R.id.btnDesc -> "Descending"
                    else -> ""
                }
        }


        btnCari.setOnClickListener {
            if(valueButtonSort.equals("Ascending")){
                firestore?.collection("mahasiswa")?.whereEqualTo("nama",nama.text.toString())?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"]}\n"

                        }
                        txtOutput.setText(output)
                        btnAsc.isChecked = false
                    }
            }
            else if(valueButtonSort.equals("Descending")){
                firestore?.collection("mahasiswa")?.whereEqualTo("nama",nama.text.toString())?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"]}\n"

                        }
                        txtOutput.setText(output)
                        btnDesc.isChecked = false
                    }
            }
            else{

                if(!nama.text.toString().equals("")){
                    firestore?.collection("mahasiswa")?.whereEqualTo("nama",nama.text.toString())?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"]}\n"

                            }
                            txtOutput.setText(output)

                        }
                }
                else if(!nim.text.toString().equals("")){
                    firestore?.collection("mahasiswa")?.whereEqualTo("nim", nim.text.toString())?.get()!!
                        .addOnSuccessListener {
                                docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"]}\n"
                            }

                            txtOutput.setText(output)

                        }
                }

                else if(!ipk.text.toString().equals("")){
                    firestore?.collection("mahasiswa")?.whereEqualTo("ipk", ipk.text.toString().toDouble())?.get()!!
                        .addOnSuccessListener {
                                docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"].toString()}\n"
                            }
                            txtOutput.setText(output)
                        }
                    }
                }
            }
        }
}