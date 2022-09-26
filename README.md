# Wallet-Pay
This is a payments app that helps in paying via UPI with QR code. You can provide your UPI details and QR code will be generated for the same. Once you scan the QR code you directly get the Pay option with Amount and comments filled in.

## Pre-requisites:
### Tools
 - Java 8
 - Maven

### Tech stack
 - Spring boot for scaffolding application
 - Thymeleaf for templating UI
 - Lombok for reducing boilerplate code
 - Google Zxing for QR code
 - Swagger for API documentation

### Lombok setup:
 - Install Lombok:
 - Path: C:\Users\giriraj_vyas\.m2\repository\org\projectlombok\lombok
 - Command: java -jar lombok-1.18.24.jar

## Documentation



### APIs
/api/registration/register
 - First Name
 - Last Name
 - Mobile Number
 - Email (Optional)
 - generate OTP -> /api/otp/generate
/api/otp/verify

Login:
/api/registration/login
 - Mobile number
 - generate OTP -> /api/otp/generate
/api/otp/verify

/api/otp/generate
/api/otp/verify

 