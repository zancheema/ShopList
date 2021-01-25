package com.asw.shoplist;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static org.testng.Assert.*;

public class MainActivityTest {
    private AndroidDriver<AndroidElement> driver;
    private DesiredCapabilities capabilities;

    @Before
    public void setUp() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appPackage", "com.asw.shoplist");
        capabilities.setCapability("appActivity", ".MainActivity");
        capabilities.setCapability("noReset", true);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void addItem() {
        // navigate to add new list item
        MobileElement el1 = driver.findElementById("com.asw.shoplist:id/addList");
        assertTrue(el1.isDisplayed());
        el1.click();

        // type shop name
        MobileElement el2 = driver.findElementById("com.asw.shoplist:id/edtShopName");
        assertTrue(el2.isDisplayed());
        el2.sendKeys("Shop 1");

        // type item name
        MobileElement el3 = driver.findElementById("com.asw.shoplist:id/edtItemName");
        assertTrue(el3.isDisplayed());
        el3.sendKeys("Item 1");

        // type item price
        MobileElement el4 = driver.findElementById("com.asw.shoplist:id/edtItemPrice");
        assertTrue(el4.isDisplayed());
        el4.sendKeys("25");

        // add the item
        MobileElement el5 = driver.findElementById("com.asw.shoplist:id/btnAddList");
        assertTrue(el5.isDisplayed());
        el5.click();

        // Item is saved and shop name and item price text fields are show hints
        assertEquals(el3.getText(), "Item Name");
        assertEquals(el4.getText(), "Item Price");
    }

    @Test
    public void addLists_ShowsAddedListsInViewListsAcitivity() {
        // add new items
        MobileElement el1 = driver.findElementById("com.asw.shoplist:id/addList");
        el1.click();
        MobileElement el2 = driver.findElementById("com.asw.shoplist:id/edtShopName");
        el2.sendKeys("Shop 1");
        MobileElement el3 = driver.findElementById("com.asw.shoplist:id/edtItemName");
        el3.sendKeys("Item 1");
        MobileElement el4 = driver.findElementById("com.asw.shoplist:id/edtItemPrice");
        el4.sendKeys("25");
        MobileElement el5 = driver.findElementById("com.asw.shoplist:id/btnAddList");
        el5.click();
        MobileElement el6 = driver.findElementById("com.asw.shoplist:id/edtItemName");
        el6.sendKeys("Item 2");
        MobileElement el7 = driver.findElementById("com.asw.shoplist:id/edtItemPrice");
        el7.sendKeys("30");
        MobileElement el8 = driver.findElementById("com.asw.shoplist:id/btnAddList");
        el8.click();
        MobileElement el9 = driver.findElementById("com.asw.shoplist:id/edtShopName");
        el9.sendKeys("Shop 2");
        MobileElement el10 = driver.findElementById("com.asw.shoplist:id/edtItemName");
        el10.sendKeys("Item 1");
        MobileElement el11 = driver.findElementById("com.asw.shoplist:id/edtItemPrice");
        el11.sendKeys("45");
        MobileElement el12 = driver.findElementById("com.asw.shoplist:id/btnAddList");
        el12.click();

        // navigate up
        MobileElement el13 = driver.findElementByAccessibilityId("Navigate up");
        el13.click();

        // navigate to view lists
        MobileElement el14 = driver.findElementById("com.asw.shoplist:id/viewLists");
        el14.click();

        // name of second shop is shown
        MobileElement el15 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/" +
                "android.widget.FrameLayout[2]/android.view.ViewGroup/" +
                "androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/" +
                "android.view.ViewGroup/android.widget.TextView[1]");
        assertEquals(el15.getText(), "Shop 2");
        // total price of first shop items is shown
        MobileElement el16 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/" +
                "android.widget.FrameLayout[2]/android.view.ViewGroup/" +
                "androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/" +
                "android.view.ViewGroup/android.widget.TextView[2]");
        assertEquals(el16.getText(), "€ 45.0");

        // name of first shop is shown
        MobileElement el17 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/" +
                "android.widget.FrameLayout[2]/android.view.ViewGroup/" +
                "androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/" +
                "android.view.ViewGroup/android.widget.TextView[1]");
        assertEquals(el17.getText(), "Shop 1");
        // total price of first shop items is shown
        MobileElement el18 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/" +
                "android.widget.FrameLayout[2]/android.view.ViewGroup/" +
                "androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/" +
                "android.view.ViewGroup/android.widget.TextView[2]");
        assertEquals(el18.getText(), "€ 55.0");
    }

    @Test
    public void addItemsAndCompare() {
        // add items
        MobileElement el1 = driver.findElementById("com.asw.shoplist:id/addList");
        el1.click();
        MobileElement el2 = driver.findElementById("com.asw.shoplist:id/edtShopName");
        el2.sendKeys("Shop 1");
        MobileElement el3 = driver.findElementById("com.asw.shoplist:id/edtItemName");
        el3.sendKeys("Item 1");
        MobileElement el4 = driver.findElementById("com.asw.shoplist:id/edtItemPrice");
        el4.sendKeys("25");
        MobileElement el5 = driver.findElementById("com.asw.shoplist:id/btnAddList");
        el5.click();
        MobileElement el6 = driver.findElementById("com.asw.shoplist:id/edtShopName");
        el6.sendKeys("Shop 2");
        MobileElement el7 = driver.findElementById("com.asw.shoplist:id/edtItemName");
        el7.sendKeys("Item 1");
        MobileElement el8 = driver.findElementById("com.asw.shoplist:id/edtItemPrice");
        el8.sendKeys("30");
        MobileElement el9 = driver.findElementById("com.asw.shoplist:id/btnAddList");
        el9.click();
        MobileElement el10 = driver.findElementByAccessibilityId("Navigate up");
        el10.click();
        MobileElement el11 = driver.findElementById("com.asw.shoplist:id/compare");
        el11.click();

        // select Shop 1 as first shop
        MobileElement el12 = driver.findElementById("com.asw.shoplist:id/spinnerShop1");
        el12.click();
        MobileElement el13 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        el13.click();

        // select Shop 2 as second shop
        MobileElement el14 = driver.findElementById("com.asw.shoplist:id/spinnerShop2");
        el14.click();
        MobileElement el15 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]");
        el15.click();

        // compare both shops
        MobileElement el16 = driver.findElementById("com.asw.shoplist:id/btnCompareList");
        el16.click();

        // the comparison is shown in the two tables below
        assertTrue(driver.findElementById("com.asw.shoplist:id/table_shop1").isDisplayed());
        assertTrue(driver.findElementById("com.asw.shoplist:id/table_shop1").isDisplayed());

        // a message is displayed showing that its more convenient to buy from Shop 1
        MobileElement el17 = (MobileElement) driver.findElementById("com.asw.shoplist:id/text_result");
        assertEquals(el17.getText(), "It is more convenient to buy from Shop 1 because " +
                "there are 1 items cheaper than Shop 2");
    }
}