package com.sopt_nyh.db_design_lecture_project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sopt_nyh.db_design_lecture_project.adapter.JoinProjectRecyclerViewAdapter
import com.sopt_nyh.db_design_lecture_project.data.*
import com.sopt_nyh.db_design_lecture_project.db.DatabaseOpenHelper
import kotlinx.android.synthetic.main.activity_employee.*

class EmployeeActivity : AppCompatActivity() {

    val dataList : ArrayList<JoinProjectItemData> by lazy {
        ArrayList<JoinProjectItemData>()
    }

    val joinProjectRecyclerViewAdapter : JoinProjectRecyclerViewAdapter by lazy {
        JoinProjectRecyclerViewAdapter(this, dataList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        val emp_number = intent.getIntExtra("emp_number", 0)
        val dty_code = intent.getStringExtra("dty_code")

        setInitView(emp_number)
        setRecyclverView(emp_number)

    }

    private fun setInitView(emp_number : Int){
        val db = DatabaseOpenHelper.getInstance(this).readableDatabase

        val sql = "SELECT ${EmployeeData.EMP_NAME}, ${EmployeeData.LAST_ACDMCR}, ${EmployeeData.JOB_CODE}, " +
                "${DeveloperData.TCHNLGY_RATE}, ${DeveloperData.CAREER} " +
                "FROM ${EmployeeData.TABLE_NAME} " +
                "INNER JOIN ${DeveloperData.TABLE_NAME} " +
                "ON ${EmployeeData.TABLE_NAME}.${EmployeeData.EMP_NUMBER} = ${DeveloperData.TABLE_NAME}.${DeveloperData.EMP_NUMBER} " +
                "WHERE ${EmployeeData.TABLE_NAME}.${EmployeeData.EMP_NUMBER} = $emp_number"
        val cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        tv_emp_act_name.text = cursor.getString(0)
        tv_emp_act_last_acdmcr.text = "최종학력- ${cursor.getString(1)}"
        if (cursor.getInt(2) == 3){
            tv_emp_act_tchnlgy_job_code.text = "직무- 개발자"
        }

        tv_emp_act_career.text = "경력- ${cursor.getString(3)}"
        tv_emp_act_tchnlgy_rate.text = "기술등급- ${cursor.getString(4)}"
    }

    private fun setRecyclverView(emp_number : Int){
        rl_emp_act_join_project_list.adapter = joinProjectRecyclerViewAdapter
        rl_emp_act_join_project_list.layoutManager = LinearLayoutManager(this)

        val db = DatabaseOpenHelper.getInstance(this).readableDatabase
        val sql = "SELECT ${ProjectData.PRJ_NAME}, ${ProjectEmployeeData.DTY_CODE}, ${ProjectEmployeeData.SKILL_SET}, ${ProjectEmployeeData.PRJ_INPT_DATE}, ${ProjectEmployeeData.TABLE_NAME}.${ProjectEmployeeData.PRJ_END_DATE} " +
                "FROM ${ProjectEmployeeData.TABLE_NAME} " +
                "INNER JOIN ${ProjectData.TABLE_NAME} " +
                "ON ${ProjectEmployeeData.TABLE_NAME}.${ProjectEmployeeData.PRJ_NUMBER} = ${ProjectData.TABLE_NAME}.${ProjectData.PRJ_NUMBER} " +
                "WHERE ${ProjectEmployeeData.TABLE_NAME}.${ProjectEmployeeData.EMP_NUMBER} = $emp_number"

        val cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast){
            dataList.add(JoinProjectItemData(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(2)))
            cursor.moveToNext()
        }

        joinProjectRecyclerViewAdapter.notifyDataSetChanged()


    }
//    holder.prj_name.text = dataList[position].prj_name
//    holder.duty.text = "참여 직무- ${dataList[position].dty} | skill set- ${dataList[position].skill_set}"
//    holder.prj_input_date.text = dataList[position].prj_inpt_date
//    holder.prj_end_date.text = dataList[position].prj_end_date
}
