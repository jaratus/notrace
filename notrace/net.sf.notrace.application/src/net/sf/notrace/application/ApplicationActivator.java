package net.sf.notrace.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ApplicationActivator implements BundleActivator {

public static final String PLUGIN_ID = "net.sf.notrace.application";
	
	private static BundleContext context;
	
	public static BundleContext getContext() {
		return context;
	}
	
	//ServiceRegistration<StartupMonitor> monitorRegistration = null;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ApplicationActivator.context = bundleContext;
		/*
		monitorRegistration = context.registerService(StartupMonitor.class, new StartupMonitor() {
			@Override
			public void update() {
				logger.info("StartUp Monitor: update()");
			}
			@Override
			public void applicationRunning() {
				logger.info("StartUp Monitor: applicationRunning()");
			}
		}, null);
		*/
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		/*
		if (monitorRegistration != null) {
			monitorRegistration.unregister();
			monitorRegistration = null;
		}
		*/
		ApplicationActivator.context = null;
	}

}
