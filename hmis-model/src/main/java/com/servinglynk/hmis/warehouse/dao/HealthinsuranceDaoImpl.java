/**
 * 
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.HealthInsurance;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceInsurancefromanysourceEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceMedicaidEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceMedicareEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNocobrareasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNoemployerprovidedreasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNomedicaidreasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNomedicarereasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNoprivatepayreasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNoschipreasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNostatehealthinsreasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceNovamedreasonEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsurancePrivatepayEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceSchipEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceStatehealthinsEnum;
import com.servinglynk.hmis.warehouse.enums.HealthinsuranceVamedicalservicesEnum;
import com.servinglynk.hmis.warehouse.model.staging.Enrollment;
import com.servinglynk.hmis.warehouse.model.staging.Export;
import com.servinglynk.hmis.warehouse.model.staging.Healthinsurance;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class HealthinsuranceDaoImpl extends ParentDaoImpl implements
		HealthinsuranceDao {

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain) {
		List<HealthInsurance> healthInsurances = domain.getExport().getHealthInsurance();
		if(healthInsurances!=null && healthInsurances.size() >0 )
		{
			for(HealthInsurance healthInsurance : healthInsurances)
			{
				Healthinsurance healthinsuranceModel = new Healthinsurance();
				healthinsuranceModel.setId(UUID.randomUUID());
				healthinsuranceModel.setCobra(BasicDataGenerator.getIntegerValue(healthInsurance.getCOBRA()));
				healthinsuranceModel.setEmployerprovided(BasicDataGenerator.getIntegerValue(healthInsurance.getEmployerProvided()));
				healthinsuranceModel.setInsurancefromanysource(HealthinsuranceInsurancefromanysourceEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getInsuranceFromAnySource())));
				healthinsuranceModel.setMedicaid(HealthinsuranceMedicaidEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getMedicaid())));
				healthinsuranceModel.setMedicare(HealthinsuranceMedicareEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getMedicare())));
				healthinsuranceModel.setNocobrareason(HealthinsuranceNocobrareasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoCOBRAReason())));
				healthinsuranceModel.setNoemployerprovidedreason(HealthinsuranceNoemployerprovidedreasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoEmployerProvidedReason())));
				healthinsuranceModel.setNomedicaidreason(HealthinsuranceNomedicaidreasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoMedicaidReason())));
				healthinsuranceModel.setNomedicarereason(HealthinsuranceNomedicarereasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoMedicareReason())));
				healthinsuranceModel.setNoprivatepayreason(HealthinsuranceNoprivatepayreasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoPrivatePayReason())));
				healthinsuranceModel.setNoschipreason(HealthinsuranceNoschipreasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoSCHIPReason())));
				healthinsuranceModel.setNostatehealthinsreason(HealthinsuranceNostatehealthinsreasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoStateHealthInsReason())));
				healthinsuranceModel.setNovamedreason(HealthinsuranceNovamedreasonEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getNoVAMedReason())));
				healthinsuranceModel.setPrivatepay(HealthinsurancePrivatepayEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getPrivatePay())));
				healthinsuranceModel.setSchip(HealthinsuranceSchipEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getSCHIP())));
				healthinsuranceModel.setStatehealthins(HealthinsuranceStatehealthinsEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getStateHealthIns())));
				healthinsuranceModel.setVamedicalservices(HealthinsuranceVamedicalservicesEnum.lookupEnum(BasicDataGenerator.getStringValue(healthInsurance.getVAMedicalServices())));
				healthinsuranceModel.setDateCreated(BasicDataGenerator.getLocalDate(healthInsurance.getDateCreated()));
				healthinsuranceModel.setDateUpdated(BasicDataGenerator.getLocalDate(healthInsurance.getDateUpdated()));
				Enrollment enrollmentModel = (Enrollment) get(Enrollment.class, domain.getEnrollmentProjectEntryIDMap().get(healthInsurance.getProjectEntryID()));
				healthinsuranceModel.setEnrollmentid(enrollmentModel);
				com.servinglynk.hmis.warehouse.model.staging.Export exportEntity = (com.servinglynk.hmis.warehouse.model.staging.Export) get(com.servinglynk.hmis.warehouse.model.staging.Export.class, domain.getExportId());
				healthinsuranceModel.setExport(exportEntity);
				exportEntity.addHealthinsurance(healthinsuranceModel);
				insertOrUpdate(healthinsuranceModel);
			}
		}
	}

	@Override
	public void hydrateLive(Export export) {
		Set<Healthinsurance> healthinsurances = export.getHealthinsurances();
		if(healthinsurances !=null && !healthinsurances.isEmpty()) {
			for(Healthinsurance healthinsurance : healthinsurances) {
				com.servinglynk.hmis.warehouse.model.live.Healthinsurance target = new com.servinglynk.hmis.warehouse.model.live.Healthinsurance();
				BeanUtils.copyProperties(healthinsurance, target,getNonCollectionFields(target));
				insert(target);
			}
		}
		
	}

}
