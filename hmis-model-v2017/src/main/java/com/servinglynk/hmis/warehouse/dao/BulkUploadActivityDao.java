package com.servinglynk.hmis.warehouse.dao;

import java.util.List;

import com.servinglynk.hmis.warehouse.model.v2017.BulkUploadActivity;

public interface BulkUploadActivityDao {
	public List<BulkUploadActivity> getBulkUploadActivityByUploadId(Long bulkUploadId);
}
