package com.thoughtworks.portlet;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.PortletMode;
import org.apache.commons.logging.Log;

public class HelloYouPortlet extends  GenericPortlet {

	protected String editJSP;
	protected String viewJSP;
	private static Log _log = LogFactory.getLog(HelloYouPortlet.class);

	public void init() throws PortletException {
		editJSP = getInitParameter("edit-template");
		viewJSP = getInitParameter("view-template");
	}

	public void doView(RenderRequest renderRequest, 
			RenderResponse renderResponse) throws IOException,
			PortletException {
		PortletPreferences prefs = renderRequest.getPreferences();
		if (username.equalsIgnoreCase("no")) {
		renderRequest.setAttribute("userName", username);
	}

	protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse)
	}

	public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse)

	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		String addName = actionRequest.getParameter("addName");
		if (addName != null) {
			prefs.setValue("name", actionRequest.getParameter("username"));
			prefs.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
		}
	}		
}
 