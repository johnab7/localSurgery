package com.localgp.localgp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.localgp.localgp.model.viewModel.AppointmentsSerializerModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment_table")
//@JsonSerialize(using = AppointmentsSerializerModel.class)
public class Appointment implements Comparable<Appointment>,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    private Prescription prescription;

    @Override
    public int compareTo(Appointment o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
