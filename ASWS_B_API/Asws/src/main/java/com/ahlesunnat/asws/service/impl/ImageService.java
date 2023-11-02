package com.ahlesunnat.asws.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;


@Service
public class ImageService {

    public byte[] generateThumbnail(InputStream imageInputStream, int maxWidth, int maxHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageInputStream);

        // Calculate new dimensions for the thumbnail while preserving the aspect ratio
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        double scale = Math.min((double) maxWidth / width, (double) maxHeight / height);
        int newWidth = (int) (width * scale);
        int newHeight = (int) (height * scale);

        // Create a new thumbnail image
        BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        thumbnail.getGraphics().drawImage(originalImage, 0, 0, newWidth, newHeight, null);

        // Convert the thumbnail to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "PNG", baos);
        return baos.toByteArray();
    }

}
