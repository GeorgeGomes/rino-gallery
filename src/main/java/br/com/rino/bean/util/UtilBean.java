package br.com.rino.bean.util;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.rino.util.JSFUtil;

@ManagedBean(name = "utilBean")
public class UtilBean {

	public void redirect(String url) {
		try {
			JSFUtil.redirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initAppAlone(String url) {
		try {
			JSFUtil.initAppAlone(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getSessionObj(String id) {
		return JSFUtil.getSessionObj(id);
	}

	public String getRequestURL() {
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURL().toString();
		} else {
			return "";
		}
	}

	public String getFullURL() {
		Enumeration<String> lParameters;
		String sParameter;
		StringBuilder sbURL = new StringBuilder();
		Object oRequest = FacesContext.getCurrentInstance().getExternalContext().getRequest();

		try {
			if (oRequest instanceof HttpServletRequest) {
				sbURL.append(((HttpServletRequest) oRequest).getRequestURL().toString());

				lParameters = ((HttpServletRequest) oRequest).getParameterNames();

				if (lParameters.hasMoreElements()) {
					if (!sbURL.toString().contains("?")) {
						sbURL.append("?");
					} else {
						sbURL.append("&");
					}
				}

				while (lParameters.hasMoreElements()) {
					sParameter = lParameters.nextElement();

					sbURL.append(sParameter);
					sbURL.append("=");
					sbURL.append(URLEncoder.encode(((HttpServletRequest) oRequest).getParameter(sParameter), "UTF-8"));

					if (lParameters.hasMoreElements()) {
						sbURL.append("&");
					}
				}
			}
		} catch (Exception e) {
			// Do nothing
		}

		return sbURL.toString();
	}
}
