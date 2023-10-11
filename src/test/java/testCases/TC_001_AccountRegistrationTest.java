package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test
    public void test_account_Registration() throws InterruptedException
    {
        logger.info("******* Starting TC_001_AccountRegistrationTest ********");

        try {

            HomePage hp=new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on Account link");
            hp.clickRegister();
            logger.info("Clicked on Registrer link");

            AccountRegistrationPage regpage=new AccountRegistrationPage(driver);

            regpage.setFirstName(randomeString().toUpperCase());

            regpage.setLastName(randomeString().toUpperCase());

            regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email

            regpage.setTelephone(randomeNumber());

            String password=randomAlphaNumeric();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.setPrivacyPolicy();

            regpage.clickContinue();
            logger.info("Clicked on continue");

            String confmsg=regpage.getConfirmationMsg();

            Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        }catch (Exception e) {
            logger.error("test failed");
            Assert.fail();
        }
        logger.info("******* Finished TC_001_AccountRegistrationTest ********");
    }

}
