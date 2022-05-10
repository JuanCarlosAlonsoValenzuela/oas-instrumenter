decl-version 2.0
var-comparability implicit

ppt main.sampleEndpointId&Input:::CLASS
ppt-type class

ppt main.sampleEndpointId&Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable this.parameter1
	var-kind field parameter1
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.parameter2
	var-kind field parameter2
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.sampleEndpointId&Output&200:::CLASS
ppt-type class

ppt main.sampleEndpointId&Output&200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId&Output&200
	rep-type hashcode
variable this.locations
	var-kind field locations
	enclosing-var this
	dec-type main.locations[]
	rep-type hashcode
variable this.locations[..]
	var-kind array
	enclosing-var this.locations
	array 1
	dec-type main.locations[]
	rep-type hashcode[]

ppt main.sampleEndpointId&Output&200&locations.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId&Output&200&locations.array
	rep-type hashcode
variable this.array
	var-kind field array
	enclosing-var this
	dec-type main.array[]
	rep-type hashcode
variable this.array[..]
	var-kind array
	enclosing-var this.array
	array 1
	dec-type main.array[]
	rep-type hashcode[]

ppt main.sampleEndpointId&Output&200&locations:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId&Output&200&locations
	rep-type hashcode
variable this.locationId
	var-kind field locationId
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.provider
	var-kind field provider
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.sampleEndpointId&Output&200&locations.array.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId&Output&200&locations.array.array
	rep-type hashcode
variable this.array
	var-kind field array
	enclosing-var this
	dec-type main.array[]
	rep-type hashcode
variable this.array[..]
	var-kind array
	enclosing-var this.array
	array 1
	dec-type main.array[]
	rep-type hashcode[]


ppt main.sampleEndpoint:::CLASS
ppt-type class

ppt main.sampleEndpoint.sampleEndpointId&200(main.sampleEndpointId&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.sampleEndpoint.sampleEndpointId&200&locations.array(main.sampleEndpointId&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.sampleEndpoint.sampleEndpointId&200&locations(main.sampleEndpointId&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.sampleEndpoint.sampleEndpointId&200&locations.array.array(main.sampleEndpointId&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.sampleEndpoint.sampleEndpointId&200(main.sampleEndpointId&Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.sampleEndpointId&Output&200
	rep-type hashcode
variable return.locations
	var-kind field locations
	enclosing-var return
	dec-type main.locations[]
	rep-type hashcode
variable return.locations[..]
	var-kind array
	enclosing-var return.locations
	array 1
	dec-type main.locations[]
	rep-type hashcode[]

ppt main.sampleEndpoint.sampleEndpointId&200&locations.array(main.sampleEndpointId&Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.sampleEndpointId&Output&200&locations.array
	rep-type hashcode
variable return.array
	var-kind field array
	enclosing-var return
	dec-type main.array[]
	rep-type hashcode
variable return.array[..]
	var-kind array
	enclosing-var return.array
	array 1
	dec-type main.array[]
	rep-type hashcode[]

ppt main.sampleEndpoint.sampleEndpointId&200&locations(main.sampleEndpointId&Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.sampleEndpointId&Output&200&locations
	rep-type hashcode
variable return.locationId
	var-kind field locationId
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.provider
	var-kind field provider
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.sampleEndpoint.sampleEndpointId&200&locations.array.array(main.sampleEndpointId&Input):::EXIT4
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId&Input
	rep-type hashcode
variable input.parameter1
	var-kind field parameter1
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.parameter2
	var-kind field parameter2
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.sampleEndpointId&Output&200&locations.array.array
	rep-type hashcode
variable return.array
	var-kind field array
	enclosing-var return
	dec-type main.array[]
	rep-type hashcode
variable return.array[..]
	var-kind array
	enclosing-var return.array
	array 1
	dec-type main.array[]
	rep-type hashcode[]

