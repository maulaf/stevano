import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


def firstNames = ['John', 'Jane', 'James', 'Jennifer', 'Jessica']
def lastNames = ['Smith', 'Johnson', 'Williams', 'Jones', 'Brown']
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


println fullName
println email
println phoneNumber
println password





