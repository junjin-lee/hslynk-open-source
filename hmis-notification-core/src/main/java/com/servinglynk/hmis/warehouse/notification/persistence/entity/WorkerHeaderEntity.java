package com.servinglynk.hmis.warehouse.notification.persistence.entity;import static javax.persistence.GenerationType.IDENTITY;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.SequenceGenerator;import javax.persistence.Table;@SuppressWarnings("serial")@Entity@Table(name = "HMIS_WORKER_HEADER" )public class WorkerHeaderEntity extends ParentEntity {	private Long id;		@Id	@SequenceGenerator(name="pk_sequence",sequenceName="hmis_worker_header_id_seq", allocationSize=1)	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")	public Long getId() {		return id;	}	private String externalId;		private String description;		private int retryInterval = 120; // in seconds		private int maxRetry;		private String status = "PENDING";	public void setId(Long id) {		this.id = id;	}		@Column(name = "external_id")	public String getExternalId() {		return externalId;	}	public void setExternalId(String externalId) {		this.externalId = externalId;	}	@Column(name = "description")	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}	@Column(name = "retry_interval")	public int getRetryInterval() {		return retryInterval;	}	public void setRetryInterval(int retryInterval) {		this.retryInterval = retryInterval;	}	@Column(name = "max_retry")	public int getMaxRetry() {		return maxRetry;	}	public void setMaxRetry(int maxRetry) {		this.maxRetry = maxRetry;	}		@Column(name = "status")	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	}