decl-version 2.0
var-comparability implicit

ppt main.multipleOperationsEndpoint&Input:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint&Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.multipleOperationsEndpoint&Output&200:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint&Output&200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Output&200
	rep-type hashcode
variable this.stringProperty
	var-kind field stringProperty
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.arrayOfObjectsProperty
	var-kind field arrayOfObjectsProperty
	enclosing-var this
	dec-type main.arrayOfObjectsProperty[]
	rep-type hashcode
variable this.arrayOfObjectsProperty[..]
	var-kind array
	enclosing-var this.arrayOfObjectsProperty
	array 1
	dec-type main.arrayOfObjectsProperty[]
	rep-type hashcode[]

ppt main.multipleOperationsEndpoint&Output&200&arrayOfObjectsProperty:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Output&200&arrayOfObjectsProperty
	rep-type hashcode
variable this.property1
	var-kind field property1
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.multipleOperationsEndpoint&Output&400:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint&Output&400:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Output&400
	rep-type hashcode
variable this.errorDescription
	var-kind field errorDescription
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.multipleOperationsEndpoint:::CLASS
ppt-type class

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint&200(main.multipleOperationsEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint&200&arrayOfObjectsProperty(main.multipleOperationsEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint&400(main.multipleOperationsEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint&200(main.multipleOperationsEndpoint&Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.multipleOperationsEndpoint&Output&200
	rep-type hashcode
variable return.stringProperty
	var-kind field stringProperty
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.arrayOfObjectsProperty
	var-kind field arrayOfObjectsProperty
	enclosing-var return
	dec-type main.arrayOfObjectsProperty[]
	rep-type hashcode
variable return.arrayOfObjectsProperty[..]
	var-kind array
	enclosing-var return.arrayOfObjectsProperty
	array 1
	dec-type main.arrayOfObjectsProperty[]
	rep-type hashcode[]

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint&200&arrayOfObjectsProperty(main.multipleOperationsEndpoint&Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.multipleOperationsEndpoint&Output&200&arrayOfObjectsProperty
	rep-type hashcode
variable return.property1
	var-kind field property1
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.multipleOperationsEndpoint.multipleOperationsEndpoint&400(main.multipleOperationsEndpoint&Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.multipleOperationsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.multipleOperationsEndpoint&Output&400
	rep-type hashcode
variable return.errorDescription
	var-kind field errorDescription
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

