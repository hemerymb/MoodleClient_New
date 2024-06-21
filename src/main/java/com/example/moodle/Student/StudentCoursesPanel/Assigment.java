package com.example.moodle.Student.StudentCoursesPanel;

import java.time.LocalDate;
import java.util.Date;

public class Assigment {

    private String assigmentCourseName;
    private String assigmentName;
    private String statut; // statut = inProgress pour devoir en cours et finished pour devoir rendu
    private Date Deadline;
    private Date CreationDate;

    public Assigment(String assigmentCourseName, String assigmentName, String statut, Date Deadline, Date CreationDate) {
        this.assigmentCourseName = assigmentCourseName;
        this.assigmentName = assigmentName;
        this.statut = statut;
        this.Deadline = Deadline;
        this.CreationDate = CreationDate;
    }

    public String getAssigmentCourseName() {
        return assigmentCourseName;
    }
    public String getAssigmentName() {
        return assigmentName;
    }
    public String getStatut() {
        return statut;
    }
    public Date getDeadline() {
        return Deadline;
    }
    public Date getCreationDate() {return CreationDate;}

}
