package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import com.servinglynk.report.bean.Q19a1ClientCashIncomeChangeIncomeSourceEntryDataBean;
import com.servinglynk.report.bean.ReportData;

public class Q19a1DataBeanMaker extends BaseBeanMaker {
	
	public static List<Q19a1ClientCashIncomeChangeIncomeSourceEntryDataBean> getQ19a1ClientCashIncomeChangeIncomeSourceEntryDataBeanList(ReportData data){
		
		Q19a1ClientCashIncomeChangeIncomeSourceEntryDataBean q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate = new Q19a1ClientCashIncomeChangeIncomeSourceEntryDataBean();
		if(data.isLiveMode()) {
			try {
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeDidNotHaveTheIncomeCategoryAtEntryAndGainedTheIncome(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeDidNotHaveTheIncomeCategoryAtEntryOrFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeHadIncomeCategoryAtEntryAndNotHaveFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomePerformanceMeasures(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomePercent(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeRetainedIncomeCategoryAndSameDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeRetainedIncomeCategoryButHadLessDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInEarnedIncomeTotalAdult(BigInteger.valueOf(0));
				
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeDidNotHaveTheIncomeCategoryAtEntryAndGainedTheIncome(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeDidNotHaveTheIncomeCategoryAtEntryOrFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeHadIncomeCategoryAtEntryAndNotHaveFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomePercent(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomePerformanceMeasures(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeRetainedIncomeCategoryAndSameDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeRetainedIncomeCategoryButHadLessDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOtherIncomeTotalAdult(BigInteger.valueOf(0));
				
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeDidNotHaveTheIncomeCategoryAtEntryAndGainedTheIncome(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeDidNotHaveTheIncomeCategoryAtEntryOrFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeHadIncomeCategoryAtEntryAndNotHaveFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomePercent(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomePerformanceMeasures(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeRetainedIncomeCategoryAndSameDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeRetainedIncomeCategoryButHadLessDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1AverageChangeInOverallIncomeTotalAdult(BigInteger.valueOf(0));
				
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeDidNotHaveTheIncomeCategoryAtEntryAndGainedTheIncome(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeDidNotHaveTheIncomeCategoryAtEntryOrFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeHadIncomeCategoryAtEntryAndNotHaveFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomePercent(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomePerformanceMeasures(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeRetainedIncomeCategoryAndSameDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeRetainedIncomeCategoryButHadLessDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdultsWithOtherIncomeTotalAdult(BigInteger.valueOf(0));
				
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeDidNotHaveTheIncomeCategoryAtEntryAndGainedTheIncome(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeDidNotHaveTheIncomeCategoryAtEntryOrFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeHadIncomeCategoryAtEntryAndNotHaveFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomePercent(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomePerformanceMeasures(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeRetainedIncomeCategoryAndSameDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeRetainedIncomeCategoryButHadLessDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NoOfAdltsWithEarnedIncomeTotalAdult(BigInteger.valueOf(0));
				
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeDidNotHaveTheIncomeCategoryAtEntryAndGainedTheIncome(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeDidNotHaveTheIncomeCategoryAtEntryOrFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeHadIncomeCategoryAtEntryAndNotHaveFollowup(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomePercent(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomePerformanceMeasures(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeRetainedIncomeCategoryAndIncreasedDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeRetainedIncomeCategoryAndSameDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeRetainedIncomeCategoryButHadLessDollar(BigInteger.valueOf(0));
				q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate.setQ19a1NumberOfAdultsWithAnyIncomeTotalAdult(BigInteger.valueOf(0));
				
				
			}catch(Exception e) {
		logger.error("Error in Q26cBeanMaker:" + e);
			}
		}
		return Arrays.asList(q19a1ClientCashIncomeChangeIncomeSourceEntryTableDate);
		
	}

}
