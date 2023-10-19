package com.example.demo;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PhotoReadService {
    public Map<String, Double> readExifAndReturnGPS(String fileScope) throws Exception {
        String pdsLat = "";
        String pdsLon = "";

        File file = new File(fileScope);

        Metadata metadata = ImageMetadataReader.readMetadata(file);
        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        Map<String, Double> map = new HashMap<>();

        if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {

            pdsLat = String.valueOf(gpsDirectory.getGeoLocation().getLatitude());
            pdsLon = String.valueOf(gpsDirectory.getGeoLocation().getLongitude());

            double lat = Double.parseDouble(pdsLat);    //위도
            double lon = Double.parseDouble(pdsLon);    //경도

            map.put("lat", lat);
            map.put("lon", lon);

            log.info("lat: " + lat);
            log.info("lon: " + lon);
        }

        return map;
    }

    public Map<String, Double> readExifAndReturnGPS(MultipartFile multipartFile) throws Exception {

        String pdsLat = "";
        String pdsLon = "";

        Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());
        GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

        Map<String, Double> map = new HashMap<>();

        if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {

            pdsLat = String.valueOf(gpsDirectory.getGeoLocation().getLatitude());
            pdsLon = String.valueOf(gpsDirectory.getGeoLocation().getLongitude());

            double lat = Double.parseDouble(pdsLat);    //위도
            double lon = Double.parseDouble(pdsLon);    //경도

            map.put("lat", lat);
            map.put("lon", lon);

            log.info("lat: " + lat);
            log.info("lon: " + lon);
        }

        return map;
    }
}
