package com.sopt_nyh.db_design_lecture_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt_nyh.db_design_lecture_project.DetailedProjectActivity
import com.sopt_nyh.db_design_lecture_project.EmployeeActivity
import com.sopt_nyh.db_design_lecture_project.R
import com.sopt_nyh.db_design_lecture_project.data.ProjectEmployeeData
import com.sopt_nyh.db_design_lecture_project.data.ProjectEmployeeItemData
import com.sopt_nyh.db_design_lecture_project.data.ProjectItemData
import org.jetbrains.anko.startActivity

class ProjectEmployeeRecyclerViewAdapter(val ctx : Context, var dataList : ArrayList<ProjectEmployeeItemData>) : RecyclerView.Adapter<ProjectEmployeeRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_project_emp_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = "이름- " +dataList[position].emp_name
        holder.duty.text = "직무- " + dataList[position].dty
        holder.input_date.text = dataList[position].prj_inpt_date
        holder.end_date.text = dataList[position].prj_end_date
        holder.skill.text = "skill set : " + dataList[position].skill_set

        holder.whole.setOnClickListener {
            ctx.startActivity<EmployeeActivity>("emp_number" to dataList[position].emp_number, "dty_code" to dataList[position].dty)
        }

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.tv_project_emp_item_name) as TextView
        val duty : TextView = itemView.findViewById(R.id.tv_project_emp_item_duty) as TextView
        val input_date : TextView = itemView.findViewById(R.id.tv_project_emp_item_input_date) as TextView
        val end_date : TextView = itemView.findViewById(R.id.tv_project_emp_item_end_date) as TextView
        val whole : LinearLayout = itemView.findViewById(R.id.btn_project_emp_item_whole) as LinearLayout
        val skill : TextView = itemView.findViewById(R.id.tv_project_emp_item_skill) as TextView
    }
}