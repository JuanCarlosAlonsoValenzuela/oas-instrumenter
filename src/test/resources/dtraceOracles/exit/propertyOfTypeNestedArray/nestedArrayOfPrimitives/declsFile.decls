decl-version 2.0
var-comparability implicit

ppt main.exitOfTypeNestedArrayEndpoint&Input:::CLASS
ppt-type class

ppt main.exitOfTypeNestedArrayEndpoint&Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.exitOfTypeNestedArrayEndpoint&Output&200:::CLASS
ppt-type class

ppt main.exitOfTypeNestedArrayEndpoint&Output&200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Output&200
	rep-type hashcode
variable this.stringProperty
	var-kind field stringProperty
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.nestedArrayProperty
	var-kind field nestedArrayProperty
	enclosing-var this
	dec-type main.nestedArrayProperty[]
	rep-type hashcode
variable this.nestedArrayProperty[..]
	var-kind array
	enclosing-var this.nestedArrayProperty
	array 1
	dec-type main.nestedArrayProperty[]
	rep-type hashcode[]

ppt main.exitOfTypeNestedArrayEndpoint&Output&200&nestedArrayProperty.array.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Output&200&nestedArrayProperty.array.array
	rep-type hashcode
variable this.array
	var-kind field array
	enclosing-var this
	dec-type double[]
	rep-type hashcode
variable this.array[..]
	var-kind array
	enclosing-var this.array
	array 1
	dec-type double[]
	rep-type double[]

ppt main.exitOfTypeNestedArrayEndpoint&Output&200&nestedArrayProperty.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Output&200&nestedArrayProperty.array
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


ppt main.exitOfTypeNestedArrayEndpoint:::CLASS
ppt-type class

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint&200(main.exitOfTypeNestedArrayEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint&200&nestedArrayProperty.array.array(main.exitOfTypeNestedArrayEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint&200&nestedArrayProperty.array(main.exitOfTypeNestedArrayEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint&200(main.exitOfTypeNestedArrayEndpoint&Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeNestedArrayEndpoint&Output&200
	rep-type hashcode
variable return.stringProperty
	var-kind field stringProperty
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.nestedArrayProperty
	var-kind field nestedArrayProperty
	enclosing-var return
	dec-type main.nestedArrayProperty[]
	rep-type hashcode
variable return.nestedArrayProperty[..]
	var-kind array
	enclosing-var return.nestedArrayProperty
	array 1
	dec-type main.nestedArrayProperty[]
	rep-type hashcode[]

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint&200&nestedArrayProperty.array.array(main.exitOfTypeNestedArrayEndpoint&Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeNestedArrayEndpoint&Output&200&nestedArrayProperty.array.array
	rep-type hashcode
variable return.array
	var-kind field array
	enclosing-var return
	dec-type double[]
	rep-type hashcode
variable return.array[..]
	var-kind array
	enclosing-var return.array
	array 1
	dec-type double[]
	rep-type double[]

ppt main.exitOfTypeNestedArrayEndpoint.exitOfTypeNestedArrayEndpoint&200&nestedArrayProperty.array(main.exitOfTypeNestedArrayEndpoint&Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeNestedArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeNestedArrayEndpoint&Output&200&nestedArrayProperty.array
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

