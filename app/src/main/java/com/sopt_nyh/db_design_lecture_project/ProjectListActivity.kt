package com.sopt_nyh.db_design_lecture_project

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sopt_nyh.db_design_lecture_project.adapter.ProjectRecyclerViewAdapter
import com.sopt_nyh.db_design_lecture_project.data.CustomerData
import com.sopt_nyh.db_design_lecture_project.data.ProjectData
import com.sopt_nyh.db_design_lecture_project.data.ProjectItemData
import com.sopt_nyh.db_design_lecture_project.db.DatabaseOpenHelper
import kotlinx.android.synthetic.main.activity_project_list.*

class ProjectListActivity : AppCompatActivity() {

    private val dataList : ArrayList<ProjectItemData> by lazy {
        ArrayList<ProjectItemData>()
    }

    private val projectRecyclerViewAdapter : ProjectRecyclerViewAdapter by lazy {
        ProjectRecyclerViewAdapter(this, dataList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)
        setRecyclerView()

        getProjectList()

    }

    private fun setRecyclerView(){
        rv_project_act_list.adapter = projectRecyclerViewAdapter
        rv_project_act_list.layoutManager = LinearLayoutManager(this)

    }

    private fun getProjectList(){
        val db = DatabaseOpenHelper.getInstance(this).readableDatabase
        val sql = "SELECT DISTINCT ${ProjectData.PRJ_NUMBER}, " +
                "${ProjectData.PRJ_NAME}, " +
                "${ProjectData.PRJ_OUTSET_DATE}, " +
                "${ProjectData.PRJ_END_DATE}, " +
                "${ProjectData.CSTMR_ID} " +
                "FROM ${ProjectData.TABLE_NAME}"
        val cursor : Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast){
            dataList.add(ProjectItemData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)))
            cursor.moveToNext()
        }
        projectRecyclerViewAdapter.notifyDataSetChanged()

        tv_project_count.text = "${cursor.count}개 프로젝트 진행 중"

    }
}
