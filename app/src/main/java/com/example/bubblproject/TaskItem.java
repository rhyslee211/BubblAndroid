package com.example.bubblproject;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

public class TaskItem implements Serializable {

    private String TaskName;
    private Date TaskDate;
    private String TaskLocation;
    private int TaskPriority;

    private boolean isVisible;
    //TODO: Create an overall Priority variable

    public TaskItem(String TaskName, Date TaskDate,  String TaskLocation, int TaskPriority){
        this.TaskName = TaskName;
        this.TaskDate = TaskDate;
        this.TaskLocation = TaskLocation;
        this.TaskPriority = TaskPriority;
        this.isVisible = false;
    }

    public TaskItem(String TaskName, int TaskPriority, String Location){
        this.TaskName = TaskName;
        this.TaskPriority = TaskPriority;
        this.TaskLocation = Location;
        this.isVisible = false;
    }

    public TaskItem(){
        this.TaskName = null;
        this.TaskDate = null;
        this.TaskLocation = null;
        this.TaskPriority = 0;
        this.isVisible = false;
    }
    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Date getTaskDate() {
        return TaskDate;
    }

    public void setTaskDate(Date taskDate) {
        TaskDate = taskDate;
    }

    public String getTaskLocation() {
        return TaskLocation;
    }

    public void setTaskLocation(String taskLocation) {
        TaskLocation = taskLocation;
    }

    public int getTaskPriority() {
        return TaskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        TaskPriority = taskPriority;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public String toString(){
        return(this.TaskName);
    }
}
