package com.crossover.trial.journals.pages.aat;

import com.crossover.trial.journals.Application;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author newlife
 */
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc; // just in case we will need it
    private WebDriver driver;

    @Before
    public void setup() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(webApplicationContext).build();
    }

    @When("^I go to login page$")
    public void iGoToLoginPage() throws Throwable {
        driver.get("http://localhost:8085/login");
    }

    @Then("^I expect to have a form with class \"([^\"]*)\"$")
    public void iExpectToHaveAFormWithClass(String arg1) throws Throwable {
        driver.findElement(By.className("login-container"));
    }

    @Then("^an input field for name \"([^\"]*)\"$")
    public void anInputFieldForName(String arg1) throws Throwable {
        driver.findElement(By.name(arg1));
    }

    @Then("^an input field for password \"([^\"]*)\"$")
    public void anInputFieldForPassword(String arg1) throws Throwable {
        driver.findElement(By.name(arg1));
    }

    @Then("^a button with text \"([^\"]*)\"$")
    public void aButtonOfType(String arg1) throws Throwable {
        driver.findElement(By.xpath("//input[@type='submit'][@value='" + arg1 + "']"));
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }
}
