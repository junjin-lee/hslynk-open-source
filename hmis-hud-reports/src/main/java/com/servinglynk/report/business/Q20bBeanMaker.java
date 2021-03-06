package com.servinglynk.report.business;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.servinglynk.hive.connection.ImpalaConnection;
import com.servinglynk.report.bean.Q20bNumberOfNonCashBenefitSourcesDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.DataCollectionStage;
import com.servinglynk.report.model.EnrollmentModel;
import com.servinglynk.report.model.NonCashModel;

public class Q20bBeanMaker extends BaseBeanMaker {
	
	
	
	public static List<Q20bNumberOfNonCashBenefitSourcesDataBean> getQ20bNumberOfNonCashBenefitSourcesList(ReportData data){
		
		Q20bNumberOfNonCashBenefitSourcesDataBean q20bNumberOfNonCashBenefitSourcesTable = new Q20bNumberOfNonCashBenefitSourcesDataBean();
		String entryQuery = " select  nb.snap as snap ,nb.wic as wic ,nb.tanfchildcare as tanfchildcare,nb.tanftransportation as tanftransportation,nb.othertanf as othertanf,nb.benefitsfromanysource as benefitsfromanysource,e.dedup_client_id  as dedup_client_id from %s.incomeandsources i, %s.enrollment e,%s.noncashbenefits nb where e.id=i.enrollmentid  "+
		      "   and nb.enrollmentid = e.id "+
			  " and i.information_date = e.entrydate and i.information_date <= :startDate and i.information_date >= :endDate "+
			  " and e.ageatentry >=18  and i.datacollectionstage = '1' ";
		       
		String  exitQuery = " select  nb.snap as snap ,nb.wic as wic ,nb.tanfchildcare as tanfchildcare,nb.tanftransportation as tanftransportation,nb.othertanf as othertanf,nb.benefitsfromanysource as benefitsfromanysource,e.dedup_client_id  as dedup_client_id from %s.incomeandsources i, %s.enrollment e,%s.noncashbenefits nb,%s.exit ext where e.id=i.enrollmentid  "+
				      " and nb.enrollmentid = e.id and e.id = ext.enrollmentid"+
				  " and i.information_date = ext.exitdate and i.information_date <= :startDate and i.information_date >= :endDate "+
				  " and e.ageatentry >=18  and i.datacollectionstage = '3' ";
			       
		String stayersQuery = " select  nb.snap as snap ,nb.wic as wic ,nb.tanfchildcare as tanfchildcare,nb.tanftransportation as tanftransportation,nb.othertanf as othertanf,nb.benefitsfromanysource as benefitsfromanysource,e.dedup_client_id  as dedup_client_id from %s.incomeandsources i, %s.enrollment e,%s.noncashbenefits nb where e.id=i.enrollmentid  "+
						" and i.information_date >= e.entrydate and i.information_date >= :startDate and i.information_date <= :endDate and e.ageatentry >= 18 "+
					" and   e.id not in ( select enrollmentid from %s.exit  where  exitdate < :endDate  )   "+
					" and   e.id not in ( select enrollmentid from %s.enrollment_coc where datacollectionstage='5' and datediff(now(),information_date) < 365 ) ";
			try {
				if(data.isLiveMode()) {
					List<NonCashModel> entryNonCashBenefits = getNonCashBenefit(data, entryQuery,DataCollectionStage.ENTRY.getCode());
					List<NonCashModel> exitNonCashBenefits = getNonCashBenefit(data, exitQuery,DataCollectionStage.EXIT.getCode());
					List<NonCashModel> stayersNonCashBenefits = getNonCashBenefit(data, stayersQuery,DataCollectionStage.ANNUAL_ASSESMENT.getCode());

					if(CollectionUtils.isNotEmpty(entryNonCashBenefits)) {
						List<NonCashModel> noSourceAtEntry = entryNonCashBenefits.parallelStream().filter(nonCashBenefit -> isNullOrNegative(nonCashBenefit.getSnap()) && isNullOrNegative(nonCashBenefit.getTanftransportation()) && isNullOrNegative(nonCashBenefit.getBenefitsfromanysource()) &&  isNullOrNegative(nonCashBenefit.getOthertanf()) && isNullOrNegative(nonCashBenefit.getWic()) && isNullOrNegative(nonCashBenefit.getTanfchildcare())  ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bNosourcesAtEntry(BigInteger.valueOf(noSourceAtEntry != null ? noSourceAtEntry.size() :0));
						List<NonCashModel> onePlusSourceAtEntry = entryNonCashBenefits.parallelStream().filter(nonCashBenefit -> isPositive(nonCashBenefit.getSnap()) || isPositive(nonCashBenefit.getTanftransportation()) || isPositive(nonCashBenefit.getBenefitsfromanysource()) ||  isPositive(nonCashBenefit.getOthertanf()) || isPositive(nonCashBenefit.getWic()) || isPositive(nonCashBenefit.getTanfchildcare())  ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20b1PlusSourcesAtEntry(BigInteger.valueOf(onePlusSourceAtEntry != null ? onePlusSourceAtEntry.size() : 0));
						List<NonCashModel> didNotKnowOrRefusedAtEntry = entryNonCashBenefits.parallelStream().filter(nonCashBenefit ->  didNotKnowOrRefused(nonCashBenefit.getBenefitsfromanysource()) ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bDKRAtEntry(BigInteger.valueOf(didNotKnowOrRefusedAtEntry != null ? didNotKnowOrRefusedAtEntry.size() :0));
						List<NonCashModel> noCollectedAtEntry = entryNonCashBenefits.parallelStream().filter(nonCashBenefit ->  dataNotCollected(nonCashBenefit.getBenefitsfromanysource()) ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bMissingInfoAtEntry(BigInteger.valueOf(noCollectedAtEntry != null ? noCollectedAtEntry.size() :0 ));
						}
					if(CollectionUtils.isNotEmpty(exitNonCashBenefits)) {
						List<NonCashModel> noSourceAtExit= exitNonCashBenefits.parallelStream().filter(nonCashBenefit -> isNullOrNegative(nonCashBenefit.getSnap()) && isNullOrNegative(nonCashBenefit.getTanftransportation()) && isNullOrNegative(nonCashBenefit.getBenefitsfromanysource()) &&  isNullOrNegative(nonCashBenefit.getOthertanf()) && isNullOrNegative(nonCashBenefit.getWic()) && isNullOrNegative(nonCashBenefit.getTanfchildcare())  ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bNosourcesAtExitLeavers(BigInteger.valueOf(noSourceAtExit != null ? noSourceAtExit.size() :0));
						List<NonCashModel> onePlusSourceAtExit = noSourceAtExit.parallelStream().filter(nonCashBenefit -> isPositive(nonCashBenefit.getSnap()) || isPositive(nonCashBenefit.getTanftransportation()) || isPositive(nonCashBenefit.getBenefitsfromanysource()) ||  isPositive(nonCashBenefit.getOthertanf()) || isPositive(nonCashBenefit.getWic()) || isPositive(nonCashBenefit.getTanfchildcare())  ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20b1PlusSourcesAtExitLeavers(BigInteger.valueOf(onePlusSourceAtExit != null ? onePlusSourceAtExit.size() :0));
						
						List<NonCashModel> didNotKnowOrRefusedAtExit = exitNonCashBenefits.parallelStream().filter(nonCashBenefit ->  didNotKnowOrRefused(nonCashBenefit.getBenefitsfromanysource()) ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bDKRAtExitLeavers(BigInteger.valueOf(didNotKnowOrRefusedAtExit != null ? didNotKnowOrRefusedAtExit.size() :0));
						List<NonCashModel> noCollectedAtExit = exitNonCashBenefits.parallelStream().filter(nonCashBenefit ->  dataNotCollected(nonCashBenefit.getBenefitsfromanysource()) ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bMissingInfoAtExitLeavers(BigInteger.valueOf(noCollectedAtExit != null ? noCollectedAtExit.size() :0));
					}
					if(CollectionUtils.isNotEmpty(stayersNonCashBenefits)) {
						List<NonCashModel> noSourceAtStayers = stayersNonCashBenefits.parallelStream().filter(nonCashBenefit -> isNullOrNegative(nonCashBenefit.getSnap()) && isNullOrNegative(nonCashBenefit.getTanftransportation()) && isNullOrNegative(nonCashBenefit.getBenefitsfromanysource()) &&  isNullOrNegative(nonCashBenefit.getOthertanf()) && isNullOrNegative(nonCashBenefit.getWic()) && isNullOrNegative(nonCashBenefit.getTanfchildcare())  ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bNosourcesAtLatestStayers(BigInteger.valueOf(noSourceAtStayers != null ? noSourceAtStayers.size() :0));
						List<NonCashModel> onePlusSourceForStayers = stayersNonCashBenefits.parallelStream().filter(nonCashBenefit -> isPositive(nonCashBenefit.getSnap()) || isPositive(nonCashBenefit.getTanftransportation()) || isPositive(nonCashBenefit.getBenefitsfromanysource()) ||  isPositive(nonCashBenefit.getOthertanf()) || isPositive(nonCashBenefit.getWic()) || isPositive(nonCashBenefit.getTanfchildcare())  ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20b1PlusSourcesAtLatestStayers(BigInteger.valueOf(onePlusSourceForStayers != null ?onePlusSourceForStayers.size() :0));
						List<NonCashModel> didNotKnowOrRefusedAtStayers = stayersNonCashBenefits.parallelStream().filter(nonCashBenefit ->  didNotKnowOrRefused(nonCashBenefit.getBenefitsfromanysource()) ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bDKRAtLatestStayers(BigInteger.valueOf(didNotKnowOrRefusedAtStayers != null ? didNotKnowOrRefusedAtStayers.size() :0));
						List<NonCashModel> noCollectedAtStayers = stayersNonCashBenefits.parallelStream().filter(nonCashBenefit ->  dataNotCollected(nonCashBenefit.getBenefitsfromanysource()) ).collect(Collectors.toList());
						q20bNumberOfNonCashBenefitSourcesTable.setQ20bMissingInfoAtLatestStayers(BigInteger.valueOf(noCollectedAtStayers != null ? noCollectedAtStayers.size() :0));
					}

					q20bNumberOfNonCashBenefitSourcesTable.setQ20bTotalClientsAtEntry(data.getNumOfAdults());
					q20bNumberOfNonCashBenefitSourcesTable.setQ20bTotalClientsAtLatestStayers(data.getTotNoOfAdultStayers());
					q20bNumberOfNonCashBenefitSourcesTable.setQ20bTotalClientsAtExitLeavers(data.getTotNoOfAdultLeavers());
						
				}
			}catch(Exception e) {
				logger.error("Error in Q20bBeanMaker:" + e);
			}
		
		return Arrays.asList(q20bNumberOfNonCashBenefitSourcesTable);
	}

	 public static List<NonCashModel> getNonCashBenefit(ReportData data,String query,String datacollectionStage) {
		 List<NonCashModel> nonCashModels = new ArrayList<NonCashModel>();
			ResultSet resultSet = null;
			Statement statement = null;
			Connection connection = null;
			try {
				connection = ImpalaConnection.getConnection();
				statement = connection.createStatement();
				List<EnrollmentModel> enrollments = data.getEnrollments();
				if(CollectionUtils.isNotEmpty(enrollments)) {
					StringBuilder builder = new StringBuilder(" and e.id in (");
					for(EnrollmentModel model : enrollments){
						if(StringUtils.isNotBlank(model.getProjectEntryID())) {
							builder.append("'");
							builder.append(model.getProjectEntryID());
							builder.append("'");
							builder.append(",");
						}
					}
					builder.deleteCharAt(builder.length()-1);
					builder.append(" ) " );
					query = query + builder.toString();
				}
				
				if(StringUtils.equals(datacollectionStage, DataCollectionStage.ANNUAL_ASSESMENT.getCode())) {
					//statement.setDate(3, data.getReportEndDate());
				} 
				resultSet = statement.executeQuery(formatQuery(query,data.getSchema(),data));
				
			 while(resultSet.next()) {
				 nonCashModels.add(new NonCashModel(resultSet.getString("snap"), resultSet.getString("wic"), resultSet.getString("tanfchildcare"), resultSet.getString("tanftransportation"), resultSet.getString("othertanf") , resultSet.getString("benefitsfromanysource") , resultSet.getString("dedup_client_id")));
		     }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
						//connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return nonCashModels;
		}	

}
