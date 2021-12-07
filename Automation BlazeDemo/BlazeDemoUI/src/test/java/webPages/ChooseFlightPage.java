package webPages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ChooseFlightPage extends CommonWebElements {

	@FindBy(xpath="//tbody/tr[1]/td[1]/input[1]")
	public WebElement flight;
	
	
	@FindBy(xpath="//tbody/tr/td[6]")
	public List<WebElement> Price;
	
	@FindBy(xpath="//thead/tr/th[2]")
	public WebElement Flightno;
	
	@FindBy(xpath="//thead/tr/th[3]")
	public WebElement Airline;
	
	@FindBy(xpath="//thead/tr/th[4]")
	public WebElement Departuretime;
	
	@FindBy(xpath="//thead/tr/th[5]")
	public WebElement Arrivaltime;
	
	@FindBy(xpath="//td[text()='%s']/ancestor::tr//input[@value='Choose This Flight']")
	public WebElement chooseflightlow;
	
	private static final String GENERIC_MIN_FLIGHT="//td[contains(text(),'%s')]/ancestor::tr//input[@value='Choose This Flight']";
		
	public void chooseflights(){
		flight.click();
		l.info("clicked on find flights");
	}


	public void flightchoose() {

		List<WebElement> myList=dr.findElements(By.xpath("//thead//th[text()=\"Price\"]/ancestor::thead/following-sibling::tbody//td[6]"));
		//To store all web elements into list

		List<String> all_elements_text=new ArrayList<>();
		//If you want to get all elements text into array list

		for(int i=0; i<myList.size(); i++){

			all_elements_text.add(myList.get(i).getText());
			//loading text of each element in to array all_elements_text
			System.out.println(myList.get(i).getText());
		}

		Object minimumValue = Collections.min(all_elements_text);

		System.out.println(minimumValue);

		String flight ="//td[contains(text(),'%s')]/ancestor::tr//input[@value='Choose This Flight']";

		WebElement elem=dr.findElement(By.xpath(flight.replace("%s",String.valueOf(minimumValue))));
		elem.click();
	}

}

	

