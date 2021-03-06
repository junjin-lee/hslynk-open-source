package com.servinglynk.hive.connection;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.servinglynk.hmis.warehouse.Logging;
import com.servinglynk.hmis.warehouse.Properties;
import com.servinglynk.hmis.warehouse.ReportConfig;

public class SyncPostgresProcessor extends Logging{

    public static int batchSize = 1000;
    private static Connection connection = null;
    static final Logger logger = Logger.getLogger(SyncPostgresProcessor.class);
    static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + Properties.POSTGRESQL_DB_HOST + ":" + Properties.POSTGRESQL_DB_PORT + "/" + Properties.POSTGRESQL_DB_DATABASE,
                    Properties.POSTGRESQL_DB_USERNAME,
                    Properties.POSTGRESQL_DB_PASSWORD);
        }
        if (connection.isClosed()) {
            throw new SQLException("connection could not initiated");
        }
        return connection;
    }
    
   //  statement = connection.prepareStatement("select value as projectid,report_config_id, report_config_id as reportconfig from base.report_config_param where key='PROJECT_ID' and report_config_id in ( select id from base.report_config where status='INITIAL' limit 1 )");
    
    public static ReportConfig getProjects() throws Exception{
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        ReportConfig reportConfig = null; 
        try{
            connection = getConnection();
            statement = connection.prepareStatement("select id,project_group_code,start_date,end_date,coc_id from base.report_config where status='INITIAL' limit 1 ");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
            	Long reportConfigId = resultSet.getLong("id"); 
            	String projectGroupCode = resultSet.getString("project_group_code");
            	Date startDate = resultSet.getDate("start_date");
            	Date endDate = resultSet.getDate("end_date");
            	String cocId = resultSet.getString("coc_id");
            	reportConfig = new ReportConfig(reportConfigId, projectGroupCode, null	, startDate, endDate, true,cocId);
            	populateProject(reportConfig);
            }
        }catch (Exception ex){
            throw ex;
        }
        return reportConfig;
    }
    
    public static void populateProject(ReportConfig reportConfig) throws Exception{
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        List<String> projects = new ArrayList<>();
        try{
            connection = getConnection();
            statement = connection.prepareStatement("select value from base.report_config_param where  report_config_id= ? and key='PROJECT_ID' ");
            statement.setLong(1, reportConfig.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
            	String projectId = resultSet.getString("value");
            	projects.add(projectId);
            }
        }catch (Exception ex){
            throw ex;
        }
        reportConfig.setProjectds(projects);
    }

   
}
