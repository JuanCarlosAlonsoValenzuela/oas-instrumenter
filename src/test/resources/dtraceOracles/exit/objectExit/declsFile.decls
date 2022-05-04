decl-version 2.0
var-comparability implicit

ppt main.objectExitEndpoint_Input:::CLASS
ppt-type class

ppt main.objectExitEndpoint_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.objectExitEndpoint_Input
	rep-type hashcode
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.objectExitEndpoint_Output_200:::CLASS
ppt-type class

ppt main.objectExitEndpoint_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.objectExitEndpoint_Output_200
	rep-type hashcode
variable this.stringProperty
	var-kind field stringProperty
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.objectProperty
	var-kind variable
	enclosing-var this
	dec-type main.objectExitEndpoint_Output_200_objectProperty
	rep-type hashcode
variable this.objectProperty.arrayStringProperty
	var-kind field arrayStringProperty
	enclosing-var this.objectProperty
	dec-type java.lang.String[]
	rep-type hashcode
variable this.objectProperty.arrayStringProperty[..]
	var-kind array
	enclosing-var this.objectProperty.arrayStringProperty
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.objectProperty.subObjectProperty
	var-kind variable
	enclosing-var this.objectProperty
	dec-type main.objectExitEndpoint_Output_200_subObjectProperty
	rep-type hashcode
variable this.objectProperty.subObjectProperty.stringProperty
	var-kind field stringProperty
	enclosing-var this.objectProperty.subObjectProperty
	dec-type java.lang.String
	rep-type java.lang.String
variable this.objectProperty.subObjectProperty.arrayDoubleProperty
	var-kind field arrayDoubleProperty
	enclosing-var this.objectProperty.subObjectProperty
	dec-type double[]
	rep-type hashcode
variable this.objectProperty.subObjectProperty.arrayDoubleProperty[..]
	var-kind array
	enclosing-var this.objectProperty.subObjectProperty.arrayDoubleProperty
	array 1
	dec-type double[]
	rep-type double[]


ppt main.objectExitEndpoint:::CLASS
ppt-type class

ppt main.objectExitEndpoint.objectExitEndpoint_200(main.objectExitEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.objectExitEndpoint_Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.objectExitEndpoint.objectExitEndpoint_200(main.objectExitEndpoint_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.objectExitEndpoint_Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.objectExitEndpoint_Output_200
	rep-type hashcode
variable return.stringProperty
	var-kind field stringProperty
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.objectProperty
	var-kind return
	enclosing-var return
	dec-type main.objectExitEndpoint_Output_200_objectProperty
	rep-type hashcode
variable return.objectProperty.arrayStringProperty
	var-kind field arrayStringProperty
	enclosing-var return.objectProperty
	dec-type java.lang.String[]
	rep-type hashcode
variable return.objectProperty.arrayStringProperty[..]
	var-kind array
	enclosing-var return.objectProperty.arrayStringProperty
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.objectProperty.subObjectProperty
	var-kind return
	enclosing-var return.objectProperty
	dec-type main.objectExitEndpoint_Output_200_subObjectProperty
	rep-type hashcode
variable return.objectProperty.subObjectProperty.stringProperty
	var-kind field stringProperty
	enclosing-var return.objectProperty.subObjectProperty
	dec-type java.lang.String
	rep-type java.lang.String
variable return.objectProperty.subObjectProperty.arrayDoubleProperty
	var-kind field arrayDoubleProperty
	enclosing-var return.objectProperty.subObjectProperty
	dec-type double[]
	rep-type hashcode
variable return.objectProperty.subObjectProperty.arrayDoubleProperty[..]
	var-kind array
	enclosing-var return.objectProperty.subObjectProperty.arrayDoubleProperty
	array 1
	dec-type double[]
	rep-type double[]

