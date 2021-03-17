package com.example.orientacao

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var names = arrayListOf<String>() //declarado e inicializado com o método que retorna uma lista vazia
    var adapter: ArrayAdapter<String>? = null //para preencher o listview devemos instanciar um
    // arrayadapter cujo trabalho
    // é pegar elementos da lista e preencher um textview que representará uma linha da listview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            names = savedInstanceState.getStringArrayList("names_list")!!
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)

        lstName.adapter = adapter
    }

    fun btnAddClick(view: View) {
        names.add(editName.text.toString())
        editName.text.clear()
        adapter?.notifyDataSetChanged() //adapter atualizado e com isso o listview tb
        hideSoftKeyboard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putStringArrayList("names_list", names)
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
//
//    fun showSoftKeyboard(view: View) {
//        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        view.requestFocus()
//        inputMethodManager.showSoftInput(view, 0)
//    }


//    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return super.onRetainCustomNonConfigurationInstance()
//    }
}