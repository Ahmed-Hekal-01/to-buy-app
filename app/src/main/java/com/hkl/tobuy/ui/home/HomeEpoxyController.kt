package com.hkl.tobuy.ui.home

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyController
import com.hkl.tobuy.R
import com.hkl.tobuy.dataabase.entity.ItemEntity
import com.hkl.tobuy.databinding.ModelItemEntityBinding
import com.hkl.tobuy.ui.epoxy.ViewBindingKotlinModel

class HomeEpoxyController(itemEntityInterface: ItemEntityInterface) : EpoxyController() {

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
            //todo show loading state
            return
        }
        if(itemEntityList.isEmpty()) {
            //todo show loading state
            return
        }

        itemEntityList.forEach { itemEntity ->
            ItemEntityModel(itemEntity).id(itemEntity.id).addTo(this)
        }
    }
    data class ItemEntityModel (
            private val itemEntity: ItemEntity
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
                // todo
            }
        }

    }
}