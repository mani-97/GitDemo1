package demositepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DSRegisterPage {
	public static WebDriver driver;
	public DSRegisterPage(WebDriver driver1) {}
	
@FindBy(xpath = "//input[@placeholder='First Name']")
public static WebElement firstName;
@FindBy(xpath="//input[@placeholder='Last Name']")
public static WebElement lastName;
@FindBy(xpath = "//div[@class='col-md-8 col-xs-8 col-sm-8']")
public static WebElement address;
@FindBy(xpath = "//div[@id='eid']")
public static WebElement email;
@FindBy(xpath = "//form[@id='basicBootstrapForm']/child::div[4]/div")
public static WebElement phoneNumber;
@FindBy(xpath = "//div[@class='form-group'][5]//input[@value='Male']")
public static WebElement gender;
@FindBy(xpath = "//input[@id='checkbox2']")
public static WebElement hobbies;
public WebElement selectLanguage(String language) {
	WebElement languageKnown=driver.findElement(By.xpath("//div[@id='msdd']/following::div/ul/li/a[text()='"+language+"']"));
	return languageKnown;
}
public  WebElement selectSkill(String skill) {
	WebElement neededSkill=driver.findElement(By.xpath("//select[@id='Skills']//option[text()='"+skill+"']"));
	return neededSkill;
}
public WebElement selectCountry(String country) {
	WebElement excountry=driver.findElement(By.xpath("//span[@id='select2-country-container' and text()='"+country+"']"));
	return excountry;
}

}
