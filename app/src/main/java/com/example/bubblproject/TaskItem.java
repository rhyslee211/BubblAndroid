package com.example.bubblproject;
import java.sql.Time;
import java.util.*;

public class TaskItem {

    private String TaskName;
    private Date TaskDate;
    private String TaskLocation;
    private Double TaskLength;
    private String TaskType;

    public TaskItem(String TaskName, Date TaskDate, String TaskLocation, Double  TaskLength, String TaskType){
        this.TaskName = TaskName;
        this.TaskDate = TaskDate;
        this.TaskLocation = TaskLocation;
        this.TaskLength = TaskLength;
        this.TaskType = TaskType;
    }
}
