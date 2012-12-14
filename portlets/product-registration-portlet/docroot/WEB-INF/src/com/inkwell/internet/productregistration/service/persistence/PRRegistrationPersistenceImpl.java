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

package com.inkwell.internet.productregistration.service.persistence;

import com.inkwell.internet.productregistration.NoSuchRegistrationException;
import com.inkwell.internet.productregistration.model.PRRegistration;
import com.inkwell.internet.productregistration.model.impl.PRRegistrationImpl;
import com.inkwell.internet.productregistration.model.impl.PRRegistrationModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the p r registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
manish

 * @see PRRegistrationPersistence
 * @see PRRegistrationUtil
 * @generated
 */
public class PRRegistrationPersistenceImpl extends BasePersistenceImpl<PRRegistration>
	implements PRRegistrationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PRRegistrationUtil} to access the p r registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PRRegistrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			PRRegistrationModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_RU = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_RU",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_RU = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_RU",
			new String[] { Long.class.getName(), Long.class.getName() },
			PRRegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			PRRegistrationModelImpl.PRUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_RU = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_RU",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DP = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_DP",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DP = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_DP",
			new String[] { Long.class.getName(), Date.class.getName() },
			PRRegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			PRRegistrationModelImpl.DATEPURCHASED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DP = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DP",
			new String[] { Long.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SN = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_SN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_SN",
			new String[] { Long.class.getName(), String.class.getName() },
			PRRegistrationModelImpl.GROUPID_COLUMN_BITMASK |
			PRRegistrationModelImpl.SERIALNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_SN = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_SN",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED,
			PRRegistrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the p r registration in the entity cache if it is enabled.
	 *
	 * @param prRegistration the p r registration
	 */
	public void cacheResult(PRRegistration prRegistration) {
		EntityCacheUtil.putResult(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationImpl.class, prRegistration.getPrimaryKey(),
			prRegistration);

		prRegistration.resetOriginalValues();
	}

	/**
	 * Caches the p r registrations in the entity cache if it is enabled.
	 *
	 * @param prRegistrations the p r registrations
	 */
	public void cacheResult(List<PRRegistration> prRegistrations) {
		for (PRRegistration prRegistration : prRegistrations) {
			if (EntityCacheUtil.getResult(
						PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						PRRegistrationImpl.class, prRegistration.getPrimaryKey()) == null) {
				cacheResult(prRegistration);
			}
			else {
				prRegistration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all p r registrations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PRRegistrationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PRRegistrationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the p r registration.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PRRegistration prRegistration) {
		EntityCacheUtil.removeResult(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationImpl.class, prRegistration.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PRRegistration> prRegistrations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PRRegistration prRegistration : prRegistrations) {
			EntityCacheUtil.removeResult(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				PRRegistrationImpl.class, prRegistration.getPrimaryKey());
		}
	}

	/**
	 * Creates a new p r registration with the primary key. Does not add the p r registration to the database.
	 *
	 * @param registrationId the primary key for the new p r registration
	 * @return the new p r registration
	 */
	public PRRegistration create(long registrationId) {
		PRRegistration prRegistration = new PRRegistrationImpl();

		prRegistration.setNew(true);
		prRegistration.setPrimaryKey(registrationId);

		return prRegistration;
	}

	/**
	 * Removes the p r registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param registrationId the primary key of the p r registration
	 * @return the p r registration that was removed
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration remove(long registrationId)
		throws NoSuchRegistrationException, SystemException {
		return remove(Long.valueOf(registrationId));
	}

	/**
	 * Removes the p r registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the p r registration
	 * @return the p r registration that was removed
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PRRegistration remove(Serializable primaryKey)
		throws NoSuchRegistrationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PRRegistration prRegistration = (PRRegistration)session.get(PRRegistrationImpl.class,
					primaryKey);

			if (prRegistration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegistrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(prRegistration);
		}
		catch (NoSuchRegistrationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected PRRegistration removeImpl(PRRegistration prRegistration)
		throws SystemException {
		prRegistration = toUnwrappedModel(prRegistration);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, prRegistration);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(prRegistration);

		return prRegistration;
	}

	@Override
	public PRRegistration updateImpl(
		com.inkwell.internet.productregistration.model.PRRegistration prRegistration,
		boolean merge) throws SystemException {
		prRegistration = toUnwrappedModel(prRegistration);

		boolean isNew = prRegistration.isNew();

		PRRegistrationModelImpl prRegistrationModelImpl = (PRRegistrationModelImpl)prRegistration;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, prRegistration, merge);

			prRegistration.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PRRegistrationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((prRegistrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((prRegistrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_RU.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getOriginalGroupId()),
						Long.valueOf(prRegistrationModelImpl.getOriginalPrUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_RU, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_RU,
					args);

				args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getGroupId()),
						Long.valueOf(prRegistrationModelImpl.getPrUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_RU, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_RU,
					args);
			}

			if ((prRegistrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getOriginalGroupId()),
						
						prRegistrationModelImpl.getOriginalDatePurchased()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_DP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DP,
					args);

				args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getGroupId()),
						
						prRegistrationModelImpl.getDatePurchased()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_DP, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DP,
					args);
			}

			if ((prRegistrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getOriginalGroupId()),
						
						prRegistrationModelImpl.getOriginalSerialNumber()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_SN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN,
					args);

				args = new Object[] {
						Long.valueOf(prRegistrationModelImpl.getGroupId()),
						
						prRegistrationModelImpl.getSerialNumber()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_SN, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN,
					args);
			}
		}

		EntityCacheUtil.putResult(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			PRRegistrationImpl.class, prRegistration.getPrimaryKey(),
			prRegistration);

		return prRegistration;
	}

	protected PRRegistration toUnwrappedModel(PRRegistration prRegistration) {
		if (prRegistration instanceof PRRegistrationImpl) {
			return prRegistration;
		}

		PRRegistrationImpl prRegistrationImpl = new PRRegistrationImpl();

		prRegistrationImpl.setNew(prRegistration.isNew());
		prRegistrationImpl.setPrimaryKey(prRegistration.getPrimaryKey());

		prRegistrationImpl.setRegistrationId(prRegistration.getRegistrationId());
		prRegistrationImpl.setPrUserId(prRegistration.getPrUserId());
		prRegistrationImpl.setDatePurchased(prRegistration.getDatePurchased());
		prRegistrationImpl.setHowHear(prRegistration.getHowHear());
		prRegistrationImpl.setWherePurchased(prRegistration.getWherePurchased());
		prRegistrationImpl.setSerialNumber(prRegistration.getSerialNumber());
		prRegistrationImpl.setProductId(prRegistration.getProductId());
		prRegistrationImpl.setCompanyId(prRegistration.getCompanyId());
		prRegistrationImpl.setGroupId(prRegistration.getGroupId());

		return prRegistrationImpl;
	}

	/**
	 * Returns the p r registration with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the p r registration
	 * @return the p r registration
	 * @throws com.liferay.portal.NoSuchModelException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PRRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the p r registration with the primary key or throws a {@link com.inkwell.internet.productregistration.NoSuchRegistrationException} if it could not be found.
	 *
	 * @param registrationId the primary key of the p r registration
	 * @return the p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByPrimaryKey(long registrationId)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByPrimaryKey(registrationId);

		if (prRegistration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + registrationId);
			}

			throw new NoSuchRegistrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				registrationId);
		}

		return prRegistration;
	}

	/**
	 * Returns the p r registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the p r registration
	 * @return the p r registration, or <code>null</code> if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PRRegistration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the p r registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param registrationId the primary key of the p r registration
	 * @return the p r registration, or <code>null</code> if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByPrimaryKey(long registrationId)
		throws SystemException {
		PRRegistration prRegistration = (PRRegistration)EntityCacheUtil.getResult(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				PRRegistrationImpl.class, registrationId);

		if (prRegistration == _nullPRRegistration) {
			return null;
		}

		if (prRegistration == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				prRegistration = (PRRegistration)session.get(PRRegistrationImpl.class,
						Long.valueOf(registrationId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (prRegistration != null) {
					cacheResult(prRegistration);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PRRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						PRRegistrationImpl.class, registrationId,
						_nullPRRegistration);
				}

				closeSession(session);
			}
		}

		return prRegistration;
	}

	/**
	 * Returns all the p r registrations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<PRRegistration> list = (List<PRRegistration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PRRegistration prRegistration : list) {
				if ((groupId != prRegistration.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<PRRegistration>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByGroupId_First(groupId,
				orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PRRegistration> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		List<PRRegistration> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set where groupId = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] findByGroupId_PrevAndNext(long registrationId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, prRegistration,
					groupId, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = getByGroupId_PrevAndNext(session, prRegistration,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration getByGroupId_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByGroupId(long groupId, int start,
		int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(2);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<PRRegistration>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set of p r registrations that the user has permission to view where groupId = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] filterFindByGroupId_PrevAndNext(
		long registrationId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(registrationId, groupId,
				orderByComparator);
		}

		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, prRegistration,
					groupId, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = filterGetByGroupId_PrevAndNext(session, prRegistration,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration filterGetByGroupId_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @return the matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_RU(long groupId, long prUserId)
		throws SystemException {
		return findByG_RU(groupId, prUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations where groupId = &#63; and prUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_RU(long groupId, long prUserId,
		int start, int end) throws SystemException {
		return findByG_RU(groupId, prUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations where groupId = &#63; and prUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_RU(long groupId, long prUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_RU;
			finderArgs = new Object[] { groupId, prUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_RU;
			finderArgs = new Object[] {
					groupId, prUserId,
					
					start, end, orderByComparator
				};
		}

		List<PRRegistration> list = (List<PRRegistration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PRRegistration prRegistration : list) {
				if ((groupId != prRegistration.getGroupId()) ||
						(prUserId != prRegistration.getPrUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_RU_GROUPID_2);

			query.append(_FINDER_COLUMN_G_RU_PRUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(prUserId);

				list = (List<PRRegistration>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByG_RU_First(long groupId, long prUserId,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByG_RU_First(groupId, prUserId,
				orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", prUserId=");
		msg.append(prUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByG_RU_First(long groupId, long prUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PRRegistration> list = findByG_RU(groupId, prUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByG_RU_Last(long groupId, long prUserId,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByG_RU_Last(groupId, prUserId,
				orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", prUserId=");
		msg.append(prUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByG_RU_Last(long groupId, long prUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_RU(groupId, prUserId);

		List<PRRegistration> list = findByG_RU(groupId, prUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] findByG_RU_PrevAndNext(long registrationId,
		long groupId, long prUserId, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = getByG_RU_PrevAndNext(session, prRegistration, groupId,
					prUserId, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = getByG_RU_PrevAndNext(session, prRegistration, groupId,
					prUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration getByG_RU_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId, long prUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_RU_GROUPID_2);

		query.append(_FINDER_COLUMN_G_RU_PRUSERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(prUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations that the user has permission to view where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @return the matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_RU(long groupId, long prUserId)
		throws SystemException {
		return filterFindByG_RU(groupId, prUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations that the user has permission to view where groupId = &#63; and prUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_RU(long groupId, long prUserId,
		int start, int end) throws SystemException {
		return filterFindByG_RU(groupId, prUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations that the user has permissions to view where groupId = &#63; and prUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_RU(long groupId, long prUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_RU(groupId, prUserId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_RU_GROUPID_2);

		query.append(_FINDER_COLUMN_G_RU_PRUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(prUserId);

			return (List<PRRegistration>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set of p r registrations that the user has permission to view where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] filterFindByG_RU_PrevAndNext(long registrationId,
		long groupId, long prUserId, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_RU_PrevAndNext(registrationId, groupId, prUserId,
				orderByComparator);
		}

		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = filterGetByG_RU_PrevAndNext(session, prRegistration,
					groupId, prUserId, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = filterGetByG_RU_PrevAndNext(session, prRegistration,
					groupId, prUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration filterGetByG_RU_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId, long prUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_RU_GROUPID_2);

		query.append(_FINDER_COLUMN_G_RU_PRUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(prUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @return the matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_DP(long groupId, Date datePurchased)
		throws SystemException {
		return findByG_DP(groupId, datePurchased, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations where groupId = &#63; and datePurchased = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_DP(long groupId, Date datePurchased,
		int start, int end) throws SystemException {
		return findByG_DP(groupId, datePurchased, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations where groupId = &#63; and datePurchased = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_DP(long groupId, Date datePurchased,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_DP;
			finderArgs = new Object[] { groupId, datePurchased };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_DP;
			finderArgs = new Object[] {
					groupId, datePurchased,
					
					start, end, orderByComparator
				};
		}

		List<PRRegistration> list = (List<PRRegistration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PRRegistration prRegistration : list) {
				if ((groupId != prRegistration.getGroupId()) ||
						!Validator.equals(datePurchased,
							prRegistration.getDatePurchased())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_DP_GROUPID_2);

			if (datePurchased == null) {
				query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_1);
			}
			else {
				query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (datePurchased != null) {
					qPos.add(CalendarUtil.getTimestamp(datePurchased));
				}

				list = (List<PRRegistration>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByG_DP_First(long groupId, Date datePurchased,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByG_DP_First(groupId,
				datePurchased, orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", datePurchased=");
		msg.append(datePurchased);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByG_DP_First(long groupId, Date datePurchased,
		OrderByComparator orderByComparator) throws SystemException {
		List<PRRegistration> list = findByG_DP(groupId, datePurchased, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByG_DP_Last(long groupId, Date datePurchased,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByG_DP_Last(groupId,
				datePurchased, orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", datePurchased=");
		msg.append(datePurchased);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByG_DP_Last(long groupId, Date datePurchased,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_DP(groupId, datePurchased);

		List<PRRegistration> list = findByG_DP(groupId, datePurchased,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] findByG_DP_PrevAndNext(long registrationId,
		long groupId, Date datePurchased, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = getByG_DP_PrevAndNext(session, prRegistration, groupId,
					datePurchased, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = getByG_DP_PrevAndNext(session, prRegistration, groupId,
					datePurchased, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration getByG_DP_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId, Date datePurchased,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_DP_GROUPID_2);

		if (datePurchased == null) {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_1);
		}
		else {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (datePurchased != null) {
			qPos.add(CalendarUtil.getTimestamp(datePurchased));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations that the user has permission to view where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @return the matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_DP(long groupId,
		Date datePurchased) throws SystemException {
		return filterFindByG_DP(groupId, datePurchased, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations that the user has permission to view where groupId = &#63; and datePurchased = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_DP(long groupId,
		Date datePurchased, int start, int end) throws SystemException {
		return filterFindByG_DP(groupId, datePurchased, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations that the user has permissions to view where groupId = &#63; and datePurchased = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_DP(long groupId,
		Date datePurchased, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_DP(groupId, datePurchased, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_DP_GROUPID_2);

		if (datePurchased == null) {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_1);
		}
		else {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (datePurchased != null) {
				qPos.add(CalendarUtil.getTimestamp(datePurchased));
			}

			return (List<PRRegistration>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set of p r registrations that the user has permission to view where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] filterFindByG_DP_PrevAndNext(long registrationId,
		long groupId, Date datePurchased, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_DP_PrevAndNext(registrationId, groupId,
				datePurchased, orderByComparator);
		}

		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = filterGetByG_DP_PrevAndNext(session, prRegistration,
					groupId, datePurchased, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = filterGetByG_DP_PrevAndNext(session, prRegistration,
					groupId, datePurchased, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration filterGetByG_DP_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId, Date datePurchased,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_DP_GROUPID_2);

		if (datePurchased == null) {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_1);
		}
		else {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (datePurchased != null) {
			qPos.add(CalendarUtil.getTimestamp(datePurchased));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @return the matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_SN(long groupId, String serialNumber)
		throws SystemException {
		return findByG_SN(groupId, serialNumber, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations where groupId = &#63; and serialNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_SN(long groupId, String serialNumber,
		int start, int end) throws SystemException {
		return findByG_SN(groupId, serialNumber, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations where groupId = &#63; and serialNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findByG_SN(long groupId, String serialNumber,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_SN;
			finderArgs = new Object[] { groupId, serialNumber };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_SN;
			finderArgs = new Object[] {
					groupId, serialNumber,
					
					start, end, orderByComparator
				};
		}

		List<PRRegistration> list = (List<PRRegistration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PRRegistration prRegistration : list) {
				if ((groupId != prRegistration.getGroupId()) ||
						!Validator.equals(serialNumber,
							prRegistration.getSerialNumber())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

			if (serialNumber == null) {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_1);
			}
			else {
				if (serialNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (serialNumber != null) {
					qPos.add(serialNumber);
				}

				list = (List<PRRegistration>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByG_SN_First(long groupId, String serialNumber,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByG_SN_First(groupId,
				serialNumber, orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serialNumber=");
		msg.append(serialNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the first p r registration in the ordered set where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByG_SN_First(long groupId, String serialNumber,
		OrderByComparator orderByComparator) throws SystemException {
		List<PRRegistration> list = findByG_SN(groupId, serialNumber, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration findByG_SN_Last(long groupId, String serialNumber,
		OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = fetchByG_SN_Last(groupId, serialNumber,
				orderByComparator);

		if (prRegistration != null) {
			return prRegistration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serialNumber=");
		msg.append(serialNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRegistrationException(msg.toString());
	}

	/**
	 * Returns the last p r registration in the ordered set where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p r registration, or <code>null</code> if a matching p r registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration fetchByG_SN_Last(long groupId, String serialNumber,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_SN(groupId, serialNumber);

		List<PRRegistration> list = findByG_SN(groupId, serialNumber,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] findByG_SN_PrevAndNext(long registrationId,
		long groupId, String serialNumber, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = getByG_SN_PrevAndNext(session, prRegistration, groupId,
					serialNumber, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = getByG_SN_PrevAndNext(session, prRegistration, groupId,
					serialNumber, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration getByG_SN_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId, String serialNumber,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

		if (serialNumber == null) {
			query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_1);
		}
		else {
			if (serialNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (serialNumber != null) {
			qPos.add(serialNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations that the user has permission to view where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @return the matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_SN(long groupId,
		String serialNumber) throws SystemException {
		return filterFindByG_SN(groupId, serialNumber, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations that the user has permission to view where groupId = &#63; and serialNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_SN(long groupId,
		String serialNumber, int start, int end) throws SystemException {
		return filterFindByG_SN(groupId, serialNumber, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations that the user has permissions to view where groupId = &#63; and serialNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> filterFindByG_SN(long groupId,
		String serialNumber, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_SN(groupId, serialNumber, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

		if (serialNumber == null) {
			query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_1);
		}
		else {
			if (serialNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (serialNumber != null) {
				qPos.add(serialNumber);
			}

			return (List<PRRegistration>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the p r registrations before and after the current p r registration in the ordered set of p r registrations that the user has permission to view where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param registrationId the primary key of the current p r registration
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p r registration
	 * @throws com.inkwell.internet.productregistration.NoSuchRegistrationException if a p r registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PRRegistration[] filterFindByG_SN_PrevAndNext(long registrationId,
		long groupId, String serialNumber, OrderByComparator orderByComparator)
		throws NoSuchRegistrationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_SN_PrevAndNext(registrationId, groupId,
				serialNumber, orderByComparator);
		}

		PRRegistration prRegistration = findByPrimaryKey(registrationId);

		Session session = null;

		try {
			session = openSession();

			PRRegistration[] array = new PRRegistrationImpl[3];

			array[0] = filterGetByG_SN_PrevAndNext(session, prRegistration,
					groupId, serialNumber, orderByComparator, true);

			array[1] = prRegistration;

			array[2] = filterGetByG_SN_PrevAndNext(session, prRegistration,
					groupId, serialNumber, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PRRegistration filterGetByG_SN_PrevAndNext(Session session,
		PRRegistration prRegistration, long groupId, String serialNumber,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

		if (serialNumber == null) {
			query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_1);
		}
		else {
			if (serialNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, PRRegistrationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, PRRegistrationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (serialNumber != null) {
			qPos.add(serialNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PRRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p r registrations.
	 *
	 * @return the p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p r registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @return the range of p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the p r registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of p r registrations
	 * @param end the upper bound of the range of p r registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<PRRegistration> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<PRRegistration> list = (List<PRRegistration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PRREGISTRATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PRREGISTRATION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PRRegistration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PRRegistration>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the p r registrations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (PRRegistration prRegistration : findByGroupId(groupId)) {
			remove(prRegistration);
		}
	}

	/**
	 * Removes all the p r registrations where groupId = &#63; and prUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_RU(long groupId, long prUserId)
		throws SystemException {
		for (PRRegistration prRegistration : findByG_RU(groupId, prUserId)) {
			remove(prRegistration);
		}
	}

	/**
	 * Removes all the p r registrations where groupId = &#63; and datePurchased = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_DP(long groupId, Date datePurchased)
		throws SystemException {
		for (PRRegistration prRegistration : findByG_DP(groupId, datePurchased)) {
			remove(prRegistration);
		}
	}

	/**
	 * Removes all the p r registrations where groupId = &#63; and serialNumber = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_SN(long groupId, String serialNumber)
		throws SystemException {
		for (PRRegistration prRegistration : findByG_SN(groupId, serialNumber)) {
			remove(prRegistration);
		}
	}

	/**
	 * Removes all the p r registrations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PRRegistration prRegistration : findAll()) {
			remove(prRegistration);
		}
	}

	/**
	 * Returns the number of p r registrations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of p r registrations that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of p r registrations where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @return the number of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_RU(long groupId, long prUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, prUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_RU,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_RU_GROUPID_2);

			query.append(_FINDER_COLUMN_G_RU_PRUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(prUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_RU,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of p r registrations that the user has permission to view where groupId = &#63; and prUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param prUserId the pr user ID
	 * @return the number of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_RU(long groupId, long prUserId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_RU(groupId, prUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_RU_GROUPID_2);

		query.append(_FINDER_COLUMN_G_RU_PRUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(prUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of p r registrations where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @return the number of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_DP(long groupId, Date datePurchased)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, datePurchased };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_DP,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_DP_GROUPID_2);

			if (datePurchased == null) {
				query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_1);
			}
			else {
				query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (datePurchased != null) {
					qPos.add(CalendarUtil.getTimestamp(datePurchased));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_DP,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of p r registrations that the user has permission to view where groupId = &#63; and datePurchased = &#63;.
	 *
	 * @param groupId the group ID
	 * @param datePurchased the date purchased
	 * @return the number of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_DP(long groupId, Date datePurchased)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_DP(groupId, datePurchased);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_DP_GROUPID_2);

		if (datePurchased == null) {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_1);
		}
		else {
			query.append(_FINDER_COLUMN_G_DP_DATEPURCHASED_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (datePurchased != null) {
				qPos.add(CalendarUtil.getTimestamp(datePurchased));
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of p r registrations where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @return the number of matching p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_SN(long groupId, String serialNumber)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, serialNumber };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_SN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PRREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

			if (serialNumber == null) {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_1);
			}
			else {
				if (serialNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (serialNumber != null) {
					qPos.add(serialNumber);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_SN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of p r registrations that the user has permission to view where groupId = &#63; and serialNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serialNumber the serial number
	 * @return the number of matching p r registrations that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_SN(long groupId, String serialNumber)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_SN(groupId, serialNumber);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_PRREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_G_SN_GROUPID_2);

		if (serialNumber == null) {
			query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_1);
		}
		else {
			if (serialNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_SN_SERIALNUMBER_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				PRRegistration.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (serialNumber != null) {
				qPos.add(serialNumber);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of p r registrations.
	 *
	 * @return the number of p r registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PRREGISTRATION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the p r registration persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.inkwell.internet.productregistration.model.PRRegistration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PRRegistration>> listenersList = new ArrayList<ModelListener<PRRegistration>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PRRegistration>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PRRegistrationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = PRProductPersistence.class)
	protected PRProductPersistence prProductPersistence;
	@BeanReference(type = PRRegistrationPersistence.class)
	protected PRRegistrationPersistence prRegistrationPersistence;
	@BeanReference(type = PRUserPersistence.class)
	protected PRUserPersistence prUserPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PRREGISTRATION = "SELECT prRegistration FROM PRRegistration prRegistration";
	private static final String _SQL_SELECT_PRREGISTRATION_WHERE = "SELECT prRegistration FROM PRRegistration prRegistration WHERE ";
	private static final String _SQL_COUNT_PRREGISTRATION = "SELECT COUNT(prRegistration) FROM PRRegistration prRegistration";
	private static final String _SQL_COUNT_PRREGISTRATION_WHERE = "SELECT COUNT(prRegistration) FROM PRRegistration prRegistration WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "prRegistration.groupId = ?";
	private static final String _FINDER_COLUMN_G_RU_GROUPID_2 = "prRegistration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_RU_PRUSERID_2 = "prRegistration.prUserId = ?";
	private static final String _FINDER_COLUMN_G_DP_GROUPID_2 = "prRegistration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DP_DATEPURCHASED_1 = "prRegistration.datePurchased IS NULL";
	private static final String _FINDER_COLUMN_G_DP_DATEPURCHASED_2 = "prRegistration.datePurchased = ?";
	private static final String _FINDER_COLUMN_G_SN_GROUPID_2 = "prRegistration.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_SN_SERIALNUMBER_1 = "prRegistration.serialNumber IS NULL";
	private static final String _FINDER_COLUMN_G_SN_SERIALNUMBER_2 = "prRegistration.serialNumber = ?";
	private static final String _FINDER_COLUMN_G_SN_SERIALNUMBER_3 = "(prRegistration.serialNumber IS NULL OR prRegistration.serialNumber = ?)";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "prRegistration.registrationId";
	private static final String _FILTER_SQL_SELECT_PRREGISTRATION_WHERE = "SELECT DISTINCT {prRegistration.*} FROM PR_PRRegistration prRegistration WHERE ";
	private static final String _FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {PR_PRRegistration.*} FROM (SELECT DISTINCT prRegistration.registrationId FROM PR_PRRegistration prRegistration WHERE ";
	private static final String _FILTER_SQL_SELECT_PRREGISTRATION_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN PR_PRRegistration ON TEMP_TABLE.registrationId = PR_PRRegistration.registrationId";
	private static final String _FILTER_SQL_COUNT_PRREGISTRATION_WHERE = "SELECT COUNT(DISTINCT prRegistration.registrationId) AS COUNT_VALUE FROM PR_PRRegistration prRegistration WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "prRegistration";
	private static final String _FILTER_ENTITY_TABLE = "PR_PRRegistration";
	private static final String _ORDER_BY_ENTITY_ALIAS = "prRegistration.";
	private static final String _ORDER_BY_ENTITY_TABLE = "PR_PRRegistration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PRRegistration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PRRegistration exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PRRegistrationPersistenceImpl.class);
	private static PRRegistration _nullPRRegistration = new PRRegistrationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PRRegistration> toCacheModel() {
				return _nullPRRegistrationCacheModel;
			}
		};

	private static CacheModel<PRRegistration> _nullPRRegistrationCacheModel = new CacheModel<PRRegistration>() {
			public PRRegistration toEntityModel() {
				return _nullPRRegistration;
			}
		};
}