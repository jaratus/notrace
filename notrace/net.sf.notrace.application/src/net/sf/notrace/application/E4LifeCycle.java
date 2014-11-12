/*******************************************************************************
 * Copyright (c) 2014 TwelveTone LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Steven Spungin <steven@spungin.tv> - initial API and implementation
 *******************************************************************************/
package net.sf.notrace.application;

import net.sf.notrace.application.splash.ISplashService;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * This is a stub implementation containing e4 LifeCycle annotated methods.<br />
 * There is a corresponding entry in <em>plugin.xml</em> (under the
 * <em>org.eclipse.core.runtime.products' extension point</em>) that references
 * this class.
 **/
@SuppressWarnings("restriction")
public class E4LifeCycle {

	@PostContextCreate
	void postContextCreate(final ISplashService splashService, final IEventBroker eventBroker, IApplicationContext context, IEclipseContext workbenchContext) {
		
		splashService.setSplashPluginId(ApplicationActivator.PLUGIN_ID);
		
		/* 
		 * Splash image from http://www.deviantart.com/art/Eclipse-simple-splash-screen-155597715
		 * By http://nicoulaj.deviantart.com/
		 */
		splashService.setSplashImagePath("icons/splash.bmp");
		splashService.open();
		splashService.setMessage("Starting Applikation ...");
		
		// The should be a better way to close the Splash
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=376821
		eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				splashService.close();
				eventBroker.unsubscribe(this);
				System.out.println("E4LifeCycle postContextCreate");
			}
		});
		
		// close static splash screen
		context.applicationRunning();
	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
	}

	@ProcessAdditions
	void processAdditions(IEclipseContext workbenchContext) {
	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
	}
}
