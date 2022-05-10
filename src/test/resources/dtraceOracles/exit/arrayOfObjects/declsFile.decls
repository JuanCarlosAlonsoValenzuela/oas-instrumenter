decl-version 2.0
var-comparability implicit

ppt main.arrayOfObjectsEndpoint&Input:::CLASS
ppt-type class

ppt main.arrayOfObjectsEndpoint&Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Input
	rep-type hashcode
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.arrayOfObjectsEndpoint&Output&200:::CLASS
ppt-type class

ppt main.arrayOfObjectsEndpoint&Output&200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Output&200
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

ppt main.arrayOfObjectsEndpoint&Output&200&arrayOfObjectsProperty:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Output&200&arrayOfObjectsProperty
	rep-type hashcode
variable this.property1
	var-kind field property1
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.property2
	var-kind field property2
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.arrayOfObjectsEndpoint:::CLASS
ppt-type class

ppt main.arrayOfObjectsEndpoint.arrayOfObjectsEndpoint&200(main.arrayOfObjectsEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.arrayOfObjectsEndpoint.arrayOfObjectsEndpoint&200&arrayOfObjectsProperty(main.arrayOfObjectsEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.arrayOfObjectsEndpoint.arrayOfObjectsEndpoint&200(main.arrayOfObjectsEndpoint&Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.arrayOfObjectsEndpoint&Output&200
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

ppt main.arrayOfObjectsEndpoint.arrayOfObjectsEndpoint&200&arrayOfObjectsProperty(main.arrayOfObjectsEndpoint&Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.arrayOfObjectsEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.arrayOfObjectsEndpoint&Output&200&arrayOfObjectsProperty
	rep-type hashcode
variable return.property1
	var-kind field property1
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.property2
	var-kind field property2
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

