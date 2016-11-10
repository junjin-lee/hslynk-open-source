package com.servinglynk.report.business;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.servinglynk.hive.connection.HiveConnection;
import com.servinglynk.hive.connection.ReportQuery;
import com.servinglynk.report.bean.ClientModel;
import com.servinglynk.report.bean.EnrollmentModel;
import com.servinglynk.report.bean.ExitModel;
import com.servinglynk.report.bean.HomePageDataBean;
import com.servinglynk.report.bean.ReportData;

public class HomePageDataBeanMaker {
	
		public static List<HomePageDataBean> getHomePageDataList(String schema,String projectId){
	       
			HomePageDataBean homePageDataBean = new HomePageDataBean();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
             
			homePageDataBean.setHomePageStartDate(dateFormat.format(date/*"1/1/2015"*/));
			homePageDataBean.setHomePageEndDate(dateFormat.format(date/*"11/10/2016"*/));
			homePageDataBean.setHomePageProjects("APR - Services Only");
			homePageDataBean.setHomePageHomeLess("Everyone");
			homePageDataBean.setHomePageGrants("all grants");
			homePageDataBean.setHomePageView("Aggregate / summary");
			
			populateProject(schema, projectId, homePageDataBean);
			homePageDataBean.setQ04aHmisProjectIdService(BigInteger.valueOf(240));
			homePageDataBean.setQ04aIdentityProjectId(BigInteger.valueOf(0));
			List<EnrollmentModel> enrollments = getEnrollmentsByProjectId(schema, projectId);
			ReportData data = new ReportData();
			data.setEnrollments(enrollments);
			List<ClientModel> allClients = getClients(schema);
			List<String> clientIds = new ArrayList<String>(); 
			List<String> enrollmentIds = new ArrayList<String>(); 
			enrollments.parallelStream().forEach(enrollment -> { clientIds.add(enrollment.getPersonalID()); enrollmentIds.add(enrollment.getProjectEntryID());});
			List<ClientModel> clients = allClients.parallelStream().filter(client -> clientIds.contains(client.getPersonalID())).collect(Collectors.toList());
			data.setClients(clients);
			List<ExitModel> allExits = getAllExits(schema);
			List<ExitModel> filteredExits = allExits.parallelStream().filter(exit -> enrollmentIds.contains(exit.getProjectEntryID())).collect(Collectors.toList());
			data.setExits(filteredExits);
			homePageDataBean.setQ05aHMISComparableDBDataQualityDataBean(Q05aHMISComparableDBDataQualityDataBeanMaker.getQ05aHMISCDDQDataList(schema,projectId,data));
			homePageDataBean.setQ06aReportValidationsTableDataBean(Q06aReportValidationsTableDataBeanMaker.getQ06aReportValidationsTableList(schema,data));
			homePageDataBean.setQ06bNumberOfPersonsServedDataBean(Q06bNumberOfPersonsServedDataBeanMaker.getQ06bNumberOfPersonsServedTableList());
			homePageDataBean.setQ06cPointInTimeCountPersonsLastWednesdayDataBean(Q06cPointInTimeCountPersonsLastWednesdayDataBeanMaker.getQ06cPointInTimeCountPersonsLastWednesdayList());
			homePageDataBean.setQ07aHouseholdsServedDataBean(Q07aHouseHoldsDataBeanMaker.getQ07aHouseholdsServeList());
			homePageDataBean.setQ07bPointInTimeCountHouseholdsLastWednesdayDataBean(Q07bPointInTimeCountHouseholdsLastWednesdayDataBeanMaker.getQ07bPointInTimeCountHouseholdsLastWednesdayList());
			homePageDataBean.setqQ09aNumberPersonsContactedDataBean(Q09aNumberPersonsContactedDataBeanMaker.getQ09aNumberPersonsContactedList());
			homePageDataBean.setQ09bNumberofPersonsEngagedDataBean(Q09bNumberofPersonsEngagedDataBeanMaker.getQ09bNumberofPersonsEngagedList());
			
			
	        return Arrays.asList(homePageDataBean);
	    }
		
		public static void populateProject(String schema,String id, HomePageDataBean homePageDataBean) {
			ResultSet resultSet = null;
			PreparedStatement statement = null;
			Connection connection = null;
			try {
				connection = HiveConnection.getConnection();
				statement = connection.prepareStatement(ReportQuery.GET_PROJECT_BY_ID);
				statement.setString(1, id);
				resultSet = statement.executeQuery();
			 while(resultSet.next()) {
				 homePageDataBean.setQ04aProjectName(resultSet.getString("project.projectname"));
				 homePageDataBean.setQ04aHmisProjectType(resultSet.getString("project.projecttype_desc"));
				 homePageDataBean.setQ04aProjectId(resultSet.getString("project.project_source_system_id"));
				 homePageDataBean.setQ04aMethodOfTracking(resultSet.getString("project.trackingmethod_desc"));
				 String organizationId = resultSet.getString("project.organizationid");
				 populateOranization(schema, organizationId, homePageDataBean);
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
		}
			public static void populateOranization(String schema,String id, HomePageDataBean homePageDataBean) {
				ResultSet resultSet = null;
				PreparedStatement statement = null;
				Connection connection = null;
				try {
					connection = HiveConnection.getConnection();
					statement = connection.prepareStatement(ReportQuery.GET_ORG_BY_ID);
					statement.setString(1, id);
					resultSet = statement.executeQuery();
					int count = 0;
				 while(resultSet.next()) {
					 homePageDataBean.setQ04aOrgName(resultSet.getString("organization.organizationname"));
				     homePageDataBean.setQ04aOrgId(resultSet.getString("organization.organization_source_system_id"));
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
			}
			
			public static List<ClientModel> getClients(String schema) {
				ResultSet resultSet = null;
				PreparedStatement statement = null;
				Connection connection = null;
				List<ClientModel>  models = new ArrayList<ClientModel>();
				try {
					connection = HiveConnection.getConnection();
					statement = connection.prepareStatement(ReportQuery.GET_ALL_CLIENTS);
					resultSet = statement.executeQuery();
				 while(resultSet.next()) {
					 ClientModel model = new ClientModel(resultSet.getString("client.personalid"), resultSet.getString("client.dedup_client_id"), 
							 resultSet.getString("client.name_data_quality"),resultSet.getString("client.name_data_quality_desc"), 
							 resultSet.getString("client.ssn_data_quality"), resultSet.getString("client.ssn_data_quality_desc"), 
							 resultSet.getDate("client.dob"),resultSet.getString("client.dob_data_quality"), 
							 resultSet.getString("client.dob_data_quality_desc"), resultSet.getString("client.gender"), 
							 resultSet.getString("client.gender_desc"), resultSet.getString("client.other_gender"), resultSet.getString("client.ethnicity"), 
							 resultSet.getString("client.ethnicity_desc"), resultSet.getString("client.race"), resultSet.getString("client.race_desc"), 
							 resultSet.getString("client.veteran_status"), resultSet.getString("client.client_source_system_id"));
					 models.add(model);
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
				return models;
			}

			
			public static List<EnrollmentModel> getEnrollmentsByProjectId(String schema,String projectId) {
				ResultSet resultSet = null;
				PreparedStatement statement = null;
				Connection connection = null;
				List<EnrollmentModel>  models = new ArrayList<EnrollmentModel>();
				try {
					connection = HiveConnection.getConnection();
					statement = connection.prepareStatement(ReportQuery.GET_ENROLLMENTS_BY_PROJECT_ID);
					statement.setString(1, projectId);
					resultSet = statement.executeQuery();
				 while(resultSet.next()) {
					 EnrollmentModel model = new EnrollmentModel(resultSet.getString("enrollment.projectentryid"), 
							 resultSet.getString("enrollment.continuouslyhomelessoneyear"),
							 resultSet.getString("enrollment.disablingcondition"), 
							 null, 
							 resultSet.getString("enrollment.householdid"), 
							 resultSet.getString("enrollment.housingstatus"), 
							 resultSet.getString("enrollment.housingstatus_desc"), 
							 resultSet.getString("enrollment.monthshomelesspastthreeyears"), 
							 resultSet.getString("enrollment.monthshomelesspastthreeyears_desc"), 
							 resultSet.getString("enrollment.monthshomelessthistime"), 
							 resultSet.getString("enrollment.otherresidenceprior"), 
							 resultSet.getString("enrollment.projectid"), 
							 resultSet.getString("enrollment.relationshiptohoh"), 
							 resultSet.getString("enrollment.relationshiptohoh_desc"), 
							 resultSet.getString("enrollment.residenceprior"), 
							 resultSet.getString("enrollment.residenceprior_desc"), 
							 resultSet.getString("enrollment.residencepriorlengthofstay"), 
							 resultSet.getString("enrollment.residencepriorlengthofstay_desc"), 
							 resultSet.getString("enrollment.statusdocumented"), 
							 resultSet.getString("enrollment.timeshomelesspastthreeyears"), 
							 resultSet.getString("enrollment.timeshomelesspastthreeyears_desc"), 
							 resultSet.getString("enrollment.ageatentry"), 
							 resultSet.getString("enrollment.personalid"), 
							 resultSet.getInt("enrollment.yearshomeless"), 
							 (Boolean)resultSet.getBoolean("enrollment.chronichomeless"), 
							 resultSet.getString("enrollment.enrollment_source_system_id"));
					 models.add(model);
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
				return models;
			}

			
			public static List<ExitModel> getAllExits(String schema) {
				ResultSet resultSet = null;
				PreparedStatement statement = null;
				Connection connection = null;
				List<ExitModel>  models = new ArrayList<ExitModel>();
				try {
					connection = HiveConnection.getConnection();
					statement = connection.prepareStatement(ReportQuery.GET_ALL_EXITS);
					resultSet = statement.executeQuery();
				 while(resultSet.next()) {
					 ExitModel model = new ExitModel( resultSet.getString("exit.exitid"), resultSet.getString("exit.destination"), 
							 resultSet.getString("exit.destination_desc"), 
							 resultSet.getTimestamp("exit.exitdate"), 
							 resultSet.getString("exit.otherdestination"), 
							 resultSet.getString("exit.projectEntryID"), resultSet.getString("exit.exit_source_system_id"));
					 models.add(model);
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
				return models;
			}

}
