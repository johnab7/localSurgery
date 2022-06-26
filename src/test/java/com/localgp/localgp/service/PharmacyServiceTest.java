//package com.localgp.localgp.service;
//
//import com.localgp.localgp.entity.Pharmacy;
//import com.localgp.localgp.repository.GroupedUserRepository;
//import com.localgp.localgp.repository.PharmacyRepository;
//import com.localgp.localgp.service.serviceImplementation.PharmacyServiceImplementation;
//import mockit.Expectations;
//import mockit.Injectable;
//import mockit.Tested;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.parameters.P;
//
//import static org.mockito.Mockito.mock;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//
//public class PharmacyServiceTest {
//
//    private String phm = "PharmacyName";
//
////    @Test
////    public void testPharmacyName(){
////        new Expectations(){
////            {
////                Pharmacy pharmacy = new Pharmacy();
////                pharmacy.setPharmacyName("test");
////                pharmacy.setPharmacyAddress("test");
////                pharmacyRepository.findByUsername(anyString);
////                result = pharmacy;
////            }
////        };
////        Pharmacy pharmacy = pharmacyService.getByPharmacyName(phm);
////        assertEquals("test",pharmacy.getPharmacyName());
////    }
//
//
//
////    @Test
////    public void testPharmacyName(){
////            PharmacyRepository pharmacyRepository = mock(PharmacyRepository.class);
////            Pharmacy pharmacy = new Pharmacy();
////            pharmacy.setPharmacyName("test");
////            pharmacy.setPharmacyAddress("test");
////            when(pharmacyRepository.findByUsername(phm)).thenReturn(pharmacy);
////
////            PharmacyServiceImplementation pharmacyService = new PharmacyServiceImplementation();
////
////            Pharmacy result = pharmacyService.getByPharmacyName("test");
////            assertEquals("test",result.getPharmacyName());
////        }
//
//}
