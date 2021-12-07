package core;

public interface PageObject {

    PageObject getPreviousPageObject();
    PageObject getNewInstanceOfPreviousPageObject();
    PageObject refreshPage();
    String getCurrentUrl();
    String getCurrentTitle();
    PageObject waitForSpecifiedSeconds(int seconds);
    PageObject waitForSpecifiedMilliSeconds(int milliseconds);
}
