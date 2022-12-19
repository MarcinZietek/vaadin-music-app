package pl.mz.vadin.music.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
public class VadinMusicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VadinMusicAppApplication.class, args);
	}

}
