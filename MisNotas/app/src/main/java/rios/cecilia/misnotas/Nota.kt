package rios.cecilia.misnotas

import android.widget.BaseAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notas.view.*


data class Nota(var titulo: String, var contenido: String)

class AdaptadorNota: BaseAdapter {

    var notas = ArrayList<Nota>()
    var contexto: Context? = null

    constructor(context: Context, peliculas: ArrayList<Nota>){
        this.contexto = context
        this.notas = peliculas
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var nota = notas[p0]
        var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.notas, null)

        vista.tv_titulo_det.text=nota.titulo
        vista.tv_contenido_det.text=nota.contenido

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