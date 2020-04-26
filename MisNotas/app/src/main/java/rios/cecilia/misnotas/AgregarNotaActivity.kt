package rios.cecilia.misnotas

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_agregar_nota.*
import java.io.*
import java.lang.Exception

class AgregarNotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        btn_guardar.setOnClickListener {
            guardar_nota();
        }
    }



    fun guardar_nota(){
        //verificar que tenga los permisos
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions( this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 235)
        }else {
            guardar()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            235 -> {
                //pregunta si el usuario acepto los permisos
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    guardar()
                } else {
                    //si no acepto, coloca un mensaje
                    Toast.makeText(this, "Error: permisos denegado", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    public fun guardar(){
        var titulo = et_titulo.text.toString()
        var cuerpo= et_contenido.text.toString()
        if(titulo=="" || cuerpo==""){
            Toast.makeText(this, "Error: campos vacios", Toast.LENGTH_SHORT).show()
        }else{
            try{
                val archivo=File(ubicacion(), titulo + ".txt")
                val fos = FileOutputStream(archivo)
                fos.write(cuerpo.toByteArray())
                fos.close()
                Toast.makeText(this, "Se guarda el archivo en la carpeta publica", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this, "Error: no se guarda el archivo", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }


    private fun ubicacion(): String{
        val carpeta= File(Environment.getExternalStorageDirectory() , "notas")
        if (!carpeta.exists()){
            carpeta.mkdir()
        }
        return carpeta.absolutePath
    }

}
