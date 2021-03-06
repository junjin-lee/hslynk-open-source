package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.servinglynk.report.bean.Q06bNumberOfPersonsServedDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.ClientModel;
import com.servinglynk.report.model.EnrollmentModel;


public class Q06bBeanMaker  extends BaseBeanMaker {
	public static Long veteranStatusErrorCount = 0L;
	public static Long pedErrorCount = 0L;
	public static Long relationShipHHErrorCount = 0L;
	public static Long clientLocationErrorCount = 0L;
	public static Long disablingCondErrorCount = 0L;
	
	public static List<Q06bNumberOfPersonsServedDataBean> getQ06bNumberOfPersonsServedTableList(ReportData data){
		Q06bNumberOfPersonsServedDataBean q06bDataBean = new Q06bNumberOfPersonsServedDataBean();
		if(data.isLiveMode()) {
		try {
		List<ClientModel> clients = data.getClients();
		List<EnrollmentModel> enrollments = data.getEnrollments();
		Long numOfClients = Long.valueOf(clients.size());
		clients.forEach(client -> { 
			
			if(StringUtils.equals("8", client.getVeteran_status()) || StringUtils.equals("9", client.getVeteran_status()) || StringUtils.equals("99", client.getVeteran_status()) ) {
				veteranStatusErrorCount++;
			}
		}
		);
		enrollments.forEach(enrollment -> { 
			if(StringUtils.equals("8", enrollment.getDisablingcondition()) || StringUtils.equals("9", enrollment.getDisablingcondition())) {
				disablingCondErrorCount++;
			}
			if(StringUtils.equals("8", enrollment.getRelationshiptohoh()) || StringUtils.equals("9", enrollment.getRelationshiptohoh())) {
				relationShipHHErrorCount++;
			}
			if(enrollment.getEntrydate() == null) {
				pedErrorCount++;
			}
		 }
		);
		
		
		q06bDataBean.setVeteranStatusErrorCount(BigInteger.valueOf(veteranStatusErrorCount));
		Long numOfAdults = Long.parseLong(String.valueOf(data.getNumOfAdults()));
		if(numOfAdults !=0) {
			q06bDataBean.setVeteranStatusErrorRate(BigInteger.valueOf(veteranStatusErrorCount/numOfAdults));
		}else {
			q06bDataBean.setVeteranStatusErrorRate(BigInteger.valueOf(0));
		}
		
		q06bDataBean.setPedErrorCount(BigInteger.valueOf(pedErrorCount));
		if(numOfClients !=0) {
			q06bDataBean.setPedErrorRate(BigInteger.valueOf(pedErrorCount/numOfClients));
		}
		q06bDataBean.setRelationshipHHErrorCount(BigInteger.valueOf(relationShipHHErrorCount));
		if(numOfClients !=0)
			q06bDataBean.setRelationshipHHErrorRate(BigInteger.valueOf(relationShipHHErrorCount/numOfClients));
		q06bDataBean.setClientLocationErrorCount(BigInteger.valueOf(0));
		q06bDataBean.setClientLocationErrorRate(BigInteger.valueOf(0));
		q06bDataBean.setDisablingCondErrorCount(BigInteger.valueOf(disablingCondErrorCount));
		if(numOfClients !=0)
			q06bDataBean.setDisablingCondErrorRate(BigInteger.valueOf(disablingCondErrorCount/numOfClients));
		
		}catch(Exception e) {
			logger.error("Error in Q06bBeanMaker:"+e);
		}
		}
		return Arrays.asList(q06bDataBean);
	}
}
