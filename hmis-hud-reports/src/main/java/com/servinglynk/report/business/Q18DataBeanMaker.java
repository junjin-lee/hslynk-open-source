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

import org.apache.commons.lang3.StringUtils;

import com.servinglynk.hive.connection.ImpalaConnection;
import com.servinglynk.hive.connection.ReportQuery;
import com.servinglynk.report.bean.Q18ClientCashIncomeCategoryEarnedOtherIncomeDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.DataCollectionStage;
import com.servinglynk.report.model.IncomeSourceModel;
import com.servinglynk.report.model.NoYesEnum;

public class Q18DataBeanMaker extends BaseBeanMaker {

	public static List<Q18ClientCashIncomeCategoryEarnedOtherIncomeDataBean> getQ18ClientCashIncomeCategoryEarnedOtherIncomeList(ReportData data){
		
		Q18ClientCashIncomeCategoryEarnedOtherIncomeDataBean q18eData = new Q18ClientCashIncomeCategoryEarnedOtherIncomeDataBean();
//		
//		String query = " select count(dedup_client_id)  from %s.incomeandsources i, %s.enrollment e where i.datacollectionstage='1' and  e.project_entry_id=i.enrollmentid "+ 
//				" and i.information_date >= e.entrydate and i.information_date >= ? and i.information_date <= ? and e.ageatentry >= 18 ";
//
//		
		if(data.isLiveMode()) {
		try {
		String query = "select  alimonyamount,childsupportamount,earnedamount,gaamount,othersourceamount,pensionamount,privatedisabilityamount, "+
		" socsecretirementamount,ssiamount,tanfamount,totalmonthlyincome,unemploymentamount,vadisabilitynonserviceamount, "+
		" vadisabilityserviceamount,workerscompamount,e.dedup_client_id,i.incomefromanysource  as incomefromanysource from %s.incomeandsources i, %s.enrollment e,%s.project p where i.datacollectionstage=:datacollectionstage and  e.id=i.enrollmentid  and e.projectid = p.id %p "+
		" and i.information_date >= e.entrydate and i.information_date >= :startDate and i.information_date <= :endDate and e.ageatentry >= 18 ";

				
		
		List<IncomeSourceModel> incomeAtEntry = getIncome(data.getSchema(), query , DataCollectionStage.ENTRY.getCode(),data);
		List<IncomeSourceModel> incomeAtExit = getIncome(data.getSchema(), query, DataCollectionStage.EXIT.getCode(),data);
		List<IncomeSourceModel> incomeAtAnnualAssesment = getQ18IncomeForAnnualAssesment(data, ReportQuery.REQUIRED_ANNUAL_ASSESMENT_QUERY, DataCollectionStage.ANNUAL_ASSESMENT.getCode());
		
		q18eData.setQ18AdultsWithIncomeInfoAtEntryNumberOfAdultsAtEntry(BigInteger.valueOf(incomeAtEntry !=null ?incomeAtEntry.size() :0 ));
		q18eData.setQ18AdultsWithIncomeInfoAtEntryNumberOfAdultsAtExit(BigInteger.valueOf(incomeAtExit != null ? incomeAtExit.size() : 0 ));
		q18eData.setQ18AdultsWithIncomeInfoAtEntryNumberOfAdultsAtFollowup(BigInteger.valueOf(incomeAtAnnualAssesment != null ? incomeAtAnnualAssesment.size() :0));
		
		List<IncomeSourceModel> earnedIncomeAtEntry = incomeAtEntry.parallelStream().filter(income -> income.getEarnedIncome() != null && income.getEarnedIncome().floatValue() >0 &&  income.getOtherIncome() != null && income.getOtherIncome().floatValue() ==0).collect(Collectors.toList());
		List<IncomeSourceModel> earnedIncomeAtsAtExit = incomeAtExit.parallelStream().filter(income -> income.getEarnedIncome() != null && income.getEarnedIncome().floatValue() >0 &&  income.getOtherIncome() != null && income.getOtherIncome().floatValue() ==0).collect(Collectors.toList());
		List<IncomeSourceModel> earnedIncomeAtAnnualAssesment = incomeAtAnnualAssesment.parallelStream().filter(income -> income.getEarnedIncome() != null && income.getEarnedIncome().floatValue() >0 &&  income.getOtherIncome() != null && income.getOtherIncome().floatValue() ==0).collect(Collectors.toList());
		
		q18eData.setQ18AdultsWithOnlyEarnedIncomeNumberOfAdultsAtEntry(BigInteger.valueOf(earnedIncomeAtEntry !=null ? earnedIncomeAtEntry.size() :0 ));
		q18eData.setQ18AdultsWithOnlyEarnedIncomeNumberOfAdultsAtExit(BigInteger.valueOf(earnedIncomeAtsAtExit != null ? earnedIncomeAtsAtExit.size() : 0));
		q18eData.setQ18AdultsWithOnlyEarnedIncomeNumberOfAdultsAtFollowup(BigInteger.valueOf(earnedIncomeAtAnnualAssesment != null ? earnedIncomeAtAnnualAssesment.size() : 0));
		
		List<IncomeSourceModel> bothEarnedAndOtherIncomeAtEntry = incomeAtEntry.parallelStream().filter(income -> income.getEarnedIncome() != null && income.getEarnedIncome().floatValue() >0 &&  income.getOtherIncome() != null && income.getOtherIncome().floatValue() >0).collect(Collectors.toList());
		List<IncomeSourceModel> bothEarnedAndOtherIncomeAtsAtExit = incomeAtExit.parallelStream().filter(income -> income.getEarnedIncome() != null && income.getEarnedIncome().floatValue() >0 &&  income.getOtherIncome() != null && income.getOtherIncome().floatValue() >0).collect(Collectors.toList());
		List<IncomeSourceModel> bothEarnedAndOtherIncomeAtAnnualAssesment = incomeAtAnnualAssesment.parallelStream().filter(income -> income.getEarnedIncome() != null && income.getEarnedIncome().floatValue() >0 &&  income.getOtherIncome() != null && income.getOtherIncome().floatValue() >0).collect(Collectors.toList());
		
		q18eData.setQ18AdultsWithBothEarnedAndOtherIncomeNumberOfAdultsAtEntry(BigInteger.valueOf(bothEarnedAndOtherIncomeAtEntry != null ? bothEarnedAndOtherIncomeAtEntry.size() : 0));
		q18eData.setQ18AdultsWithBothEarnedAndOtherIncomeNumberOfAdultsAtExit(BigInteger.valueOf(bothEarnedAndOtherIncomeAtsAtExit != null ? bothEarnedAndOtherIncomeAtsAtExit.size() :0));
		q18eData.setQ18AdultsWithBothEarnedAndOtherNumberOfAdultsAtFollowup(BigInteger.valueOf(bothEarnedAndOtherIncomeAtAnnualAssesment != null ?bothEarnedAndOtherIncomeAtAnnualAssesment.size() :0));
		
		List<IncomeSourceModel> noearnedIncomeAtEntry = getIncome(data.getSchema(), query+" and ( earnedamount = 0 or  earnedamount is null ) " , DataCollectionStage.ENTRY.getCode(),data);
		List<IncomeSourceModel> noearnedIncomeAtExit = getIncome(data.getSchema(), query+" and ( earnedamount = 0 or  earnedamount is null) ", DataCollectionStage.EXIT.getCode(),data);
		List<IncomeSourceModel> noearnedIncomeAtAnnualAssesment = getIncome(data.getSchema(), ReportQuery.REQUIRED_ANNUAL_ASSESMENT_QUERY+" and (  earnedamount = 0 or  earnedamount is null )  ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
		
		
		List<IncomeSourceModel> otherIncomeAtEntry = noearnedIncomeAtEntry.parallelStream().filter(income ->  (income.getOtherIncome() != null  && income.getOtherIncome().floatValue() > 0)).collect(Collectors.toList());
		List<IncomeSourceModel> otherIncomeAtsAtExit = noearnedIncomeAtExit.parallelStream().filter(income ->  (income.getOtherIncome() != null  && income.getOtherIncome().floatValue() > 0)).collect(Collectors.toList());
		List<IncomeSourceModel> otherIncomeAtAnnualAssesment = noearnedIncomeAtAnnualAssesment.parallelStream().filter(income ->  (income.getOtherIncome() != null  && income.getOtherIncome().floatValue() > 0)).collect(Collectors.toList());
		

		q18eData.setQ18AdultsWithOnlyOtherIncomeNumberOfAdultsAtEntry(BigInteger.valueOf(otherIncomeAtEntry != null ? otherIncomeAtEntry.size() :0));
		q18eData.setQ18AdultsWithOnlyOtherIncomeNumberOfAdultsAtExit(BigInteger.valueOf(otherIncomeAtsAtExit != null ?otherIncomeAtsAtExit.size():0));
		q18eData.setQ18AdultsWithOnlyOtherIncomeNumberOfAdultsAtFollowup(BigInteger.valueOf(otherIncomeAtAnnualAssesment != null ?  otherIncomeAtAnnualAssesment.size() :0));
		
		List<IncomeSourceModel> dkIncomeAtEntry = incomeAtEntry.parallelStream().filter(income -> income != null && ( StringUtils.equals(NoYesEnum.EIGHT.getValue(), income.getIncomefromanysource()) || StringUtils.equals(NoYesEnum.NINE.getValue(), income.getIncomefromanysource())) ).collect(Collectors.toList());
		List<IncomeSourceModel> dkIncomeAtsAtExit = incomeAtExit.parallelStream().filter(income -> income != null && ( StringUtils.equals(NoYesEnum.EIGHT.getValue(), income.getIncomefromanysource()) || StringUtils.equals(NoYesEnum.NINE.getValue(), income.getIncomefromanysource())) ).collect(Collectors.toList());
		List<IncomeSourceModel> dkIncomeAtAnnualAssesment = incomeAtAnnualAssesment.parallelStream().filter(income -> income != null && ( StringUtils.equals(NoYesEnum.EIGHT.getValue(), income.getIncomefromanysource()) || StringUtils.equals(NoYesEnum.NINE.getValue(), income.getIncomefromanysource())) ).collect(Collectors.toList());

		q18eData.setQ18AdultsWithDKRNumberOfAdultsAtEntry(BigInteger.valueOf(dkIncomeAtEntry != null ? dkIncomeAtEntry.size(): 0));
		q18eData.setQ18AdultsWithDKRNumberOfAdultsAtExit(BigInteger.valueOf(dkIncomeAtsAtExit != null ? dkIncomeAtsAtExit.size() : 0));
		q18eData.setQ18AdultsWithDKRNumberOfAdultsAtFollowup(BigInteger.valueOf(dkIncomeAtAnnualAssesment  != null ? dkIncomeAtAnnualAssesment.size() : 0));
		
		List<IncomeSourceModel> missingIncomeAtEntry = incomeAtEntry.parallelStream().filter(income -> income != null && ( StringUtils.equals(NoYesEnum.NINTY_NINE.getValue(), income.getIncomefromanysource()) )).collect(Collectors.toList());
		List<IncomeSourceModel> missingIncomeAtsAtExit = incomeAtExit.parallelStream().filter(income -> income != null && ( StringUtils.equals(NoYesEnum.NINTY_NINE.getValue(), income.getIncomefromanysource()) )).collect(Collectors.toList());
		List<IncomeSourceModel> missingIncomeAtAnnualAssesment = incomeAtAnnualAssesment.parallelStream().filter(income -> income != null && ( StringUtils.equals(NoYesEnum.NINTY_NINE.getValue(), income.getIncomefromanysource()) )).collect(Collectors.toList());
		
	
		q18eData.setQ18AdultsWithMissingInfoNumberOfAdultsAtEntry(BigInteger.valueOf(missingIncomeAtEntry != null ? missingIncomeAtEntry .size() :0));
		q18eData.setQ18AdultsWithMissingInfoNumberOfAdultsAtExit(BigInteger.valueOf(missingIncomeAtsAtExit != null ? missingIncomeAtsAtExit.size() :0 ));
		q18eData.setQ18AdultsWithMissingInfoNumberOfAdultsAtFollowup(BigInteger.valueOf(missingIncomeAtAnnualAssesment != null ? missingIncomeAtAnnualAssesment.size() :0));
		
		List<IncomeSourceModel> noIncomeAtEntry = incomeAtEntry.parallelStream().filter(income -> income.getIncomeAmount() != null && income.getIncomeAmount().floatValue() ==0 ).collect(Collectors.toList());
		List<IncomeSourceModel> noIncomeAtsAtExit = incomeAtExit.parallelStream().filter(income -> income.getIncomeAmount() != null && income.getIncomeAmount().floatValue() ==0).collect(Collectors.toList());
		List<IncomeSourceModel> noIncomeAtAnnualAssesment = incomeAtAnnualAssesment.parallelStream().filter(income -> income.getIncomeAmount() != null && income.getIncomeAmount().floatValue() ==0).collect(Collectors.toList());
		
		
		q18eData.setQ18AdultsWithNoIncomeNumberOfAdultsAtEntry(BigInteger.valueOf(noIncomeAtEntry !=null ? noIncomeAtEntry.size() :0));
		q18eData.setQ18AdultsWithNoIncomeNumberOfAdultsAtExit(BigInteger.valueOf(noIncomeAtsAtExit != null ? noIncomeAtsAtExit.size() :0 ));
		q18eData.setQ18AdultsWithNoOtherNumberOfAdultsAtFollowup(BigInteger.valueOf(noIncomeAtAnnualAssesment != null ? noIncomeAtAnnualAssesment.size() :0));
		
		
	
		q18eData.setQ18NumberOfAdultStayersNotRequiredNumberOfAdultsAtEntry(BigInteger.valueOf(0));
		q18eData.setQ18NumberOfAdultStayersNotRequiredNumberOfAdultsAtExit(BigInteger.valueOf(0));
		q18eData.setQ18NumberOfAdultStayersNotRequiredNumberOfAdultsAtFollowup(data.getNumOfAdultStayersNotRequiredAnnualAssesment());
		
		q18eData.setQ18NumberOfAdultStayersWithoutRequiredNumberOfAdultsAtEntry(BigInteger.valueOf(0));
		q18eData.setQ18NumberOfAdultStayersWithoutRequiredNumberOfAdultsAtExit(BigInteger.valueOf(0));
		q18eData.setQ18NumberOfAdultStayersWithoutRequiredNumberOfAdultsAtFollowup(data.getNumOfAdultStayersWithoutRequiredAnnualAssesment());
		
		
		q18eData.setQ18TotalAdultsNumberOfAdultsAtEntry(data.getNumOfAdults());
		q18eData.setQ18TotalAdultsNumberOfAdultsAtExit(data.getTotNoOfAdultLeavers());
		q18eData.setQ18TotalAdultsNumberOfAdultsAtFollowup(data.getTotNoOfAdultStayers());
		
		List<IncomeSourceModel> oneOrMoreIncomeAtEntry = incomeAtEntry.parallelStream().filter(income -> income.getIncomeAmount() != null && income.getIncomeAmount().floatValue() >0 ).collect(Collectors.toList());
		List<IncomeSourceModel> oneOrMoreIncomeAtsAtExit = incomeAtExit.parallelStream().filter(income -> income.getIncomeAmount() != null && income.getIncomeAmount().floatValue() >0).collect(Collectors.toList());
		List<IncomeSourceModel> oneOrMoreIncomeAtAnnualAssesment = incomeAtAnnualAssesment.parallelStream().filter(income -> income.getIncomeAmount() != null && income.getIncomeAmount().floatValue() >0).collect(Collectors.toList());
		
		q18eData.setQ18OneOrMoreSourceofIncomeNumberOfAdultsAtEntry(BigInteger.valueOf(oneOrMoreIncomeAtEntry != null ? oneOrMoreIncomeAtEntry.size() :0));
		q18eData.setQ18OneOrMoreSourceofIncomeNumberOfAdultsAtExit(BigInteger.valueOf(oneOrMoreIncomeAtsAtExit != null ? oneOrMoreIncomeAtsAtExit.size() :0));
		q18eData.setQ18OneOrMoreSourceofIncomeNumberOfAdultsAtFollowup(BigInteger.valueOf(oneOrMoreIncomeAtAnnualAssesment != null ? oneOrMoreIncomeAtAnnualAssesment.size() :0));
		} catch (Exception e) {
			logger.error("Error in Q18BeanMaker:" + e);
		}
		}
		return Arrays.asList(q18eData);
	}
	
	
	
	public static List<IncomeSourceModel> getIncome(String schema,String query,String datacollectionStage,ReportData data) {
		List<IncomeSourceModel> incomes = new ArrayList<>();
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = ImpalaConnection.getConnection();
			statement = connection.createStatement();
			data.setQueryDataCollectionStage(datacollectionStage);
			resultSet = statement.executeQuery(formatQuery(getQueryForProjectDB(data, query),schema,data));
			
		 while(resultSet.next()) {
			 int totalIncome = getFloatValue(resultSet,1)+getFloatValue(resultSet,2)+getFloatValue(resultSet,3)+getFloatValue(resultSet,4)+getFloatValue(resultSet,5)+getFloatValue(resultSet,6)+getFloatValue(resultSet,7)+
			 getFloatValue(resultSet,8)+getFloatValue(resultSet,9)+getFloatValue(resultSet,10)+getFloatValue(resultSet,11)+getFloatValue(resultSet,12)+getFloatValue(resultSet,13)+getFloatValue(resultSet,14)+getFloatValue(resultSet,15);
			 BigInteger totIncome = new BigInteger(String.valueOf(totalIncome));
			 int earned = getFloatValue(resultSet,3);
			 String earnedIncome = String.valueOf(earned);
			 BigInteger earnedIncomeBigInt = new BigInteger(earnedIncome);
			 String dedupClientId = (String) resultSet.getObject(16);
			 BigInteger otherIncome = BigInteger.ZERO;
			 if( totIncome != null &&  totIncome.longValue() > 0 ) {
				 otherIncome = totIncome.subtract(earnedIncomeBigInt != null  && earnedIncomeBigInt.longValue() > 0 ? earnedIncomeBigInt : BigInteger.ZERO);
			 }
			 String incomefromanysource = (String) resultSet.getString("incomefromanysource");
			 incomes.add(new IncomeSourceModel(totIncome, earnedIncomeBigInt, otherIncome, dedupClientId,incomefromanysource));
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
		return incomes;
	}
	
	public static List<IncomeSourceModel> getQ18IncomeForAnnualAssesment(ReportData data,String query,String datacollectionStage) {
		List<IncomeSourceModel> incomes = new ArrayList<>();
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = ImpalaConnection.getConnection();
			statement = connection.createStatement();
			data.setQueryDataCollectionStage(datacollectionStage);
			resultSet = statement.executeQuery(formatQuery(getQueryForProjectDB(data, query),data.getSchema(),data));
			
		 while(resultSet.next()) {
			 int totalIncome = getFloatValue(resultSet,1)+getFloatValue(resultSet,2)+getFloatValue(resultSet,3)+getFloatValue(resultSet,4)+getFloatValue(resultSet,5)+getFloatValue(resultSet,6)+getFloatValue(resultSet,7)+
			 getFloatValue(resultSet,8)+getFloatValue(resultSet,9)+getFloatValue(resultSet,10)+getFloatValue(resultSet,11)+getFloatValue(resultSet,12)+getFloatValue(resultSet,13)+getFloatValue(resultSet,14)+getFloatValue(resultSet,15);
			 BigInteger totIncome = new BigInteger(String.valueOf(totalIncome));
			 int earned = getFloatValue(resultSet,3);
			 String earnedIncome = String.valueOf(earned);
			 BigInteger earnedIncomeBigInt = new BigInteger(earnedIncome);
			 String dedupClientId = (String) resultSet.getObject(16);
			 BigInteger otherIncome = BigInteger.ZERO;
			 if( totIncome != null &&  totIncome.longValue() > 0 ) {
				 otherIncome = totIncome.subtract(earnedIncomeBigInt != null  && earnedIncomeBigInt.longValue() > 0 ? earnedIncomeBigInt : BigInteger.ZERO);
			 }
			 String incomefromanysource = (String) resultSet.getString("incomefromanysource");
			 incomes.add(new IncomeSourceModel(totIncome, earnedIncomeBigInt, otherIncome, dedupClientId,incomefromanysource));
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
		return incomes;
	}
	public static int getIncomeCnt(String schema,String query,String datacollectionStage,ReportData data) {
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		int count =0;
		try {
			connection = ImpalaConnection.getConnection();
			statement = connection.createStatement();
			data.setQueryDataCollectionStage(datacollectionStage);
			resultSet = statement.executeQuery(formatQuery(getQueryForProjectDB(data, query),schema,data));
			
		 while(resultSet.next()) {
			 count = resultSet.getInt(1);
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
		return count;
	}
	
}
