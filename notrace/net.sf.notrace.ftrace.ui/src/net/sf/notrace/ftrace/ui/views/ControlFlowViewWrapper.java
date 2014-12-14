package net.sf.notrace.ftrace.ui.views;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;

public class ControlFlowViewWrapper extends ControlFlowView {

	
	private IEclipseContext _context;
	
	// ------------------------------------------------------------------------
    // Injections
    // ------------------------------------------------------------------------
    
   @Override
    public void init(IViewSite site) throws PartInitException {
       
  
       _context = (IEclipseContext) site.getService(IEclipseContext.class);
       
       assert _context != null;
       
       super.init(site);
       
       ContextInjectionFactory.inject(this, _context);
       
      System.out.println("ControlFlowViewWrapper.init ");
    }

	@Override
	public void dispose() {
		
		super.dispose();
		
		ContextInjectionFactory.uninject(this, _context);
		
	}
    
    
}
