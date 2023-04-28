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

    private final double timeConst = 0.99178;
    private final double locConst = 0.70710;
    private String TaskName;
    private Date TaskDate;
    private String TaskLocation;
    private double TaskPriority;

    private double TaskLatitude;

    private double TaskLongitude;

    private int OverallPriority;

    private boolean isVisible;

    private int hasDateTime;
    private int hasLocation;
    private int hasSetPrio;

    private boolean isOverdue;

    public TaskItem(String TaskName, Date TaskDate,  String TaskLocation, int TaskPriority){
        this.TaskName = TaskName;
        this.TaskDate = TaskDate;
        this.TaskLocation = TaskLocation;
        this.TaskPriority = TaskPriority;
        this.isVisible = false;
        this.TaskLongitude = 0;
        this.OverallPriority = 0;
        this.isOverdue = false;
        this.hasDateTime = 0;
        this.hasLocation = 0;
        this.hasSetPrio = 0;
    }

    public TaskItem(String TaskName, int TaskPriority, String Location){
        this.TaskName = TaskName;
        this.TaskPriority = TaskPriority;
        this.TaskLocation = Location;
        this.isVisible = false;
        this.hasDateTime = 0;
        this.hasLocation = 0;
        this.hasSetPrio = 0;
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
        this.hasDateTime = 0;
        this.hasLocation = 0;
        this.hasSetPrio = 0;
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

    public double getTaskPriority() {
        return TaskPriority;
    }

    public void setTaskPriority(double taskPriority) {
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

        double DistPrio;
        double TimePrio;
        //LatLng destinationLocation = new LatLng(TaskLatitude, TaskLongitude);

        if(Objects.isNull(TaskLocation) == false){
            hasLocation = 1;
            double Latdistance = TaskLatitude - CanavanArena.latitude;
            double mileLatdistance = Latdistance * 69;
            double Longdistance = TaskLongitude - CanavanArena.longitude;
            double mileLongdistance = Longdistance * 54.6;

            double totalDist = Math.sqrt(Math.pow(mileLatdistance, 2) + Math.pow(mileLongdistance, 2));

            DistPrio = Math.pow(locConst, totalDist);
        }
        else{
            DistPrio = 0;
        }

        if(TaskPriority != 0){
            hasSetPrio = 1;
        }

        if(Objects.isNull(TaskDate) == false){
            hasDateTime = 1;
            Date currentDate = new Date(LocalDateTime.now().getYear() - 1900, LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());


            System.out.println(getTaskDate().toString());
            System.out.println(currentDate.toString());
            long diffInMillis = TaskDate.getTime() - currentDate.getTime();
            long diff = TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            System.out.println(diff);

            if(diff < 0){
                isOverdue = true;
                OverallPriority = 1;
                System.out.println(OverallPriority);
                return;
            }else {

                TimePrio = Math.pow(timeConst, diff);
            }
        }
        else{
            TimePrio = 0;
        }

        System.out.println("User set prio: " + TaskPriority);
        System.out.println("Distance Prio: " + DistPrio);
        System.out.println("Time prio: " + TimePrio);

        if(hasDateTime + hasLocation + hasSetPrio > 0) {
            OverallPriority = (int) ((int) 100 * ((DistPrio + TimePrio + TaskPriority) / (hasDateTime + hasLocation + hasSetPrio)));
        }
        else{
            OverallPriority = 0;
        }
        System.out.println(OverallPriority);
    }

    public double getOverallPriority(){return this.OverallPriority;}

    @Override
    public String toString(){
        return(this.TaskName);
    }
}
