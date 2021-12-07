package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import util.DriverUtil;
import webPages.CommonWebElements;
import webPages.ChooseFlightPage;
import webPages.Bookingpage;


public class BookFlight extends CommonWebElements {

	public CommonWebElements cw;
	public ChooseFlightPage dp;
	public Bookingpage ip;
	public DriverUtil du;
	@BeforeClass
	public void setup() {
		cw = PageFactory.initElements(dr, CommonWebElements.class);
		dp = PageFactory.initElements(dr, ChooseFlightPage.class);
		ip = PageFactory.initElements(dr, Bookingpage.class);
		du = new DriverUtil(dr);
	}

	@Test(priority =1,description = "Flight search by selecting the departure city and destination city")
	public void searchForFlights() {
		extentTest = extent.startTest("Search for flights");
		cw.clickondeparturecity();
		cw.clickondestinationcity();
		cw.findflights();
	}
	@Test(priority =2, description = "Choose the first available airline", dependsOnMethods ="searchForFlights")
	public void bookFlight() {
		extentTest = extent.startTest("Choose the Cheapest flight");
		dp.flightchoose();
	}
	@Test(priority =3,description = "Form page to enter the personal details and buy ticket", dependsOnMethods ="bookFlight")
	public void flightTicketBuy() {
		extentTest = extent.startTest("Purchase the ticket");
		ip.fillname();
		ip.filladdress();
		ip.fillcity();
		ip.fillstate();
		ip.fillzipcode();
		ip.fillcardtype();
		ip.fillcardnumber();
		ip.fillcardmonth();
		ip.fillcardyear();
		ip.fillcardname();
		ip.checkcheckbox();
		ip.clickpurchaseflight();
		l.info("Ticket Booked Successfully");
		assertall();
	}
}


