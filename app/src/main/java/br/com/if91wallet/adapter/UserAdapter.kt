package br.com.if91wallet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.if91wallet.R
import br.com.if91wallet.vo.UserVo

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var usersList: ArrayList<UserVo> = arrayListOf()

    fun loadItems(newList: List<UserVo>) {
        usersList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user_item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = usersList[position]
        holder.bindView(item)
    }
}