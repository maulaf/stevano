package forgetPassword
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



class StepsForgetPassword {
	
	@When('user clicks on the "Forgot Password" link')
	def clickForgetPassword() {
		WebUI.click(findTestObject('Object Repository/03 - Forget Password/a_forgot password'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/03 - Forget Password/h1_Atur Ulang Kata Sandi'), 0)
		WebUI.takeScreenshot()
		
	}
	
	
	@And('user enters their registered email address')
	def inputRegisterEmail() {		
		def email = 'fetty.maula98@gmail.com'
		WebUI.setText(findTestObject('Object Repository/03 - Forget Password/input_Atur Ulang Kata Sandi_email'), email)
		WebUI.takeScreenshot()
		
	}
	
	@And('user enters an invalid email address')
	def enterInvalidEmail(){
		def invalidEmail = 'test@test'
		WebUI.setText(findTestObject('Object Repository/03 - Forget Password/input_Atur Ulang Kata Sandi_email'), invalidEmail)
		WebUI.takeScreenshot()
	}
	
	
	@And('user enters an email address that is not registered')
	def inputNotRegisteredEmail() {
		def email = 'test@yahoo.com'
		WebUI.setText(findTestObject('Object Repository/03 - Forget Password/input_Atur Ulang Kata Sandi_email'), email)
		WebUI.takeScreenshot()
	}
	
	
	@And('user clicks on the submit button')
	def clickSubmit() {
		WebUI.click(findTestObject('Object Repository/03 - Forget Password/a_forgot password'))
	}
	
	@Then('user should receive an email with instructions to reset their password')
	def successSendEmail() {	
		//system should be display a pop up message that email is sent
		WebUI.takeScreenshot()		
	}
	
	
	@Then('user should receive an error message indicating that the email is invalid')
	def verifyInvalidEmail() {
		//system should be display an error message due invalid email
		WebUI.takeScreenshot()
		
	}
	
	@Then('user should receive an error message indicating that the email address is not registered')
	def verifyEmailNotRegistered() {
		//shoud be display an error message due email is not registered
		WebUI.takeScreenshot()
	}
	
 
}