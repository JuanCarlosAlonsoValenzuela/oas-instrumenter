decl-version 2.0
var-comparability implicit

ppt main.browseCategoriesByCategoryId_Input:::CLASS
ppt-type class

ppt main.browseCategoriesByCategoryId_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.browseCategoriesByCategoryId_Input
	rep-type java.lang.String
variable this.category_id
	var-kind field category_id
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.country
	var-kind field country
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.locale
	var-kind field locale
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.Accept
	var-kind field Accept
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.browseCategoriesByCategoryId_Output_200:::CLASS
ppt-type class

ppt main.browseCategoriesByCategoryId_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.browseCategoriesByCategoryId_Output_200
	rep-type java.lang.String
variable this.href
	var-kind field href
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.icons
	var-kind field icons
	enclosing-var this
	dec-type main.icons[]
	rep-type java.lang.String
variable this.icons[..]
	var-kind array
	enclosing-var this.icons
	array 1
	dec-type main.icons[]
	rep-type java.lang.String[]
variable this.icons[..].height
	var-kind field height
	enclosing-var this.icons[..]
	array 1
	dec-type int[]
	rep-type int[]
variable this.icons[..].url
	var-kind field url
	enclosing-var this.icons[..]
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.icons[..].width
	var-kind field width
	enclosing-var this.icons[..]
	array 1
	dec-type int[]
	rep-type int[]
variable this.id
	var-kind field id
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.name
	var-kind field name
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.browsecategories{category_id}:::CLASS
ppt-type class

ppt main.browsecategories{category_id}.browseCategoriesByCategoryId(main.browseCategoriesByCategoryId_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.browseCategoriesByCategoryId_Input
	rep-type java.lang.String
variable input.category_id
	var-kind field category_id
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.country
	var-kind field country
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.Accept
	var-kind field Accept
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.browsecategories{category_id}.browseCategoriesByCategoryId(main.browseCategoriesByCategoryId_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.browseCategoriesByCategoryId_Input
	rep-type java.lang.String
variable input.category_id
	var-kind field category_id
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.country
	var-kind field country
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.Accept
	var-kind field Accept
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.browseCategoriesByCategoryId_Output_200
	rep-type java.lang.String
variable return.href
	var-kind field href
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.icons
	var-kind field icons
	enclosing-var return
	dec-type main.icons[]
	rep-type java.lang.String
variable return.icons[..]
	var-kind array
	enclosing-var return.icons
	array 1
	dec-type main.icons[]
	rep-type java.lang.String[]
variable return.icons[..].height
	var-kind field height
	enclosing-var return.icons[..]
	array 1
	dec-type int[]
	rep-type int[]
variable return.icons[..].url
	var-kind field url
	enclosing-var return.icons[..]
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.icons[..].width
	var-kind field width
	enclosing-var return.icons[..]
	array 1
	dec-type int[]
	rep-type int[]
variable return.id
	var-kind field id
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.name
	var-kind field name
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

