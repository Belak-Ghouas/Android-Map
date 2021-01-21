package com.mzd.myapp.data.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mzd.myapp.data.database.entities.MediaEntity


@Dao
abstract class MediaDao {

    @Query("SELECT * FROM media WHERE mediaTag = :tag")
    abstract fun getMediaByTag(tag: String?): MediaEntity?

    @Query("SELECT * FROM media ORDER BY id DESC")
    abstract fun getAllMedia(): List<MediaEntity?>?

    @Update
    abstract fun updateMedia(media: MediaEntity?)

    @Delete
    abstract fun deleteMedia(media: MediaEntity?): Int

    @Query("DELETE FROM media WHERE mediaTag = :tag")
    abstract fun deleteMediaByTag(tag: String?)

    @Insert(onConflict = REPLACE)
    abstract fun insertMedia(media: MediaEntity?): Long

    open fun addMedia(mediaEntity: MediaEntity): Long {
        val id = insertMedia(mediaEntity)
        mediaEntity.id = id
        return id
    }

}