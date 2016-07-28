/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.model.v2014.Error2014;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.SchoolStatus;
import com.servinglynk.hmis.warehouse.enums.SchoolStatusEnum;
import com.servinglynk.hmis.warehouse.model.v2014.Enrollment;
import com.servinglynk.hmis.warehouse.model.v2014.HmisBaseModel;
import com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class SchoolstatusDaoImpl extends ParentDaoImpl implements
		SchoolstatusDao {
	private static final Logger logger = LoggerFactory
			.getLogger(SchoolstatusDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		List<SchoolStatus> schoolStatusList = domain.getExport().getSchoolStatus();
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus.class, getProjectGroupCode(domain));
		com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2014.Export) getModel(com.servinglynk.hmis.warehouse.model.v2014.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		if(schoolStatusList!=null && !schoolStatusList.isEmpty())
		{
			for(SchoolStatus schoolStatus : schoolStatusList)
			{
				Schoolstatus schoolstatusModel = null;
				try {
					schoolstatusModel = getModelObject(domain, schoolStatus,data,modelMap);
					schoolstatusModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(schoolStatus.getDateCreated()));
					schoolstatusModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(schoolStatus.getDateUpdated()));
					schoolstatusModel.setInformationDate(BasicDataGenerator.getLocalDateTime(schoolStatus.getInformationDate()));
					schoolstatusModel.setSchoolStatus(
							SchoolStatusEnum
							.lookupEnum(BasicDataGenerator
									.getStringValue(schoolStatus.getSchoolStatus())));
					Enrollment enrollmentModel = (Enrollment) getModel(Enrollment.class, schoolStatus.getProjectEntryID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
					schoolstatusModel.setEnrollmentid(enrollmentModel);
					schoolstatusModel.setExport(exportEntity);
					performSaveOrUpdate(schoolstatusModel);
				}catch(Exception e) {
					String errorMessage = "Failure in Schoolstatus:::"+schoolStatus.toString()+ " with exception"+e.getLocalizedMessage();
					if (schoolstatusModel != null) {
						Error2014 error = new Error2014();
						error.model_id = schoolstatusModel.getId();
						error.bulk_upload_ui = domain.getUpload().getId();
						error.project_group_code = domain.getUpload().getProjectGroupCode();
						error.source_system_id = schoolstatusModel.getSourceSystemId();
						error.type = ErrorType.ERROR;
						error.error_description = errorMessage;
						error.date_created = schoolstatusModel.getDateCreated();
						performSave(error);
					}
					logger.error(errorMessage);
				}
			
			}
		}
		hydrateBulkUploadActivityStaging(data.i,data.j, com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus.class.getSimpleName(), domain,exportEntity);
	}
	public com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus getModelObject(ExportDomain domain,SchoolStatus schoolstatus ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus SchoolstatusModel = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			SchoolstatusModel = (com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus) getModel(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus.class, schoolstatus.getSchoolStatusID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(SchoolstatusModel == null) {
			SchoolstatusModel = new com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus();
			SchoolstatusModel.setId(UUID.randomUUID());
			SchoolstatusModel.setInserted(true);
			++data.i;
		}else{
			++data.j;
		}
		hydrateCommonFields(SchoolstatusModel, domain,schoolstatus.getSchoolStatusID(),data.i+data.j);
		return SchoolstatusModel;
	}
	   public com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus createSchoolstatus(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus schoolstatus){
	       schoolstatus.setId(UUID.randomUUID()); 
	       insert(schoolstatus);
	       return schoolstatus;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus updateSchoolstatus(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus schoolstatus){
	       update(schoolstatus);
	       return schoolstatus;
	   }
	   public void deleteSchoolstatus(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus schoolstatus){
	       delete(schoolstatus);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus getSchoolstatusById(UUID schoolstatusId){ 
	       return (com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus) get(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus.class, schoolstatusId);
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus> getAllEnrollmentSchoolstatuss(UUID enrollmentId,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return (List<com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getEnrollmentSchoolstatussCount(UUID enrollmentId){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2014.Schoolstatus.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return countRows(criteria);
	   }
}
