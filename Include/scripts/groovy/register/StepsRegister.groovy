package register
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
import org.openqa.selenium.Keys as Keys

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


class StepsRegister {

	@Given("user is on the registration page")
	def navigateToUrl() {
		WebUI.openBrowser(GlobalVariable.url)
		WebUI.maximizeWindow()
		WebUI.takeFullPageScreenshot()
		WebUI.waitForElementPresent(findTestObject('Object Repository/01 - Registrasi/rect'), 0)
		WebUI.click(findTestObject('Object Repository/01 - Registrasi/rect'))
		WebUI.click(findTestObject('Object Repository/01 - Registrasi/div_REGISTER'))
		WebUI.takeFullPageScreenshot()
	}


	@And("user enters required information")
	def inputRegistrationForm(){

		def firstNames = ['Fetty', 'Fenny', 'Alwi', 'Kafi', 'Ummu']
		def lastNames = ['Maula', 'Nimatul', 'Rahayu', 'Zakiyyah', 'Lestari']
		def domains = ['gmail.com', 'yahoo.com', 'hotmail.com', 'outlook.com', 'aol.com']

		def phoneNumberLength = 12
		def number = '0123456789'
		def passwordLength = 10
		def chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*'
		def random = new java.security.SecureRandom()

		def randomFirstName = firstNames[(int)(Math.random() * firstNames.size())].toLowerCase()
		def randomLastName = lastNames[(int)(Math.random() * lastNames.size())].toLowerCase()
		def randomDomain = domains[(int)(Math.random() * domains.size())].toLowerCase()

		def fullName = "${randomFirstName} ${randomLastName}"
		def email = "${randomFirstName}.${randomLastName}@${randomDomain}"
		def phoneNumber = (1..phoneNumberLength).collect { number[random.nextInt(number.length())] }.join()
		def password = (1..passwordLength).collect { chars[random.nextInt(chars.length())] }.join()

		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Name'), fullName)
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Phone Number'), phoneNumber)
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Email'), email)
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Password'), password)
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_REGISTER_re-retype-password'), password)
		WebUI.takeFullPageScreenshot()

		GlobalVariable.email = email
		GlobalVariable.password = password
	}

	@And("click register button")
	def clickRegister(){
		WebUI.click(findTestObject('Object Repository/01 - Registrasi/button_REGISTER'))
	}


	@And("message to input verification code should be displayed")
	def messageAfterRegis(){
		WebUI.verifyElementPresent(findTestObject('Object Repository/01 - Registrasi/title_otp'), 20, FailureHandling.STOP_ON_FAILURE)
		WebUI.takeFullPageScreenshot()
	}

	@And("the user's information should be stored in the database")
	def saveRegisInfo(){
		String excelRegis = RunConfiguration.getProjectDir() + '/Excel/01 - Registrasi.xlsx/'
		String sheet = 'suksesRegis'

		def workbook01 = ExcelKeywords.getWorkbook(excelRegis)
		def sheet01 = ExcelKeywords.getExcelSheet(workbook01, sheet)

		ExcelKeywords.setValueToCellByIndex(sheet01, 1, 0, GlobalVariable.email)
		ExcelKeywords.setValueToCellByIndex(sheet01, 1, 1, GlobalVariable.password)

		ExcelKeywords.saveWorkbook(excelRegis, workbook01)
	}

	@Then("system should display an error message register")
	def errorMissingFieldsRegis() {

		assert WebUI.verifyElementPresent(findTestObject('Object Repository/01 - Registrasi/err_FieldName'), 10)
		assert WebUI.getText(findTestObject('Object Repository/01 - Registrasi/err_FieldName')).contains("Name cannot be empty")

		assert WebUI.verifyElementPresent(findTestObject('Object Repository/01 - Registrasi/err_FieldPhone'), 10)
		assert WebUI.getText(findTestObject('Object Repository/01 - Registrasi/err_FieldPhone')).contains("Phone Number cannot be empty")

		assert WebUI.verifyElementPresent(findTestObject('Object Repository/01 - Registrasi/err_FieldEmail'), 10)
		assert WebUI.getText(findTestObject('Object Repository/01 - Registrasi/err_FieldEmail')).contains("Email cannot be empty")

		assert WebUI.verifyElementPresent(findTestObject('Object Repository/01 - Registrasi/err_FieldPassword'), 10)
		assert WebUI.getText(findTestObject('Object Repository/01 - Registrasi/err_FieldPassword')).contains("Password cannot be empty")

		WebUI.takeScreenshot()
	}

	@When("user enters an invalid email format")
	def invalidEmail(){
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Email'), 'test@test')
	}

	@Then("system should display an error message indicating that the email format is invalid")
	def verifyInvalidEmail(){
		assert WebUI.verifyTextPresent('Format email (Cth: asd@xxx.com)', false)
		WebUI.takeScreenshot()
	}

	@When("user enter invalid password")
	def invalidPassword() {
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Password'), '123')
	}

	@Then("system should display an error message indicating why the password is not valid")
	def verifyInvalidPassword() {
		assert WebUI.verifyTextPresent('Please use at least 8 characters', false)
		WebUI.takeScreenshot()
	}


	@When("user enter invalid characters")
	def invalidChar() {
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Name'), '&')
		WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_Phone Number'), '&')
	}


	@Then("system should display an error message indicating which characters are not allowed")
	def verifyInvalidChar() {

		def valueFullName = WebUI.getAttribute(findTestObject('Object Repository/01 - Registrasi/input_Name'), 'text')
		def valuePhone = WebUI.getAttribute(findTestObject('Object Repository/01 - Registrasi/input_Phone Number'), 'text')

		println valueFullName
		println valuePhone

		//		assert valueFullName.equals(null)
		assert valuePhone.equals(null)

		WebUI.takeFullPageScreenshot()
	}

	@And("user input detail registrasi")
	def inputDetailRegis() {
		TestData data = findTestData('Data Files/01 - Registrasi/registrasi-negative')

		for(int i=1; i <= data.getRowNumbers(); i++) {

			String[][] detailRegis = [['Name', 'name', 'validasi_name'], ['Phone Number', 'phone', 'validasi_phone'], ['Email', 'email', 'validasi_email'], ['Password', 'password', 'validasi_password']]

			for (int input = 0; input < detailRegis.length; input++) {
				WebUI.sendKeys(findTestObject('Object Repository/01 - Registrasi/input_'+detailRegis[input][0]), Keys.chord(Keys.CONTROL, 'a', Keys.BACK_SPACE))
				WebUI.setText(findTestObject('Object Repository/01 - Registrasi/input_'+detailRegis[input][0]), data.getValue(detailRegis[input][1], i))

				if (i == data.getRowNumbers()) {
					WebElement buttonRegister = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/01 - Registrasi/button_REGISTER'),20)
					WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(buttonRegister))
				}

				assert WebUI.verifyTextPresent(data.getValue(detailRegis[input][2], i), false)
				WebUI.takeScreenshot()
			}
		}
	}
}