# RTB2022SpringReactive

**Prerequisite-**

•	Maven.

•	Mongodb (local installation on default port 8080).

•	Okta Account.


**Setup Steps: -**

•	Checkout project and open in IDE like Eclipse or STS.

•	Build project.

•	Run DemoApplication.java as java application.

•	Run mongodb server.

•	Refer attached postman collection (RTB_APIs.postman_collection.json) for accessing APIs.



**Authorization: -**

•	Open "OpenID Connect <debugger/>" by clicking URL- https://oidcdebugger.com/ 

•	Enter below Okta credentials when prompt – 

   -	Username – qwertykeypad1614@gmail.com

   -	Password - RTB@okta1234

•	Refer below screenshot and enter required details: -

   -	Authorize URI - https://dev-04150982.okta.com/oauth2/default/v1/authorize

   -	Redirect URL- https://oidcdebugger.com/debug

   -	Client ID - 0oa3u9j9ubTZHaunA5d7

   -	Scope – openid

   -	State – 1234

   -	Response mode- form_post


![image](https://user-images.githubusercontent.com/63362526/153239966-771b5cbf-3813-4038-b34c-ac9698b8ea9c.png)

•	Click on Send request and you will get access token like as below: -
 
![image](https://user-images.githubusercontent.com/63362526/153241379-db2f6b71-d218-46bc-afc5-17e492593697.png)

•	Use this as authorization header in postman request.

Hit - http://localhost:8080/profiles to view profiles on browser.
