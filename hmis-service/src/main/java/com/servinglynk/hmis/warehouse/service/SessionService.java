package com.servinglynk.hmis.warehouse.service;


import com.servinglynk.hmis.warehouse.core.model.Session;

public interface SessionService {

	public Session getSession(String userName);

	public Session validateSessionToken(String authenticationToken);

	public void endSession(String sessionToken);

	public void createSession(Session session, String trustedAppId,
			String userService);


}
