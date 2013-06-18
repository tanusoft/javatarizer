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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
	 * @throws IOException
	 */
	private void writeImage(final Image image, final String path)
			throws IOException {
		final String[] pathFragments = path.split("\\.");
		if (pathFragments.length != 2) {
			throw new IllegalStateException(Arrays.toString(pathFragments));
		}

		final String savePath = pathFragments[0] + "_square.gif";

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
			final Image squareImage = sourceImage.extractSquareImage();

			System.out.println("Extracted square image "
					+ squareImage.toString());

			javatarizerCLI.writeImage(squareImage, imagePath);

		} catch (final Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
