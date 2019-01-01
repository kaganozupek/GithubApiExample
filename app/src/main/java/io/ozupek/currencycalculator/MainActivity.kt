package io.ozupek.currencycalculator

import android.annotation.SuppressLint

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import io.ozupek.currencycalculator.R.id.prgs
import io.ozupek.currencycalculator.R.id.rclRepositories
import io.ozupek.currencycalculator.network.NetworkManager
import io.ozupek.currencycalculator.network.responsemodel.Repository
import io.ozupek.currencycalculator.network.responsemodel.RepositoryResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {


    lateinit var adapter: RepositoryAdapter
    var subscription : Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {

        adapter = RepositoryAdapter(this)
        rclRepositories.layoutManager = LinearLayoutManager(this)
        rclRepositories.adapter = adapter
        //Button Click Listener
        btnSearch.setOnClickListener {
            search(etSearch.text.toString())
        }

        etSearch.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(p0: Editable?) {
                subscription?.dispose()
                search(etSearch.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })



    }

    fun search(text: String) {

        if(text.isEmpty()){
            return
        }
        showProgress()
        performSearchRequest(text)
    }

    private fun showProgress() {
        prgs.visibility = View.VISIBLE
        rclRepositories.visibility = View.GONE
    }

    private fun hideProgress() {
        prgs.visibility = View.GONE
        rclRepositories.visibility = View.VISIBLE
    }

    @SuppressLint("CheckResult")
    private fun performSearchRequest(text: String) {
        subscription = NetworkManager.instance.githubAPI.searchRepositories(text, 20, 1)
            .subscribeOn(Schedulers.io())
            .debounce(2,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.items
            }
            .subscribe({
                showResults(it)
                hideProgress()
            }, {
                it.printStackTrace()
            })

    }

    private fun showResults(response: ArrayList<Repository>) {
        hideKeyboard(this)
        adapter.datasource = response
        adapter.notifyDataSetChanged()
    }


    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
