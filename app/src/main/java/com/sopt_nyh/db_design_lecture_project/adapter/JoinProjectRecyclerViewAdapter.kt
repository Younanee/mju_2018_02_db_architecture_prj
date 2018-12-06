package com.sopt_nyh.db_design_lecture_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sopt_nyh.db_design_lecture_project.DetailedProjectActivity
import com.sopt_nyh.db_design_lecture_project.MainActivity
import com.sopt_nyh.db_design_lecture_project.R
import com.sopt_nyh.db_design_lecture_project.data.JoinProjectItemData
import com.sopt_nyh.db_design_lecture_project.data.ProjectData
import com.sopt_nyh.db_design_lecture_project.data.ProjectEmployeeItemData
import com.sopt_nyh.db_design_lecture_project.data.ProjectItemData
import org.jetbrains.anko.startActivity

class JoinProjectRecyclerViewAdapter(val ctx : Context, var dataList : ArrayList<JoinProjectItemData>) : RecyclerView.Adapter<JoinProjectRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_join_project_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.prj_name.text = dataList[position].prj_name
        holder.duty.text = "참여 직무- ${dataList[position].dty} | skill set- ${dataList[position].skill_set}"
        holder.prj_input_date.text = dataList[position].prj_inpt_date
        holder.prj_end_date.text = dataList[position].prj_end_date

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val prj_name : TextView = itemView.findViewById(R.id.tv_join_project_item_name) as TextView
        val prj_input_date : TextView = itemView.findViewById(R.id.tv_join_project_item_input_date) as TextView
        val prj_end_date : TextView = itemView.findViewById(R.id.tv_join_project_item_end_date) as TextView
        val duty : TextView = itemView.findViewById(R.id.tv_join_project_item_duty) as TextView
    }
}