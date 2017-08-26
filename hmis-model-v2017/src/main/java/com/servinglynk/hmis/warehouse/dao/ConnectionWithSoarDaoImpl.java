package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.lang.model.type.ErrorType;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar;
import com.servinglynk.hmis.warehouse.model.v2017.Error2017;
import com.servinglynk.hmis.warehouse.model.v2017.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

public class ConnectionWithSoarDaoImpl extends ParentDaoImpl implements ConnectionWithSoarDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionWithSoarDaoImpl.class);
	@Autowired
	private ParentDaoFactory factory;

	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		 List<ConnectionWithSoar> connectionWithSoars = domain.getExport().getConnectionWithSoar();
		 com.servinglynk.hmis.warehouse.model.v2017.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2017.Export) getModel(com.servinglynk.hmis.warehouse.model.v2017.Export.class,String.valueOf(domain.getExport().getExportID()),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		 Data data =new Data();
		 Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar.class, getProjectGroupCode(domain));
		 if(connectionWithSoars != null && !connectionWithSoars.isEmpty())
		 {
			 for(ConnectionWithSoar connectionWithSoar : connectionWithSoars)
			 {
				 com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoarModel = null;
				 try {
					 connectionWithSoarModel = getModelObject(domain, connectionWithSoar,data,modelMap);
					 connectionWithSoarModel.setConnectionWithSoar(connectionWithSoar.getConnectionWithSoar());
					 connectionWithSoarModel.setInformationDate(connectionWithSoar.getInformationDate());
					 
					 performSaveOrUpdate(connectionWithSoarModel);
				 } catch(Exception e){
					 String errorMessage = "Exception because of the connectionWithSoar::"+connectionWithSoar.getId() +" Exception ::"+e.getMessage();
					 if(connectionWithSoarModel != null){
						 Error2017 error = new Error2017();
						 error.model_id = connectionWithSoarModel.getId();
						 error.bulk_upload_ui = domain.getUpload().getId();
						 error.project_group_code = domain.getUpload().getProjectGroupCode();
						 error.source_system_id = connectionWithSoarModel.getSourceSystemId();
						 error.type = ErrorType.ERROR;
						 error.error_description = errorMessage;
						 error.date_created = connectionWithSoarModel.getDateCreated();
						 performSave(error);
					 }
					 logger.error(errorMessage);
				 }
			 }
		 }
		 hydrateBulkUploadActivityStaging(data.i,data.j,data.ignore, com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar.class.getSimpleName(), domain,exportEntity);
	}
	
	public com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar getModelObject(ExportDomain domain, ConnectionWithSoar connectionWithSoar ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar modelFromDB = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			modelFromDB = (com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar) getModel(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar.class, connectionWithSoar.getId(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(modelFromDB == null) {
			modelFromDB = new com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar();
			modelFromDB.setId(UUID.randomUUID());
			modelFromDB.setRecordToBeInserted(true);
		}
		com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar model = new com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar();
		// org.springframework.beans.BeanUtils.copyProperties(modelFromDB, model);
		model.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(connectionWithSoar.getInformationDate()));
		performMatch(domain, modelFromDB, model, data);
		hydrateCommonFields(model, domain,connectionWithSoar.getId(),data);
		return model;
	}
	   public com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar createConnectionWithSoar(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoar){
		   connectionWithSoar.setId(UUID.randomUUID());
	       insert(connectionWithSoar);
	       ConnectionWithSoar entity = new ConnectionWithSoar();
	       BeanUtils.copyProperties(connectionWithSoar, entity);
	       insert(entity);
	       return connectionWithSoar;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar updateConnectionWithSoar(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoar){
	       update(connectionWithSoar);
	       return connectionWithSoar;
	   }
	   public void deleteConnectionWithSoar(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar connectionWithSoar){
	       delete(connectionWithSoar);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar getConnectionWithSoarById(UUID connectionWithSoarId){ 
		      DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar.class);
		      criteria.add(Restrictions.eq("id", connectionWithSoarId));
		      List<com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar> entities = (List<com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar>) findByCriteria(criteria);
		      if(!entities.isEmpty()) return entities.get(0);
		      return null;
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar> getAllConnectionWithSoar(String connectionWithSoar,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar.class);
	       criteria.add(Restrictions.eq("connectionWithSoar", connectionWithSoar));
	       return (List<com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getConnectionWithSoarCount(String connectionWithSoar){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2017.ConnectionWithSoar.class);
	       criteria.add(Restrictions.eq("connectionWithSoar", connectionWithSoar));
	       return countRows(criteria);
	   }

	@Override
	public void hydrateHBASE(SyncDomain syncDomain) {
		// TODO Auto-generated method stub
		
	}





}