package com.example.sping_portfolio.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageStorage {
    @Getter
    private List<ImageInfo> lii = new ArrayList<>();

    public ImageStorage() {
        //String web_server = "http://localhost:8080/";
        String web_server = "https://csa.nighthawkcodingsociety.com";

        String file0 = "/images/Mona_Lisa.png";
        lii.add(new ImageInfo(file0, web_server+file0, 12));
        lii.get(0).read_image();

        String file1 = "/images/ncs_logo.png";
        lii.add(new ImageInfo(file1, web_server+file1, 2));
        lii.get(1).read_image();
        /*
        String file2 = "/images/bulb_off.png";
        lii.add(new ImageInfo(file2, web_server+file2, 7));
        lii.get(2).read_image();
         */
    }
}
