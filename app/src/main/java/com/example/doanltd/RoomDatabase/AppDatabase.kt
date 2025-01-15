package com.example.doanltd


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.doanltd.RoomDatabase.CartRoom.CartItemEntity
import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity

<<<<<<< HEAD
@Database(entities = [CartItemEntity::class], version = 2   )
=======
@Database(entities = [CartItemEntity::class, NgDungEntity::class], version = 2, exportSchema = false)
>>>>>>> d7dd8f80d2a134ac95f41b9bb40b3f168decfc9a
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
<<<<<<< HEAD
                    "cart_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
=======
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Avoid crash on schema updates
                    .build()

>>>>>>> d7dd8f80d2a134ac95f41b9bb40b3f168decfc9a
                INSTANCE = instance
                instance
            }
        }
    }
}


