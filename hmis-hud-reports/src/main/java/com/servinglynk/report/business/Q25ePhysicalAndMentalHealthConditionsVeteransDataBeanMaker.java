package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import com.servinglynk.report.bean.Q25ePhysicalAndMentalHealthConditionsVeteransDataBean;
import com.servinglynk.report.bean.ReportData;

public class Q25ePhysicalAndMentalHealthConditionsVeteransDataBeanMaker extends BaseBeanMaker {
	
	// mental health -- select enrollment_id from disabilities where datacollectionstage =1 and disabilitytype='9'
	// alcohol --select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='10' and disabilityresponse='1'
	// drug -- select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='10' and disabilityresponse='2'
	// alcohol and drug -- select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='10' and disabilityresponse='3'
	// Chronic Health Condition -- select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='7'
	// HIV/AIDS -- select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='8'
	// Physical Disability -- select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='5'
	// Developmental Disability -- select project_entry_id from disabilities where datacollectionstage = '1' and disabilitytype='6'
	

	public static List<Q25ePhysicalAndMentalHealthConditionsVeteransDataBean> getQ25ePhysicalAndMentalHealthConditionsVeteransList(ReportData data){
		
		Q25ePhysicalAndMentalHealthConditionsVeteransDataBean q25ePhysicalAndMentalHealthConditionsVeteransTable = new Q25ePhysicalAndMentalHealthConditionsVeteransDataBean();
		try {
			if(data.isLiveMode()) {
				String query = " select distinct(e.dedup_client_id) from %s.disabilities d,%s.enrollment e, %s.client c  where e.id = d.enrollmentid and e.client_id = c.id  and c.veteran_status='1' and d.datacollectionstage = '1' and d.information_date >= :startDate and d.information_date <= :endDate ";
				String queryExit = " select distinct(e.dedup_client_id) from %s.disabilities d, %s.enrollment e, %s.exit ext,%s.client c   where e.id = d.enrollmentid and e.client_id = c.id  and c.veteran_status='1' and e.id=ext.enrollmentid and d.datacollectionstage = '3' and d.information_date >= :startDate and d.information_date <= :endDate ";
				String queryStayers = " select distinct(e.dedup_client_id) from %s.disabilities d, %s.enrollment e,%s.client c  where e.id = d.enrollmentid and e.client_id = c.id  and c.veteran_status='1' and d.datacollectionstage = '4' and d.information_date >= :startDate and d.information_date <= :endDate ";
				
				List<String> enrollmentFromDisabilities = getEnrollmentFromDisabilities(data.getSchema(), data, query+" and d.disabilitytype='9' ");
				List<String> enrollmentFromDisabilitiesExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+"  and d.disabilitytype='9' ");
				List<String> enrollmentFromDisabilitiesStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+"  and d.disabilitytype='9' ");
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eMentalIllnessAtEntry(BigInteger.valueOf(getSize(enrollmentFromDisabilities)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eMentalIllnessStayers(BigInteger.valueOf(getSize(enrollmentFromDisabilitiesStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eMentalIllnessLeavers(BigInteger.valueOf(getSize(enrollmentFromDisabilitiesExit)));
				
				String acholoAbuseQuery = " and disabilitytype='10' and disabilityresponse='1' ";
				List<String> alchoholAbuse = getEnrollmentFromDisabilities(data.getSchema(), data, query+acholoAbuseQuery);
				List<String> alchoholAbuseExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+acholoAbuseQuery);
				List<String> alchoholAbuseStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+acholoAbuseQuery);
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eAlcoholAbuseAtEntry(BigInteger.valueOf(getSize(alchoholAbuse)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eAlcoholAbuseStayers(BigInteger.valueOf(getSize(alchoholAbuseStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eAlcoholAbuseLeavers(BigInteger.valueOf(getSize(alchoholAbuseExit)));

				String drugAbuseQuery = " and disabilitytype='10' and disabilityresponse='2' ";
				List<String> drugAbuse = getEnrollmentFromDisabilities(data.getSchema(), data, query+drugAbuseQuery);
				List<String> drugAbuseExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+drugAbuseQuery);
				List<String> drugAbuseStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+drugAbuseQuery);
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eDrugAbuseAtEntry(BigInteger.valueOf(getSize(drugAbuse)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eDrugAbuseStayers(BigInteger.valueOf(getSize(drugAbuseStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eDrugAbuseLeavers(BigInteger.valueOf(getSize(drugAbuseExit)));
				
				String alcoholAndDrugQuery = "  and disabilitytype='10' and disabilityresponse='3' ";
				List<String> alcoholAndDrug = getEnrollmentFromDisabilities(data.getSchema(), data, query+alcoholAndDrugQuery);
				List<String> alcoholAndDrugExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+alcoholAndDrugQuery);
				List<String> alcoholAndDrugStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+alcoholAndDrugQuery);
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eBothAlcoholAndDrugAbuseAtEntry(BigInteger.valueOf(getSize(alcoholAndDrug)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eBothAlcoholAndDrugAbuseStayers(BigInteger.valueOf(getSize(alcoholAndDrugStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eBothAlcoholAndDrugAbuseLeavers(BigInteger.valueOf(getSize(alcoholAndDrugExit)));

				String chronicHealthConditionQuery = "   and disabilitytype='7' ";
				List<String> chronicHealthCondition = getEnrollmentFromDisabilities(data.getSchema(), data, query+chronicHealthConditionQuery);
				List<String> chronicHealthConditionExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+chronicHealthConditionQuery);
				List<String> chronicHealthConditionStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+chronicHealthConditionQuery);
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eChronicHealthConditionAtEntry(BigInteger.valueOf(getSize(chronicHealthCondition)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eChronicHealthConditionStayers(BigInteger.valueOf(getSize(chronicHealthConditionStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eChronicHealthConditionLeavers(BigInteger.valueOf(getSize(chronicHealthConditionExit)));

				String hivRelatedDiseaseQuery = "   and disabilitytype='8' ";
				List<String> hivRelatedDisease = getEnrollmentFromDisabilities(data.getSchema(), data, query+hivRelatedDiseaseQuery);
				List<String> hivRelatedDiseaseExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+hivRelatedDiseaseQuery);
				List<String> hivRelatedDiseaseStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+hivRelatedDiseaseQuery);
				
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eHIVandRelatedDiseaseAtEntry(BigInteger.valueOf(getSize(hivRelatedDisease)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eHIVandRelatedDiseaseStayers(BigInteger.valueOf(getSize(hivRelatedDiseaseStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eHIVandRelatedDiseaseLeavers(BigInteger.valueOf(getSize(hivRelatedDiseaseExit)));

				String developmentalDisabilityQuery = "  and disabilitytype='5' ";
				List<String> developmentalDisability = getEnrollmentFromDisabilities(data.getSchema(), data, query+developmentalDisabilityQuery);
				List<String> developmentalDisabilityExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+developmentalDisabilityQuery);
				List<String> developmentalDisabilityStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+developmentalDisabilityQuery);
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eDevelopmentalDisabilityAtEntry(BigInteger.valueOf(getSize(developmentalDisability)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eDevelopmentalDisabilityStayers(BigInteger.valueOf(getSize(developmentalDisabilityStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25eDevelopmentalDisabilityLeavers(BigInteger.valueOf(getSize(developmentalDisabilityExit)));

				String physicalDisabilityQuery = "  and disabilitytype='6' ";
				List<String> physicalDisability = getEnrollmentFromDisabilities(data.getSchema(), data, query+physicalDisabilityQuery);
				List<String> physicalDisabilityExit = getEnrollmentFromDisabilities(data.getSchema(), data, queryExit+physicalDisabilityQuery);
				List<String> physicalDisabilityStayers = getEnrollmentFromDisabilities(data.getSchema(), data, queryStayers+physicalDisabilityQuery);
				
				
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25ePhysicalDisabilityAtEntry(BigInteger.valueOf(getSize(physicalDisability)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25ePhysicalDisabilityStayers(BigInteger.valueOf(getSize(physicalDisabilityStayers)));
				q25ePhysicalAndMentalHealthConditionsVeteransTable.setQ25ePhysicalDisabilityLeavers(BigInteger.valueOf(getSize(physicalDisabilityExit)));
			}
		}catch(Exception e){
			logger.error("Error in Q25ePhysicalAndMentalHealthConditionsVeteransDataBeanMaker:" + e);
		}
			
				return Arrays.asList(q25ePhysicalAndMentalHealthConditionsVeteransTable);
	}

}
