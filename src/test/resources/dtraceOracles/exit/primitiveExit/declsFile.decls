decl-version 2.0
var-comparability implicit

ppt main.primitiveExitEndpoint&Input:::CLASS
ppt-type class

ppt main.primitiveExitEndpoint&Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.primitiveExitEndpoint&Input
	rep-type hashcode
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.primitiveExitEndpoint&Output&200:::CLASS
ppt-type class

ppt main.primitiveExitEndpoint&Output&200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.primitiveExitEndpoint&Output&200
	rep-type hashcode
variable this.stringProperty
	var-kind field stringProperty
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.doubleProperty
	var-kind field doubleProperty
	enclosing-var this
	dec-type double
	rep-type double
variable this.integerProperty
	var-kind field integerProperty
	enclosing-var this
	dec-type int
	rep-type int
variable this.booleanProperty
	var-kind field booleanProperty
	enclosing-var this
	dec-type boolean
	rep-type boolean


ppt main.primitiveExitEndpoint:::CLASS
ppt-type class

ppt main.primitiveExitEndpoint.primitiveExitEndpoint&200(main.primitiveExitEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.primitiveExitEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.primitiveExitEndpoint.primitiveExitEndpoint&200(main.primitiveExitEndpoint&Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.primitiveExitEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.primitiveExitEndpoint&Output&200
	rep-type hashcode
variable return.stringProperty
	var-kind field stringProperty
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.doubleProperty
	var-kind field doubleProperty
	enclosing-var return
	dec-type double
	rep-type double
variable return.integerProperty
	var-kind field integerProperty
	enclosing-var return
	dec-type int
	rep-type int
variable return.booleanProperty
	var-kind field booleanProperty
	enclosing-var return
	dec-type boolean
	rep-type boolean

