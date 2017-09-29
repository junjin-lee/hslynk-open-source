package com.servinglynk.hmis.warehouse.service.core;

import com.servinglynk.hmis.warehouse.base.service.BulkUploadService;
import com.servinglynk.hmis.warehouse.base.service.SearchService;
import com.servinglynk.hmis.warehouse.base.service.core.BaseServiceFactory;
import com.servinglynk.hmis.warehouse.service.AffiliationService;
import com.servinglynk.hmis.warehouse.service.BulkUploadErrorActivityService;
import com.servinglynk.hmis.warehouse.service.ClientService;
import com.servinglynk.hmis.warehouse.service.CommercialSexualExploitationService;
import com.servinglynk.hmis.warehouse.service.ConnectionwithsoarService;
import com.servinglynk.hmis.warehouse.service.DateofengagementService;
import com.servinglynk.hmis.warehouse.service.DisabilitiesService;
import com.servinglynk.hmis.warehouse.service.DomesticViolenceService;
import com.servinglynk.hmis.warehouse.service.EmploymentService;
import com.servinglynk.hmis.warehouse.service.EnrollmentService;
import com.servinglynk.hmis.warehouse.service.ExitPlansActionsService;
import com.servinglynk.hmis.warehouse.service.ExitService;
import com.servinglynk.hmis.warehouse.service.FamilyReunificationService;
import com.servinglynk.hmis.warehouse.service.FunderService;
import com.servinglynk.hmis.warehouse.service.HMISTypeService;
import com.servinglynk.hmis.warehouse.service.HealthInsuranceService;
import com.servinglynk.hmis.warehouse.service.HealthStatusService;
import com.servinglynk.hmis.warehouse.service.HousingAssessmentDispositionService;
import com.servinglynk.hmis.warehouse.service.IncomeAndSourceService;
import com.servinglynk.hmis.warehouse.service.InformationReleasesService;
import com.servinglynk.hmis.warehouse.service.InventoryService;
import com.servinglynk.hmis.warehouse.service.LastPermanentAddressService;
import com.servinglynk.hmis.warehouse.service.MedicalassistanceService;
import com.servinglynk.hmis.warehouse.service.NonCashBenefitService;
import com.servinglynk.hmis.warehouse.service.OrganizationService;
import com.servinglynk.hmis.warehouse.service.PathstatusService;
import com.servinglynk.hmis.warehouse.service.PercentamiService;
import com.servinglynk.hmis.warehouse.service.ProjectCompletionStatusService;
import com.servinglynk.hmis.warehouse.service.ProjectService;
import com.servinglynk.hmis.warehouse.service.ProjectcocService;
import com.servinglynk.hmis.warehouse.service.QuestionService;
import com.servinglynk.hmis.warehouse.service.ReferralsourceService;
import com.servinglynk.hmis.warehouse.service.ResidentialmoveindateService;
import com.servinglynk.hmis.warehouse.service.RhybcpstatusService;
import com.servinglynk.hmis.warehouse.service.SchoolstatusService;
import com.servinglynk.hmis.warehouse.service.ServicesService;
import com.servinglynk.hmis.warehouse.service.SexualorientationService;
import com.servinglynk.hmis.warehouse.service.SharingRuleService;
import com.servinglynk.hmis.warehouse.service.SiteService;
import com.servinglynk.hmis.warehouse.service.VeteranInfoService;
import com.servinglynk.hmis.warehouse.service.WorstHousingSituationService;
import com.servinglynk.hmis.warehouse.service.YouthCriticalIssuesService;



public interface ParentServiceFactory  extends BaseServiceFactory {
	
	SharingRuleService getSharingRuleService();
	EnrollmentService getEnrollmentService();
	InformationReleasesService getInformationReleasesService();
	ProjectService getProjectService();
	ExitService getExitService();
	EmploymentService getEmploymentService();
	VeteranInfoService getVeteranInfoService();
	DisabilitiesService getDisabilitiesService();
	ConnectionwithsoarService getConnectionwithsoarService();
	ExitPlansActionsService getExitPlansActionsService();
	FamilyReunificationService getFamilyReunificationService();
	HousingAssessmentDispositionService getHousingAssessmentDispositionService();
	ProjectCompletionStatusService getProjectCompletionStatusService();
	IncomeAndSourceService getIncomeAndSourceService();
	HealthInsuranceService getHealthInsuranceService();
	ServicesService getServicesService();
	HealthStatusService getHealthStatusService();
	CommercialSexualExploitationService getCommercialSexualExploitationService();
	DateofengagementService getDateofengagementService();
	DomesticViolenceService getDomesticViolenceService();
	LastPermanentAddressService getLastPermanentAddressService();
	YouthCriticalIssuesService getYouthCriticalIssuesService();
	WorstHousingSituationService getWorstHousingSituationService();
	SexualorientationService getSexualorientationService();
	SchoolstatusService getSchoolstatusService();
	PathstatusService getPathstatusService();
	PercentamiService getPercentamiService();
	RhybcpstatusService getRhybcpstatusService();
	ResidentialmoveindateService getResidentialmoveindateService();
	ReferralsourceService getReferralsourceService();
	MedicalassistanceService getMedicalassistanceService();
	ProjectcocService getProjectcocService();
	FunderService getFunderService();
	AffiliationService getAffiliationService();
	NonCashBenefitService getNonCashBenefitService();
	SiteService getSiteService();
	InventoryService getInventoryService();
	BulkUploadService getBulkUploadService();
	OrganizationService getOrganizationService();
	ClientService getClientService();
	SearchService getSearchService();
	BulkUploadErrorActivityService getBulkUploadErrorActivityService();
	HMISTypeService getHmisTypeService();
	QuestionService getQuestionService();
}
