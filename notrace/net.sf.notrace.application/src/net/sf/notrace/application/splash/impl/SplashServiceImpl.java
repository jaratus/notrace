package net.sf.notrace.application.splash.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.notrace.application.splash.ISplashService;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.BundleException;
import org.osgi.service.component.ComponentContext;

public class SplashServiceImpl implements ISplashService {

	public static ImageDescriptor getImageDescriptor(String pluginId,
			String path) {
		try {
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			URL url = new URL("platform:/plugin/" + pluginId + path);
			url = FileLocator.resolve(url);
			return ImageDescriptor.createFromURL(url);
		} catch (MalformedURLException e) {
			String msg = NLS
					.bind("The image path {0} in not a valid location in the bundle {1}.",
							path, pluginId);
			throw new RuntimeException(msg, e);
		} catch (IOException e) {
			String msg = NLS.bind(
					"The image {0} was not found in the bundle {1}.", path,
					pluginId);
			throw new RuntimeException(msg, e);
		}
	}

	public static Point getMonitorCenter(Shell shell) {
		Monitor primary = shell.getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		return new Point(x, y);
	}

	void activate(ComponentContext context) {
	}

	void deactivate(ComponentContext context) {
	}

	private String pluginId = null;
	private String splashPath = "splash.bmp";
	private Rectangle textRect = null;
	private Color textColor = null;
	private Font textFont = null;
	private Rectangle progressRect = null;
	private int totalWork = 0;

	private Shell splashShell = null;
	private Label textLabel = null;
	private ProgressBar progressBar = null;
	private String nextMessage = null;
	private int progress = 0;

	@Override
	public void setSplashPluginId(String pluginId) {
		Assert.isLegal(pluginId != null && !pluginId.equals(""));
		this.pluginId = pluginId;
	}

	@Override
	public void setSplashImagePath(String splashPath) {
		Assert.isLegal(splashPath != null && !splashPath.equals(""));
		this.splashPath = splashPath;
	}

	@Override
	public void setTextBounds(Rectangle rect) {
		Assert.isLegal(rect != null);
		this.textRect = rect;
		if (textLabel != null)
			textLabel.setBounds(rect);
	}

	@Override
	public void setTextColor(Color color) {
		Assert.isLegal(color != null);
		this.textColor = color;
		if (textLabel != null)
			textLabel.setForeground(color);
	}

	@Override
	public void setTextFont(Font font) {
		Assert.isLegal(font != null);
		this.textFont = font;
		if (textLabel != null)
			textLabel.setFont(font);
	}

	@Override
	public void setProgressBarBounds(Rectangle rect) {
		Assert.isLegal(rect != null);
		this.progressRect = rect;
		if (progressBar != null)
			progressBar.setBounds(rect);
	}

	@Override
	public void setTotalWork(int totalWork) {
		this.totalWork = totalWork;
		if (progressBar != null) {
			progressBar.setMaximum(totalWork);
		}
	}

	@Override
	public void open() {
		if (pluginId == null)
			throw new IllegalStateException(
					"The SplashPluginId has not been set.");
		if (splashPath == null)
			throw new IllegalStateException(
					"The SplashImagePath has not been set.");

		splashShell = createSplashShell();
		splashShell.open();
	}

	private Shell createSplashShell() {
		final Shell shell = new Shell(SWT.TOOL | SWT.NO_TRIM);
		Image image = createBackgroundImage(shell);
		shell.setBackgroundImage(image);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		Rectangle imageBounds = image.getBounds();

		/*
		 * final GridLayout layout = new GridLayout(); layout.numColumns = 1;
		 * layout.marginHeight = 40; layout.marginWidth = 20;
		 * layout.verticalSpacing = 6; layout.horizontalSpacing = 6;
		 * shell.setLayout(layout);
		 */
		textLabel = createTextLabel(shell);
		if (textRect == null) {
			textRect = new Rectangle(20, imageBounds.height - 60,
					imageBounds.width - 40, 40);
		}
		textLabel.setBounds(textRect);

		if (totalWork != 0) {
			progressBar = createProgressBar(shell);
			if (progressRect == null) {
				progressRect = new Rectangle(0, imageBounds.height - 14
						- progressBar.getBorderWidth(), imageBounds.width
						- progressBar.getBorderWidth(), 14);
			}
			progressBar.setBounds(progressRect);
		}

		shell.setSize(imageBounds.width, imageBounds.height);
		shell.setLocation(getMonitorCenter(shell));
		return shell;
	}

	private Image createBackgroundImage(Shell parent) {
		final Image splashImage = getImageDescriptor(pluginId, splashPath)
				.createImage();
		parent.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				splashImage.dispose();
			}
		});
		return splashImage;
	}

	private Label createTextLabel(Composite parent) {
		Label label = new Label(parent, SWT.WRAP);
		/*
		 * GridData gd = new GridData(); gd.horizontalAlignment = SWT.FILL;
		 * gd.verticalAlignment = SWT.BOTTOM; gd.grabExcessHorizontalSpace =
		 * true; gd.grabExcessVerticalSpace = true; label.setLayoutData(gd);
		 */
		if (textColor == null) {
			textColor = parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		}
		label.setForeground(textColor);
		if (textFont == null) {
			textFont = parent.getDisplay().getSystemFont();
		}
		label.setFont(textFont);

		if (nextMessage != null) {
			label.setText(nextMessage);
		}
		return label;
	}

	private ProgressBar createProgressBar(Shell shell) {
		int style = SWT.BORDER | SWT.SMOOTH | SWT.HORIZONTAL;
		if (totalWork < 0) {
			// FIXME This does not work ... could be an issue with the UI thread
			// being busy in the background, so the progress bar has no chance
			// to update in indeterminate mode
			style |= SWT.INDETERMINATE;
		}
		ProgressBar pb = new ProgressBar(shell, style);
		if (totalWork > 0) {
			pb.setMinimum(0);
			pb.setMaximum(totalWork);
		}
		return pb;
	}

	@Override
	public void close() {
		splashShell.close();
		splashShell = null;
		textLabel = null;
		textRect = null;
		textColor = null;
		textFont = null;
		progressBar = null;
		progressRect = null;
	}

	@Override
	public void setMessage(final String message) {
		if (textLabel != null && !textLabel.isDisposed()) {
			splashShell.getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					textLabel.setText(message);
					// splashShell.layout();
					splashShell.update();
				}
			});
		} else {
			nextMessage = message;
		}
	}

	@Override
	public void worked(int worked) {
		if (progressBar != null && !progressBar.isDisposed()) {
			progress += worked;
			splashShell.getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					progressBar.setSelection(progress);
					splashShell.update();
				}
			});
		}
	}

}
