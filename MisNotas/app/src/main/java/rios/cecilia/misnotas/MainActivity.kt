package rios.cecilia.misnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var notas= ArrayList<Nota>()
    lateinit var adaptador: AdaptadorNota

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    notasDePruebas()

fab.setOnClickListener{
  var intent=Intent(this,AgregarNotaActivity::class.java)
    startActivityForResult(intent, 123
    )
}
        adaptador= AdaptadorNota(this,notas)
        listview.adapter=adaptador
    }

    fun notasDePruebas(){
        notas.add(Nota("prueba 1", "contenido de la nota 1"))
        notas.add(Nota("prueba 2", "contenido de la nota 2"))
        notas.add(Nota("prueba 3", "contenido de la nota 3"))
    }
}
