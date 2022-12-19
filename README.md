# OAS-INSTRUMENTER
OAS-INSTRUMENTER is a tool for automated generation of test oracles in the context of black-box testing of RESTful APIs.

This software takes as input an OpenAPI specification (OAS) of a REST API and a set of test cases in csv format. Each test case is an API requests containing simply the input values and the response in JSON format.

This instrumenter processes these files and generates two files (decls file and dtrace file) that can be processed by the DAIKON library for automated detection of likely invariants, resulting in a set of invariants that can be used as test oracles.


![images/approachWorkflow.png](http://url/to/img.png)