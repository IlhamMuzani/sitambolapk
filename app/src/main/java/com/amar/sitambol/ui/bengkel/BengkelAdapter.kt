package com.amar.sitambol.ui.bengkel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amar.sitambol.R
import com.amar.sitambol.data.Constant
import com.amar.sitambol.data.model.bengkel.DataBengkel
import com.amar.sitambol.ui.bengkel.detail.BengkelDetailActivity
import com.amar.sitambol.util.GlideHelper

class BengkelAdapter(
    var context: Context,
    var bengkel: ArrayList<DataBengkel>,
) : RecyclerView.Adapter<BengkelAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)!!
        val tvKeterangan = view.findViewById<TextView>(R.id.tv_keterangan)!!
        val ivGambar = view.findViewById<ImageView>(R.id.iv_gambar)!!
        val layoutBengkel = view.findViewById<CardView>(R.id.cv_bengkel)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_bengkel, parent, false)
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val bengkel = bengkel[position]

        holder.tvNama.text = bengkel.fasilitas
        holder.tvKeterangan.text = bengkel.keterangan
        GlideHelper.setImage(context, bengkel.gambar!![0].gambar!!, holder.ivGambar)

        holder.layoutBengkel.setOnClickListener {
            Constant.BENGKEL_ID = bengkel.id!!
            context.startActivity(Intent(context, BengkelDetailActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return bengkel.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDataBengkel: List<DataBengkel>) {
        bengkel.clear()
        bengkel.addAll(newDataBengkel)
        notifyDataSetChanged()
    }

}