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
	}
}
