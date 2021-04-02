package com.mx.ebitware.mvp.clientLocal

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.mx.ebitware.R
import com.mx.ebitware.adapter.AdapterListClient
import com.mx.ebitware.base.BaseFragment
import com.mx.ebitware.bd.entity.ClienteEntity
import com.mx.ebitware.databinding.FragmentBdLocalBinding
import com.mx.ebitware.ext.showSnackBar
import com.mx.ebitware.interfaces.OnItemClickListener
import com.mx.ebitware.mvp.client.ClientActivity
import org.koin.android.ext.android.inject
import java.io.Serializable

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

@RequiresApi(Build.VERSION_CODES.M)
class ClientLocalFragment : BaseFragment(R.layout.fragment_bd_local) {


    private val adapterList by lazy { AdapterListClient() }
    private lateinit var _binding: FragmentBdLocalBinding
    private val cPresenter: ClientLocalContract.Presenter by inject()
    private var cView: ClientLocalContract.View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBdLocalBinding.bind(view)
        initSettings()
        initContract()
        initListeners()
    }

    private fun initSettings() {
        initProgress(_binding.containerRootLocal)
        _binding.rvListClientLocal.setHasFixedSize(true)
        _binding.rvListClientLocal.layoutManager = LinearLayoutManager(requireContext())
        _binding.nestFragClientLocal.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY > oldScrollY + 12 && _binding.fabAddClientLocal.isShown){
                _binding.fabAddClientLocal.hide()
            }
            if(scrollY < oldScrollY - 12 && !_binding.fabAddClientLocal.isShown){
                _binding.fabAddClientLocal.show()
            }
            if(scrollY == 0){
                _binding.fabAddClientLocal.show()
            }
        }
    }

    private fun initContract() {
        cView = object : ClientLocalContract.View {
            override fun showError(error: Throwable) {
                showProgress(false)
                showSnackBar(error.message!!)
                _binding.refreshBdLocal.isRefreshing = false
            }

            override fun resultGetClients(response: List<ClienteEntity>) {
                showProgress(false)
                adapterList.list = response as ArrayList<ClienteEntity>
                _binding.rvListClientLocal.adapter = adapterList
                _binding.refreshBdLocal.isRefreshing = false
            }
        }
        cPresenter.subscribe(cView!!)
        cPresenter.getClients()
    }

    private fun initListeners() {
        _binding.fabAddClientLocal.setOnClickListener {
            val intent = Intent(activity, ClientActivity::class.java)
            intent.putExtra("type", "local")
            startActivity(intent)
        }
        adapterList.onclick = object :OnItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, ClientActivity::class.java)
                intent.putExtra("type", "editLocal")
                intent.putExtra("client", adapterList.list[position] as Serializable)
                startActivity(intent)
            }
        }
        _binding.refreshBdLocal.setOnRefreshListener {
            cPresenter.getClients()
        }
    }

    override fun onDestroyView() {
        cPresenter.unSubscribe()
        cView = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientLocalFragment().apply {

        }

        @JvmStatic
        val TAG = ClientLocalFragment::class.simpleName
    }
}