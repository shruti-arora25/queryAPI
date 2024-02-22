package com.example.jsonplaceholderapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonplaceholderapi.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var list: ArrayList<posts>
    private lateinit var list1: ArrayList<comments>

    private lateinit var adapterC: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        list = ArrayList()
        list1=ArrayList()

        adapterC = RecyclerAdapter(list, this)
        val layout = LinearLayoutManager(this)
        bind.recycle.layoutManager = layout


        post()
        //comments()


    }


    private fun comments() {


        var retro = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)

        val data: Call<List<comments>> = retro.getComments(2)

        data.enqueue(object : Callback<List<comments>> {

            override fun onResponse(
                call: Call<List<comments>>,
                response: Response<List<comments>>
            ) {
                if (response.isSuccessful) {

                    var dataResponse: List<comments>? = response.body()

                    if (dataResponse != null) {
                        for (mydata in dataResponse) {
                            list1.add(mydata)
                        }


                       // adapterC = RecyclerAdapter(list1, this@MainActivity)
                        adapterC.notifyDataSetChanged()

                        bind.recycle.adapter = adapterC
                    }
                }
            }

            override fun onFailure(call: Call<List<comments>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }


        })


    }


    private fun post() {


        var retro = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)


        val data: Call<List<posts>> = retro.getData(1)


        data.enqueue(object : Callback<List<posts>> {
            override fun onResponse(call: Call<List<posts>>, response: Response<List<posts>>) {

                if (response.isSuccessful) {

                    var dataResponse: List<posts>? = response.body()

                    if (dataResponse != null) {
                        for (mydata in dataResponse) {
                            list.add(mydata)
                        }


                        adapterC = RecyclerAdapter(list, this@MainActivity)
                        adapterC.notifyDataSetChanged()

                        bind.recycle.adapter = adapterC
                    }
                }
            }

            override fun onFailure(call: Call<List<posts>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }


        })


    }

}