package com.psa.containeroptim.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.psa.containeroptim.data.database.dao.MediaDao
import com.psa.containeroptim.data.database.dao.UserDao
import com.psa.containeroptim.data.database.entities.MediaEntity
import com.psa.containeroptim.data.database.entities.User

@Database(entities = [User::class, MediaEntity::class], version = 1)
@TypeConverters(MediaEntity.MediaStatusConverter::class , MediaEntity.MediaTypeConverter::class )
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mediaDao():MediaDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE:AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   AppDatabase::class.java,
                    "mediaBase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}