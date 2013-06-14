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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JavatarizerCLI {

	public void readImage(final String imagePath) {
		try {
			// the line that reads the image file
			final BufferedImage image = ImageIO.read(new File(imagePath));

			System.out.println("Successfully opened image file " + imagePath
					+ " (" + image.getWidth() + "x" + image.getHeight() + ")");

		} catch (final IOException e) {
			throw new RuntimeException("Unable to read image file " + imagePath);
		}

	}

	/**
	 * Command-Line Interface for Javatarizer
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final JavatarizerCLI javatarizerCLI = new JavatarizerCLI();

		try {
			javatarizerCLI.readImage(args[0]);
		} catch (final Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
