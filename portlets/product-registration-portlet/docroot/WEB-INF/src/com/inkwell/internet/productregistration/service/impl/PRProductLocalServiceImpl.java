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

import com.inkwell.internet.productregistration.NoSuchProductException;
import com.inkwell.internet.productregistration.model.PRProduct;
import com.inkwell.internet.productregistration.service.base.PRProductLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceConstants;

/**
 * The implementation of the p r product local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.inkwell.internet.productregistration.service.PRProductLocalService}
 * interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author manish
 * 
 * @see com.inkwell.internet.productregistration.service.base.PRProductLocalServiceBaseImpl
 * @see com.inkwell.internet.productregistration.service.PRProductLocalServiceUtil
 */
public class PRProductLocalServiceImpl extends PRProductLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.inkwell.internet
	 * .productregistration.service.PRProductLocalServiceUtil} to access the p r
	 * product local service.
	 */

	public PRProduct addProduct(PRProduct newProduct, long userId)
			throws SystemException, PortalException {
		PRProduct product = prProductPersistence.create(counterLocalService
				.increment(PRProduct.class.getName()));
		resourceLocalService.addResources(newProduct.getCompanyId(),
				newProduct.getGroupId(), userId, PRProduct.class.getName(),
				product.getProductId(), false, true, true);
		product.setProductName(newProduct.getProductName());
		product.setSerialNumber(newProduct.getSerialNumber());
		product.setCompanyId(newProduct.getCompanyId());
		product.setGroupId(newProduct.getGroupId());

		return prProductPersistence.update(product, false);
	}

	public void deleteProduct(long productId) throws SystemException,
			PortalException {
		PRProduct product = prProductPersistence.findByPrimaryKey(productId);
		deleteProduct(product);
	}

	public void deleteProduct(PRProduct product) throws PortalException,
			SystemException {
		resourceLocalService.deleteResource(product.getCompanyId(),
				PRProduct.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
				product.getPrimaryKey());
		prProductPersistence.remove(product);
	}

	public List<PRProduct> getAllProducts(long groupId) throws SystemException {
		List<PRProduct> products = prProductPersistence.findByGroupId(groupId);
		return products;
	}
}