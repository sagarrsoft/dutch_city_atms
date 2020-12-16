Rquirements ::

Create a Java web application based on the standard servlet spec
• The web application should provide a REST api to create a list of ING ATMs in a
given Dutch city and return a well formed JSON response
• The web application should invoke an external service to gather a super set of the
data: https://www.ing.nl/api/locator/atms/
• Use Spring and Maven. If you do not have experience in these frameworks, please
feel free to use an alternative

Solution provided
# dutch_city_atms

steps  ::
1)Hit the url and got the json format https://www.ing.nl/api/locator/atms/ 
2)Analysed the josn and converted json to java using code beautify url.
3)IngAtmController is the controller class and used spring annotation.
4)Used RestTemplate to get the entity list.
5) Get city name from path variable parameter. 
6) Send Rest request to external service using rest template.
7) Remove wrong start from the response.
8) Convert adapted response to list.
9) Using streams API filter returned list based on city name.
10) Return response to user.
11) POM support war, jar embedded tomcat, jar embedded jetty.
