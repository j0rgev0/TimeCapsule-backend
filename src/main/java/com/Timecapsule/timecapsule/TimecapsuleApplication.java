package com.Timecapsule.timecapsule;
import com.Timecapsule.timecapsule.config.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class TimecapsuleApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TimecapsuleApplication.class, args);



		CloudinaryConfig cloudinaryConfig = new CloudinaryConfig();
		Cloudinary cloudinary = cloudinaryConfig.cloudinary();

		Map params1 = ObjectUtils.asMap(
				"use_filename", true,
				"unique_filename", false,
				"overwrite", true
		);

		cloudinary.uploader().upload("https://cloudinary-devs.github.io/cld-docs-assets/assets/images/coffee_cup.jpg", params1);
	}
}
