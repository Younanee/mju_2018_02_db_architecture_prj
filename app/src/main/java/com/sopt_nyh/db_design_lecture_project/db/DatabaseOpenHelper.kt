package com.sopt_nyh.db_design_lecture_project.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sopt_nyh.db_design_lecture_project.data.*

class DatabaseOpenHelper(val ctx: Context) : SQLiteOpenHelper(ctx, DATABASE_NAME, null, 1){
    companion object {
        private var instance : DatabaseOpenHelper? = null
        private val DATABASE_NAME = "employee_management"
        @Synchronized
        fun getInstance(ctx: Context) : DatabaseOpenHelper{
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val SQL_CREATE_TABLE_EMP = "CREATE TABLE IF NOT EXISTS ${EmployeeData.TABLE_NAME} (" +
                "${EmployeeData.EMP_NUMBER} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${EmployeeData.EMP_NAME} TEXT NOT NULL," +
                "${EmployeeData.REG_NO} TEXT NOT NULL," +
                "${EmployeeData.LAST_ACDMCR} TEXT NOT NULL," +
                "${EmployeeData.ID} TEXT NOT NULL," +
                "${EmployeeData.PWD} TEXT NOT NULL," +
                "${EmployeeData.JOB_CODE} INTEGER NOT NULL)"

        val SQL_CREATE_TABLE_DEV = "CREATE TABLE IF NOT EXISTS ${DeveloperData.TABLE_NAME} (" +
                "${DeveloperData.EMP_NUMBER} INTEGER NOT NULL," +
                "${DeveloperData.CAREER} TEXT," +
                "${DeveloperData.TCHNLGY_RATE} TEXT," +
                "FOREIGN KEY(${DeveloperData.EMP_NUMBER}) REFERENCES ${EmployeeData.TABLE_NAME}(${EmployeeData.EMP_NUMBER}))"

        val SQL_CREATE_TABLE_CUST = "CREATE TABLE IF NOT EXISTS ${CustomerData.TABLE_NAME}(" +
                "${CustomerData.CSTMR_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${CustomerData.ORDER_OFFIC} TEXT)"

        val SQL_CREATE_TABLE_PRJ = "CREATE TABLE IF NOT EXISTS ${ProjectData.TABLE_NAME} (" +
                "${ProjectData.PRJ_NUMBER} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${ProjectData.CSTMR_ID} INTEGER NOT NULL," +
                "${ProjectData.PRJ_NAME} TEXT NOT NULL," +
                "${ProjectData.PRJ_OUTSET_DATE} TEXT," +
                "${ProjectData.PRJ_END_DATE} TEXT," +
                "FOREIGN KEY(${ProjectData.CSTMR_ID}) REFERENCES ${CustomerData.TABLE_NAME}(${CustomerData.CSTMR_ID}))"

        val SQL_CREATE_TABLE_PRJ_EMP = "CREATE TABLE IF NOT EXISTS ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER} INTEGER NOT NULL, " +
                "${ProjectEmployeeData.PRJ_NUMBER} INTEGER NOT NULL, " +
                "${ProjectEmployeeData.DTY_CODE} TEXT NOT NULL, " +
                "${ProjectEmployeeData.PRJ_INPT_DATE} TEXT, " +
                "${ProjectEmployeeData.PRJ_END_DATE} TEXT, " +
                "${ProjectEmployeeData.SKILL_SET} TEXT, " +
                "FOREIGN KEY(${ProjectEmployeeData.EMP_NUMBER}) REFERENCES ${EmployeeData.TABLE_NAME}(${EmployeeData.EMP_NUMBER}), " +
                "FOREIGN KEY(${ProjectEmployeeData.PRJ_NUMBER}) REFERENCES ${ProjectData.TABLE_NAME}(${ProjectData.PRJ_NUMBER}))"

        db!!.execSQL(SQL_CREATE_TABLE_EMP)
        db!!.execSQL(SQL_CREATE_TABLE_DEV)
        db!!.execSQL(SQL_CREATE_TABLE_CUST)
        db!!.execSQL(SQL_CREATE_TABLE_PRJ)
        db!!.execSQL(SQL_CREATE_TABLE_PRJ_EMP)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}