package net.sf.notrace.application.splash;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;

public interface ISplashService {
	/**
	 * Tell the Service where to find the splash image
	 * @param pluginId ID of the plug-in where the image resides
	 */
	public void setSplashPluginId(String pluginId);
	
	/**
	 * Tell the service the path and name of the splash image
	 * @param path Path and filename of the splash image
	 */
	public void setSplashImagePath(String path);
	
	/**
	 * Set the position and size of the text field.
	 * @param rec Text Bounds (Default positions the Text at the bottom of the image)
	 */
	public void setTextBounds(Rectangle rec);
	
	/**
	 * Set the foreground color of the text
	 * @param color Default: White
	 */
	public void setTextColor(Color color);
	
	/**
	 * Set the font for the text
	 * @param font Default: System-Font
	 */
	public void setTextFont(Font font);
	
	/**
	 * Set the position and size of the progress bar.
	 * @param rec Default: The progress bar is positioned below the image
	 */
	public void setProgressBarBounds(Rectangle rec);
	
	/**
	 * Set the total amount of work to be done.
	 * 0: disables the progress bar
	 * -1: shows an INDETERMINATE running bar 
	 * @param totalWork total amount of work to be done
	 */
	public void setTotalWork(int totalWork);
	
	/**
	 * Notifies that a given number of work unit of the main task
	 * has been completed. Note that this amount represents an
	 * installment, as opposed to a cumulative amount of work done
	 * to date.
	 * @param worked a non-negative number of work units just completed
	 */
	public void worked(int worked);
	
	/**
	 * Open the splash screen
	 */
	public void open();
	
	/**
	 * Close the splash screen (also stops the service)
	 */
	public void close();
	
	/**
	 * Set the displayed message on the splash screen
	 * @param message text message to be displayed.
	 */
	public void setMessage(String message);
}
