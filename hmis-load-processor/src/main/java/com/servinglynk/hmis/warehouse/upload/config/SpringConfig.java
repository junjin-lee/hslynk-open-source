package com.servinglynk.hmis.warehouse.upload.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.servinglynk.hmis.warehouse.config.DatabaseConfig;
import com.servinglynk.hmis.warehouse.dao.BulkUploaderWorkerDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ParentDaoFactory;
import com.servinglynk.hmis.warehouse.upload.business.service.core.ParentService;
import com.servinglynk.hmis.warehouse.upload.business.service.core.ParentServiceFactory;
import com.servinglynk.hmis.warehouse.upload.service.BulkUploadService;
import com.servinglynk.hmis.warehouse.upload.service.BulkUploadWorker;

@Configuration
@EnableScheduling
@EnableTransactionManagement
@Import({DatabaseConfig.class})
@ImportResource("classpath:com/servinglynk/hmis/warehouse/upload/property-config.xml")
@ComponentScan(value={"com.servinglynk.hmis.warehouse.upload.service"})
@PropertySource("classpath:com/servinglynk/hmis/warehouse/upload/upload.properties")

public class SpringConfig {

	@Autowired
	Environment env;
	
	@Bean
    public static PropertyPlaceholderConfigurer properties(){
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ClassPathResource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "com/servinglynk/hmis/warehouse/upload/upload.properties" ) };
        ppc.setLocations( resources );
        ppc.setIgnoreUnresolvablePlaceholders( true );
        return ppc;
    }
	


	
	@Bean
	public BulkUploadService bulkUploadService(){
		return new BulkUploadService();
	}
	
	@Bean
	public ParentService parentService(){
		ParentService service =new ParentService();
		service.setServiceFactory(parentServiceFactory());
		//service.setDaoFactory(getParentDaoFactory());
		return new ParentService();
	}
	
	@Bean
	public ParentServiceFactory parentServiceFactory(){
		ParentServiceFactory serviceFactory=new ParentServiceFactory();
		return serviceFactory;
	}
	
	
	@Bean
	public BulkUploadWorker bulkUploadWorker(){
		return new BulkUploadWorker();
	}
	
	@Bean(destroyMethod="close")
	public BasicDataSource relationalDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(uploadConfig().getDbDriverClassName());
    	dataSource.setUrl(uploadConfig().getDbUrl());
    	dataSource.setUsername(uploadConfig().getDbUserName());
    	dataSource.setPassword(uploadConfig().getDbPassword());
		return dataSource;
	}
	

	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory= new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(relationalDataSource());
		sessionFactory.setPackagesToScan("com.servinglynk.hmis.warehouse");
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		
		return sessionFactory;
	}
	
	
	private Properties getHibernateProperties(){
		Properties properties = new Properties();
		 properties.put("hibernate.show_sql", "true");
		 properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		 properties.put("hibernate.format_sql", "false");
		 properties.put("hibernate.cache.use_query_cache", "false");
		 properties.put("hibernate.cache.use_second_level_cache", "true");

		 return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	

	
	
	@Bean
	public BulkUploaderWorkerDaoImpl getBulkUploaderWorkerDao(){
		return new BulkUploaderWorkerDaoImpl();
	}
	
	
	@Bean
	public UploadConfig uploadConfig(){
		return new UploadConfig();
	}
	
}
