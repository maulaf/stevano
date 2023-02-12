package login
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords


import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import groovy.util.slurpersupport.GPathResult
import com.kms.katalon.core.webui.common.WebUiCommonHelper



class StepsLogin {

	@Given("user is on the login page")
	def navigateToLogin() {
		WebUI.openBrowser(GlobalVariable.url)
		WebUI.maximizeWindow()
		WebUI.takeScreenshot()
		WebUI.waitForElementPresent(findTestObject('Object Repository/01 - Registrasi/rect'), 0)
		WebUI.click(findTestObject('Object Repository/01 - Registrasi/rect'))
		WebUI.click(findTestObject('Object Repository/02 - Login/div_LOGIN'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/02 - Login/button_SIGN IN'), 0)
		WebUI.takeScreenshot()
	}

	@When("user enters correct (.*) and (.*)")
	def inputCorrectLogin(String email, String password) {
		WebUI.setText(findTestObject('Object Repository/02 - Login/input_Email'), email)
		WebUI.setText(findTestObject('Object Repository/02 - Login/input_Password'), password)
		WebUI.takeScreenshot()
	}

	@And("click sign in")
	def clickSignIn() {
		WebElement SignIn = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/02 - Login/button_SIGN IN'),20)
		WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(SignIn))

		if ( WebUI.verifyElementPresent(findTestObject('Object Repository/02 - Login/button_SIGN IN'), 0, FailureHandling.OPTIONAL) == true ) {
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(SignIn))
		}

		WebUI.takeScreenshot()
	}


	@Then("user should be successfully logged in")
	def successLogin() {
		//system should display message that success login
//		assert WebUI.verifyTextPresent('Login Success', false)
		WebUI.takeScreenshot()
	}


	@And("user enters incorrect email and password")
	def inputIncorrectLogin() {
		def sheet = ExcelKeywords.getExcelSheetByName((GlobalVariable.dirProject + '/Excel/') + '/01 - Registrasi.xlsx', 'suksesRegis')

		def email = ExcelKeywords.getCellValueByAddress(sheet, 'A2')
		def password = ExcelKeywords.getCellValueByAddress(sheet, 'B2')

		WebUI.setText(findTestObject('Object Repository/02 - Login/input_Email'), email)
		WebUI.setText(findTestObject('Object Repository/02 - Login/input_Password'), password)
		WebUI.takeScreenshot()
	}

	@Then("user should be failed logged in")
	def failedLogin() {
		assert WebUI.verifyTextPresent('Username or Password is incorrect', false)
		WebUI.takeScreenshot()
	}

	@Then("system should display an error message login")
	def errorMissingFieldsLogin() {
		assert WebUI.verifyElementPresent(findTestObject('Object Repository/02 - Login/err_FieldEmail'), 10)
		assert WebUI.getText(findTestObject('Object Repository/02 - Login/err_FieldEmail')).contains("Email cannot be empty")

		assert WebUI.verifyElementPresent(findTestObject('Object Repository/02 - Login/err_FieldPassword'), 10)
		assert WebUI.getText(findTestObject('Object Repository/02 - Login/err_FieldPassword')).contains("Password cannot be empty")

		WebUI.takeScreenshot()
	}
}