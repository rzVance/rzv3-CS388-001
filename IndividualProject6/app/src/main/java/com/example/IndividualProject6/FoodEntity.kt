package com.example.IndividualProject6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity (
    @ColumnInfo val foodAmount : String,
    @ColumnInfo val foodNotes : String,
    @PrimaryKey(autoGenerate = true) val id : Long =0,
)
