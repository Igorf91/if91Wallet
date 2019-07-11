package br.com.if91wallet.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.if91wallet.util.getLoaderPlaceholder
import br.com.if91wallet.vo.UserVo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_user_item_layout.view.user_item_iv
import kotlinx.android.synthetic.main.list_user_item_layout.view.user_item_name
import kotlinx.android.synthetic.main.list_user_item_layout.view.user_item_nickname

class UserViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.user_item_iv
    private val userNickname = itemView.user_item_nickname
    private val userName = itemView.user_item_name

    fun bindView(item: UserVo){
        Glide
            .with(itemView)
            .load(item.img)
            .placeholder(getLoaderPlaceholder(itemView.context))
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)

        userNickname.text = item.username
        userName.text = item.name

        itemView.setOnClickListener {
            //TODO() Open payment
        }
    }
}