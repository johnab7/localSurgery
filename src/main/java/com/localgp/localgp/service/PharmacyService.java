package com.localgp.localgp.service;

import com.localgp.localgp.entity.Pharmacy;

import java.util.List;

public interface PharmacyService {
    Pharmacy getByPharmacyName(String pharmacyName);
    String savePrescription(long id);
}
