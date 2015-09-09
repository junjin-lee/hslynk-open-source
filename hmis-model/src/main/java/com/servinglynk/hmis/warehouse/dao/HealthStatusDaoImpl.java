/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.HealthStatus;
import com.servinglynk.hmis.warehouse.enums.HealthStatusHealthCategoryEnum;
import com.servinglynk.hmis.warehouse.enums.HealthStatusHealthStatusEnum;
import com.servinglynk.hmis.warehouse.model.staging.Enrollment;
import com.servinglynk.hmis.warehouse.model.staging.Export;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class HealthStatusDaoImpl extends ParentDaoImpl implements
		HealthStatusDao {

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain) {
		List<HealthStatus> healthStatuses = domain.getExport().getHealthStatus();
		if(healthStatuses !=null &&  !healthStatuses.isEmpty())
		{
			for(HealthStatus healthStatus : healthStatuses )
			{
				com.servinglynk.hmis.warehouse.model.staging.HealthStatus healthStatusModel = new com.servinglynk.hmis.warehouse.model.staging.HealthStatus();
				healthStatusModel.setId(UUID.randomUUID());
				healthStatusModel.setDueDate(BasicDataGenerator.getLocalDate(healthStatus.getDueDate()));
				healthStatusModel.setHealthCategory(HealthStatusHealthCategoryEnum.lookupEnum(BasicDataGenerator.getStringValue(healthStatus.getHealthCategory())));
				healthStatusModel.setHealthStatus(HealthStatusHealthStatusEnum.lookupEnum(BasicDataGenerator.getStringValue(healthStatus.getHealthStatus())));
				healthStatusModel.setInformationDate(BasicDataGenerator.getLocalDate(healthStatus.getInformationDate()));
				healthStatusModel.setDateCreated(BasicDataGenerator.getLocalDate(healthStatus.getDateCreated()));
				healthStatusModel.setDateUpdated(BasicDataGenerator.getLocalDate(healthStatus.getDateUpdated()));
				Enrollment enrollmentModel = (Enrollment) get(Enrollment.class, domain.getEnrollmentProjectEntryIDMap().get(healthStatus.getProjectEntryID()));
				healthStatusModel.setEnrollmentid(enrollmentModel);
				com.servinglynk.hmis.warehouse.model.staging.Export exportEntity = (com.servinglynk.hmis.warehouse.model.staging.Export) get(com.servinglynk.hmis.warehouse.model.staging.Export.class, domain.getExportId());
				healthStatusModel.setExport(exportEntity);
				exportEntity.addHealthStatus(healthStatusModel);
				insertOrUpdate(healthStatusModel);
			}
      	  }
		}

	@Override
	public void hydrateLive(Export export) {
		Set<com.servinglynk.hmis.warehouse.model.staging.HealthStatus> healthStatuses = export.getHealthStatuses();
		if(healthStatuses !=null && !healthStatuses.isEmpty()) {
			for(com.servinglynk.hmis.warehouse.model.staging.HealthStatus healthStatus : healthStatuses) {
				if(healthStatus !=null) {
					com.servinglynk.hmis.warehouse.model.live.HealthStatus target = new com.servinglynk.hmis.warehouse.model.live.HealthStatus();
					BeanUtils.copyProperties(healthStatus, target,getNonCollectionFields(target));
					insert(target);
				}
			}
		}
	}
}
