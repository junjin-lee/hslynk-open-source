package com.servinglynk.hmis.warehouse.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.SearchRequest;
import com.servinglynk.hmis.warehouse.Sort;
import com.servinglynk.hmis.warehouse.SortedPagination;
import com.servinglynk.hmis.warehouse.base.service.SearchService;
import com.servinglynk.hmis.warehouse.base.service.converter.ClientConverter;
import com.servinglynk.hmis.warehouse.core.model.Account;
import com.servinglynk.hmis.warehouse.core.model.BaseClients;
import com.servinglynk.hmis.warehouse.core.model.SearchResults;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.model.base.Client;


public class BaseSearchServiceImpl extends ServiceBase implements SearchService {

	  @Transactional
	  public void indexing()
	  {
		  
		  String indexClassList = "com.servinglynk.hmis.warehouse.model.base.Client";
	    this.daoFactory.getSearchDao().indexing(indexClassList);
	  }
	  
	  @Transactional
	  public SearchResults performSearch(SearchRequest searchVo,String freeText, String sort, String order, Integer startIndex,
			  Integer maxItems,String exclude,Session session)
	  {
	    // searchVo = new SearchRequest();
	    boolean dedupIdFilter = false;
	    try{
	    	UUID.fromString(freeText);
	    	dedupIdFilter = false;
	    }catch (IllegalArgumentException e) {
	    		dedupIdFilter = true;
		}
	    Account account = serviceFactory.getAccountService().getAccount(session.getAccount(),false);
	    
	    	searchVo.setProjectGroupCode(account.getProjectGroup().getProjectGroupCode());
	    Sort sorting = new Sort();
	    sorting.setOrder(order);
	    sorting.setField(sort);
	    searchVo.setSort(sorting);
	    if(exclude!=null)
	    	searchVo.setExcludeFields(exclude.toLowerCase().split(","));
	    
	    searchVo.setDbfileds(new String[] { "firstName","lastName","middleName","sourceSystemId","ssn"});
	    searchVo.setFreeText(freeText);
	    searchVo.setSearchEntity(Client.class);
	    
	    SortedPagination pagination = new SortedPagination();
	    pagination.setFrom(startIndex);
	    pagination.setMaximum(maxItems);
	    pagination.setSort(sorting);
	    searchVo.setPagination(pagination);
	    
	    List<Client> searchItems = (List<Client>) this.daoFactory.getSearchDao().search(searchVo,false);
	    List<UUID> distinctDedupIds = new ArrayList<UUID>();
	    BaseClients clients = new BaseClients();
	    int skipCount=0;
	    for (Client pClient : searchItems) {
	    	
	    	if(dedupIdFilter) {
			    	if(pClient!=null && pClient.getDedupClientId()!=null && !distinctDedupIds.contains(pClient.getDedupClientId())) {
			    		clients.addClient(ClientConverter.entityToModel(pClient));
			    	}else {
			    		skipCount++;
			    	}
			    		
			    	if(pClient.getDedupClientId()!=null) {
			    		distinctDedupIds.add(pClient.getDedupClientId());
			    	}
	    	}else{
	    		clients.addClient(ClientConverter.entityToModel(pClient));
	    	}
	    }
	    pagination.setFrom(startIndex);
	    pagination.setReturned(Integer.valueOf(clients.getClients().size()));
	    pagination.setTotal(Integer.valueOf(searchVo.getPagination().getTotal().intValue()-skipCount));
	    
	    SearchResults result = new SearchResults();
	    result.setPagination(pagination);
	    result.addItems(clients.getClients());
	    
	    return result;
	  }
}