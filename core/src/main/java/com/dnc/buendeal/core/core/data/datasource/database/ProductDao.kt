package com.dnc.buendeal.core.core.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dnc.buendeal.core.core.data.response.ProductDto

@Dao
interface ProductDao {
    @Query("SELECT * FROM productdto")
    fun getAll(): List<ProductDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: ProductDto)
}
