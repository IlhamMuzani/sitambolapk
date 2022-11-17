package com.amar.sitambol.ui.kelurahan

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amar.sitambol.R
import com.amar.sitambol.data.Constant
import com.amar.sitambol.data.model.kelurahan.DataKelurahan
import com.amar.sitambol.ui.bengkel.BengkelActivity

class KelurahanAdapter(
    var context: Context,
    var kelurahan: ArrayList<DataKelurahan>,
) : RecyclerView.Adapter<KelurahanAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)!!
        val cvKelurahan = view.findViewById<CardView>(R.id.cv_tempat)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_tempat, parent, false)
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val kelurahan = kelurahan[position]
        holder.tvNama.text = kelurahan.nama
        holder.cvKelurahan.setOnClickListener {
            Constant.KELURAHAN_ID = kelurahan.id!!
            context.startActivity(Intent(context, BengkelActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return kelurahan.size
    }

    fun setData(newDataKelurahan: List<DataKelurahan>) {
        kelurahan.clear()
        kelurahan.addAll(newDataKelurahan)
        notifyDataSetChanged()
    }

}