decl-version 2.0
var-comparability implicit

ppt main.exitOfTypeArrayEndpoint&Input:::CLASS
ppt-type class

ppt main.exitOfTypeArrayEndpoint&Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Input
	rep-type hashcode
variable this.inputParameter
	var-kind field inputParameter
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.exitOfTypeArrayEndpoint&Output&200:::CLASS
ppt-type class

ppt main.exitOfTypeArrayEndpoint&Output&200.array:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Output&200.array
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

ppt main.exitOfTypeArrayEndpoint&Output&200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Output&200
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


ppt main.exitOfTypeArrayEndpoint:::CLASS
ppt-type class

ppt main.exitOfTypeArrayEndpoint.exitOfTypeArrayEndpoint&200.array(main.exitOfTypeArrayEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeArrayEndpoint.exitOfTypeArrayEndpoint&200(main.exitOfTypeArrayEndpoint&Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.exitOfTypeArrayEndpoint.exitOfTypeArrayEndpoint&200.array(main.exitOfTypeArrayEndpoint&Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeArrayEndpoint&Output&200.array
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

ppt main.exitOfTypeArrayEndpoint.exitOfTypeArrayEndpoint&200(main.exitOfTypeArrayEndpoint&Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.exitOfTypeArrayEndpoint&Input
	rep-type hashcode
variable input.inputParameter
	var-kind field inputParameter
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.exitOfTypeArrayEndpoint&Output&200
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

