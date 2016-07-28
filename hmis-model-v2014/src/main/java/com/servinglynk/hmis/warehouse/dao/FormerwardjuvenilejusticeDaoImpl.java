/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.model.v2014.Error2014;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.FormerWardJuvenileJustice;
import com.servinglynk.hmis.warehouse.enums.FormerwardjuvenilejusticeFormerwardjuvenilejusticeEnum;
import com.servinglynk.hmis.warehouse.enums.FormerwardjuvenilejusticeJuvenilejusticeyearsEnum;
import com.servinglynk.hmis.warehouse.model.v2014.Enrollment;
import com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice;
import com.servinglynk.hmis.warehouse.model.v2014.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class FormerwardjuvenilejusticeDaoImpl extends ParentDaoImpl implements
		FormerwardjuvenilejusticeDao {
	private static final Logger logger = LoggerFactory
			.getLogger(FormerwardjuvenilejusticeDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		List<FormerWardJuvenileJustice> formerWardJuvenileJustices = domain.getExport().getFormerWardJuvenileJustice();
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice.class, getProjectGroupCode(domain));
		com.servinglynk.hmis.warehouse.model.v2014.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2014.Export) getModel(com.servinglynk.hmis.warehouse.model.v2014.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		if(formerWardJuvenileJustices !=null && !formerWardJuvenileJustices.isEmpty()) 
		{
			for(FormerWardJuvenileJustice formerWardJuvenileJustice : formerWardJuvenileJustices )
			{
				Formerwardjuvenilejustice formerWardJuvenileJusticeModel = null;
				try {
					formerWardJuvenileJusticeModel = getModelObject(domain, formerWardJuvenileJustice,data,modelMap);
					formerWardJuvenileJusticeModel.setFormerwardjuvenilejustice(FormerwardjuvenilejusticeFormerwardjuvenilejusticeEnum.lookupEnum(BasicDataGenerator.getStringValue(formerWardJuvenileJustice.getFormerWardJuvenileJustice())));
					formerWardJuvenileJusticeModel.setJuvenilejusticemonths(BasicDataGenerator.getIntegerValue(formerWardJuvenileJustice.getJuvenileJusticeMonths()));
					formerWardJuvenileJusticeModel.setJuvenilejusticeyears(FormerwardjuvenilejusticeJuvenilejusticeyearsEnum.lookupEnum(BasicDataGenerator.getStringValue(formerWardJuvenileJustice.getJuvenileJusticeYears())));
					formerWardJuvenileJusticeModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(formerWardJuvenileJustice.getDateCreated()));
					formerWardJuvenileJusticeModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(formerWardJuvenileJustice.getDateUpdated()));
					Enrollment enrollmentModel = (Enrollment) getModel(Enrollment.class, formerWardJuvenileJustice.getProjectEntryID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
					formerWardJuvenileJusticeModel.setEnrollmentid(enrollmentModel);
					formerWardJuvenileJusticeModel.setExport(exportEntity);
					performSaveOrUpdate(formerWardJuvenileJusticeModel);
				}catch (Exception e) {
					String errorMessage = "Exception in:"+formerWardJuvenileJustice.getProjectEntryID()+  ":: Exception" +e.getLocalizedMessage();
					if (formerWardJuvenileJusticeModel != null) {
						Error2014 error = new Error2014();
						error.model_id = formerWardJuvenileJusticeModel.getId();
						error.bulk_upload_ui = domain.getUpload().getId();
						error.project_group_code = domain.getUpload().getProjectGroupCode();
						error.source_system_id = formerWardJuvenileJusticeModel.getSourceSystemId();
						error.type = ErrorType.ERROR;
						error.error_description = errorMessage;
						error.date_created = formerWardJuvenileJusticeModel.getDateCreated();
						performSave(error);
					}
					logger.error(errorMessage);
				}
			}
		}
		hydrateBulkUploadActivityStaging(data.i,data.j, com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice.class.getSimpleName(), domain,exportEntity);
	}
	
	public com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice getModelObject(ExportDomain domain, FormerWardJuvenileJustice formerWardJuvenileJustice ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice formerwardjuvenilejusticeModel = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			formerwardjuvenilejusticeModel = (com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice) getModel(com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice.class, formerWardJuvenileJustice.getFormerWardJuvenileJusticeID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(formerwardjuvenilejusticeModel == null) {
			formerwardjuvenilejusticeModel = new com.servinglynk.hmis.warehouse.model.v2014.Formerwardjuvenilejustice();
			formerwardjuvenilejusticeModel.setId(UUID.randomUUID());
			formerwardjuvenilejusticeModel.setInserted(true);
			++data.i;
		}else{
			++data.j;
		}
		hydrateCommonFields(formerwardjuvenilejusticeModel, domain,formerWardJuvenileJustice.getFormerWardJuvenileJusticeID(),data.i+data.j);
		return formerwardjuvenilejusticeModel;
	}
}
