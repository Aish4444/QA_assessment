package webPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

@Test
public class Bookingpage extends CommonWebElements {
	@FindBy(xpath="//input[@id='inputName']")
	public WebElement Name;
	
	@FindBy(xpath="//input[@id='address']")
	public WebElement Address;
	
	@FindBy(xpath="//input[@id='city']")
	public WebElement City;
	
	@FindBy(xpath="//input[@id='state']")
	public WebElement State;
	
	@FindBy(xpath="//input[@id='zipCode']")
	public WebElement Zipcode;
	
	@FindBy(xpath="//select[@id='cardType']")
	public WebElement CardType;
	
	@FindBy(xpath="//input[@id='creditCardNumber']")
	public WebElement Cardnumber;
	
	@FindBy(xpath="//input[@id='creditCardMonth']")
	public WebElement Month;
	
	@FindBy(xpath="//input[@id='creditCardYear']")
	public WebElement Year;
	
	@FindBy(xpath="//input[@id='nameOnCard']")
	public WebElement NameonCard;
	
	@FindBy(xpath="//h2[contains(text(),'Your flight from TLV to SFO has been reserved.')]")
	public WebElement messageheading;
	
	@FindBy(xpath="//input[@id='rememberMe']")
	private WebElement checkbox;
	
	@FindBy(xpath="//body/div[2]/form[1]/div[11]/div[1]/input[1]")
	public WebElement purchaseflight;
	
	@FindBy(xpath="//h1[contains(text(),'Thank you for your purchase today!')]")
	public WebElement bookingsuccessmsg;
	
	public void fillname(){
		Name.sendKeys("Aishwarya Sunthe");
		l.info("Enter name");
	}
	
	public void filladdress(){
		Address.sendKeys("P.No. 123, Sukashi,Sahyadri");
		l.info("Enter address");
	}
	
	public void fillcity(){
		City.sendKeys("Belagavi");
		l.info("Enter city");
	}
	
	public void fillstate(){
		State.sendKeys("Karnataka");
		l.info("Enter state");
	}
	
	public void fillzipcode(){
		Zipcode.sendKeys("590001");
		l.info("Enter Zipcode");
	}
	
	public void fillcardtype(){
		Select drpcard = new Select(CardType);
		drpcard.selectByVisibleText("Visa");
		l.info("Card type select");
	}
	
	public void fillcardnumber(){
		Cardnumber.sendKeys("1231231231231231");
		l.info("Enter card number");
	}
	
	public void fillcardmonth(){
		Month.clear();
		Month.sendKeys("12");
		l.info("Enter card month");
	}
	
	public void fillcardyear(){
		Year.clear();
		Year.sendKeys("2022");
		l.info("Enter card year");
	}
	
	public void fillcardname(){
		NameonCard.sendKeys("Aishwarya K Sunthe");
		l.info("Enter full name as in card");
	}
	
	public void checkcheckbox() {
		if (checkbox.isSelected()) {
			l.info("checkbox already checked");
		}
		
		else {
			checkbox.click();
			l.info("checkbox is toggled on");
		}
		}
	
	public void clickpurchaseflight(){
		purchaseflight.click();
		l.info("Purchase flight is clicked");
	}
	
	public void verifymessage(){
		if(messageheading.getText().equals("Your flight from TLV to SFO has been reserved.")) {
			
		}else {
			st.fail("Heading message is not displayed");
			
		}
	}
	
	public void verifysuccessmessage(){
		if(bookingsuccessmsg.getText().equals("Thank you for your purchase today!")) {
			
		}else {
			st.fail("Heading message is not displayed");
			
		}
	}

}
