package org.openmrs.contrib.isanteplus.qaframework.automation;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.isanteplus.qaframework.RunTest;
import org.openmrs.contrib.isanteplus.qaframework.automation.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.isanteplus.qaframework.automation.page.FindPatientPage;
import org.openmrs.contrib.isanteplus.qaframework.automation.page.HomePage;
import org.openmrs.contrib.isanteplus.qaframework.automation.page.LoginPage;
import org.openmrs.contrib.isanteplus.qaframework.automation.test.RemoteTestBase;

public class DeletePatientSteps extends RemoteTestBase {
	
	private ClinicianFacingPatientDashboardPage dashboardPage;
	
	private FindPatientPage findPatientPage;
	
	private LoginPage loginPage;
	
	private HomePage homePage;
	
	private static String REASON = "patient discharged";
	
	@Before(RunTest.HOOK.DELETE_PATIENT)
	public void setUp() {
		loginPage = new LoginPage(getDriver());
	}
	
	@After(RunTest.HOOK.DELETE_PATIENT)
	public void destroy() {
		quit();
	}
	
	@Given("setup logs in the system")
	public void setupLoginPage() throws Exception {
		System.out.println("....Delete Patient......");
		homePage = loginPage.goToHomePage();
	}
	
	@When("User clicks on search Patient Record app")
	public void userVisitFindPatientRecordApp() throws Exception {
		findPatientPage = homePage.clickOnSearchPatientRecord();
	}
	
	@And("User searches for a patient {string} patientName and load their cover page")
	public void userSearchForPatient(String name) throws Exception {
		findPatientPage.enterPatientName(name);
		Thread.sleep(2000);
		findPatientPage.getFirstPatientIdentifier();
		findPatientPage.clickOnFirstPatient();
	}
	
	@And("User clicks 'Delete Patient'")
	public void userClickOnDeletePatient() {
		dashboardPage = findPatientPage.clickOnDeletePatient();
	}
	
	@Then("Patient deleted successfully message appears, and redirected to patient search page")
	public void loadFindPatientPage() {
		assertTrue(findPatientPage.hasSearchPatientRecord());
	}
}
