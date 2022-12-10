package com.example.tdpa_3p_exf

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat

class resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val bundle = intent.extras
        val matricula = bundle?.getInt("matricula")
        val nombre = bundle?.getString("nombre")
        val materia = bundle?.getString("materia")
        val cal1 = bundle?.getDouble("cal1")
        val cal2 = bundle?.getDouble("cal2")

        val resNombre = findViewById<TextView>(R.id.nombreTxt)
        val resDescripcion = findViewById<TextView>(R.id.descripcionTxt)
        val res = findViewById<TextView>(R.id.resultadoTxt)
        val resimg = findViewById<ImageView>(R.id.resImg)

        //var urlTriste:String = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c4b9.png"
        //var urlFeliz:String = "https://static.vecteezy.com/system/resources/previews/001/192/193/non_2x/emoji-circle-face-happy-png.png"


        // función para sacar 10 en el final
        var calFinal = (cal1.toString().toDouble() *.2) + (cal2.toString().toDouble()*.2)
        val calpre = 10 - calFinal
        val cal10 = (calpre * 10)/6
        // función para sacar 6 en el final me ayudó mi compa pancracio del IPN
        val calpre2 = (calFinal * .4)
        val calpr3 = 6 - calpre2
        val calpre4 = calpr3/.6
        val cal6 = calpre4 - calFinal

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundoff = df.format(cal6)

        val df1 = DecimalFormat("#.##")
        df1.roundingMode = RoundingMode.DOWN
        val roundoff1 = df1.format(cal10)
        resNombre.setText("Nombre: " + nombre.toString() + " Matricula: " + matricula.toString())
        resDescripcion.setText("Calificación necesaria para sacar 10: " + roundoff1 + " Calificación necesaria para sacar 6: " + roundoff + "  Materia: " + materia.toString())

        val numero = 1..90
        val r = numero.random()
        if(calFinal.equals(4.0)){
            res.setText("Aun se puede")
            // val urlParse: Uri = Uri.parse((urlFeliz))
            // Glide.with(applicationContext).load(urlParse).into(resimg)
            Picasso.get().load("https://picsum.photos/id/${r}/200/300").into(resimg)
        } else {
            res.setText("Ya no se puede")
            // val urlParse: Uri = Uri.parse((urlTriste))
            //Glide.with(applicationContext).load(urlParse).into(resimg)
            Picasso.get().load("https://picsum.photos/id/${r}/200/300").into(resimg)
        }
    }
}