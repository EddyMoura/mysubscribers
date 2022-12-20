package com.example.mysubscribers.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubscribers.data.framework.db.entity.SubscriberEntity
import com.example.mysubscribers.databinding.ItemSubscriberBinding

class SubscriberListAdapter(
    private val subscribers: List<SubscriberEntity>
) :
    RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        return SubscriberListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bind(subscribers[position])
    }

    override fun getItemCount() = subscribers.size

    class SubscriberListViewHolder(itemSubscriberBinding: ItemSubscriberBinding) :
        RecyclerView.ViewHolder(itemSubscriberBinding.root) {

        private val subscriberName = itemSubscriberBinding.textSubscriberName
        private val subscriberEmail = itemSubscriberBinding.textSubscriberEmail

        fun bind(subscriber: SubscriberEntity) {
            subscriberName.text = subscriber.name
            subscriberEmail.text = subscriber.email
        }

        companion object {
            fun create(
                parent: ViewGroup,
            ): SubscriberListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = ItemSubscriberBinding
                    .inflate(inflater, parent, false)
                return SubscriberListViewHolder(itemBinding)
            }
        }
    }
}