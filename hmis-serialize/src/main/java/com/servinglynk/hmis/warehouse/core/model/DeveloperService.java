package com.servinglynk.hmis.warehouse.core.model;import org.apache.commons.lang.builder.ToStringBuilder;import com.fasterxml.jackson.annotation.JsonRootName;@JsonRootName("developerService")public class DeveloperService extends ClientModel {	private String serviceId;		private String friendlyName;	private String description;		private String licenseType;	private String status;	private DeveloperCompany developerCompany;		public String getServiceId() {		return serviceId;	}	public void setServiceId(String serviceId) {		this.serviceId = serviceId;	}	public String getFriendlyName() {		return friendlyName;	}	public void setFriendlyName(String friendlyName) {		this.friendlyName = friendlyName;	}	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}	public String getLicenseType() {		return licenseType;	}	public void setLicenseType(String licenseType) {		this.licenseType = licenseType;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public DeveloperCompany getDeveloperCompany() {		return developerCompany;	}	public void setDeveloperCompany(DeveloperCompany developerCompany) {		this.developerCompany = developerCompany;	}	public String toString() {		return ToStringBuilder.reflectionToString(this);	}}