package com.sopt_nyh.db_design_lecture_project.data

data class EmployeeData(var emp_number : Int,
                    val emp_name : String,
                    val reg_no : String,
                    val last_acdmcr : String,
                    val id : String,
                    val pwd : String,
                    val job_code : Int){
    companion object {
        val TABLE_NAME = "EMPLOYEE"
        val EMP_NUMBER = "emp_number"
        val EMP_NAME = "emp_name"
        val REG_NO = "reg_no"
        val LAST_ACDMCR = "last_acdmcr"
        val ID = "id"
        val PWD = "pwd"
        val JOB_CODE = "job_code"
    }
}

data class DeveloperData(val emp_number : Int,
                     val career : String,
                     val tchnlgy_rate : String){
    companion object {
        val TABLE_NAME = "DEVELOPER"
        val EMP_NUMBER = "emp_number"
        val CAREER = "career"
        val TCHNLGY_RATE = "tchnlgy_rate"
    }
}
data class ProjectItemData(val prj_number : Int,
                       val prj_name : String,
                       val prj_outset_date : String,
                       val prj_end_date : String,
                           val cstmr_id : Int)

data class ProjectData(val prj_number : Int,
                       val cstmr_id : Int,
                       val prj_name : String,
                       val prj_outset_date : String,
                       val prj_end_date : String){
    companion object {
        val TABLE_NAME = "PROJECT"
        val PRJ_NUMBER = "prj_number"
        val CSTMR_ID = "cstmr_id"
        val PRJ_NAME = "prj_name"
        val PRJ_OUTSET_DATE = "prj_outset_date"
        val PRJ_END_DATE = "prj_end_date"
    }
}

data class CustomerData(val cstmr_id : Int,
                        val order_offic : String){
    companion object {
        val TABLE_NAME = "CUSTOMER"
        val CSTMR_ID = "cstmr_id"
        val ORDER_OFFIC = "order_offic"
    }
}
data class ProjectEmployeeItemData(val emp_number : Int,
                               val emp_name : String,
                               val dty : String,
                               val prj_inpt_date : String,
                               val prj_end_date : String,
                               val skill_set : String)

data class JoinProjectItemData(val prj_name : String,
                                   val dty : String,
                                   val prj_inpt_date : String,
                                   val prj_end_date : String,
                                   val skill_set : String)

data class ProjectEmployeeData(val emp_number : Int,
                               val prj_number : Int,
                               val dty_code : String,
                               val prj_inpt_date : String,
                               val prj_end_date : String,
                               val skill_set : String){
    companion object {
        val TABLE_NAME = "PROJECT_EMPLOYEE"
        val EMP_NUMBER = "emp_number"
        val PRJ_NUMBER = "prj_number"
        val DTY_CODE = "dty_code"
        val PRJ_INPT_DATE = "prj_inpt_date"
        val PRJ_END_DATE = "prj_end_date"
        val SKILL_SET = "skill_set"
    }
}