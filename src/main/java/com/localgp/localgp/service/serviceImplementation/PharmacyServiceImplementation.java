package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.Pharmacy;
import com.localgp.localgp.entity.Prescription;
import com.localgp.localgp.repository.PharmacyRepository;
import com.localgp.localgp.repository.PrescriptionRepository;
import com.localgp.localgp.service.PharmacyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PharmacyServiceImplementation implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PrescriptionRepository prescriptionRepository;


    @Override
    public Pharmacy getByPharmacyName(String pharmacyName) throws UsernameNotFoundException{
        return pharmacyRepository.findByUsername(pharmacyName);
    }

    @Override
    public String savePrescription(long id) {
        Prescription prescription = this.prescriptionRepository.findById(id);
        prescription.setStatus(true);
        this.prescriptionRepository.save(prescription);

        return "Success";
    }


}
