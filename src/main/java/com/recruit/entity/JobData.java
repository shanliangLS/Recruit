package com.recruit.entity;

public class JobData {
    private int id;
    private String title;
    private String content;
    private String release_time;

    public JobData(){

    }
    public void setData(int id,String title,String content,String release_time){
        this.id=id;
        this.title=title;
        this.content=content;
        this.release_time=release_time;
    }
}
