package com.example.bubblproject;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

public class TaskItem implements Serializable {

    private String TaskName;
    private String TaskDate;
    private String TaskLocation;
    private Double TaskLength;
    private String TaskType;

    public TaskItem(String TaskName, String TaskDate, String TaskLocation, Double  TaskLength, String TaskType){
        this.TaskName = TaskName;
        this.TaskDate = TaskDate;
        this.TaskLocation = TaskLocation;
        this.TaskLength = TaskLength;
        this.TaskType = TaskType;
    }

    public TaskItem(){
        this.TaskName = null;
        this.TaskDate = null;
        this.TaskLocation = null;
        this.TaskLength = null;
        this.TaskType = null;
    }
    public void setName(String name){
        this.TaskName = name;
    }
    public void setDate(String date){
        this.TaskDate = date;
    }
    public void setLoc(String loc){
        this.TaskLocation = loc;
    }
    public String getName(){
        return this.TaskName;
    }
    public String getDate(){
        return this.TaskDate;
    }
    public String getLoc(){
        return this.TaskLocation;
    }

}
