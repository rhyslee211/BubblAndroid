package com.example.bubblproject.Task;
import static com.example.bubblproject.MainActivity.CanavanArena;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;


import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;


public class TaskItem implements Serializable {

    private String TaskName;
    private Date TaskDate;
    private String TaskLocation;
    private int TaskPriority;

    private double TaskLatitude;

    private double TaskLongitude;

    private double OverallPriority;

    private boolean isVisible;

    private boolean isOverdue;
    //TODO: Create an overall Priority variable

    public TaskItem(String TaskName, Date TaskDate,  String TaskLocation, int TaskPriority){
        this.TaskName = TaskName;
        this.TaskDate = TaskDate;
        this.TaskLocation = TaskLocation;
        this.TaskPriority = TaskPriority;
        this.isVisible = false;
        this.TaskLongitude = 0;
        this.OverallPriority = 0;
        this.isOverdue = false;
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
        this.TaskLatitude = 0;
        this.TaskLongitude = 0;
        this.OverallPriority = 0;
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

    public double getTaskLatitude(){ return this.TaskLatitude;}

    public double getTaskLongitude(){ return this.TaskLongitude;}

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

    public void setTaskLatitude(double taskLat){
        this.TaskLatitude = taskLat;
    }

    public void setTaskLongitude(double taskLong){
        this.TaskLongitude = taskLong;
    }

    public void setOverallPriority(){

        int DistPrio;
        int TimePrio;
        //LatLng destinationLocation = new LatLng(TaskLatitude, TaskLongitude);

        double Latdistance = TaskLatitude - CanavanArena.latitude;
        Latdistance = Latdistance * 69;
        double Longdistance = TaskLongitude - CanavanArena.longitude;
        Longdistance = Longdistance * 54.6;

        double totalDist = Math.sqrt(Math.pow(Latdistance, 2) + Math.pow(Longdistance, 2));

        if(totalDist < 1)
            DistPrio = 5;
        else if (totalDist < 2) {
            DistPrio = 4;
        } else if (totalDist < 5) {
            DistPrio = 3;
        } else if (totalDist < 10) {
            DistPrio = 2;
        }
        else{
            DistPrio = 1;
        }

        if(Objects.isNull(TaskDate) == false){
            Date currentDate = new Date(LocalDateTime.now().getYear() - 1900, LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());


            System.out.println(getTaskDate().toString());
            System.out.println(currentDate.toString());
            long diffInMillis = TaskDate.getTime() - currentDate.getTime();
            long diff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            System.out.println(diff);

            if(diff < 0 || diffInMillis < 0){
                TimePrio = 16;
                isOverdue = true;
            }
            else if (diff < 24 && diff >= 0)
                TimePrio = 5;
            else if (diff < 48) {
                TimePrio = 4;
            } else if (diff < 96) {
                TimePrio = 3;
            } else if (diff < 168) {
                TimePrio = 2;
            } else {
                TimePrio = 1;
            }
        }
        else{
            TimePrio = 0;
        }

        OverallPriority = DistPrio + TimePrio + TaskPriority;

    }

    public double getOverallPriority(){return this.OverallPriority;}

    @Override
    public String toString(){
        return(this.TaskName);
    }
}
