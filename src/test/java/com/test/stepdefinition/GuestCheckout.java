package com.test.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.selenium.AbstractPage;
import com.test.selenium.BasePage;
import com.test.selenium.Configuration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GuestCheckout extends AbstractPage {

	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

	@Given("The user is on the e-commerce website homepage")
	public void the_user_is_on_the_e_commerce_website_homepage() throws Exception {
		String url = Configuration.getConfig("AppleURL");

		logger.info("the_user_is_on_the_e_commerce_website_homepage");
		driver.navigate().to(url);
	}

	@When("the user searches for {string} and clicked on the product tab")
	public void the_user_searches_for_and_clicked_on_the_product_tab(String respectiveTab) throws InterruptedException {
		logger.info("the user searches for " + respectiveTab + " and clicked on the product tab");
		getAppleHomePage().clickOnTheDesiredProductTab(respectiveTab);
	}

	@When("the user clicks on the first iPhone product in the search results {string}")
	public void the_user_clicks_on_the_first_i_phone_product_in_the_search_results(String productName) {
		logger.info("the user clicks on the first iPhone product in the search results {string}");
		getAppleHomePage().theUserSearchesFor(productName);
	}

	@Then("user select the colour {string}")
	public void user_select_the_colour(String chooseColour) {
		logger.info("user select the colour" + chooseColour);
		getAppleHomePage().selectTheProductColour(chooseColour);
	}

	@Then("user clicked on buy button")
	public void user_clicked_on_buy_button() {
		logger.info("user clicked on buy button");
		getAppleHomePage().clickOnBuyButton();
	}

	@Then("user selcted the model and choosen colour :{string}")
	public void user_selcted_the_model_and_choosen_colour(String colour) {
		logger.info("user selcted the model and choosen colour :" + colour);
		getAppleHomePage().selectTheModel();
		getAppleHomePage().selectColour(colour);
	}

	@Then("user choose the storage :{string}")
	public void user_choose_the_storage(String storage) {
		logger.info("user choose the storage :" + storage);
		getAppleHomePage().selectTheStorage(storage);
	}

	@Then("clicked on No trade button")
	public void clicked_on_no_trade_button() throws InterruptedException {
		logger.info("clicked on No trade button");
		getAppleHomePage().clickOnNoTradeInButton();
	}

	@Then("user selected the payment option:{string}")
	public void user_selected_the_payment_option(String paymentPartner) throws InterruptedException {
		logger.info("user selected the payment option:" + paymentPartner);
		getAppleHomePage().selectPaymentOption(paymentPartner);
	}

	@Then("user choosen payment carrier:{string}")
	public void user_choosen_payment_carrier(String carrier) {
		logger.info("user choosen payment carrier:" + carrier);
		getAppleHomePage().chooseAPaymentCarrier(carrier);
	}

	@Then("choosen apple care coverage")
	public void choosen_apple_care_coverage() throws InterruptedException {
		logger.info("choosen apple care coverage");
		getAppleHomePage().selectAppleCareCoverage();
	}

	@Then("selected coverage type")
	public void selected_coverage_type() {
		logger.info("selected coverage type");
		getAppleHomePage().selectCoverageType();
	}

	@Then("clicked on Add to bag button")
	public void clicked_on_add_to_bag_button() {
		logger.info("clicked on Add to bag button");
		getAppleHomePage().clickOnAddToBagButton();
	}

	@And("user verify the selected colour {string}")
	public void user_verify_the_selected_colour(String colour) {
		logger.info("user verify the selected colour is " + colour);
		Assert.assertTrue(getAppleHomePage().verifyChoosenColour().contains(colour));
	}

	@Then("click on review bag button")
	public void click_on_review_bag_button() {
		logger.info("click on review bag button");
		getAppleHomePage().clickOnReviewBag();
	}

	@Then("the user proceeds to checkout")
	public void the_user_proceeds_to_checkout() {
		logger.info("the user proceeds to checkout");
		getAppleHomePage().clickOnCheckoutButton();

	}

	@Then("the user selects as Guest User")
	public void the_user_selects_as_guest_user() {
		logger.info("the user selects as Guest User");
		getAppleHomePage().proceedAsGuestUser();
	}

	@Then("the user enters their shipping information and enter pincode {string}")
	public void the_user_enters_their_shipping_information_and_enter_pincode(String pincode)
			throws InterruptedException {
		logger.info("the user enters their shipping information and enter pincode {string}" + pincode);
		getAppleHomePage().clickOnLikeToDelivered();
		getAppleHomePage().clickToContinue();
		getAppleHomePage().enterValidZipcode(pincode);
//		getAppleHomePage().clickOnApply();
//		getAppleHomePage().clickOnStandardDelivery();
//		getAppleHomePage().clickOnContinueShippingAddressButton();

	}

	@Then("Enter firstName {string} and lastName {string}")
	public void enter_first_name_and_last_name(String firstName, String lastName) {
		logger.info("Enter firstName :" + firstName + " and lastName :" + lastName);
		getAppleHomePage().enterFirstnameAndLastname(firstName, lastName);
	}

	@Then("enter street address {string} and landmark address {string}")
	public void enter_street_address_and_landmark_address(String streetAddress, String landmark) {
		logger.info("enter street address :" + streetAddress + " and landmark address :" + landmark);
		getAppleHomePage().enterStreetAddressAndLandmark(streetAddress, landmark);
	}

	@Then("enter email address {string} and phone number {string}")
	public void enter_email_address_and_phone_number(String email, String phone) {
		logger.info("enter email address :" + email + " and phone number :" + phone);
		getAppleHomePage().enterEmailAndPhone(email, phone);
	}

	@Then("click on continue payment button")
	public void click_on_continue_payment_button() throws InterruptedException {
		logger.info("click on continue payment button");
		getAppleHomePage().clickOnContinuePaymentButton();
		Thread.sleep(3000);
	}


}