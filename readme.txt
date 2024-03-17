############### PROJECT #################
Spring Cloud project
Eureka used as register center
greengate-invoice-app-service as a microservice
multi-currency-invoice-gateway is the gateway
you can use port:6003 (gateway port) or 8080(microservice port)
############### How to Start ##############
start in the order with:
in IDE:
EurekaServerApplication,
MultiCurrencyInvoiceGateway,
MultiCurrencyInvoiceServiceApplication,
or mvn build project and
javac each .java file
####################SWAGGER################
http://localhost:8080/swagger-ui/#/
############## TEST CASE ###############
Since I use Java8 for my project and it would be late to upgrade the language level, I change test case to user lower level Java
since the requirements: Exchange rates should be calculated at a precision of 4 decimal places - you must round the Exchange Rates API rates before using them to convert line amounts to line totals.
                        Line totals and the invoice total only need to be calculated to 2 decimal places - you must round each line total.
                        When rounding these numbers, you should round according to standard rules.
                        Rounding should match the Google Sheets ROUND function, for example.
by given: 700 usd, and 500 aud, with rate  "AUD": 0.94212" and "USD": 0.65411

we should use 700/0.9421 = 1070.17
and 500/0.6541 = 530.73
= 1600.9
So I changed the value to 1600.9 instead
############### TO IMPROVE ################
create kafka service for greengate-invoice-app-service microservice to send http request and query response
use data and Cache between greengate-invoice-app-service microservice and frankfurter.app
cache/store data in DB rather than send http request each time

