package rios.cecilia.misnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

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
    leerNotas()
}
        adaptador= AdaptadorNota(this,notas)
        listview.adapter=adaptador
    }

    fun leerNotas(){
        notas.clear()
        var carpeta=File(ubicacion())

        if(carpeta.exists()){
            var archivos=carpeta.listFiles()
            if(archivos !=null){
                for (archivo in archivos){
                    leerArchivo(archivo)
                }

            }
        }
    }

    fun leerArchivo(archivo:File){
        val fis= FileInputStream(archivo)
        val di= DataInputStream(fis)
        val br= BufferedReader(InputStreamReader(di))
        var striLine: String? =br.readLine()
        var myData=""

        //lee los archivos almacenados en la memoria
        while (striLine != null){
            myData=myData+striLine
            striLine=br.readLine()
        }
        br.close()
        di.close()
        fis.close()
        //elimina el .txt
        var nombre=archivo.name.substring(0, archivo.name.length-4)
        //crea la nota y la agrega a la lista
        var nota =Nota(nombre, myData)
        notas.add(nota)
    }


    private fun ubicacion(): File {

        val folder = File(Environment.getExternalStorageDirectory(), "")
        if (!folder.exists()){
            folder.mkdir()
        }
        return folder
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //se activa cuando regresa del AgregarNotaActivity
        if (requestCode==123){
            leerNotas()
            adaptador.notifyDataSetChanged()
        }
    }
    fun notasDePruebas(){
        notas.add(Nota("prueba 1", "contenido de la nota 1"))
        notas.add(Nota("prueba 2", "contenido de la nota 2"))
        notas.add(Nota("prueba 3", "contenido de la nota 3"))
    }
}
