package com.rithik;


import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sun.deploy.util.SessionState;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.Properties;

public class PhotoOrganizer {

    public ClientPhoto getClientPhoto(Metadata metaData) {
        ClientPhoto clientPhoto = new ClientPhoto();
        for(Directory dir : metaData.getDirectories() ) {
            for(Tag tag : dir.getTags()) {
             if (tag.getTagName().equalsIgnoreCase("Aperture Value")) {
                 clientPhoto.setAperture( tag.getDescription() );
             }
            }
        }
        return clientPhoto;

    }


    public static void main(String[] args) throws Exception{
	// write your code here
        if (args.length <= 0) {
            System.err.println("Usage: java com.rithik.PhotoOrganizer <config-property-file-path>");
            System.exit(0);
        }

        String configFileName = args[0]; // first argument is the config file location

        Properties properties = new Properties();
        properties.load(new FileReader(configFileName)); // load all the properties into property object


        ClientInformation clientInformation = new ClientInformation();
        clientInformation.setClientName( properties.getProperty("client.name"));
        clientInformation.setClientEmail( properties.getProperty("client.email"));
        clientInformation.setClientPhone(properties.getProperty("client.phone"));

        // create ClientAddress similarly

        // Open the directory
        // read the EXIF data for one file at a time
        // from EXIT data create ClientCamera, ClientPhotos, ClientLens
        // then insert all the objects into database

        Metadata metadata = ImageMetadataReader.readMetadata(new FileInputStream("D:\\home\\rithik\\photo\\samples\\28mm-F11-ISO100-SS143-flash-off-3562.jpg"));


        }
     }
}