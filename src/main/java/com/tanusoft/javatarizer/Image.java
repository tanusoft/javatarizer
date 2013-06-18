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

/**
 * An image.
 * 
 * @author Christophe Franco
 */
public class Image {

	private final BufferedImage image;

	public Image(final BufferedImage image) {
		this.image = image;
	}

	/**
	 * Creates an image from a {@link File}.
	 * 
	 * @param imageFile
	 *            the file
	 * @throws IOException
	 */
	public Image(final File imageFile) throws IOException {
		this(ImageIO.read(imageFile));

		final String canonicalPath = imageFile.getCanonicalPath();

		System.out.println("Successfully opened image file " + canonicalPath
				+ this);

	}

	@Override
	public String toString() {
		return " Image (" + this.getWidth() + "x" + this.getHeight()
				+ ") Type=" + this.image.getType();
	}

	public int getHeight() {
		return this.image.getHeight();
	}

	public int getWidth() {
		return this.image.getWidth();
	}

	/**
	 * @return
	 */
	public Image extractSquareImage() {
		final int height = this.getHeight();
		final int width = this.getWidth();

		final int squareSize = Math.min(height, width);

		final int x, y;
		if (height < width) {
			x = (width - height) / 2;
			y = 0;
		} else {
			x = 0;
			y = (height - width) / 2;
		}

		final BufferedImage squareImage = this.image.getSubimage(x, y,
				squareSize, squareSize);

		return new Image(squareImage);
	}

	public BufferedImage getImage() {
		return this.image;
	}

}