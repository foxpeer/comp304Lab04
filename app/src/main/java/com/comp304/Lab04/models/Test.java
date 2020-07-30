package com.comp304.Lab04.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_test")
public class Test {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "test_id")
    private int testId ;

    @ForeignKey(entity = Patient.class, parentColumns = "patientId", childColumns ="patientId" )
    @ColumnInfo(name = "patient_id")
    private int patientId;

    @ForeignKey(entity = Nurse.class, parentColumns = "nurseId", childColumns ="nurseId" )
    @ColumnInfo(name = "nurse_id")
    private int nurseId;

    @ColumnInfo(name = "bpl")
    private int BPL;

    @ColumnInfo(name = "bph")
    private int BPH;

    private double temperature;
    private double weight;
    private double height;

    //constructor
    public Test(){}
    public Test(int testId, Patient patient, Nurse nurse, int BPL, int BPH, double temperature, double weight, double height) {
        this.testId = testId;
        this.patientId = patient.getPatientId();
        this.nurseId = nurse.getNurseId();
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
        this.weight = weight;
        this.height = height;
    }
    public Test(int testId, int patientId, int nurseId, int BPL, int BPH, double temperature, double weight, double height) {
        this.testId = testId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
        this.weight = weight;
        this.height = height;
    }


    //test toString()
    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", patientId=" + patientId +
                ", nurseId=" + nurseId +
                ", BPL=" + BPL +
                ", BPH=" + BPH +
                ", temperature=" + temperature +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }


    //getters and setters
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getBPL() {
        return BPL;
    }

    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    public int getBPH() {
        return BPH;
    }

    public void setBPH(int BPH) {
        this.BPH = BPH;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
