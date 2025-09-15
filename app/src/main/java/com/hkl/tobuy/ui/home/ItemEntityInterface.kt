package com.hkl.tobuy.ui.home

import com.hkl.tobuy.dataabase.entity.ItemEntity

interface ItemEntityInterface {
    fun onBumpPriority(itemEntity: ItemEntity)
    fun onItemSelected(itemEntity: ItemEntity)
}