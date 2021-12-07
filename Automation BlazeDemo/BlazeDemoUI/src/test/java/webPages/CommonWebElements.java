package webPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public class CommonWebElements extends TestBase {
	
	@FindBy(xpath="//body/div[3]/form[1]/select[1]")
	public WebElement departurecity;
	
	@FindBy(xpath="//body/div[3]/form[1]/select[2]") 
	public WebElement destinationcity;
	
	public void clickondeparturecity() {
		Select drpcity = new Select(departurecity);
		drpcity.selectByVisibleText("Boston");
	}
	
	
	public void clickondestinationcity(){
		Select descity = new Select(destinationcity);
		descity.selectByVisibleText("London");

	}
	
	@FindBy(xpath="//body/div[3]/form[1]/div[1]/input[1]") 
	public WebElement findflights;

	public void findflights(){
		findflights.click();
		l.info("clicked on find flights");
	}

	public static void assertall() {
		st.assertAll();
	}

}
