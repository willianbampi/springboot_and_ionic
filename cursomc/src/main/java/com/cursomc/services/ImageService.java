package com.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cursomc.services.exceptions.FileException;

@Service
public class ImageService {
	
	public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if(!extension.equals("png") && !extension.equals("jpg")) {
			throw new FileException("Only PNG and JPG extensions are allowed.");
		}
		try {
			BufferedImage img = ImageIO.read(multipartFile.getInputStream());
			if(extension.equals("png")) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Error on read file.");
		}
		
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage image, String extension) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(image, extension, output);
			return new ByteArrayInputStream(output.toByteArray());
		} catch (IOException e) {
			throw new FileException("Error on read file.");
		}
	}
	
}