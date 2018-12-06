package com.sopt_nyh.db_design_lecture_project

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.sopt_nyh.db_design_lecture_project.adapter.ProjectEmployeeRecyclerViewAdapter
import com.sopt_nyh.db_design_lecture_project.data.*
import com.sopt_nyh.db_design_lecture_project.db.DatabaseOpenHelper
import kotlinx.android.synthetic.main.activity_detailed_project.*

class DetailedProjectActivity : AppCompatActivity() {


    val dataList : ArrayList<ProjectEmployeeItemData> by lazy {
        ArrayList<ProjectEmployeeItemData>()
    }

    val projectEmployeeRecyclerViewAdapter : ProjectEmployeeRecyclerViewAdapter by lazy {
        ProjectEmployeeRecyclerViewAdapter(this, dataList)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_project)

        val prj_id = intent.getIntExtra("prj_id",0)
        val cstmr_id  = intent.getIntExtra("cstmr_id",0)
        Log.e("선택 프로젝트 ", prj_id.toString() + "프로젝트 번호, 발주 번호 = $cstmr_id")
        setInitData(prj_id, cstmr_id)
        setRecyclerView(prj_id)
    }

    private fun setRecyclerView(prj_id : Int){
        rv_detailed_project_employee_list.adapter = projectEmployeeRecyclerViewAdapter
        rv_detailed_project_employee_list.layoutManager = LinearLayoutManager(this)
        val db  = DatabaseOpenHelper.getInstance(this).readableDatabase
        val sql_project_emp = "SELECT ${ProjectEmployeeData.TABLE_NAME}.${ProjectEmployeeData.EMP_NUMBER}, " +
                "${EmployeeData.EMP_NAME}, " +
                "${ProjectEmployeeData.DTY_CODE}, " +
                "${ProjectEmployeeData.PRJ_INPT_DATE}, " +
                "${ProjectEmployeeData.PRJ_END_DATE}, " +
                "${ProjectEmployeeData.SKILL_SET} " +
                "FROM ${ProjectEmployeeData.TABLE_NAME} " +
                "INNER JOIN ${EmployeeData.TABLE_NAME} " +
                "ON ${ProjectEmployeeData.TABLE_NAME}.${ProjectEmployeeData.EMP_NUMBER} = ${EmployeeData.TABLE_NAME}.${EmployeeData.EMP_NUMBER} " +
                "WHERE ${ProjectEmployeeData.PRJ_NUMBER} = $prj_id"
        val cursor_prj_emp : Cursor = db.rawQuery(sql_project_emp,null)

        cursor_prj_emp.moveToFirst()
        while (!cursor_prj_emp.isAfterLast){
            dataList.add(ProjectEmployeeItemData(cursor_prj_emp.getInt(0), cursor_prj_emp.getString(1), cursor_prj_emp.getString(2), cursor_prj_emp.getString(3), cursor_prj_emp.getString(4), cursor_prj_emp.getString(5)))
            cursor_prj_emp.moveToNext()
        }
        projectEmployeeRecyclerViewAdapter.notifyDataSetChanged()

        tv_detailed_emp_count.text = "프로젝트 투입 직원 - ${cursor_prj_emp.count}명"


    }

    private fun setInitData(prj_id : Int, cstmr_id : Int){
        val db  = DatabaseOpenHelper.getInstance(this).readableDatabase
        //고객
        val sql_customer = "SELECT ${CustomerData.ORDER_OFFIC} FROM ${CustomerData.TABLE_NAME} WHERE ${CustomerData.CSTMR_ID} = $cstmr_id"
        val cursor_customer = db.rawQuery(sql_customer, null)
        cursor_customer.moveToFirst()
        //프로젝트
        val sql_project = "SELECT ${ProjectData.PRJ_NAME}, ${ProjectData.PRJ_OUTSET_DATE}, ${ProjectData.PRJ_END_DATE} FROM ${ProjectData.TABLE_NAME} WHERE ${ProjectData.PRJ_NUMBER} = $prj_id"
        val cursor_project = db.rawQuery(sql_project, null)
        cursor_project.moveToFirst()

        tv_detailed_project_name.text = cursor_project.getString(0) + " 프로젝트"
        tv_detailed_project_customer.text = cursor_customer.getString(0)
        tv_detailed_outset_date.text = cursor_project.getString(1)
        tv_detailed_end_date.text = cursor_project.getString(2)
    }
}
