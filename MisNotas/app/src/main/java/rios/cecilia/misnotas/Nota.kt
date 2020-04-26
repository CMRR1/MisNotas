package rios.cecilia.misnotas

import android.widget.BaseAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notas.view.*
import java.io.File
import java.lang.Exception


data class Nota(var titulo: String, var contenido: String)

class AdaptadorNota: BaseAdapter {

    var notas = ArrayList<Nota>()
    var contexto: Context? = null

    constructor(context: Context, peliculas: ArrayList<Nota>){
        this.contexto = context
        this.notas = peliculas
    }

    private fun ubicacion(): String{
        val carpeta= File(Environment.getExternalStorageDirectory() , "notas")
        if (!carpeta.exists()){
            carpeta.mkdir()
        }
        return carpeta.absolutePath
    }

    private fun eliminar(titulo: String){
        if (titulo==null){
            Toast.makeText(contexto, "Error: titulo vacio", Toast.LENGTH_SHORT).show()
        }else{
            try{
                val archivo= File(ubicacion(), titulo+".txt")
                archivo.delete()
                Toast.makeText(contexto, "Se elimino el archivo", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(contexto, "Error al eliminar el archivo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var nota = notas[p0]
        var inflator = LayoutInflater.from(contexto)
        var vista = inflator.inflate(R.layout.notas, null)

        vista.tv_titulo_det.text=nota.titulo
        vista.tv_contenido_det.text=nota.contenido

        vista.btn_borrar.setOnClickListener {
            eliminar(nota.titulo)
            notas.remove(nota)
            this.notifyDataSetChanged()
        }

        return vista
    }

    override fun getItem(p0: Int): Any {
        return notas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return notas.size
    }

}