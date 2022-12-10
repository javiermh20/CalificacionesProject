package com.example.tdpa_3p_exf

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tdpa_3p_exf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.guardarBtn.setOnClickListener {
            alta()
        }
        binding.buscarBtn.setOnClickListener {
            buscarPorNombre()
        }
        binding.editarBtn.setOnClickListener {
            modificarAlumno()
        }
        binding.eliminarBtn.setOnClickListener {
            borrarAlumno()
        }
    }

    fun alta(){
        val admin = AdminSQLiteOpenHelper(this, "administracion",null,1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("matricula", binding.idTxt.text.toString())
        registro.put("nombre", binding.nameTxt.text.toString())
        registro.put("materia", binding.materiaTxt.text.toString())
        registro.put("cal1", binding.calificacion1Txt.text.toString())
        registro.put("cal2", binding.calificacion2Txt.text.toString())
        bd.insert("alumnos",null,registro)
        bd.close()
        binding.idTxt.setText("")
        binding.nameTxt.setText("")
        binding.materiaTxt.setText("")
        binding.calificacion1Txt.setText("")
        binding.calificacion2Txt.setText("")
        Toast.makeText(this,"Se insertó correctamente el alumno", Toast.LENGTH_SHORT).show()
    }

    fun buscarPorNombre(){
        val admin = AdminSQLiteOpenHelper(this, "administracion",null,1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("Select matricula, nombre, materia, cal1, cal2 from " +
                "alumnos where nombre='${binding.nameTxt.text}'",null)
        if(fila.moveToFirst()){
            binding.idTxt.setText(fila.getString(0))
            binding.nameTxt.setText(fila.getString(1))
            binding.materiaTxt.setText(fila.getString(2))
            binding.calificacion1Txt.setText(fila.getString(3))
            binding.calificacion2Txt.setText(fila.getString(4))
            mostrarSiguiente()
        }
        else {
            Toast.makeText(this,"No hubo coincidencias con ese nombre",Toast.LENGTH_SHORT).show()
        }
    }

    fun borrarAlumno(){
        val admin = AdminSQLiteOpenHelper(this, "administracion",null,1)
        val bd = admin.writableDatabase
        val cant = bd.delete("alumnos","nombre='${binding.nameTxt.text}'",null)
        bd.close()
        if(cant == 1){
            Toast.makeText(this,"El Alumno fue eliminado",Toast.LENGTH_SHORT).show()
            binding.idTxt.setText("")
            binding.nameTxt.setText("")
            binding.materiaTxt.setText("")
            binding.calificacion1Txt.setText("")
            binding.calificacion2Txt.setText("")
        }
        else {
            Toast.makeText(this,"No hubo coincidencias con ese nombre",Toast.LENGTH_SHORT).show()
        }
    }

    fun modificarAlumno(){
        val admin = AdminSQLiteOpenHelper(this, "administracion",null,1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("matricula", binding.idTxt.text.toString())
        registro.put("materia", binding.materiaTxt.text.toString())
        registro.put("cal1", binding.calificacion1Txt.text.toString())
        registro.put("cal2", binding.calificacion2Txt.text.toString())
        val cant = bd.update("alumnos",registro,"nombre='${binding.nameTxt.text}'",null)
        bd.close()
        if(cant == 1){
            Toast.makeText(this,"El Alumno fue actualizado",Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this,"No hubo coincidencias con ese nombre",Toast.LENGTH_SHORT).show()
        }
    }

    fun mostrarSiguiente(){
        val matricula = binding.idTxt.text.toString().toInt()
        val nombre = binding.nameTxt.text.toString()
        val materia = binding.materiaTxt.text.toString()
        val cal1 = binding.calificacion1Txt.text.toString().toDouble()
        val cal2 = binding.calificacion2Txt.text.toString().toDouble()
        val intento = Intent(this, resultado::class.java);

        intento.putExtra("matricula", matricula);
        intento.putExtra("nombre", nombre)
        intento.putExtra("materia", materia)
        intento.putExtra("cal1", cal1);
        intento.putExtra("cal2", cal2);
        startActivity(intento)
    }
}