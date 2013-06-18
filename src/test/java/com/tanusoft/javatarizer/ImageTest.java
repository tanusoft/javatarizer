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

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit-test class for {@link Image}.
 * 
 * @author Christophe Franco
 * 
 */
public class ImageTest {

	private final int imageType = BufferedImage.TYPE_3BYTE_BGR;
	private int height;
	private int width;
	private Image image;
	private Image squareImage;

	@Before
	public void prepare() {
		this.width = 200;
		this.height = 100;

		final BufferedImage bufferedImage = new BufferedImage(this.width,
				this.height, this.imageType);

		this.image = new Image(bufferedImage);

		this.squareImage = this.image.extractSquareImage();
	}

	@Test
	public void widthTest() {
		assertEquals("width", this.width, this.image.getWidth());
	}

	@Test
	public void heightTest() {
		assertEquals("height", this.height, this.image.getHeight());
	}

	@Test
	public void squareImageWidthTest() {
		assertEquals("width", Math.min(this.width, this.height),
				this.squareImage.getWidth());
	}

	@Test
	public void squareImageHeightTest() {
		assertEquals("height", Math.min(this.width, this.height),
				this.squareImage.getHeight());
	}

	// TODO : square image from square image
}