package com.recruit.entity;

public class IntensionData {

    private int id;
    private int uid;
    private String annual_salary;
    private String desired_industry;
    private String desired_location;
    private String desired_job;
//    annual_salary desired_industry desired_location desired_job
    public IntensionData(){

    }
    public IntensionData(String annual_salary,String desired_industry,String desired_location,String  desired_job){
        this.annual_salary=annual_salary;
        this.desired_industry=desired_industry;
        this.desired_location=desired_location;
        this.desired_job=desired_job;
    }
    public void setData(int id,int uid,String annual_salary,String desired_industry,String desired_location,String  desired_job){
        this.id=id;
        this.uid=uid;
        this.annual_salary=annual_salary;
        this.desired_industry=desired_industry;
        this.desired_location=desired_location;
        this.desired_job=desired_job;
    }
}