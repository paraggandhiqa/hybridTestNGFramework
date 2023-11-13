package com.tutorialninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties properties = new Properties();
	public Properties dataProperties = new Properties();

	// constructor to load properties into child class
	public Base() {

		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialninja\\config\\config.properties");
		File dataPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\testdata\\testdata.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			try {
				properties.load(fis);
				dataProperties.load(dataFis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//	if we make static method and call in test
//	public void loadPropertiesFile() {
//
//		File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialninja\\utils");
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			try {
//				properties.load(fis);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public WebDriver intializeBrowserAndOpenApplication(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICITE_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(properties.getProperty("url"));

		return driver;

	}

}
