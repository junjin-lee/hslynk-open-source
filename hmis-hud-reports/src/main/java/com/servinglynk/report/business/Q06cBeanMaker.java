package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.servinglynk.report.bean.Q06cPointInTimeCountPersonsLastWednesdayDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.ExitModel;
import com.servinglynk.report.model.IncomeAndSourceModel;

public class Q06cBeanMaker extends BaseBeanMaker {

	public static Long destinationErroCount = 0L;
	public static Long iseErrorCount = 0L;
	public static Long isaaErrorCount = 0L;
	public static Long isaeErrorCount = 0L;

	public static List<Q06cPointInTimeCountPersonsLastWednesdayDataBean> getQ06cPointInTimeCountPersonsLastWednesdayList(ReportData data) {

		Q06cPointInTimeCountPersonsLastWednesdayDataBean q06cDataBean = new Q06cPointInTimeCountPersonsLastWednesdayDataBean();
		if(data.isLiveMode()) {
		try {
			List<ExitModel> exits = data.getExits();
			exits.forEach(exit -> {

				if (StringUtils.equals("8", exit.getDestination()) || StringUtils.equals("9", exit.getDestination())
						|| StringUtils.equals("99", exit.getDestination())) {
					destinationErroCount++;
				}
			});
			List<IncomeAndSourceModel> incomeAndSources = data.getIncomeAndSources();
			incomeAndSources.forEach(incomeAndSource -> {

				if (StringUtils.equals("1", incomeAndSource.getDataCollectionStage())) {
					iseErrorCount++;
				}
			});
			incomeAndSources.forEach(incomeAndSource -> {

				if (StringUtils.equals("3", incomeAndSource.getDataCollectionStage())) {
					isaeErrorCount++;
				}
			});
			incomeAndSources.forEach(incomeAndSource -> {

				if (StringUtils.equals("5", incomeAndSource.getDataCollectionStage())) {
					isaaErrorCount++;
				}
			});
			q06cDataBean.setDestinationStatusErrorCount(BigInteger.valueOf(destinationErroCount));
			int exitCount = exits.size();
			if(exitCount !=0)
				q06cDataBean.setDestinationStatusErrorRate(BigInteger.valueOf(destinationErroCount / exitCount));
			
			q06cDataBean.setIseErrorCount(BigInteger.valueOf(iseErrorCount));
			if (data.getNoOfAdultHeadsOfHousehold() != null && data.getNoOfChildHeadsOfHousehold() != null)
				q06cDataBean.setIseErrorRate(
						BigInteger.valueOf(iseErrorCount / (data.getNoOfAdultHeadsOfHousehold().intValue()
								+ data.getNoOfChildHeadsOfHousehold().intValue())));
			q06cDataBean.setIsaaErrorCount(BigInteger.valueOf(isaaErrorCount));
			if (data.getNumOfHeadsOfHHandAdults365Days() != null
					&& data.getNumOfHeadsOfHHandAdults365Days().intValue() != 0)
				if(data.getNumOfHeadsOfHHandAdults365Days() != null) {
					q06cDataBean.setIsaaErrorRate(
							BigInteger.valueOf(isaaErrorCount / data.getNumOfHeadsOfHHandAdults365Days().intValue()));
				}
			q06cDataBean.setIsaeErrorCount(BigInteger.valueOf(isaeErrorCount));
			if (data.getTotNoOfAdultLeavers() != null && data.getTotNoOfAdultLeavers().intValue() != 0)
				q06cDataBean.setIsaeErrorRate(BigInteger.valueOf(data.getTotNoOfAdultLeavers().intValue()));
		} catch (Exception e) {
			logger.error("Error in Q06bBeanMaker:" + e);
		}
		}
		return Arrays.asList(q06cDataBean);
	}

}