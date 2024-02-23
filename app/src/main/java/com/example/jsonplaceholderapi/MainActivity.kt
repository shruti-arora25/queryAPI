package com.example.jsonplaceholderapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonplaceholderapi.databinding.ActivityMainBinding
import posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        list1 = ArrayList()

        adapterC = RecyclerAdapter(list, this)
        val layout = LinearLayoutManager(this)
        bind.recycle.layoutManager = layout


        //post()
        //comments()
        //createPOst()

        updatePost()

       //onDelete()


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
                        // adapterC = RecyclerAdapter(list, this@MainActivity)
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

    private fun createPOst() {

        val postObj = posts(userId = 1, id = 0, title = "TITLE", body = "BODY")


        var retro = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)


        val data: Call<posts> = retro.create(postObj)
        data.enqueue(object : Callback<posts> {


            override fun onResponse(call: Call<posts>, response: Response<posts>) {
                if (response.isSuccessful) {

                    var dataresponse: posts? = response.body()

                    val userId = dataresponse?.userId
                    val id = dataresponse?.id
                    val title = dataresponse?.title
                    val body = dataresponse?.body

                    if (dataresponse != null) {
                        list.add(dataresponse)
                    }


                }


                adapterC = RecyclerAdapter(list, this@MainActivity)
                adapterC.notifyDataSetChanged()
                bind.recycle.adapter = adapterC

                Log.d("idhie", "SUCCESS")

            }

            override fun onFailure(call: Call<posts>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error, TRY", Toast.LENGTH_SHORT).show()
            }


        })
    }

    fun updatePost() {

        val p = posts(1, 22, null, "New Body")

        var retro = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)

        val data: Call<posts> = retro.patchPost(5,p)

        data.enqueue(object : Callback<posts> {

            override fun onResponse(call: Call<posts>, response: Response<posts>) {
                Toast.makeText(this@MainActivity, "Response " + response.code(), Toast.LENGTH_SHORT)
                    .show()

                if (response.isSuccessful) {


                    var dataresponse: posts? = response.body()

                    val userId = dataresponse?.userId
                    val id = dataresponse?.id
                    val title = dataresponse?.title
                    val body = dataresponse?.body



                    if (dataresponse != null) {
                        list.add(dataresponse)
                    }
                }

                Log.d("jo", "done3")

                adapterC = RecyclerAdapter(list, this@MainActivity)
                adapterC.notifyDataSetChanged()
                bind.recycle.adapter = adapterC



            }

            override fun onFailure(call: Call<posts>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error, TRY", Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun onDelete() {
        var retro = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)


        val data: Call<Unit> = retro.delete(1)

        data.enqueue(object : Callback<Unit> {

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

                if (response.isSuccessful)


                    Toast.makeText(
                        this@MainActivity,
                        "Response " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()


            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error, TRY", Toast.LENGTH_SHORT).show()
            }


        })


    }

}