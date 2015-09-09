/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.PATHStatus;
import com.servinglynk.hmis.warehouse.model.staging.Enrollment;
import com.servinglynk.hmis.warehouse.model.staging.Export;
import com.servinglynk.hmis.warehouse.model.staging.Pathstatus;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class PathstatusDaoImpl extends ParentDaoImpl implements PathstatusDao {

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain) {
		List<PATHStatus> pathStatusList = domain.getExport().getPATHStatus();
		if(pathStatusList !=null && !pathStatusList.isEmpty())
		{
			for(PATHStatus pathStatus : pathStatusList)
			{
				Pathstatus pathstatusModel = new Pathstatus();
				UUID id = UUID.randomUUID();
				pathstatusModel.setId(id);
				pathstatusModel.setClientEnrolledInPath( new Long(BasicDataGenerator.getStringValue(pathStatus.getClientEnrolledInPATH())));
				pathstatusModel.setReasonNotEnrolled(new Long(BasicDataGenerator.getStringValue(pathStatus.getReasonNotEnrolled())));
				pathstatusModel.setDateCreated(BasicDataGenerator.getLocalDate(pathStatus.getDateCreated()));
				pathstatusModel.setDateUpdated(BasicDataGenerator.getLocalDate(pathStatus.getDateUpdated()));
				Enrollment enrollmentModel = (Enrollment) get(Enrollment.class, domain.getEnrollmentProjectEntryIDMap().get(pathStatus.getProjectEntryID()));
				pathstatusModel.setEnrollmentid(enrollmentModel);
				com.servinglynk.hmis.warehouse.model.staging.Export exportEntity = (com.servinglynk.hmis.warehouse.model.staging.Export) get(com.servinglynk.hmis.warehouse.model.staging.Export.class, domain.getExportId());
				pathstatusModel.setExport(exportEntity);
				exportEntity.addPathstatus(pathstatusModel);
				insertOrUpdate(pathstatusModel);
			}
		}
	}

	@Override
	public void hydrateLive(Export export) {
		Set<Pathstatus> pathstatuses = export.getPathstatuses();
		if(pathstatuses != null && !pathstatuses.isEmpty()) {
			for(Pathstatus pathStatus : pathstatuses) {
				if(pathStatus != null) {
					com.servinglynk.hmis.warehouse.model.live.Pathstatus target = new com.servinglynk.hmis.warehouse.model.live.Pathstatus();
					BeanUtils.copyProperties(pathStatus, target,getNonCollectionFields(target));
					insert(target);
				}
			}
		}
	}

}
