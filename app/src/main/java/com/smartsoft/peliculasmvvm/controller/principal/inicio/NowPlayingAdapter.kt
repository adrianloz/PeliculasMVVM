package com.smartsoft.peliculasmvvm.controller.principal.inicio

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartsoft.peliculasmvvm.databinding.CardPeliculasBinding
import com.smartsoft.peliculasmvvm.models.Result
import com.smartsoft.peliculasmvvm.utils.loadImage

class NowPlayingAdapter(
    private val result: MutableList<Result>,
    private val context: Context,
    private val listener : InicioInterface?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : CardPeliculasBinding = CardPeliculasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InicioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InicioViewHolder){
            val result = result[position]
            holder.bindData(result)
        }
    }

    override fun getItemCount(): Int {
        return result.size
    }

    inner class InicioViewHolder(binding: CardPeliculasBinding) : RecyclerView.ViewHolder(binding.root) {
        private  var binding : CardPeliculasBinding = binding
        fun bindData(result: Result?){
            result!!.poster_path?.let { loadImage(context,it, binding.imagenPelicula) }
            binding.nombrePelicula.text = result.title
            binding.textAgePelicula.text = result.release_date

            binding.imagenPelicula.setOnClickListener {
                listener?.onItemClick(result)
            }
        }

    }

    interface InicioInterface{
        fun onItemClick(result: Result)
    }
}