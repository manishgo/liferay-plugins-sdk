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

package com.inkwell.internet.productregistration.model.impl;

import com.inkwell.internet.productregistration.model.PRUser;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PRUser in entity cache.
 *
 * @author
manish

 * @see PRUser
 * @generated
 */
public class PRUserCacheModel implements CacheModel<PRUser>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{prUserId=");
		sb.append(prUserId);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", address1=");
		sb.append(address1);
		sb.append(", address2=");
		sb.append(address2);
		sb.append(", city=");
		sb.append(city);
		sb.append(", state=");
		sb.append(state);
		sb.append(", postalCode=");
		sb.append(postalCode);
		sb.append(", country=");
		sb.append(country);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", email=");
		sb.append(email);
		sb.append(", birthDate=");
		sb.append(birthDate);
		sb.append(", male=");
		sb.append(male);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		return sb.toString();
	}

	public PRUser toEntityModel() {
		PRUserImpl prUserImpl = new PRUserImpl();

		prUserImpl.setPrUserId(prUserId);

		if (firstName == null) {
			prUserImpl.setFirstName(StringPool.BLANK);
		}
		else {
			prUserImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			prUserImpl.setLastName(StringPool.BLANK);
		}
		else {
			prUserImpl.setLastName(lastName);
		}

		if (address1 == null) {
			prUserImpl.setAddress1(StringPool.BLANK);
		}
		else {
			prUserImpl.setAddress1(address1);
		}

		if (address2 == null) {
			prUserImpl.setAddress2(StringPool.BLANK);
		}
		else {
			prUserImpl.setAddress2(address2);
		}

		if (city == null) {
			prUserImpl.setCity(StringPool.BLANK);
		}
		else {
			prUserImpl.setCity(city);
		}

		if (state == null) {
			prUserImpl.setState(StringPool.BLANK);
		}
		else {
			prUserImpl.setState(state);
		}

		if (postalCode == null) {
			prUserImpl.setPostalCode(StringPool.BLANK);
		}
		else {
			prUserImpl.setPostalCode(postalCode);
		}

		if (country == null) {
			prUserImpl.setCountry(StringPool.BLANK);
		}
		else {
			prUserImpl.setCountry(country);
		}

		if (phoneNumber == null) {
			prUserImpl.setPhoneNumber(StringPool.BLANK);
		}
		else {
			prUserImpl.setPhoneNumber(phoneNumber);
		}

		if (email == null) {
			prUserImpl.setEmail(StringPool.BLANK);
		}
		else {
			prUserImpl.setEmail(email);
		}

		if (birthDate == Long.MIN_VALUE) {
			prUserImpl.setBirthDate(null);
		}
		else {
			prUserImpl.setBirthDate(new Date(birthDate));
		}

		prUserImpl.setMale(male);
		prUserImpl.setUserId(userId);
		prUserImpl.setCompanyId(companyId);
		prUserImpl.setGroupId(groupId);

		prUserImpl.resetOriginalValues();

		return prUserImpl;
	}

	public long prUserId;
	public String firstName;
	public String lastName;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String postalCode;
	public String country;
	public String phoneNumber;
	public String email;
	public long birthDate;
	public boolean male;
	public long userId;
	public long companyId;
	public long groupId;
}