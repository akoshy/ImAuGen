package com.file.reader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCodec implements Codec{

	public byte[] readFile(String imageFilePath) throws IOException {
		byte[] imageInByte;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(ImageIO.read(new File(imageFilePath)), "jpg", baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}
	
	public void writeFile(String sourcePath, String destinationPath, byte[] imageBytes) throws IOException	 {
		ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageBytes)),"jpg",new File(destinationPath));
	}
}
