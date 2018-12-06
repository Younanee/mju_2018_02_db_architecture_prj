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
import com.sopt_nyh.db_design_lecture_project.data.ProjectData
import com.sopt_nyh.db_design_lecture_project.data.ProjectItemData
import org.jetbrains.anko.startActivity

class ProjectRecyclerViewAdapter(val ctx : Context, var dataList : ArrayList<ProjectItemData>) : RecyclerView.Adapter<ProjectRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_project_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.prj_name.text = dataList[position].prj_name
        holder.prj_number.text = "No. " + dataList[position].prj_number.toString()
        holder.prj_outset_date.text = dataList[position].prj_outset_date
        holder.prj_end_date.text = dataList[position].prj_end_date

        holder.whole.setOnClickListener {
            ctx.startActivity<DetailedProjectActivity>("prj_id" to dataList[position].prj_number, "cstmr_id" to dataList[position].cstmr_id)
        }

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val prj_name : TextView = itemView.findViewById(R.id.tv_project_item_name) as TextView
        val prj_outset_date : TextView = itemView.findViewById(R.id.tv_project_item_outset_date) as TextView
        val prj_end_date : TextView = itemView.findViewById(R.id.tv_project_item_end_date) as TextView
        val prj_number : TextView = itemView.findViewById(R.id.tv_project_item_customer) as TextView
        val whole : RelativeLayout = itemView.findViewById(R.id.btn_project_item_whole) as RelativeLayout
    }
}