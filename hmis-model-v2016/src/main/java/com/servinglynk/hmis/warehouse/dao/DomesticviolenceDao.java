package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

public interface DomesticviolenceDao extends ParentDao {

	   com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence createDomesticViolence(com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence domesticViolence);
	   com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence updateDomesticViolence(com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence domesticViolence);
	   void deleteDomesticViolence(com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence domesticViolence);
	   com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence getDomesticViolenceById(UUID domesticViolenceId);
	   List<com.servinglynk.hmis.warehouse.model.v2016.Domesticviolence> getAllEnrollmentDomesticViolences(UUID enrollmentId,Integer startIndex, Integer maxItems);
	   long getEnrollmentDomesticViolencesCount(UUID enrollmentId);
}
