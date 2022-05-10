decl-version 2.0
var-comparability implicit

ppt main.bySearch_Input:::CLASS
ppt-type class

ppt main.bySearch_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable this.s
	var-kind field s
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.type
	var-kind field type
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.y
	var-kind field y
	enclosing-var this
	dec-type int
	rep-type int
variable this.r
	var-kind field r
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.page
	var-kind field page
	enclosing-var this
	dec-type int
	rep-type int


ppt main.bySearch_Output_200:::CLASS
ppt-type class

ppt main.bySearch_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.bySearch_Output_200
	rep-type hashcode
variable this.Response
	var-kind field Response
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.Search
	var-kind field Search
	enclosing-var this
	dec-type main.Search[]
	rep-type hashcode
variable this.Search[..]
	var-kind array
	enclosing-var this.Search
	array 1
	dec-type main.Search[]
	rep-type hashcode[]
variable this.totalResults
	var-kind field totalResults
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.bySearch_Output_200_Search:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.bySearch_Output_200_Search
	rep-type hashcode
variable this.Title
	var-kind field Title
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.Year
	var-kind field Year
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.imdbID
	var-kind field imdbID
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.Type
	var-kind field Type
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.Poster
	var-kind field Poster
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.bySearch_Output_401:::CLASS
ppt-type class

ppt main.bySearch_Output_401:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.bySearch_Output_401
	rep-type hashcode
variable this.Response
	var-kind field Response
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.Error
	var-kind field Error
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.:::CLASS
ppt-type class

ppt main..bySearch_200(main.bySearch_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable input.s
	var-kind field s
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.type
	var-kind field type
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.y
	var-kind field y
	enclosing-var input
	dec-type int
	rep-type int
variable input.r
	var-kind field r
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.page
	var-kind field page
	enclosing-var input
	dec-type int
	rep-type int

ppt main..bySearch_200_Search(main.bySearch_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable input.s
	var-kind field s
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.type
	var-kind field type
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.y
	var-kind field y
	enclosing-var input
	dec-type int
	rep-type int
variable input.r
	var-kind field r
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.page
	var-kind field page
	enclosing-var input
	dec-type int
	rep-type int

ppt main..bySearch_401(main.bySearch_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable input.s
	var-kind field s
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.type
	var-kind field type
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.y
	var-kind field y
	enclosing-var input
	dec-type int
	rep-type int
variable input.r
	var-kind field r
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.page
	var-kind field page
	enclosing-var input
	dec-type int
	rep-type int

ppt main..bySearch_200(main.bySearch_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable input.s
	var-kind field s
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.type
	var-kind field type
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.y
	var-kind field y
	enclosing-var input
	dec-type int
	rep-type int
variable input.r
	var-kind field r
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.page
	var-kind field page
	enclosing-var input
	dec-type int
	rep-type int
variable return
	var-kind return
	dec-type main.bySearch_Output_200
	rep-type hashcode
variable return.Response
	var-kind field Response
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.Search
	var-kind field Search
	enclosing-var return
	dec-type main.Search[]
	rep-type hashcode
variable return.Search[..]
	var-kind array
	enclosing-var return.Search
	array 1
	dec-type main.Search[]
	rep-type hashcode[]
variable return.totalResults
	var-kind field totalResults
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main..bySearch_200_Search(main.bySearch_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable input.s
	var-kind field s
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.type
	var-kind field type
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.y
	var-kind field y
	enclosing-var input
	dec-type int
	rep-type int
variable input.r
	var-kind field r
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.page
	var-kind field page
	enclosing-var input
	dec-type int
	rep-type int
variable return
	var-kind return
	dec-type main.bySearch_Output_200_Search
	rep-type hashcode
variable return.Title
	var-kind field Title
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.Year
	var-kind field Year
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.imdbID
	var-kind field imdbID
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.Type
	var-kind field Type
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.Poster
	var-kind field Poster
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main..bySearch_401(main.bySearch_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.bySearch_Input
	rep-type hashcode
variable input.s
	var-kind field s
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.type
	var-kind field type
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.y
	var-kind field y
	enclosing-var input
	dec-type int
	rep-type int
variable input.r
	var-kind field r
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.page
	var-kind field page
	enclosing-var input
	dec-type int
	rep-type int
variable return
	var-kind return
	dec-type main.bySearch_Output_401
	rep-type hashcode
variable return.Response
	var-kind field Response
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.Error
	var-kind field Error
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

