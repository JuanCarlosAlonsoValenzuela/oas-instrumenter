decl-version 2.0
var-comparability implicit

ppt main.getBusinesses_Input:::CLASS
ppt-type class

ppt main.getBusinesses_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable this.term
	var-kind field term
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location
	var-kind field location
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.latitude
	var-kind field latitude
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.longitude
	var-kind field longitude
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.radius
	var-kind field radius
	enclosing-var this
	dec-type int
	rep-type int
variable this.categories
	var-kind field categories
	enclosing-var this
	dec-type java.lang.String[]
	rep-type hashcode
variable this.categories[..]
	var-kind array
	enclosing-var this.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.locale
	var-kind field locale
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.limit
	var-kind field limit
	enclosing-var this
	dec-type int
	rep-type int
variable this.offset
	var-kind field offset
	enclosing-var this
	dec-type int
	rep-type int
variable this.sort_by
	var-kind field sort_by
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price
	var-kind field price
	enclosing-var this
	dec-type java.lang.String[]
	rep-type hashcode
variable this.price[..]
	var-kind array
	enclosing-var this.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.open_now
	var-kind field open_now
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.open_at
	var-kind field open_at
	enclosing-var this
	dec-type int
	rep-type int
variable this.attributes
	var-kind field attributes
	enclosing-var this
	dec-type java.lang.String[]
	rep-type hashcode
variable this.attributes[..]
	var-kind array
	enclosing-var this.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]


ppt main.getBusinesses_Output_200:::CLASS
ppt-type class

ppt main.getBusinesses_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getBusinesses_Output_200
	rep-type hashcode
variable this.total
	var-kind field total
	enclosing-var this
	dec-type int
	rep-type int
variable this.region
	var-kind variable
	enclosing-var this
	dec-type main.getBusinesses_Output_200_region
	rep-type hashcode
variable this.region.center
	var-kind variable
	enclosing-var this.region
	dec-type main.getBusinesses_Output_200_center
	rep-type hashcode
variable this.region.center.latitude
	var-kind field latitude
	enclosing-var this.region.center
	dec-type double
	rep-type double
variable this.region.center.longitude
	var-kind field longitude
	enclosing-var this.region.center
	dec-type double
	rep-type double
variable this.businesses
	var-kind field businesses
	enclosing-var this
	dec-type main.businesses[]
	rep-type hashcode
variable this.businesses[..]
	var-kind array
	enclosing-var this.businesses
	array 1
	dec-type main.businesses[]
	rep-type hashcode[]

ppt main.getBusinesses_Output_200_businesses_categories:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getBusinesses_Output_200_businesses_categories
	rep-type hashcode
variable this.alias
	var-kind field alias
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.title
	var-kind field title
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getBusinesses_Output_200_businesses:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getBusinesses_Output_200_businesses
	rep-type hashcode
variable this.categories
	var-kind field categories
	enclosing-var this
	dec-type main.categories[]
	rep-type hashcode
variable this.categories[..]
	var-kind array
	enclosing-var this.categories
	array 1
	dec-type main.categories[]
	rep-type hashcode[]
variable this.coordinates
	var-kind variable
	enclosing-var this
	dec-type main.getBusinesses_Output_200_businesses_coordinates
	rep-type hashcode
variable this.coordinates.latitude
	var-kind field latitude
	enclosing-var this.coordinates
	dec-type double
	rep-type double
variable this.coordinates.longitude
	var-kind field longitude
	enclosing-var this.coordinates
	dec-type double
	rep-type double
variable this.display_phone
	var-kind field display_phone
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.distance
	var-kind field distance
	enclosing-var this
	dec-type double
	rep-type double
variable this.id
	var-kind field id
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.alias
	var-kind field alias
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.image_url
	var-kind field image_url
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.is_closed
	var-kind field is_closed
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.name
	var-kind field name
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.phone
	var-kind field phone
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price
	var-kind field price
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.rating
	var-kind field rating
	enclosing-var this
	dec-type double
	rep-type double
variable this.review_count
	var-kind field review_count
	enclosing-var this
	dec-type int
	rep-type int
variable this.url
	var-kind field url
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.transactions
	var-kind field transactions
	enclosing-var this
	dec-type java.lang.String[]
	rep-type hashcode
variable this.transactions[..]
	var-kind array
	enclosing-var this.transactions
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.location
	var-kind variable
	enclosing-var this
	dec-type main.getBusinesses_Output_200_businesses_location
	rep-type hashcode
variable this.location.address1
	var-kind field address1
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.address2
	var-kind field address2
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.address3
	var-kind field address3
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.city
	var-kind field city
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.country
	var-kind field country
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.display_address
	var-kind field display_address
	enclosing-var this.location
	dec-type java.lang.String[]
	rep-type hashcode
variable this.location.display_address[..]
	var-kind array
	enclosing-var this.location.display_address
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.location.state
	var-kind field state
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.zip_code
	var-kind field zip_code
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.businessessearch:::CLASS
ppt-type class

ppt main.businessessearch.getBusinesses_200(main.getBusinesses_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable input.term
	var-kind field term
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.location
	var-kind field location
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.latitude
	var-kind field latitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.longitude
	var-kind field longitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type int
	rep-type int
variable input.categories
	var-kind field categories
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.categories[..]
	var-kind array
	enclosing-var input.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type int
	rep-type int
variable input.offset
	var-kind field offset
	enclosing-var input
	dec-type int
	rep-type int
variable input.sort_by
	var-kind field sort_by
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.price
	var-kind field price
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.price[..]
	var-kind array
	enclosing-var input.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.open_now
	var-kind field open_now
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.open_at
	var-kind field open_at
	enclosing-var input
	dec-type int
	rep-type int
variable input.attributes
	var-kind field attributes
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.attributes[..]
	var-kind array
	enclosing-var input.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]

ppt main.businessessearch.getBusinesses_200_businesses_categories(main.getBusinesses_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable input.term
	var-kind field term
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.location
	var-kind field location
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.latitude
	var-kind field latitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.longitude
	var-kind field longitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type int
	rep-type int
variable input.categories
	var-kind field categories
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.categories[..]
	var-kind array
	enclosing-var input.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type int
	rep-type int
variable input.offset
	var-kind field offset
	enclosing-var input
	dec-type int
	rep-type int
variable input.sort_by
	var-kind field sort_by
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.price
	var-kind field price
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.price[..]
	var-kind array
	enclosing-var input.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.open_now
	var-kind field open_now
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.open_at
	var-kind field open_at
	enclosing-var input
	dec-type int
	rep-type int
variable input.attributes
	var-kind field attributes
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.attributes[..]
	var-kind array
	enclosing-var input.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]

ppt main.businessessearch.getBusinesses_200_businesses(main.getBusinesses_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable input.term
	var-kind field term
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.location
	var-kind field location
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.latitude
	var-kind field latitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.longitude
	var-kind field longitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type int
	rep-type int
variable input.categories
	var-kind field categories
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.categories[..]
	var-kind array
	enclosing-var input.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type int
	rep-type int
variable input.offset
	var-kind field offset
	enclosing-var input
	dec-type int
	rep-type int
variable input.sort_by
	var-kind field sort_by
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.price
	var-kind field price
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.price[..]
	var-kind array
	enclosing-var input.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.open_now
	var-kind field open_now
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.open_at
	var-kind field open_at
	enclosing-var input
	dec-type int
	rep-type int
variable input.attributes
	var-kind field attributes
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.attributes[..]
	var-kind array
	enclosing-var input.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]

ppt main.businessessearch.getBusinesses_200(main.getBusinesses_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable input.term
	var-kind field term
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.location
	var-kind field location
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.latitude
	var-kind field latitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.longitude
	var-kind field longitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type int
	rep-type int
variable input.categories
	var-kind field categories
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.categories[..]
	var-kind array
	enclosing-var input.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type int
	rep-type int
variable input.offset
	var-kind field offset
	enclosing-var input
	dec-type int
	rep-type int
variable input.sort_by
	var-kind field sort_by
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.price
	var-kind field price
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.price[..]
	var-kind array
	enclosing-var input.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.open_now
	var-kind field open_now
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.open_at
	var-kind field open_at
	enclosing-var input
	dec-type int
	rep-type int
variable input.attributes
	var-kind field attributes
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.attributes[..]
	var-kind array
	enclosing-var input.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return
	var-kind return
	dec-type main.getBusinesses_Output_200
	rep-type hashcode
variable return.total
	var-kind field total
	enclosing-var return
	dec-type int
	rep-type int
variable return.region
	var-kind return
	enclosing-var return
	dec-type main.getBusinesses_Output_200_region
	rep-type hashcode
variable return.region.center
	var-kind return
	enclosing-var return.region
	dec-type main.getBusinesses_Output_200_center
	rep-type hashcode
variable return.region.center.latitude
	var-kind field latitude
	enclosing-var return.region.center
	dec-type double
	rep-type double
variable return.region.center.longitude
	var-kind field longitude
	enclosing-var return.region.center
	dec-type double
	rep-type double
variable return.businesses
	var-kind field businesses
	enclosing-var return
	dec-type main.businesses[]
	rep-type hashcode
variable return.businesses[..]
	var-kind array
	enclosing-var return.businesses
	array 1
	dec-type main.businesses[]
	rep-type hashcode[]

ppt main.businessessearch.getBusinesses_200_businesses_categories(main.getBusinesses_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable input.term
	var-kind field term
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.location
	var-kind field location
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.latitude
	var-kind field latitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.longitude
	var-kind field longitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type int
	rep-type int
variable input.categories
	var-kind field categories
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.categories[..]
	var-kind array
	enclosing-var input.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type int
	rep-type int
variable input.offset
	var-kind field offset
	enclosing-var input
	dec-type int
	rep-type int
variable input.sort_by
	var-kind field sort_by
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.price
	var-kind field price
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.price[..]
	var-kind array
	enclosing-var input.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.open_now
	var-kind field open_now
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.open_at
	var-kind field open_at
	enclosing-var input
	dec-type int
	rep-type int
variable input.attributes
	var-kind field attributes
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.attributes[..]
	var-kind array
	enclosing-var input.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return
	var-kind return
	dec-type main.getBusinesses_Output_200_businesses_categories
	rep-type hashcode
variable return.alias
	var-kind field alias
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.title
	var-kind field title
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.businessessearch.getBusinesses_200_businesses(main.getBusinesses_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getBusinesses_Input
	rep-type hashcode
variable input.term
	var-kind field term
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.location
	var-kind field location
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.latitude
	var-kind field latitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.longitude
	var-kind field longitude
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type int
	rep-type int
variable input.categories
	var-kind field categories
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.categories[..]
	var-kind array
	enclosing-var input.categories
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.locale
	var-kind field locale
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type int
	rep-type int
variable input.offset
	var-kind field offset
	enclosing-var input
	dec-type int
	rep-type int
variable input.sort_by
	var-kind field sort_by
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.price
	var-kind field price
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.price[..]
	var-kind array
	enclosing-var input.price
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.open_now
	var-kind field open_now
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.open_at
	var-kind field open_at
	enclosing-var input
	dec-type int
	rep-type int
variable input.attributes
	var-kind field attributes
	enclosing-var input
	dec-type java.lang.String[]
	rep-type hashcode
variable input.attributes[..]
	var-kind array
	enclosing-var input.attributes
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return
	var-kind return
	dec-type main.getBusinesses_Output_200_businesses
	rep-type hashcode
variable return.categories
	var-kind field categories
	enclosing-var return
	dec-type main.categories[]
	rep-type hashcode
variable return.categories[..]
	var-kind array
	enclosing-var return.categories
	array 1
	dec-type main.categories[]
	rep-type hashcode[]
variable return.coordinates
	var-kind return
	enclosing-var return
	dec-type main.getBusinesses_Output_200_businesses_coordinates
	rep-type hashcode
variable return.coordinates.latitude
	var-kind field latitude
	enclosing-var return.coordinates
	dec-type double
	rep-type double
variable return.coordinates.longitude
	var-kind field longitude
	enclosing-var return.coordinates
	dec-type double
	rep-type double
variable return.display_phone
	var-kind field display_phone
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.distance
	var-kind field distance
	enclosing-var return
	dec-type double
	rep-type double
variable return.id
	var-kind field id
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.alias
	var-kind field alias
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.image_url
	var-kind field image_url
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.is_closed
	var-kind field is_closed
	enclosing-var return
	dec-type boolean
	rep-type boolean
variable return.name
	var-kind field name
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.phone
	var-kind field phone
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price
	var-kind field price
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.rating
	var-kind field rating
	enclosing-var return
	dec-type double
	rep-type double
variable return.review_count
	var-kind field review_count
	enclosing-var return
	dec-type int
	rep-type int
variable return.url
	var-kind field url
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.transactions
	var-kind field transactions
	enclosing-var return
	dec-type java.lang.String[]
	rep-type hashcode
variable return.transactions[..]
	var-kind array
	enclosing-var return.transactions
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.location
	var-kind return
	enclosing-var return
	dec-type main.getBusinesses_Output_200_businesses_location
	rep-type hashcode
variable return.location.address1
	var-kind field address1
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.address2
	var-kind field address2
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.address3
	var-kind field address3
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.city
	var-kind field city
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.country
	var-kind field country
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.display_address
	var-kind field display_address
	enclosing-var return.location
	dec-type java.lang.String[]
	rep-type hashcode
variable return.location.display_address[..]
	var-kind array
	enclosing-var return.location.display_address
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.location.state
	var-kind field state
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.zip_code
	var-kind field zip_code
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String

