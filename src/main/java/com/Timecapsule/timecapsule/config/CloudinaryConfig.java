package com.Timecapsule.timecapsule.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {

        Dotenv dotenv = Dotenv.load();

        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        System.out.println("Cloudinary config: " + cloudinary.config.cloudName);
        return cloudinary;
    }
}
