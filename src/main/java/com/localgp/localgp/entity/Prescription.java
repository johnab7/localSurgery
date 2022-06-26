package com.localgp.localgp.entity;

import com.localgp.localgp.model.dtoModel.PrescriptionModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prescription")
public class Prescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String diagnosis;
    private String diagnosisDescription;
    private String medicineDescription;
    private LocalDate prescriptionDate;
//    private String medicine;
//    private String dosage;
    private Boolean status;

    private int prescriptionPeriod;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @JoinColumn(name="medical_records_id")
    private MedicalRecord medicalRecord;

    @OneToOne(mappedBy = "prescription",cascade = {CascadeType.ALL})
    private Appointment appointment;

    public Prescription(PrescriptionModel prescriptionModel) {
        this.diagnosis = prescriptionModel.getDiagnosis();
        this.diagnosisDescription = prescriptionModel.getDiagnosisDescription();
        this.prescriptionDate= prescriptionModel.getPrescriptionDate();
        this.prescriptionPeriod=prescriptionModel.getPrescriptionPeriod();
        this.medicineDescription= prescriptionModel.getMedicineDescription();
//        this.medicine = prescriptionModel.getMedicine();
//        this.dosage = prescriptionModel.getDosage();
        this.status = prescriptionModel.getStatus();
//        this.patientId = prescriptionModel.getPatientId();
//        this.doctorId = prescriptionModel.getDoctorId();
//        this.appointmentId = prescriptionModel.getAppointmentId();
//        this.pharmacyId = prescriptionModel.getPharmacyId();
    }

}
