/**
 * The MIT License (MIT)
 * Copyright (c) 2013 TanuSoft
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.tanusoft.javatarizer;

import gifutil.GifFrame;
import gifutil.GifUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class JavatarizerCLI {

	public Image readImage(final String imagePath) {
		final File imageFile = new File(imagePath);
		try {
			return new Image(imageFile);
		} catch (final IOException e) {
			// getCanonicalPath() unusable here: it may throw an IOException
			final String absolutePath = imageFile.getAbsolutePath();
			throw new RuntimeException("Unable to read image file "
					+ absolutePath);
		}
	}

	/**
	 * @param image
	 * @param path
	 * @param frameId
	 * @throws IOException
	 */
	private void writeImage(final Image image, final String path,
			final int frameId) throws IOException {
		final String[] pathFragments = path.split("\\.");
		if (pathFragments.length != 2) {
			throw new IllegalStateException(Arrays.toString(pathFragments));
		}

		final String savePath = pathFragments[0] + "_" + frameId
				+ "_square.gif";

		final File f = new File(savePath);
		ImageIO.write(image.getImage(), "GIF", f);
	}

	/**
	 * Command-Line Interface for Javatarizer
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final JavatarizerCLI javatarizerCLI = new JavatarizerCLI();

		try {
			final String imagePath = args[0];
			final Image sourceImage = javatarizerCLI.readImage(imagePath);

			final String[] pathFragments = imagePath.split("\\.");
			if (pathFragments.length != 2) {
				throw new IllegalStateException(Arrays.toString(pathFragments));
			}

			final String savePath = pathFragments[0] + "_square.gif";

			final List<GifFrame> gifFrames = new ArrayList<GifFrame>();

			final int nbFrames = 31;
			for (int i = 0; i < nbFrames; i++) {
				final double position = 1. - i / (nbFrames + 1.);
				final Image squareImage = sourceImage
						.extractSquareImage(position);

				final int transparantColor = 0xFFFFFF; // white
				final BufferedImage gif = GifUtil.convertRGBAToGIF(
						squareImage.getImage(), transparantColor);

				// every frame takes ... ms
				final long delay = 2000 / nbFrames;

				// make transparent pixels not 'shine through'
				final String disposal = GifFrame.RESTORE_TO_BGCOLOR;

				// add frame to sequence, twice
				final GifFrame frame = new GifFrame(gif, delay, disposal);
				gifFrames.add(frame);
				gifFrames.add(0, frame);

				System.out.println("Extracted square image " + i + " "
						+ squareImage.toString());

				// javatarizerCLI.writeImage(squareFrame, imagePath, i);
			}

			final OutputStream gifOS = new FileOutputStream(savePath);

			final int loopCount = 0; // loop indefinitely
			GifUtil.saveAnimatedGIF(gifOS, gifFrames, loopCount);

		} catch (final Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
