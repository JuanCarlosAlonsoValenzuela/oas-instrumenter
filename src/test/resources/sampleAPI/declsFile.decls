decl-version 2.0
var-comparability implicit

ppt main.sampleEndpointId_Input:::CLASS
ppt-type class

ppt main.sampleEndpointId_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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


ppt main.sampleEndpointId_Output_200:::CLASS
ppt-type class

ppt main.sampleEndpointId_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId_Output_200
	rep-type java.lang.String
variable this.locations
	var-kind field locations
	enclosing-var this
	dec-type main.locations[]
	rep-type java.lang.String
variable this.locations[..]
	var-kind array
	enclosing-var this.locations
	array 1
	dec-type main.locations[]
	rep-type java.lang.String[]

ppt main.sampleEndpointId_Output_200_locations.array.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId_Output_200_locations.array.array
	rep-type java.lang.String
variable this.array
	var-kind field array
	enclosing-var this
	dec-type double[]
	rep-type java.lang.String
variable this.array[..]
	var-kind array
	enclosing-var this.array
	array 1
	dec-type double[]
	rep-type double[]

ppt main.sampleEndpointId_Output_200_locations.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.sampleEndpointId_Output_200_locations.array
	rep-type java.lang.String
variable this.array
	var-kind field array
	enclosing-var this
	dec-type main.array[]
	rep-type java.lang.String
variable this.array[..]
	var-kind array
	enclosing-var this.array
	array 1
	dec-type main.array[]
	rep-type java.lang.String[]


ppt main.sampleEndpoint:::CLASS
ppt-type class

ppt main.sampleEndpoint.sampleEndpointId_200(main.sampleEndpointId_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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

ppt main.sampleEndpoint.sampleEndpointId_200_locations.array.array(main.sampleEndpointId_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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

ppt main.sampleEndpoint.sampleEndpointId_200_locations.array(main.sampleEndpointId_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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

ppt main.sampleEndpoint.sampleEndpointId_200(main.sampleEndpointId_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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
	dec-type main.sampleEndpointId_Output_200
	rep-type java.lang.String
variable return.locations
	var-kind field locations
	enclosing-var return
	dec-type main.locations[]
	rep-type java.lang.String
variable return.locations[..]
	var-kind array
	enclosing-var return.locations
	array 1
	dec-type main.locations[]
	rep-type java.lang.String[]

ppt main.sampleEndpoint.sampleEndpointId_200_locations.array.array(main.sampleEndpointId_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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
	dec-type main.sampleEndpointId_Output_200_locations.array.array
	rep-type java.lang.String
variable return.array
	var-kind field array
	enclosing-var return
	dec-type double[]
	rep-type java.lang.String
variable return.array[..]
	var-kind array
	enclosing-var return.array
	array 1
	dec-type double[]
	rep-type double[]

ppt main.sampleEndpoint.sampleEndpointId_200_locations.array(main.sampleEndpointId_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.sampleEndpointId_Input
	rep-type java.lang.String
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
	dec-type main.sampleEndpointId_Output_200_locations.array
	rep-type java.lang.String
variable return.array
	var-kind field array
	enclosing-var return
	dec-type main.array[]
	rep-type java.lang.String
variable return.array[..]
	var-kind array
	enclosing-var return.array
	array 1
	dec-type main.array[]
	rep-type java.lang.String[]

