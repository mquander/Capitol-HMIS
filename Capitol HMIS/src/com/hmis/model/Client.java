package com.hmis.model;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//
//import javax.swing.JOptionPane;

public class Client {

    private String SSN, Fname, Minit, Lname, DOB, Sex, Race1, Race2, goalsDateAdded, goalsNotes, SPDATscore, isVeteran;

    public Client() {}

    public Client(String sSN, String fname, String minit, String lname, String dOB, String sex, String race1,
                  String race2, String goalsDateAdded, String goalsNotes, String sPDATscore, String isVeteran) {
        super();
        this.SSN = sSN;
        this.Fname = fname;
        this.Minit = minit;
        this.Lname = lname;
        this.DOB = dOB;
        this.Sex = sex;
        this.Race1 = race1;
        this.Race2 = race2;
        this.goalsDateAdded = goalsDateAdded;
        this.goalsNotes = goalsNotes;
        this.SPDATscore = sPDATscore;
        this.isVeteran = isVeteran;
    }

    public Client getClientObject() {
        return this;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String sSN) {
        SSN = sSN;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getMinit() {
        return Minit;
    }

    public void setMinit(String minit) {
        Minit = minit;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dOB) {
        DOB = dOB;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getRace1() {
        return Race1;
    }

    public void setRace1(String race1) {
        Race1 = race1;
    }

    public String getRace2() {
        return Race2;
    }

    public void setRace2(String race2) {
        Race2 = race2;
    }

    public String getGoalsDateAdded() {
        return goalsDateAdded;
    }

    public void setGoalsDateAdded(String goalsDateAdded) {
        this.goalsDateAdded = goalsDateAdded;
    }

    public String getGoalsNotes() {
        return goalsNotes;
    }

    public void setGoalsNotes(String goalsNotes) {
        this.goalsNotes = goalsNotes;
    }

    public String getSPDATscore() {
        return SPDATscore;
    }

    public void setSPDATscore(String sPDATscore) {
        SPDATscore = sPDATscore;
    }

    public String getIsVeteran() {
        return isVeteran;
    }

    public void setIsVeteran(String isVeteran) {
        this.isVeteran = isVeteran;
    }
}
