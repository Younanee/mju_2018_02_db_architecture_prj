package com.sopt_nyh.db_design_lecture_project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt_nyh.db_design_lecture_project.data.CustomerData
import com.sopt_nyh.db_design_lecture_project.data.ProjectEmployeeData
import com.sopt_nyh.db_design_lecture_project.data.SharedPreferenceController
import com.sopt_nyh.db_design_lecture_project.db.DatabaseOpenHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!SharedPreferenceController.getAuthorization(this)) {
            SharedPreferenceController.setAuthorization(this, true)
            insertInitData()
        }

        btn_main_act_current_project.setOnClickListener {
            startActivity<ProjectListActivity>()
        }
    }

    private fun insertInitData() {
        val db = DatabaseOpenHelper.getInstance(this).writableDatabase
        var sqlArrayList = ArrayList<String>()
        try {
            var idx = 1
            var reader: BufferedReader = BufferedReader(InputStreamReader(assets.open("sql_one_line.txt")))
            var line = reader.readLine()
            while (line != null) {
                idx++
                sqlArrayList.add(line)
                line = reader.readLine()
            }
            reader.close()
        } catch (t: IOException) {
            Log.e("에러!", t.toString())
        }
        Log.e("다 들어 갔나?", sqlArrayList.toString())
        sqlArrayList.forEach {
            db.execSQL(it)
        }
        val offic = arrayListOf<String>("서울시", "유니끄로", "스피드PC방")
        offic.forEach {
            db.execSQL("INSERT INTO ${CustomerData.TABLE_NAME} " +
                    "(${CustomerData.ORDER_OFFIC}) " +
                    "VALUES (\"$it\")")
        }

        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}, " +
                "${ProjectEmployeeData.PRJ_INPT_DATE}, " +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (25, 2, \"PM\", \"2018-11-21\", \"진행 중\", \"애자일 마스터\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (33, 2, \"PL\", \"2018-11-21\", \"진행 중\", \"RFP\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (36, 2, \"PL\", \"2018-11-21\", \"진행 중\", \"RFP\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (46, 2, \"분석자\", \"2018-11-21\", \"진행 중\", \"하둡, 빅데이터\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (52, 2, \"설계자\", \"2018-11-21\", \"진행 중\", \"ER diagram tool\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (54, 2, \"테스터\", \"2019-9-01\", \"진행 중\", \"JAVA, python\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (65, 2, \"프로그래머\", \"2018-12-01\", \"진행 중\", \"node.js, git\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (66, 2, \"프로그래머\", \"2018-12-01\", \"진행 중\", \"javascript, python, git\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (67, 2, \"프로그래머\", \"2018-12-01\", \"진행 중\", \"node.js, aws, git\")")

        //추가프로젝트
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (66, 11, \"프로그래머\", \"2018-04-22\", \"2018-11-11\", \"java, android\")")
        db.execSQL("INSERT INTO ${ProjectEmployeeData.TABLE_NAME} (" +
                "${ProjectEmployeeData.EMP_NUMBER}, " +
                "${ProjectEmployeeData.PRJ_NUMBER}, " +
                "${ProjectEmployeeData.DTY_CODE}," +
                "${ProjectEmployeeData.PRJ_INPT_DATE}," +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET}) " +
                "VALUES (66, 12, \"프로그래머\", \"2017-06-17\", \"2018-10-13\", \"kotlin, android\")")

        db.execSQL("INSERT INTO PROJECT (cstmr_id, prj_name, prj_outset_date, prj_end_date) VALUES (11, \"데이트 어플리케이션 ToDate\", \"2018-04-22\", \"2019-09-03\")")
        db.execSQL("INSERT INTO PROJECT (cstmr_id, prj_name, prj_outset_date, prj_end_date) VALUES (12, \"안드로이드용 App 쇼핑몰\", \"2018-06-17\", \"2019-12-11\")")
    }

}
