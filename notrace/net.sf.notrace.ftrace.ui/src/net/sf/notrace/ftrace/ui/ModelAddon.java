package net.sf.notrace.ftrace.ui;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ModelAddon {

	@PostConstruct
	public void init(IEclipseContext context, IExtensionRegistry reg) {
		
		assert context != null;
		
		System.out.println("net.sf.milkfish.systrace.android.ui.ModelAddon : init");
	}
	
	@PreDestroy
	public void dispose(){
		
	}
}
