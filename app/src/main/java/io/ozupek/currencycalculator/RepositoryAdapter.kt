package io.ozupek.currencycalculator

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.ozupek.currencycalculator.network.responsemodel.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * Created by VNGRS on 1.01.2019.
 */

class RepositoryAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var datasource = ArrayList<Repository>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(context).inflate(R.layout.item_repository,p0,false)
        return RepositoryViewHolder(v)
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        val holder = p0 as RepositoryViewHolder
        var repo = datasource[p1]
        holder.itemView.txTitle.text = repo.name
        holder.itemView.txFullName.text = repo.fullName
        holder.itemView.txOwnerName.text = repo.owner.login

        Picasso.get().load(repo.owner.avatarUrl).into(holder.itemView.imgOwner)
    }

}


class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)