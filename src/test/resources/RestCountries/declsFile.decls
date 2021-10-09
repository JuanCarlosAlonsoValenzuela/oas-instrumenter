decl-version 2.0
var-comparability implicit

ppt main.v1Name_Input:::CLASS
ppt-type class

ppt main.v1Name_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.v1Name_Input
	rep-type java.lang.String
variable this.name
	var-kind field name
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.fullText
	var-kind field fullText
	enclosing-var this
	dec-type boolean
	rep-type boolean


ppt main.v1Name_Output_200:::CLASS
ppt-type class

ppt main.v1Name_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.v1Name_Output_200
	rep-type java.lang.String
variable this.primitive
	var-kind field primitive
	enclosing-var this
	dec-type double
	rep-type double


ppt main.v1name{name}:::CLASS
ppt-type class

ppt main.v1name{name}.v1Name_200(main.v1Name_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.v1Name_Input
	rep-type java.lang.String
variable input.name
	var-kind field name
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.fullText
	var-kind field fullText
	enclosing-var input
	dec-type boolean
	rep-type boolean

ppt main.v1name{name}.v1Name_200(main.v1Name_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.v1Name_Input
	rep-type java.lang.String
variable input.name
	var-kind field name
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.fullText
	var-kind field fullText
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable return
	var-kind return
	dec-type main.v1Name_Output_200
	rep-type java.lang.String
variable return.primitive
	var-kind field primitive
	enclosing-var return
	dec-type double
	rep-type double

