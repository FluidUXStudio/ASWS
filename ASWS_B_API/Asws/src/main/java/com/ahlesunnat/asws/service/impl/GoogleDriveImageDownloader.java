package com.ahlesunnat.asws.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class GoogleDriveImageDownloader {
    public  byte[] downloadImageBytesFromGoogleDrive(String googleDriveURL) {
        try {
            // Define a regular expression pattern to match the file ID
            Pattern pattern = Pattern.compile("id=([\\w-]+)");
            Matcher matcher = pattern.matcher(googleDriveURL);

            // Find the file ID in the URL
            if (matcher.find()) {
                String fileId = matcher.group(1);
                String fileUrl = "https://drive.google.com/uc?id=" + fileId;

                URL url = new URL(fileUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set request method to GET
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                ReadableByteChannel rbc = Channels.newChannel(inputStream);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = rbc.read(ByteBuffer.wrap(buffer))) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                rbc.close();
                inputStream.close();

                return byteArrayOutputStream.toByteArray();
            } else {
                System.out.println("File ID not found in the URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveImageToFile(byte[] imageBytes, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageBytes);
            System.out.println("Image saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    //     String googleDriveURL = "https://drive.google.com/open?id=12aTfFl59ROdOOmRzXqw2ouqc1gQyJoj3";
    //     byte[] imageBytes = downloadImageBytesFromGoogleDrive(googleDriveURL);

    //     if (imageBytes != null) {
    //         // Use the imageBytes for further processing or saving to a file
    //         System.out.println("Image downloaded successfully. Image size: " + imageBytes.length + " bytes");
    //         saveImageToFile(imageBytes, "download.jpg");
    //     } else {
    //         System.out.println("Failed to download image.");
    //     }
    // }
}
