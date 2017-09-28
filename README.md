# Spring Content Negotiation Demo
Working out which data format to return is called Content Negotiation.

There are three situations where we need to know what type of data-format to send in the HTTP response:
* HttpMessageConverters: Determine the right converter to use.
* Request Mappings: Map an incoming HTTP request to different methods that return different formats.
* View Resolution: Pick the right view to use.

Determining what format the user has requested relies on a ContentNegotationStrategy.

## Enabling Content Negotiation in Spring MVC
1. Add a path extension (suffix) in the URL. So, if the incoming URL is something like <host>:<port>/spring-content-negotiation-demo/user/1.html then HTML is required.
2. A URL parameter like this: <host>:<port>/spring-content-negotiation-demo/user/1?format=xls. The name of the parameter is format by default, but this may be changed.
3. Finally the Accept HTTP header property is checked.

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

## Acknowledgments
* https://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc


