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

import com.inkwell.internet.productregistration.model.PRUser;
import com.inkwell.internet.productregistration.service.base.PRUserLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the p r user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.inkwell.internet.productregistration.service.PRUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
manish

 * @see com.inkwell.internet.productregistration.service.base.PRUserLocalServiceBaseImpl
 * @see com.inkwell.internet.productregistration.service.PRUserLocalServiceUtil
 */
public class PRUserLocalServiceImpl extends PRUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.inkwell.internet.productregistration.service.PRUserLocalServiceUtil} to access the p r user local service.
	 */
	
	public PRUser addPRUser(PRUser user, long userId) throws SystemException, PortalException {
		PRUser prUser = prUserPersistence.create(counterLocalService.increment(PRUser.class.getName()));
		resourceLocalService.addResources(user.getCompanyId(), user.getGroupId(), PRUser.class.getName(), false);
		prUser.setAddress1(user.getAddress1());
		prUser.setAddress2(user.getAddress2());
		prUser.setBirthDate(user.getBirthDate());
		prUser.setCity(user.getCity());
		prUser.setCompanyId(user.getCompanyId());
		prUser.setCountry(user.getCountry());
		prUser.setEmail(user.getEmail());
		prUser.setFirstName(user.getFirstName());
		prUser.setGroupId(user.getGroupId());
		prUser.setLastName(user.getLastName());
		prUser.setMale(user.getMale());
		prUser.setPhoneNumber(user.getPhoneNumber());
		prUser.setPostalCode(user.getPostalCode());
		prUser.setState(user.getState());

		prUser.setUserId(userId);
		return prUserPersistence.update(prUser, false);
	}
}