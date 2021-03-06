/**
 *
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.enums.EnrollmentDisablingconditionEnum;
import com.servinglynk.hmis.warehouse.enums.EnrollmentMonthshomelesspastthreeyearsEnum;
import com.servinglynk.hmis.warehouse.enums.EnrollmentRelationshiptohohEnum;
import com.servinglynk.hmis.warehouse.enums.EnrollmentTimeshomelesspastthreeyearsEnum;
import com.servinglynk.hmis.warehouse.enums.LengthOfStayEnum;
import com.servinglynk.hmis.warehouse.enums.LivingSituationEnum;
import com.servinglynk.hmis.warehouse.enums.NoYesEnum;
import com.servinglynk.hmis.warehouse.model.v2017.Error2017;
import com.servinglynk.hmis.warehouse.model.v2017.HmisBaseModel;
import com.servinglynk.hmis.warehouse.model.v2017.Project;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class EnrollmentDaoImpl extends ParentDaoImpl implements EnrollmentDao {
	private static final Logger logger = LoggerFactory
			.getLogger(EnrollmentDaoImpl.class);
	@Autowired
	ParentDaoFactory parentDaoFactory;
	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		Export export =  domain.getExport();
		com.servinglynk.hmis.warehouse.model.v2017.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2017.Export) getModel(com.servinglynk.hmis.warehouse.model.v2017.Export.class,domain.getExport().getExportID(),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		Data data =new Data();
		List<com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Enrollment> enrollments = export
				.getEnrollment();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class, getProjectGroupCode(domain));
		Map<String,HmisBaseModel> projectModelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2017.Project.class, getProjectGroupCode(domain));
		if (CollectionUtils.isNotEmpty(enrollments)) {
			for(com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Enrollment enrollment  :  enrollments)
			{
				com.servinglynk.hmis.warehouse.model.v2017.Enrollment enrollmentModel = null;
				try {
					enrollmentModel = getModelObject(domain, enrollment,data,modelMap);
//				enrollmentModel
//						.setContinuouslyhomelessoneyear(EnrollmentContinuouslyhomelessoneyearEnum.lookupEnum((enrollment
//								.getContinuouslyHomelessOneYear())));
//					enrollmentModel.setHousingstatus(EnrollmentHousingstatusEnum.lookupEnum((enrollment.getHousingStatus())));
					enrollmentModel.setDisablingcondition(EnrollmentDisablingconditionEnum.lookupEnum(enrollment.getDisablingCondition()));
					enrollmentModel.setRelationshiptohoh(EnrollmentRelationshiptohohEnum.lookupEnum((enrollment.getRelationshipToHoH())));
					enrollmentModel.setLivingSituation(LivingSituationEnum.lookupEnum(enrollment.getLivingSituation()));
					enrollmentModel.setLengthOfStay(LengthOfStayEnum.lookupEnum(enrollment.getLengthOfStay()));
					enrollmentModel.setDateCreatedFromSource(BasicDataGenerator
							.getLocalDateTime(enrollment.getDateCreated()));
					enrollmentModel.setDateUpdatedFromSource(BasicDataGenerator
							.getLocalDateTime(enrollment.getDateUpdated()));
					enrollmentModel.setEntrydate(BasicDataGenerator
							.getLocalDateTime(enrollment.getEntryDate()));
					enrollmentModel
					.setMonthshomelesspastthreeyears(EnrollmentMonthshomelesspastthreeyearsEnum.lookupEnum(enrollment
							.getMonthsHomelessPastThreeYears()));
					enrollmentModel.setHouseholdid(enrollment.getHouseholdID());
					enrollmentModel.setDatetostreetessh(BasicDataGenerator
							.getLocalDateTime(enrollment.getDateToStreetESSH()));
//				enrollmentModel
//						.setMonthshomelessthistime(EnrollmentMonthshomelessthistimeEnum.lookupEnum(BasicDataGenerator
//								.getStringValue(enrollment
//										.getMonthsHomelessThisTime())));
//					enrollmentModel.setOtherresidenceprior(enrollment
//							.getOtherResidencePrior());
					enrollmentModel.setLosunderthreshold(NoYesEnum.lookupEnum(enrollment.getLosUnderThreshold()));
					enrollmentModel.setPreviousStreetESSH(NoYesEnum.lookupEnum(enrollment.getPreviousStreetESSH()));
					com.servinglynk.hmis.warehouse.model.v2017.Project project = (Project) getModel(com.servinglynk.hmis.warehouse.model.v2017.Project.class,enrollment.getProjectID(),getProjectGroupCode(domain),true,projectModelMap, domain.getUpload().getId());
					enrollmentModel.setProject(project);
					enrollmentModel.setTimeshomelesspastthreeyears(EnrollmentTimeshomelesspastthreeyearsEnum.lookupEnum(enrollment.getTimesHomelessPastThreeYears()));
					com.servinglynk.hmis.warehouse.model.v2017.Client client = (com.servinglynk.hmis.warehouse.model.v2017.Client) getModel(com.servinglynk.hmis.warehouse.model.v2017.Client.class, enrollment.getPersonalID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
					//TODO: Need to add Unduping logic here and get a unique Client for enrollments.
					// Very important logic needs to come here via a Microservice call.
					enrollmentModel.setClient(client);
					enrollmentModel.setExport(exportEntity);
					performSaveOrUpdate(enrollmentModel, domain);
				} catch(Exception e) {
					String errorMessage = "Exception beause of the enrollment::"+enrollment.getEnrollmentID() +" Exception ::"+e.getMessage();
					if(enrollmentModel != null){
						Error2017 error = new Error2017();
						error.model_id = enrollmentModel.getId();
						error.bulk_upload_ui = domain.getUpload().getId();
						error.project_group_code = domain.getUpload().getProjectGroupCode();
						error.source_system_id = enrollmentModel.getSourceSystemId();
						error.type = ErrorType.ERROR;
						error.error_description = errorMessage;
						error.date_created = enrollmentModel.getDateCreated();
						performSave(error);
					}
					logger.error(errorMessage);
				}
			}
		}
		hydrateBulkUploadActivityStaging(data.i,data.j,data.ignore, com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class.getSimpleName(), domain,exportEntity);
	}

	public com.servinglynk.hmis.warehouse.model.v2017.Enrollment getModelObject(ExportDomain domain, com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Enrollment enrollment ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2017.Enrollment modelFromDB = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			modelFromDB = (com.servinglynk.hmis.warehouse.model.v2017.Enrollment) getModel(com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class, enrollment.getEnrollmentID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(domain.isReUpload()) {
			if(modelFromDB != null) {
				return modelFromDB;
			}
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2017.Enrollment();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true);
			return modelFromDB;
		}
		if(modelFromDB == null) {
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2017.Enrollment();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true);
		}
		com.servinglynk.hmis.warehouse.model.v2017.Enrollment model = new com.servinglynk.hmis.warehouse.model.v2017.Enrollment();
		// org.springframework.beans.BeanUtils.copyProperties(modelFromDB, model);
		model.setId(modelFromDB.getId());
		model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(enrollment.getDateUpdated()));
		performMatch(domain, modelFromDB, model, data);
		hydrateCommonFields(model, domain,enrollment.getEnrollmentID(),data);
		return model;
	}
	public com.servinglynk.hmis.warehouse.model.v2017.Enrollment getEnrollmentById(UUID enrollmentId) {
	      DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class);
	      criteria.add(Restrictions.eq("id", enrollmentId));
	      List<com.servinglynk.hmis.warehouse.model.v2017.Enrollment> entities = (List<com.servinglynk.hmis.warehouse.model.v2017.Enrollment>) findByCriteria(criteria);
	      if(!entities.isEmpty()) return entities.get(0);
	      return null;
	}




	@Override
	public com.servinglynk.hmis.warehouse.model.v2017.Enrollment createEnrollment(
			com.servinglynk.hmis.warehouse.model.v2017.Enrollment enrollment) {
			enrollment.setId(UUID.randomUUID());
			insert(enrollment);
		return enrollment;
	}

	@Override
	public com.servinglynk.hmis.warehouse.model.v2017.Enrollment updateEnrollment(
			com.servinglynk.hmis.warehouse.model.v2017.Enrollment enrollment) {
			update(enrollment);
		return enrollment;
	}

	@Override
	public void deleteEnrollment(
			com.servinglynk.hmis.warehouse.model.v2017.Enrollment enrollment) {
		delete(enrollment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public com.servinglynk.hmis.warehouse.model.v2017.Enrollment getEnrollmentByClientIdAndEnrollmentId(
			UUID enrollmentId,UUID clientId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class);
		criteria.createAlias("client","client");
		criteria.add(Restrictions.eq("client.id",clientId));
		criteria.add(Restrictions.eq("id",enrollmentId));

		List<com.servinglynk.hmis.warehouse.model.v2017.Enrollment> enrollments = (List<com.servinglynk.hmis.warehouse.model.v2017.Enrollment>) findByCriteria(criteria);
		if(enrollments.size()>0) return enrollments.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.servinglynk.hmis.warehouse.model.v2017.Enrollment> getEnrollmentsByClientId(UUID clientId,Integer startIndex, Integer maxItems) {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class);
		criteria.createAlias("client","client");
		criteria.add(Restrictions.eq("client.id",clientId));

		return (List<com.servinglynk.hmis.warehouse.model.v2017.Enrollment>) findByCriteria(criteria,startIndex,maxItems);
	}

	public long getEnrollmentCount(UUID clientId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.Enrollment.class);
		criteria.createAlias("client","client");
		criteria.add(Restrictions.eq("client.id",clientId));
		return countRows(criteria);
	}

	@Override
	public void hydrateHBASE(SyncDomain syncDomain) {
		// TODO Auto-generated method stub

	}
}

