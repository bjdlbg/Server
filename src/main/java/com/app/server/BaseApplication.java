package com.app.server;

import com.app.usb.ui.MainFrame;
import org.apache.catalina.core.ApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling()
@MapperScan("com.app.server.mapper")
public class BaseApplication {

	public static MainFrame mainFrame;
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				//new MainFrame().setVisible(true);
				mainFrame=new MainFrame();
			}
		});
		SpringApplication.run(BaseApplication.class, args);

	}
}
