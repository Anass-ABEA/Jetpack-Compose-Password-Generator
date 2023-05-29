package com.whitebatcodes.passwordvault.models.password

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedPassword::class], version = 1, exportSchema = false)
abstract class SavedPasswordDb : RoomDatabase() {

    abstract fun savedPasswordDao(): SavedPasswordDao

    companion object {

        @Volatile
        private var INSTANCE: SavedPasswordDb? = null

        fun getDatabase(context: Context): SavedPasswordDb {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedPasswordDb::class.java,
                    "password_database"
                ).build()

                INSTANCE = instance
                instance
            }

        }

    }

    override fun close() {
        super.close()
        INSTANCE = null;
    }
}