package com.hkl.tobuy.ui.home

import LoadingEpoxyModel
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyController
import com.hkl.tobuy.R
import com.hkl.tobuy.dataabase.entity.ItemEntity
import com.hkl.tobuy.databinding.ModelEmptyStateBinding
import com.hkl.tobuy.databinding.ModelItemEntityBinding
import com.hkl.tobuy.ui.epoxy.ViewBindingKotlinModel

class HomeEpoxyController(private val itemEntityInterface: ItemEntityInterface) : EpoxyController() {

    var isLoading  = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var itemEntityList = ArrayList<ItemEntity>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }
    override fun buildModels() {
        if(isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }
        if(itemEntityList.isEmpty()) {
            EmptyStateEpoxyModel().id("empty_state").addTo(this)
            return
        }

        itemEntityList.forEach { itemEntity ->
            ItemEntityModel(itemEntity, itemEntityInterface).id(itemEntity.id).addTo(this)
        }
    }
    data class ItemEntityModel (
            val itemEntity: ItemEntity,
            val itemEntityInterface: ItemEntityInterface
    ) : ViewBindingKotlinModel<ModelItemEntityBinding>(R.layout.model_item_entity) {
        override fun ModelItemEntityBinding.bind() {
           titleTextView.text = itemEntity.title
            if(itemEntity.description == null) {
                descriptionTextView.isGone = true
            } else {
                descriptionTextView.isVisible = true
                descriptionTextView.text = itemEntity.description
            }
            priorityTextView.setOnClickListener {
                itemEntityInterface.onBumpPriority(itemEntity)
            }

            val priorityColor = when(itemEntity.priority) {
                1 -> android.R.color.holo_green_light
                2 -> android.R.color.holo_orange_dark
                3 -> android.R.color.holo_red_dark
                else -> android.R.color.holo_purple
            }
            priorityTextView.setBackgroundColor(ContextCompat.getColor(root.context,priorityColor))
        }

    }
    class EmptyStateEpoxyModel() : ViewBindingKotlinModel<ModelEmptyStateBinding>(R.layout.model_empty_state){
        override fun ModelEmptyStateBinding.bind() {
           // no thing to do
        }


    }
}