package com.localgp.localgp.model.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AppointmentRegistrationModel {

    private Long patientId;

    private Long doctorId;

    private Date date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

}
