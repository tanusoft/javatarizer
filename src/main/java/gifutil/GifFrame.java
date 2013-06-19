package gifutil;

import java.awt.image.BufferedImage;

/**
 * 
 * 
 * @author Riven {@link http://www.java-gaming.org/index.php?topic=24196.0}
 */
public class GifFrame {
	public static final String NONE = "none";
	public static final String DO_NOT_DISPOSE = "doNotDispose";
	public static final String RESTORE_TO_BGCOLOR = "restoreToBackgroundColor";
	public static final String RESTORE_TO_PREVIOUS = "restoreToPrevious";

	public final BufferedImage img;
	public final long delay; // in millis
	public final String disposalMethod;

	public GifFrame(final BufferedImage img, final long delay) {
		this(img, delay, NONE);
	}

	public GifFrame(final BufferedImage img, final long delay,
			final String disposalMethod) {
		this.img = img;
		this.delay = delay;
		this.disposalMethod = disposalMethod;
	}
}