package com.servinglynk.hmis.warehouse.notification.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servinglynk.hmis.warehouse.notification.business.service.core.ParentService;
import com.servinglynk.hmis.warehouse.notification.carrier.CarrierFactory;
import com.servinglynk.hmis.warehouse.notification.carrier.CarrierNotification;
import com.servinglynk.hmis.warehouse.notification.carrier.ICarrier;
import com.servinglynk.hmis.warehouse.notification.persistence.entity.NotificationLineEntity;

@Service
public class CarrierService extends ParentService implements ICarrierService{
	
	@Autowired
	CarrierFactory carrierFactory;
	
	
	public CarrierFactory getCarrierFactory() {
		return carrierFactory;
	}

	public void setCarrierFactory(CarrierFactory carrierFactory) {
		this.carrierFactory = carrierFactory;
	}

	
	public void send(CarrierNotification carrierNotification, NotificationLineEntity notificationLineEntity){
		ICarrier carrier = carrierFactory.getCarrier(notificationLineEntity.getDeliveryMethod());
		carrier.send(carrierNotification);
	}
	
}
