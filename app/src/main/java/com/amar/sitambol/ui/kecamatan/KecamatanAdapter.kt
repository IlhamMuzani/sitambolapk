package com.amar.sitambol.ui.kecamatan

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
import com.amar.sitambol.data.model.kecamatan.DataKecamatan
import com.amar.sitambol.ui.kelurahan.KelurahanActivity

class KecamatanAdapter(
    var context: Context,
    var kecamatan: ArrayList<DataKecamatan>,
) : RecyclerView.Adapter<KecamatanAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)!!
        val layoutKecamatan = view.findViewById<CardView>(R.id.cv_tempat)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_tempat, parent, false)
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val kecamatan = kecamatan[position]
        holder.tvNama.text = kecamatan.nama
        holder.layoutKecamatan.setOnClickListener {
            Constant.KECAMATAN_ID = kecamatan.id!!
            context.startActivity(Intent(context, KelurahanActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return kecamatan.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDataKecamatan: List<DataKecamatan>) {
        kecamatan.clear()
        kecamatan.addAll(newDataKecamatan)
        notifyDataSetChanged()
    }

}