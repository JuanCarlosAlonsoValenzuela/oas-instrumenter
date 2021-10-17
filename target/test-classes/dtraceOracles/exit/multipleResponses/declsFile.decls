decl-version 2.0
var-comparability implicit

ppt main.multipleOperationsEndpoint_Input:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.multipleOperationsEndpoint_Output_200:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Output_200
	rep-type java.lang.String
variable this.stringProperty
	var-kind field stringProperty
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.arrayOfObjectsProperty
	var-kind field arrayOfObjectsProperty
	enclosing-var this
	dec-type main.arrayOfObjectsProperty[]
	rep-type java.lang.String
variable this.arrayOfObjectsProperty[..]
	var-kind array
	enclosing-var this.arrayOfObjectsProperty
	array 1
	dec-type main.arrayOfObjectsProperty[]
	rep-type java.lang.String[]

ppt main.multipleOperationsEndpoint_Output_200_arrayOfObjectsProperty:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Output_200_arrayOfObjectsProperty
	rep-type java.lang.String
variable this.property1
	var-kind field property1
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.multipleOperationsEndpoint_Output_400:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint_Output_400:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Output_400
	rep-type java.lang.String
variable this.errorDescription
	var-kind field errorDescription
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.multipleOperationsEndpoint:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint_200(main.multipleOperationsEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint_200_arrayOfObjectsProperty(main.multipleOperationsEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint_400(main.multipleOperationsEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint_200(main.multipleOperationsEndpoint_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.multipleOperationsEndpoint_Output_200
	rep-type java.lang.String
variable return.stringProperty
	var-kind field stringProperty
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.arrayOfObjectsProperty
	var-kind field arrayOfObjectsProperty
	enclosing-var return
	dec-type main.arrayOfObjectsProperty[]
	rep-type java.lang.String
variable return.arrayOfObjectsProperty[..]
	var-kind array
	enclosing-var return.arrayOfObjectsProperty
	array 1
	dec-type main.arrayOfObjectsProperty[]
	rep-type java.lang.String[]

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint_200_arrayOfObjectsProperty(main.multipleOperationsEndpoint_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.multipleOperationsEndpoint_Output_200_arrayOfObjectsProperty
	rep-type java.lang.String
variable return.property1
	var-kind field property1
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint_400(main.multipleOperationsEndpoint_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.multipleOperationsEndpoint_Output_400
	rep-type java.lang.String
variable return.errorDescription
	var-kind field errorDescription
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

