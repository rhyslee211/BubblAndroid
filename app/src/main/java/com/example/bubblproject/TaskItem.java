package com.example.bubblproject;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

public class TaskItem implements Serializable {

    private String TaskName;
    private String TaskDate;
    private String TaskLocation;
    private int TaskPriority;
    //TODO: Create an overall Priority variable

    public TaskItem(String TaskName, String TaskDate, String TaskLocation, int TaskPriority){
        this.TaskName = TaskName;
        this.TaskDate = TaskDate;
        this.TaskLocation = TaskLocation;
        this.TaskPriority = TaskPriority;
    }

    public TaskItem(){
        this.TaskName = null;
        this.TaskDate = null;
        this.TaskLocation = null;
        this.TaskPriority = 0;
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
    public void setPriority(int priority){this.TaskPriority = priority;}
    public String getName(){
        return this.TaskName;
    }
    public String getDate(){ return this.TaskDate;}
    public String getTaskLocation(){return this.TaskLocation;}
    public int getPriority(){return this.TaskPriority;}

    @Override
    public String toString(){
        return(this.TaskName);
    }
}
