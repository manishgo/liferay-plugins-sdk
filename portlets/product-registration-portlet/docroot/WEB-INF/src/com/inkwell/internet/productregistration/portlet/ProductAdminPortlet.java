package com.inkwell.internet.productregistration.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.inkwell.internet.productregistration.model.PRProduct;
import com.inkwell.internet.productregistration.model.PRRegistration;
import com.inkwell.internet.productregistration.model.PRUser;
import com.inkwell.internet.productregistration.model.impl.PRRegistrationImpl;
import com.inkwell.internet.productregistration.model.impl.PRUserImpl;
import com.inkwell.internet.productregistration.service.PRProductLocalServiceUtil;
import com.inkwell.internet.productregistration.service.PRRegistrationLocalServiceUtil;
import com.inkwell.internet.productregistration.service.PRUserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class ProductAdminPortlet extends MVCPortlet {

	protected String displayRegistrationsJSP = "/admin/display_registrations.jsp";
	protected String viewRegistrationJSP = "/admin/view_registration.jsp";
	protected String editProductJSP = "/admin/edit_product.jsp";
	private static Log _log = LogFactory.getLog(ProductAdminPortlet.class);

	public void addProduct(ActionRequest request, ActionResponse response)
			throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		PRProduct product = ActionUtil.productFromRequest(request);
		ArrayList<String> errors = new ArrayList();

		if (ProdRegValidator.validateProduct(product, errors)) {
			PRProductLocalServiceUtil.addProduct(product,
					themeDisplay.getUserId());
			SessionMessages.add(request, "product-saved-successfully");

		} else {
			SessionErrors.add(request, "fields-required");

		}

	}

	public void editProduct(ActionRequest request, ActionResponse response)
			throws Exception {

		long productKey = ParamUtil.getLong(request, "resourcePrimKey");

		if (Validator.isNotNull(productKey)) {
			PRProduct product = PRProductLocalServiceUtil
					.getPRProduct(productKey);
			request.setAttribute("product", product);
			response.setRenderParameter("jspPage", editProductJSP);

		}

	}


	public void updateProduct(ActionRequest request, ActionResponse response)
			throws Exception {
		long productKey = ParamUtil.getLong(request, "resourcePrimKey");
		ArrayList<String> errors = new ArrayList	();
		if (Validator.isNotNull(productKey)) {
			PRProduct product = PRProductLocalServiceUtil
					.getPRProduct(productKey);
			PRProduct requestProduct = ActionUtil.productFromRequest(request);

			if (ProdRegValidator.validateProduct(requestProduct, errors)) {
				product.setProductName(requestProduct.getProductName());
				product.setSerialNumber(requestProduct.getSerialNumber());
				PRProductLocalServiceUtil.updatePRProduct(product);
				SessionMessages.add(request, "productUpdated");

			} else {
				for (String error : errors) {
					SessionErrors.add(request, error);

				}

			}

		} else {
			SessionErrors.add(request, "error-updating");
		}

	}

	public void deleteProduct(ActionRequest request, ActionResponse response)
			throws Exception {
		long productKey = ParamUtil.getLong(request, "resourcePrimKey");
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		if (Validator.isNotNull(productKey)) {
			PRProductLocalServiceUtil.deleteProduct(productKey, themeDisplay.getScopeGroupId());
			SessionMessages.add(request, "productDeleted");

		} else {
			SessionErrors.add(request, "error-deleting");

		}

	}


	public void editDisplayRegistrations(ActionRequest request,
			ActionResponse response) throws PortletException, IOException {
		List<PRRegistration> tempResults = Collections.EMPTY_LIST;

		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getLayout().getGroupId();
		try {
			tempResults = PRRegistrationLocalServiceUtil
					.getAllRegistrations(groupId);

		} catch (SystemException ex) {
			_log.debug(ex);

		}

		request.setAttribute("registrations", tempResults);

		response.setRenderParameter("jspPage", displayRegistrationsJSP);

	}

	/**
	 * Views a particular registraion.
	 * 
	 * @param request
	 * @param response
	 */
	public void viewRegistration(ActionRequest request, ActionResponse response) {
		String primaryKey = ParamUtil.getString(request, "resourcePrimKey");
		PRRegistration reg = new PRRegistrationImpl();
		PRUser user = new PRUserImpl();

		if (Validator.isNotNull(primaryKey)) {
			try {
				reg = PRRegistrationLocalServiceUtil.getPRRegistration(Long
						.parseLong(primaryKey));
				user = PRUserLocalServiceUtil.getPRUser(reg.getPrUserId());

			} catch (PortalException ex) {
				_log.debug(ex);

			} catch (SystemException ex) {
				_log.debug(ex);

			}

			request.setAttribute("registration", reg);
			request.setAttribute("regUser", user);
			response.setRenderParameter("jspPage", viewRegistrationJSP);
		}

	}
}
