package com.kim.deryk.byung.dogviewerapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kim.deryk.byung.dogviewerapplication.DogViewerApplication
import com.kim.deryk.byung.dogviewerapplication.R
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//Name:
//Student #:

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = BreedListAdapter(OnBreedClickListener {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
        })
        findViewById<RecyclerView>(R.id.recycler_view).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        lifecycleScope.launch {
            val apiService = (application as DogViewerApplication).serviceLocator.apiService
            val response = try {
                apiService.getBreed()
            } catch (e: HttpException) {
                null
            } catch (e: IOException) {
                null
            } catch (e: Exception) {
                null
            }

            response?.let {
                adapter.breeds = it.getBreeds()
                adapter.notifyDataSetChanged()
            }
        }
    }
}