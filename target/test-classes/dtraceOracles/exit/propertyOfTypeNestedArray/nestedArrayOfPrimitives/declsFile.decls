decl-version 2.0
var-comparability implicit

ppt main.exitOfTypeNestedArrayEndpoint_Input:::CLASS
ppt-type class

ppt main.exitOfTypeNestedArrayEndpoint_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.exitOfTypeNestedArrayEndpoint_Output_200:::CLASS
ppt-type class

ppt main.exitOfTypeNestedArrayEndpoint_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Output_200
	rep-type java.lang.String
variable this.stringProperty
	var-kind field stringProperty
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.nestedArrayProperty
	var-kind field nestedArrayProperty
	enclosing-var this
	dec-type main.nestedArrayProperty[]
	rep-type java.lang.String
variable this.nestedArrayProperty[..]
	var-kind array
	enclosing-var this.nestedArrayProperty
	array 1
	dec-type main.nestedArrayProperty[]
	rep-type java.lang.String[]

ppt main.exitOfTypeNestedArrayEndpoint_Output_200_nestedArrayProperty.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Output_200_nestedArrayProperty.array
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

ppt main.exitOfTypeNestedArrayEndpoint_Output_200_nestedArrayProperty.array.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Output_200_nestedArrayProperty.array.array
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


ppt main.exitOfTypeNestedArrayEndpoint:::CLASS
ppt-type class

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint_200(main.exitOfTypeNestedArrayEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint_200_nestedArrayProperty.array(main.exitOfTypeNestedArrayEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint_200_nestedArrayProperty.array.array(main.exitOfTypeNestedArrayEndpoint_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint_200(main.exitOfTypeNestedArrayEndpoint_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeNestedArrayEndpoint_Output_200
	rep-type java.lang.String
variable return.stringProperty
	var-kind field stringProperty
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.nestedArrayProperty
	var-kind field nestedArrayProperty
	enclosing-var return
	dec-type main.nestedArrayProperty[]
	rep-type java.lang.String
variable return.nestedArrayProperty[..]
	var-kind array
	enclosing-var return.nestedArrayProperty
	array 1
	dec-type main.nestedArrayProperty[]
	rep-type java.lang.String[]

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint_200_nestedArrayProperty.array(main.exitOfTypeNestedArrayEndpoint_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeNestedArrayEndpoint_Output_200_nestedArrayProperty.array
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

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint_200_nestedArrayProperty.array.array(main.exitOfTypeNestedArrayEndpoint_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint_Input
	rep-type java.lang.String
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeNestedArrayEndpoint_Output_200_nestedArrayProperty.array.array
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

