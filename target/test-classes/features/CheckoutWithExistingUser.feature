Feature: Guest User Checkout for iPhone

  @GuestCheckout
  Scenario: Guest user adds an iPhone to the cart and completes checkout
    Given The user is on the e-commerce website homepage
    When the user searches for "iPhone" and clicked on the product tab
    Then the user clicks on the first iPhone product in the search results "iPhone 14 Pro"
    Then user select the colour "deep-purple"
    Then user clicked on buy button
    Then user selcted the model and choosen colour :"Deep Purple"
    Then user choose the storage :"128"
    Then clicked on No trade button
    Then user selected the payment option:"Buy"
    Then user choosen payment carrier:"AT&T"
    Then choosen apple care coverage
    Then selected coverage type
    Then clicked on Add to bag button
    And user verify the selected colour "Deep Purple"
    Then click on review bag button
    Then the user proceeds to checkout
    Then the user selects as Guest User
   Then the user enters their shipping information and enter pincode "60601"
    Then Enter firstName "Global" and lastName "tester"
    And enter street address "Chicago" and landmark address "testing "
    Then enter email address "globaltester999@gmail.com" and phone number "(053) 423-2241"
    Then click on continue payment button

    