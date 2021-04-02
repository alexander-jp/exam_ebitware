package com.mx.ebitware.mvp.clientRest

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
import com.mx.ebitware.databinding.FragmentRestBinding
import com.mx.ebitware.ext.changeActivity
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
class ClientRestFragment : BaseFragment(R.layout.fragment_rest) {

    private lateinit var _binding: FragmentRestBinding
    private val adapterList by lazy { AdapterListClient() }
    private val rPresenter: ClientRestContract.Presenter by inject()
    private var rView: ClientRestContract.View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRestBinding.bind(view)
        initSettings()
        initContract()
        initListener()
    }


    private fun initListener() {
        _binding.fabAddClient.setOnClickListener {
            val intent = Intent(activity, ClientActivity::class.java)
            intent.putExtra("type", "rest")
            startActivity(intent)
        }

        adapterList.onclick = object : OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, ClientActivity::class.java)
                intent.putExtra("type", "editRest")
                intent.putExtra("client", adapterList.list[position] as Serializable)
                startActivity(intent)
            }
        }
        _binding.refreshApiRest.setOnRefreshListener {
            rPresenter.getClient()
        }

    }

    private fun initSettings() {
        initProgress(_binding.containerRootRest)
        _binding.rvListClient.setHasFixedSize(true)
        _binding.rvListClient.layoutManager = LinearLayoutManager(requireContext())
        _binding.nestFragClientRest.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY > oldScrollY + 12 && _binding.fabAddClient.isShown){
                _binding.fabAddClient.hide()
            }
            if(scrollY < oldScrollY - 12 && !_binding.fabAddClient.isShown){
                _binding.fabAddClient.show()
            }
            if(scrollY == 0){
                _binding.fabAddClient.show()
            }
        }
    }

    private fun initContract() {
        rView = object : ClientRestContract.View {

            override fun resultGetClient(response: List<ClienteEntity>) {
                showProgress(false)
                adapterList.list = response as ArrayList<ClienteEntity>
                _binding.rvListClient.adapter = adapterList
                _binding.refreshApiRest.isRefreshing = false
            }

            override fun showError(error: Throwable) {
                showProgress(false)
                showSnackBar(error.message!!)
                _binding.refreshApiRest.isRefreshing = false
            }
        }
        rPresenter.subscribe(rView!!)
        rPresenter.getClient()

    }

    override fun onDestroyView() {
        rPresenter.unSubscribe()
        rView = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientRestFragment().apply {

        }

        @JvmStatic
        val TAG = ClientRestFragment::class.simpleName
    }
}