#Author: draganuta.pavel@gmail.com
@OrderPlacement
Feature: OrderPlacement

	@OrderTest
	Scenario: User navigates to the eCommerce Store and verifies the order placement flow

		Given User navigates to the public site
		When User navigates to "PHONES"
		Then User cliks on a mobile product
		And User verifies the mobile's product Header
		And User clicks the Add to Cart button
		And User closes the product cart validation dialog
		And User navigates to "CART" within the Header Menu
		And User clicks the Place Order button
		And User verifies the place order's header
		And User inputs "COUNTRY" as Country, "CITY" as City, "CREDIT_CARD" as Credit Card, "MONTH" as Month and "YEAR" as Year
		And User verifies the purchase confirmation's header