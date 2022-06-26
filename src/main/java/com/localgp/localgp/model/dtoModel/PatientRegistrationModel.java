package com.localgp.localgp.model.dtoModel;

import com.localgp.localgp.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegistrationModel extends UserRegistrationModel{


    private int nhsNumber;
    private int weight;
    private int height;

    public PatientRegistrationModel(Patient patient){
        super(patient);
        this.setNhsNumber(patient.getNhsNumber());
        this.setWeight(patient.getWeight());
        this.setHeight(patient.getHeight());
    }


}
