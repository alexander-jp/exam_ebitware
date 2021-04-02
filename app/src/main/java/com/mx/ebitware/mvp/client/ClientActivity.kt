package com.mx.ebitware.mvp.client

import android.os.Bundle
import android.util.Log
import com.mx.ebitware.api.request.ClienteRequestBean
import com.mx.ebitware.base.BaseActivity
import com.mx.ebitware.bd.entity.ClienteEntity
import com.mx.ebitware.databinding.ActivityAddClientBinding
import com.mx.ebitware.ext.showSnackBar
import org.koin.android.ext.android.inject

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class ClientActivity : BaseActivity() {

    private val cPresenter : ClientContract.Presenter by inject()
    private var cView : ClientContract.View?=null
    private lateinit var _binding: ActivityAddClientBinding
    private var types : String?=null
    private var client : ClienteEntity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddClientBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        if(intent.getStringExtra("type")!=null){
            types = intent.getStringExtra("type")
        }
        if(intent.getSerializableExtra("client")!=null){
            client = intent.getSerializableExtra("client") as ClienteEntity
            _binding.toolbarAddClient.setTitle("Editar Cliente")
            _binding.btnSaveClient.text = "Modificar"
            setData(client)
        }

        initContract()
        initListeners()


    }

    private fun initListeners(){

        _binding.toolbarAddClient.setNavigationOnClickListener {
            finish()
        }

        _binding.btnSaveClient.setOnClickListener {
            if(validateInputs()){
                showDialogProgress(true)
                val data = ClienteRequestBean(
                    Cliente_ID = client?.clientId,
                    Nombre = _binding.txtNameClient.text.trim().toString(),
                    Apellidos = _binding.txtLastNameClient.text.trim().toString(),
                    Edad = _binding.txtAgeClient.text.toString().toInt(),
                    Contraseña = _binding.txtPasswordClient.text.trim().toString(),
                    Correo_Electronico = _binding.txtEmailClient.text.trim().toString(),
                    Peso = _binding.txtPesoClient.text.toString().toDouble(),
                    Estatura = _binding.txtHeightClient.text.toString().toDouble()
                )
               if(types=="rest"){
                   cPresenter.addClient(data)
               }else if(types =="editRest"){
                   cPresenter.editClient(data)
               }else if(types == "editLocal"){
                   cPresenter.editClientLocal(data)
               }
               else{
                   cPresenter.addClientLocal(data)
               }
            }else{
                showSnackBar(_binding.root, "Unos de los campos esta vacio")
            }
        }
    }

    private fun setData(client : ClienteEntity?){
        if(client!=null){
            _binding.txtNameClient.setText(client?.nombre)
            _binding.txtLastNameClient.setText(client?.apellidos)
            _binding.txtAgeClient.setText(client?.edad.toString())
            _binding.txtPesoClient.setText(client?.peso.toString())
            _binding.txtHeightClient.setText("")
            _binding.txtEmailClient.setText(client?.correoElectronico)
            _binding.txtPasswordClient.setText(client?.contrasenia)
        }else{
            _binding.txtNameClient.setText("")
            _binding.txtLastNameClient.setText("")
            _binding.txtPesoClient.setText("")
            _binding.txtAgeClient.setText("")
            _binding.txtPesoClient.setText("")
            _binding.txtHeightClient.setText("")
            _binding.txtEmailClient.setText("")
            _binding.txtPasswordClient.setText("")
        }
    }

    private fun initContract(){
        cView = object : ClientContract.View {
            override fun resultAddClient(response: Boolean) {
                showDialogProgress(false)
                showSnackBar(_binding.root, "Se ha registrado correctamente")
                setData(null)
            }

            override fun showError(error: Throwable) {
                showDialogProgress(false)
                showSnackBar(_binding.root, error.message)
            }

            override fun resultEditClient(response: Boolean) {
                showDialogProgress(false)
                showSnackBar(_binding.root, "Se ha modificado correctamente")
                setData(null)
            }
        }
        cPresenter.subscribe(cView!!)
    }


    private fun validateInputs() : Boolean{
        return _binding.txtNameClient.text.isNotEmpty() &&
                _binding.txtLastNameClient.text.isNotEmpty() &&
                _binding.txtAgeClient.text.isNotEmpty() &&
                _binding.txtPesoClient.text.isNotEmpty() &&
                _binding.txtHeightClient.text.isNotEmpty() &&
                _binding.txtEmailClient.text.isNotEmpty() &&
                _binding.txtPasswordClient.text.isNotEmpty()
    }

    override fun onDestroy() {
        cPresenter.unSubscribe()
        cView = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientActivity().apply {

        }

        @JvmStatic
        val TAG = ClientActivity::class.simpleName
    }
}