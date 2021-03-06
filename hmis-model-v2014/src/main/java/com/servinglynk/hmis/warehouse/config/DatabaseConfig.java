
package com.servinglynk.hmis.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.servinglynk.hmis.warehouse.base.dao.BulkUploaderWorkerDao;
import com.servinglynk.hmis.warehouse.base.dao.BulkUploaderWorkerDaoImpl;
import com.servinglynk.hmis.warehouse.base.dao.config.BaseDatabaseConfig;
import com.servinglynk.hmis.warehouse.dao.AffiliationDao;
import com.servinglynk.hmis.warehouse.dao.AffiliationDaoImpl;
import com.servinglynk.hmis.warehouse.dao.BulkUploadActivityDaoImpl;
import com.servinglynk.hmis.warehouse.dao.BulkUploadErrorDaoImpl;
import com.servinglynk.hmis.warehouse.dao.BulkUploaderDao;
import com.servinglynk.hmis.warehouse.dao.BulkUploaderDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ClientDaoImpl;
import com.servinglynk.hmis.warehouse.dao.CommercialsexualexploitationDao;
import com.servinglynk.hmis.warehouse.dao.CommercialsexualexploitationDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ConnectionwithsoarDao;
import com.servinglynk.hmis.warehouse.dao.ConnectionwithsoarDaoImpl;
import com.servinglynk.hmis.warehouse.dao.DateofengagementDao;
import com.servinglynk.hmis.warehouse.dao.DateofengagementDaoImpl;
import com.servinglynk.hmis.warehouse.dao.DisabilitiesDao;
import com.servinglynk.hmis.warehouse.dao.DisabilitiesDaoImpl;
import com.servinglynk.hmis.warehouse.dao.DomesticviolenceDao;
import com.servinglynk.hmis.warehouse.dao.DomesticviolenceDaoImpl;
import com.servinglynk.hmis.warehouse.dao.EmploymentDao;
import com.servinglynk.hmis.warehouse.dao.EmploymentDaoImpl;
import com.servinglynk.hmis.warehouse.dao.EnrollmentCocDao;
import com.servinglynk.hmis.warehouse.dao.EnrollmentCocDaoImpl;
import com.servinglynk.hmis.warehouse.dao.EnrollmentDao;
import com.servinglynk.hmis.warehouse.dao.EnrollmentDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ExitDao;
import com.servinglynk.hmis.warehouse.dao.ExitDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ExithousingassessmentDao;
import com.servinglynk.hmis.warehouse.dao.ExithousingassessmentDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ExitplansactionsDao;
import com.servinglynk.hmis.warehouse.dao.ExitplansactionsDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ExportDaoImpl;
import com.servinglynk.hmis.warehouse.dao.FamilyreunificationDao;
import com.servinglynk.hmis.warehouse.dao.FamilyreunificationDaoImpl;
import com.servinglynk.hmis.warehouse.dao.FormerwardchildwelfareDao;
import com.servinglynk.hmis.warehouse.dao.FormerwardchildwelfareDaoImpl;
import com.servinglynk.hmis.warehouse.dao.FormerwardjuvenilejusticeDao;
import com.servinglynk.hmis.warehouse.dao.FormerwardjuvenilejusticeDaoImpl;
import com.servinglynk.hmis.warehouse.dao.FunderDao;
import com.servinglynk.hmis.warehouse.dao.FunderDaoImpl;
import com.servinglynk.hmis.warehouse.dao.HMISTypeDaoImpl;
import com.servinglynk.hmis.warehouse.dao.HealthStatusDao;
import com.servinglynk.hmis.warehouse.dao.HealthStatusDaoImpl;
import com.servinglynk.hmis.warehouse.dao.HealthinsuranceDao;
import com.servinglynk.hmis.warehouse.dao.HealthinsuranceDaoImpl;
import com.servinglynk.hmis.warehouse.dao.HousingassessmentdispositionDao;
import com.servinglynk.hmis.warehouse.dao.HousingassessmentdispositionDaoImpl;
import com.servinglynk.hmis.warehouse.dao.IncomeandsourcesDao;
import com.servinglynk.hmis.warehouse.dao.IncomeandsourcesDaoImpl;
import com.servinglynk.hmis.warehouse.dao.InformationReleasesDaoImpl;
import com.servinglynk.hmis.warehouse.dao.InventoryDao;
import com.servinglynk.hmis.warehouse.dao.InventoryDaoImpl;
import com.servinglynk.hmis.warehouse.dao.LastPermAddressDao;
import com.servinglynk.hmis.warehouse.dao.LastPermAddressDaoImpl;
import com.servinglynk.hmis.warehouse.dao.LastgradecompletedDao;
import com.servinglynk.hmis.warehouse.dao.LastgradecompletedDaoImpl;
import com.servinglynk.hmis.warehouse.dao.MedicalassistanceDao;
import com.servinglynk.hmis.warehouse.dao.MedicalassistanceDaoImpl;
import com.servinglynk.hmis.warehouse.dao.NoncashbenefitsDao;
import com.servinglynk.hmis.warehouse.dao.NoncashbenefitsDaoImpl;
import com.servinglynk.hmis.warehouse.dao.OrganizationDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ParentDaoFactoryImpl;
import com.servinglynk.hmis.warehouse.dao.PathstatusDao;
import com.servinglynk.hmis.warehouse.dao.PathstatusDaoImpl;
import com.servinglynk.hmis.warehouse.dao.PercentamiDao;
import com.servinglynk.hmis.warehouse.dao.PercentamiDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ProjectDao;
import com.servinglynk.hmis.warehouse.dao.ProjectDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ProjectcocDao;
import com.servinglynk.hmis.warehouse.dao.ProjectcocDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ProjectcompletionstatusDao;
import com.servinglynk.hmis.warehouse.dao.ProjectcompletionstatusDaoImpl;
import com.servinglynk.hmis.warehouse.dao.QuestionDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ReferralsourceDao;
import com.servinglynk.hmis.warehouse.dao.ReferralsourceDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ResidentialmoveindateDao;
import com.servinglynk.hmis.warehouse.dao.ResidentialmoveindateDaoImpl;
import com.servinglynk.hmis.warehouse.dao.RhybcpstatusDao;
import com.servinglynk.hmis.warehouse.dao.RhybcpstatusDaoImpl;
import com.servinglynk.hmis.warehouse.dao.SchoolstatusDao;
import com.servinglynk.hmis.warehouse.dao.SchoolstatusDaoImpl;
import com.servinglynk.hmis.warehouse.dao.ServicesDao;
import com.servinglynk.hmis.warehouse.dao.ServicesDaoImpl;
import com.servinglynk.hmis.warehouse.dao.SexualorientationDao;
import com.servinglynk.hmis.warehouse.dao.SexualorientationDaoImpl;
import com.servinglynk.hmis.warehouse.dao.SiteDao;
import com.servinglynk.hmis.warehouse.dao.SiteDaoImpl;
import com.servinglynk.hmis.warehouse.dao.SourceDao;
import com.servinglynk.hmis.warehouse.dao.SourceDaoImpl;
import com.servinglynk.hmis.warehouse.dao.SyncListDao;
import com.servinglynk.hmis.warehouse.dao.SyncListDaoImpl;
import com.servinglynk.hmis.warehouse.dao.VeteranInfoDao;
import com.servinglynk.hmis.warehouse.dao.VeteranInfoDaoImpl;
import com.servinglynk.hmis.warehouse.dao.WorsthousingsituationDao;
import com.servinglynk.hmis.warehouse.dao.WorsthousingsituationDaoImpl;
import com.servinglynk.hmis.warehouse.dao.YouthcriticalissuesDao;
import com.servinglynk.hmis.warehouse.dao.YouthcriticalissuesDaoImpl;
import com.servinglynk.hmis.warehouse.dao.helper.BulkUploadHelper;
import com.servinglynk.hmis.warehouse.dao.helper.ChronicHomelessCalcHelper;

@Configuration
@EnableTransactionManagement
//@ComponentScan("com.servinglynk.hmis.warehouse.dao.helper")
@PropertySource("classpath:database.properties")
public class DatabaseConfig extends BaseDatabaseConfig{
	
	@Bean
	public SyncListDao syncListDao()
	{
		return new SyncListDaoImpl();
	}
	@Bean
	public VeteranInfoDao veteranInfoDao() {
		return new VeteranInfoDaoImpl();
	}
	@Bean
	public BulkUploaderDao bulkUploaderDao()
	{
		return new BulkUploaderDaoImpl();
	}
	@Bean
	public BulkUploaderWorkerDao bulkUploaderWorkerDao()
	{
		return new BulkUploaderWorkerDaoImpl();
	}
	@Bean
	public CommercialsexualexploitationDao commercialsexualexploitationDao() { 
		return new CommercialsexualexploitationDaoImpl();
	}
	@Bean
	public DateofengagementDao dateofengagementDao() { 
		return new DateofengagementDaoImpl();
	}
	@Bean
	public AffiliationDao affiliationDao() { 
		return new AffiliationDaoImpl();
	}
	@Bean
	public ConnectionwithsoarDao connectionwithsoarDao() {
		return new ConnectionwithsoarDaoImpl();
	}
	@Bean
	public DisabilitiesDao disabilitiesDao() { 
		return new DisabilitiesDaoImpl(); 
	}
	@Bean
	public DomesticviolenceDao domesticviolenceDao() { 
		return new DomesticviolenceDaoImpl();
	}
	@Bean
	public EmploymentDao employmentDao() { 
		return new EmploymentDaoImpl();
	}
	@Bean
	public EnrollmentDao enrollmentDao() { 
		return new EnrollmentDaoImpl();
	}
	@Bean
	public EnrollmentCocDao enrollmentCocDao() { 
		return new EnrollmentCocDaoImpl();
	}
	@Bean
	public ExitDao exitDao() { 
		return new ExitDaoImpl();
	}
	@Bean
	public ExithousingassessmentDao exithousingassessmentDao() { 
		return  new ExithousingassessmentDaoImpl();
	}
	@Bean
	public ExitplansactionsDao exitplansactionsDao() { 
		return new ExitplansactionsDaoImpl();
	}
	@Bean
	public FamilyreunificationDao familyreunificationDao() {
		return new FamilyreunificationDaoImpl();
	}
	@Bean
	public FormerwardchildwelfareDao formerwardchildwelfareDao() { 
		return  new FormerwardchildwelfareDaoImpl();
	}
	@Bean
	public FormerwardjuvenilejusticeDao formerwardjuvenilejusticeDao() {
		return  new FormerwardjuvenilejusticeDaoImpl();
	}
	@Bean
	public FunderDao funderDao() { 
		return new FunderDaoImpl();
	}
	@Bean
	public HealthinsuranceDao healthinsuranceDao() { 
		return new HealthinsuranceDaoImpl();
	}
	@Bean
	public HealthStatusDao healthStatusDao() { 
		return new HealthStatusDaoImpl();
	}
	@Bean
	public HousingassessmentdispositionDao housingassessmentdispositionDao() { 
		return new HousingassessmentdispositionDaoImpl();
	}
	@Bean
	public IncomeandsourcesDao incomeandsourcesDao() { 
		return new IncomeandsourcesDaoImpl();
	}
	@Bean
	public InventoryDao inventoryDao() { 
		return new InventoryDaoImpl();
	}
	@Bean
	public LastgradecompletedDao lastgradecompletedDao() { 
		return new LastgradecompletedDaoImpl();
	}
	@Bean
	public LastPermAddressDao lastPermAddressDao() {
		return new LastPermAddressDaoImpl();
	}
	@Bean
	public MedicalassistanceDao medicalassistanceDao() { 
		return new MedicalassistanceDaoImpl();
	}
	@Bean
	public NoncashbenefitsDao noncashbenefitsDao() { 
		return new NoncashbenefitsDaoImpl();
	}
	@Bean
	public PathstatusDao pathstatusDao() { 
		return new PathstatusDaoImpl();
	}
	@Bean
	public PercentamiDao percentamiDao() { 
		return new PercentamiDaoImpl();
	}
	@Bean
	public ProjectDao projectDao() { 
		return new ProjectDaoImpl();
	}
	@Bean
	public ProjectcocDao projectcocDao() { 
		return new ProjectcocDaoImpl();
	}
	@Bean
	public ProjectcompletionstatusDao projectcompletionstatusDao() { 
		return new ProjectcompletionstatusDaoImpl();
	}
	@Bean
	public ReferralsourceDao referralsourceDao() { 
		return new ReferralsourceDaoImpl();
	}
	@Bean
	public ResidentialmoveindateDao residentialmoveindateDao() { 
		return new ResidentialmoveindateDaoImpl();
	}
	@Bean
	public RhybcpstatusDao rhybcpstatusDao() { 
		return new RhybcpstatusDaoImpl();
	}
	@Bean
	public SchoolstatusDao schoolstatusDao() { 
		return new SchoolstatusDaoImpl();
	}
	@Bean
	public ServicesDao servicesDao() { 
		return new ServicesDaoImpl();
	}
	@Bean
	public SexualorientationDao sexualorientationDao() { 
		return new SexualorientationDaoImpl();
	}
	@Bean
	public SiteDao siteDao() { 
		return new SiteDaoImpl();
	}
	@Bean
	public SourceDao sourceDao() { 
		return new SourceDaoImpl();
	}
	// public SyncDao
	@Bean
	public WorsthousingsituationDao worsthousingsituationDao() { 
		return new WorsthousingsituationDaoImpl();
	}
	@Bean
	public YouthcriticalissuesDao youthcriticalissuesDao() { 
		return  new YouthcriticalissuesDaoImpl();
	}

		
	@Bean
	public String loginUri(){
		return new String("/hmis-authorization-service/login.html");
	}
	
	@Bean
	public String consentUri(){
		return new String("/hmis-authorization-service/consent.html");
	}
	
	@Bean
	public InformationReleasesDaoImpl informationReleasesDao(){
		return new InformationReleasesDaoImpl();
	}
	@Bean
	public ExportDaoImpl exportDao(){
		return new ExportDaoImpl();
	}
	
	@Bean
	public BulkUploadHelper bulkUploadHelper() {
		return new BulkUploadHelper();
	}	
	
	@Bean
	public ParentDaoFactoryImpl parentDaoFactory(){
		return new ParentDaoFactoryImpl();
	}
	
	@Bean
	public OrganizationDaoImpl organizationDao(){
		return new OrganizationDaoImpl();
	}
	
	@Bean
	public ClientDaoImpl clientDao(){
		return new ClientDaoImpl();
	}
	
	@Bean
	public ChronicHomelessCalcHelper chronicHomelessCalcHelper() {
		return new ChronicHomelessCalcHelper();
	}
	
	@Bean
	public BulkUploadActivityDaoImpl bulkUploadActivityDao() {
		return new BulkUploadActivityDaoImpl();
	}
	
	@Bean
	public BulkUploadErrorDaoImpl bulkUploadErrorDao() {
		return new BulkUploadErrorDaoImpl();
	}
	
	@Bean
	public HMISTypeDaoImpl hmisTypeDao() {
		return new HMISTypeDaoImpl();
	}
	
	@Bean
	public QuestionDaoImpl questionDao() {
		return new QuestionDaoImpl();
	}
}