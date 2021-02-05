package com.bundle.servlet;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

import com.bundle.servlet.servlet.ServletTestOne;

public class Activator implements BundleActivator, ServiceListener {

	private static BundleContext context;
	private HttpService httpService;

	// Servlet ����·��
	private String path1 = "/bundle/test/one";

	// Servlet ע��
	protected synchronized void setHttpService(HttpService httpService) {
		this.httpService = httpService;
		try {
			// ������Servlet�����Ӷ��ע�ἴ��
			httpService.registerServlet(this.path1, (Servlet) new ServletTestOne(context), null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Servlet ���ע��
	protected void unsetHttpService(HttpService httpService) {
		if (this.httpService != null) {
			this.httpService.unregister(this.path1);
		}
		this.httpService = null;
	}

	static BundleContext getContext() {
		return context;
	}

	// Bundle ״̬�仯
	@Override
	public void serviceChanged(ServiceEvent event) {
		if (event.getType() == 1) {
			ServiceReference reference = event.getServiceReference();
			Object service = context.getService(reference);
			if (service instanceof HttpService && this.httpService == null) {
				this.httpService = (HttpService) service;
				setHttpService(this.httpService);
			}
		}
	}

	// Bundle ����
	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		context.addServiceListener(this);
		ServiceReference serviceRef = context.getServiceReference(HttpService.class.getName());
		if (serviceRef != null) {
			this.httpService = (HttpService) context.getService(serviceRef);
			setHttpService(this.httpService);
		}
	}

	// Bundle ֹͣ
	public void stop(BundleContext bundleContext) throws Exception {
		unsetHttpService(this.httpService);
		bundleContext.removeServiceListener(this);
		context = null;
	}

}
