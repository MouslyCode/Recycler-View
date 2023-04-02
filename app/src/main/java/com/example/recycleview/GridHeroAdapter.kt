package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recycleview.Hero
import com.example.recycleview.ListHeroAdapter
import com.example.recycleview.R

class GridHeroAdapter (val listHeroes:ArrayList<Hero>):
    RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): GridViewHolder {

        val view: View =
            LayoutInflater.from(ViewGroup.context).inflate(R.layout.item_grid_hero, ViewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listHeroes[position].photo)
            .apply(RequestOptions().override(350,550))
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listHeroes[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto : ImageView =
            itemView.findViewById(R.id.img_item_photo)
    }

}

