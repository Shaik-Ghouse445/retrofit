package com.example.myapplication

import MyData
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    private  val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

       recyclerView = findViewById(R.id.recyleview)

           val retrofitBuilder = Retrofit.Builder()
               .baseUrl("https://dummyjson.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(ApiInterFace::class.java)


        val retrofitData = retrofitBuilder.getproductedata()


        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
              // if api call is a success show your app
                var responsebody=response.body()
                val productList=responsebody?.products!!

                   myAdapter = MyAdapter(this@MainActivity,productList)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
             // if api failed

                Log.d("main acvitiy ", "onFailure: "+ t.message)
            }
        })

    }
}