package com.hkl.tobuy.dataabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_entity")
class ItemEntity (
    @PrimaryKey
    val id : String = "",
    val description: String? = null,
    val title : String = "",
    val priority: Int = 0,
    val createdAt : Long = 0L,
    val categoryId : String = ""
)