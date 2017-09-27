# Spring Content Negotiation Demo
This initiative is to try out Spring's Content Negotiation feature.
I've tried to present demonstration of following media types as response:
* HTML
* JSON
* XML
* PDF
* XLSX

## Getting Started

Use below instructions to get the project up and running on your local work station for reference purposes.

### Prerequisites
* JAVA 1.6 or higher
* Eclipse IDE with Maven(M2E plugin)

### Installing & Running

* Import the project to your Eclipse IDE
* Do clean build
* Add a server(Tomcat/others) & add the application to it.
* Run the server.

### Use following URLs for demonstration

```
<host>:<port>/spring-content-negotiation-demo/user/1
```
```
<host>:<port>/spring-content-negotiation-demo/user/1.html
```
```
<host>:<port>/spring-content-negotiation-demo/user/1.json
```
```
<host>:<port>/spring-content-negotiation-demo/user/1.xml
```
```
<host>:<port>/spring-content-negotiation-demo/user/1.xlsx
```
```
<host>:<port>/spring-content-negotiation-demo/user/1.pdf
```

```
<host>:<port>/spring-content-negotiation-demo/user/1>mediaType=json
```
```
<host>:<port>/spring-content-negotiation-demo/user/1>mediaType=xml
```
```
<host>:<port>/spring-content-negotiation-demo/user/1>mediaType=pdf
```
```
and so on...
```



