package com.example.myapplication

import Product
import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.lang.reflect.RecordComponent

class MyAdapter (val context : Activity, val productArrayList:List<Product>) :
RecyclerView.Adapter<MyAdapter.ViewHolder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.itemview,parent,false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem=productArrayList[position]
        holder.title.text = currentItem.title
        Picasso.get().load(currentItem.thumbnail).into(holder.image);

    }
    class ViewHolder( itemView: View):RecyclerView.ViewHolder(itemView){

        val title:TextView
        val image:ImageView

        init {

            title=itemView.findViewById(R.id.producttitle)
            image=itemView.findViewById(R.id.productimage)
        }

    }


}