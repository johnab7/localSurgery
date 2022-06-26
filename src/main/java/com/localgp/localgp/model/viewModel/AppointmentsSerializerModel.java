//package com.localgp.localgp.model.viewModel;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import com.localgp.localgp.entity.Appointment;
//import com.localgp.localgp.entity.AppointmentStatus;
//
//import java.io.IOException;
//import java.time.ZoneOffset;
//
//public class AppointmentsSerializerModel extends StdSerializer<Appointment> {
//    public AppointmentsSerializerModel(Class<Appointment> t){
//        super(t);
//    }
//
//    @Override
//    public void serialize(Appointment appointment, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        gen.writeStartObject();
//        gen.writeNumberField("id", appointment.getId());
//        gen.writeStringField("title", appointment.getPatient().getFirstName());
//        gen.writeNumberField("start", appointment.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli());
//        gen.writeNumberField("end", appointment.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli());
//        gen.writeStringField("url", "/appointments/" + appointment.getId());
//        gen.writeStringField("color", appointment.getAppointmentStatus().equals(AppointmentStatus.SCHEDULED) ? "#28a745" : "grey");
//        gen.writeEndObject();
//    }
//}
