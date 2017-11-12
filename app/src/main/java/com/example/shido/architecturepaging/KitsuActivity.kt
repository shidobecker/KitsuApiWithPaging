package com.example.shido.architecturepaging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class KitsuActivity : AppCompatActivity() {

    private val viewModel : KitsuViewModel by lazy{
        ViewModelProviders.of(this).get(KitsuViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSearchField()
        searchForResults("Android")
    }

    private fun initKitsu() {
        val kitsuAdapter = KitsuPagedAdapter()
        searchResultsRecyclerView.adapter = kitsuAdapter
        viewModel.allKitsu.observe(this, Observer(kitsuAdapter::setList))
    }

    private fun searchForResults(queryFilter: String) {
        viewModel.setQueryFilter(queryFilter)
        initKitsu()
    }

    private fun hideIme() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(searchInputView.windowToken, 0)
    }

    private fun initSearchField() {
        searchStartView.setOnClickListener {
            hideIme()
            searchForResults(searchInputView.text.toString())
        }
        appBarView.addOnOffsetChangedListener { _, verticalOffset ->
            searchContainerView.translationY = verticalOffset.toFloat()
        }
    }

}
