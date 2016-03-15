package com.servinglynk.hmis.warehouse.v2015;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

import com.servinglynk.hmis.warehouse.enums.RhybcpStatusFysbYouthEnum;
import com.servinglynk.hmis.warehouse.enums.RhybcpStatusReasonNoServicesEnum;


/** 
 * Object mapping for hibernate-handled table: rhybcp_status.
 * 
 *
 * @author autogenerated
 */

@Entity(name = "rhybcp_status_v2015")
@Table(name = "rhybcp_status", catalog = "hmis", schema = "v2015")
public class RhybcpStatus extends HmisBaseModelV2015 implements Cloneable, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2991336675134814158L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, java.util.UUID> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, java.util.UUID>());
	
	/** hashCode temporary storage. */
	private volatile java.util.UUID hashCode;
	

	/** Field mapping. */
	private Enrollment enrollmentid;
	/** Field mapping. */
	private RhybcpStatusFysbYouthEnum fysbYouth;
	/** Field mapping. */
	private java.util.UUID id;
	/** Field mapping. */
	private RhybcpStatusReasonNoServicesEnum reasonNoServices;
	/** Field mapping. */
	private LocalDateTime statusDate;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public RhybcpStatus() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public RhybcpStatus(java.util.UUID id) {
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return RhybcpStatus.class;
	}
 
	 /**
	 * Return the value associated with the column: enrollmentid.
	 * @return A Enrollment object (this.enrollmentid)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = true )
	@JoinColumn(name = "enrollmentid", nullable = true )
	public Enrollment getEnrollmentid() {
		return this.enrollmentid;
		
	}
	

  
	 /**  
	 * Set the value related to the column: enrollmentid.
	 * @param enrollmentid the enrollmentid value you wish to set
	 */
	public void setEnrollmentid(final Enrollment enrollmentid) {
		this.enrollmentid = enrollmentid;
	}


	/**
	 * Return the value associated with the column: fysbYouth.
	 * @return A RhybcpStatusFysbYouthEnum object (this.fysbYouth)
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.RhybcpStatusFysbYouthEnumType")
	@Basic( optional = true )
	@Column( name = "fysb_youth"  )
	public RhybcpStatusFysbYouthEnum getFysbYouth() {
		return this.fysbYouth;

	}
	

  
	 /**  
	 * Set the value related to the column: fysbYouth.
	 * @param fysbYouth the fysbYouth value you wish to set
	 */
	public void setFysbYouth(final RhybcpStatusFysbYouthEnum fysbYouth) {
		this.fysbYouth = fysbYouth;
	}

	 /**
	 * Return the value associated with the column: id.
	 * @return A java.util.UUID object (this.id)
	 */
	@Id 
	 @Basic( optional = false )
  @Column( name = "id", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public java.util.UUID getId() {
		return this.id;
		
	}
	

  
	 /**  
	 * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final java.util.UUID id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null ) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}
	


	/**
	 * Return the value associated with the column: reasonNoServices.
	 * @return A RhybcpStatusReasonNoServicesEnum object (this.reasonNoServices)
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.RhybcpStatusReasonNoServicesEnumType")
	@Basic( optional = true )
	@Column( name = "reason_no_services"  )
	public RhybcpStatusReasonNoServicesEnum getReasonNoServices() {
		return this.reasonNoServices;

	}
	

  
	 /**  
	 * Set the value related to the column: reasonNoServices.
	 * @param reasonNoServices the reasonNoServices value you wish to set
	 */
	public void setReasonNoServices(final RhybcpStatusReasonNoServicesEnum reasonNoServices) {
		this.reasonNoServices = reasonNoServices;
	}

	 /**
	 * Return the value associated with the column: statusDate.
	 * @return A LocalDateTime object (this.statusDate)
	 */
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Basic( optional = true )
	@Column( name = "status_date"  )
	public LocalDateTime getStatusDate() {
		return this.statusDate;
		
	}
	

  
	 /**  
	 * Set the value related to the column: statusDate.
	 * @param statusDate the statusDate value you wish to set
	 */
	public void setStatusDate(final LocalDateTime statusDate) {
		this.statusDate = statusDate;
	}

   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public RhybcpStatus clone() throws CloneNotSupportedException {
		
        final RhybcpStatus copy = (RhybcpStatus)super.clone();

		copy.setDateCreated(this.getDateCreated());
		copy.setDateCreatedFromSource(this.getDateCreatedFromSource());
		copy.setDateUpdated(this.getDateUpdated());
		copy.setDateUpdatedFromSource(this.getDateUpdatedFromSource());
		copy.setDeleted(this.isDeleted());
		copy.setEnrollmentid(this.getEnrollmentid());
		copy.setExport(this.getExport());
		copy.setFysbYouth(this.getFysbYouth());
		copy.setId(this.getId());
		copy.setParentId(this.getParentId());
		copy.setProjectGroupCode(this.getProjectGroupCode());
		copy.setReasonNoServices(this.getReasonNoServices());
		copy.setStatusDate(this.getStatusDate());
		copy.setSync(this.isSync());
		copy.setUserId(this.getUserId());
		copy.setVersion(this.getVersion());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("dateCreated: " + this.getDateCreated() + ", ");
		sb.append("dateCreatedFromSource: " + this.getDateCreatedFromSource() + ", ");
		sb.append("dateUpdated: " + this.getDateUpdated() + ", ");
		sb.append("dateUpdatedFromSource: " + this.getDateUpdatedFromSource() + ", ");
		sb.append("deleted: " + this.isDeleted() + ", ");
		sb.append("fysbYouth: " + this.getFysbYouth() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("parentId: " + this.getParentId() + ", ");
		sb.append("projectGroupCode: " + this.getProjectGroupCode() + ", ");
		sb.append("reasonNoServices: " + this.getReasonNoServices() + ", ");
		sb.append("statusDate: " + this.getStatusDate() + ", ");
		sb.append("sync: " + this.isSync() + ", ");
		sb.append("userId: " + this.getUserId() + ", ");
		sb.append("version: " + this.getVersion());
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final RhybcpStatus that; 
		try {
			that = (RhybcpStatus) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getDateCreated() == null) && (that.getDateCreated() == null)) || (getDateCreated() != null && getDateCreated().equals(that.getDateCreated())));
		result = result && (((getDateCreatedFromSource() == null) && (that.getDateCreatedFromSource() == null)) || (getDateCreatedFromSource() != null && getDateCreatedFromSource().equals(that.getDateCreatedFromSource())));
		result = result && (((getDateUpdated() == null) && (that.getDateUpdated() == null)) || (getDateUpdated() != null && getDateUpdated().equals(that.getDateUpdated())));
		result = result && (((getDateUpdatedFromSource() == null) && (that.getDateUpdatedFromSource() == null)) || (getDateUpdatedFromSource() != null && getDateUpdatedFromSource().equals(that.getDateUpdatedFromSource())));
		result = result && (((getEnrollmentid() == null) && (that.getEnrollmentid() == null)) || (getEnrollmentid() != null && getEnrollmentid().getId().equals(that.getEnrollmentid().getId())));	
		result = result && (((getExport() == null) && (that.getExport() == null)) || (getExport() != null && getExport().getId().equals(that.getExport().getId())));	
		result = result && (((getFysbYouth() == null) && (that.getFysbYouth() == null)) || (getFysbYouth() != null && getFysbYouth().equals(that.getFysbYouth())));
		result = result && (((getParentId() == null) && (that.getParentId() == null)) || (getParentId() != null && getParentId().equals(that.getParentId())));
		result = result && (((getProjectGroupCode() == null) && (that.getProjectGroupCode() == null)) || (getProjectGroupCode() != null && getProjectGroupCode().equals(that.getProjectGroupCode())));
		result = result && (((getReasonNoServices() == null) && (that.getReasonNoServices() == null)) || (getReasonNoServices() != null && getReasonNoServices().equals(that.getReasonNoServices())));
		result = result && (((getStatusDate() == null) && (that.getStatusDate() == null)) || (getStatusDate() != null && getStatusDate().equals(that.getStatusDate())));
		result = result && (((getUserId() == null) && (that.getUserId() == null)) || (getUserId() != null && getUserId().equals(that.getUserId())));
		result = result && (((getVersion() == null) && (that.getVersion() == null)) || (getVersion() != null && getVersion().equals(that.getVersion())));
		return result;
	}
}
