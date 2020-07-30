package com.comp304.Lab04.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tb_nurse")
public class Nurse {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "nurse_id")
    private int nurseId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;


    //constructor
    public Nurse(){}
    public Nurse(int nurseId, String firstName, String lastName, String department, String password) {
        this.nurseId = nurseId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    //nurse toString
    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId=" + nurseId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //nurse getters and setters
    @NonNull
    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(@NonNull int nurseId) {
        this.nurseId = nurseId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
