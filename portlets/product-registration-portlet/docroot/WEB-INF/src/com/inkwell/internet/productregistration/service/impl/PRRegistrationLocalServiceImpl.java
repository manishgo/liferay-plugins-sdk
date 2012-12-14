/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.inkwell.internet.productregistration.service.impl;

import java.util.List;

import com.inkwell.internet.productregistration.model.PRRegistration;
import com.inkwell.internet.productregistration.service.base.PRRegistrationLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.NestableException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the p r registration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.inkwell.internet.productregistration.service.PRRegistrationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
manish

 * @see com.inkwell.internet.productregistration.service.base.PRRegistrationLocalServiceBaseImpl
 * @see com.inkwell.internet.productregistration.service.PRRegistrationLocalServiceUtil
 */
public class PRRegistrationLocalServiceImpl
	extends PRRegistrationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.inkwell.internet.productregistration.service.PRRegistrationLocalServiceUtil} to access the p r registration local service.
	 */
	
	public PRRegistration addRegistration(PRRegistration reg) throws SystemException, NestableException {
		PRRegistration registration = prRegistrationPersistence.create(counterLocalService.increment(PRRegistration.class.getName()));
		resourceLocalService.addResources(reg.getCompanyId(), reg.getGroupId(), PRRegistration.class.getName(), false);
		
		registration.setCompanyId(reg.getCompanyId());
		registration.setGroupId(reg.getGroupId());
		registration.setDatePurchased(reg.getDatePurchased());
		registration.setHowHear(reg.getHowHear());
		registration.setProductId(reg.getProductId());
		registration.setPrUserId(reg.getPrUserId());
		registration.setSerialNumber(reg.getSerialNumber());
		registration.setWherePurchased(reg.getWherePurchased());
		
		return prRegistrationPersistence.update(registration, false);
	}
	
	
	public List<PRRegistration> getAllRegistrations(long groupId) throws SystemException {
		return prRegistrationPersistence.findByGroupId(groupId);
	}
}