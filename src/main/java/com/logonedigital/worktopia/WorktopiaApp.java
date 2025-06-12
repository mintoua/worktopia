package com.logonedigital.worktopia;

import com.logonedigital.worktopia.user.Role;
import com.logonedigital.worktopia.user.User;
import com.logonedigital.worktopia.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class WorktopiaApp {

    private static final Logger log = LoggerFactory.getLogger(WorktopiaApp.class);

	public static void main(String[] args) {
		try {
			var context = SpringApplication.run(WorktopiaApp.class, args);
			var env = context.getEnvironment();
            Integer serverPort = Integer.valueOf(env.getProperty("server.port", "8081"));
			log.info("################################################");
			log.info("APPLICATION SUCCESSFULLY STARTED ON PORT: {}", serverPort);
			log.info("################################################");
		} catch (Exception e) {
			log.error("Application failed to start", e);
		}
	}

	@Bean
	CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			String email = "admin@worktopia.com";
			String rawPassword = "admin@123";
			String encodedPassword = passwordEncoder.encode(rawPassword);
			if(userRepository.findByEmail(email).isEmpty()) {
				User user = new User();
				user.setFirstname("Worktopia ADMIN");
				user.setEmail(email);
				user.setPassword(encodedPassword);
				user.setEnabled(true);
				user.setAccountLocked(false);
				user.setRole(Role.ADMIN);
				userRepository.save(user);
				System.out.println("Admin User created successfully");
			}
			else {
				System.out.println("Admin User already exists");
			}
		};

	}



}
