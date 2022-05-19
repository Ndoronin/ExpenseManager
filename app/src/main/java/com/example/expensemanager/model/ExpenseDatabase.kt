package com.example.expensemanager.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase: RoomDatabase(){

    abstract fun expenseDao():ExpenseDao

    companion object{
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): ExpenseDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }

}