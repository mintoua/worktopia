package com.logonedigital.worktopia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
	@EnableJpaAuditing
	@EnableAsync
	public class WorktopiaApp {

    public static void main(String[] args) {
			try {
				var context = SpringApplication.run(com.logonedigital.worktopia.WorktopiaApp.class, args);
				var env = context.getEnvironment();
				Integer serverPort = Integer.valueOf(env.getProperty("server.port", "8081"));
				log.info("################################################");
				log.info("APPLICATION SUCCESSFULLY STARTED ON PORT: {}", serverPort);
				log.info("################################################");
			} catch (Exception e) {
				log.error("Application failed to start", e);
			}
		}


   }
