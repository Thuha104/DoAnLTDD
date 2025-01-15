package com.example.doanltd


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.doanltd.RoomDatabase.CartRoom.CartItemEntity
import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity

@Database(entities = [CartItemEntity::class, NgDungEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ngDungDao(): NgDungDao
    abstract fun cartDao(): CartDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Avoid crash on schema updates
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
