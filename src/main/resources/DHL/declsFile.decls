decl-version 2.0
var-comparability implicit

ppt main.findByAddress_Input:::CLASS
ppt-type class

ppt main.findByAddress_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.findByAddress_Input
	rep-type java.lang.String
variable this.countryCode
	var-kind field countryCode
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.addressLocality
	var-kind field addressLocality
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.postalCode
	var-kind field postalCode
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.streetAddress
	var-kind field streetAddress
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.providerType
	var-kind field providerType
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.locationType
	var-kind field locationType
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.serviceType
	var-kind field serviceType
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.radius
	var-kind field radius
	enclosing-var this
	dec-type double
	rep-type double
variable this.limit
	var-kind field limit
	enclosing-var this
	dec-type double
	rep-type double


ppt main.findByAddress_Output_200:::CLASS
ppt-type class

ppt main.findByAddress_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.findByAddress_Output_200
	rep-type java.lang.String
variable this.locations
	var-kind field locations
	enclosing-var this
	dec-type main.locations[]
	rep-type java.lang.String
variable this.locations[..]
	var-kind array
	enclosing-var this.locations
	array 1
	dec-type main.locations[]
	rep-type java.lang.String[]

ppt main.findByAddress_Output_200_locations:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.findByAddress_Output_200_locations
	rep-type java.lang.String
variable this.url
	var-kind field url
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location
	var-kind variable
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.ids
	var-kind field ids
	enclosing-var this.location
	dec-type main.ids[]
	rep-type java.lang.String
variable this.location.ids[..]
	var-kind array
	enclosing-var this.location.ids
	array 1
	dec-type main.ids[]
	rep-type java.lang.String[]
variable this.location.keyword
	var-kind field keyword
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.keywordId
	var-kind field keywordId
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.location.type
	var-kind field type
	enclosing-var this.location
	dec-type java.lang.String
	rep-type java.lang.String
variable this.name
	var-kind field name
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.distance
	var-kind field distance
	enclosing-var this
	dec-type double
	rep-type double

ppt main.findByAddress_Output_200_locations_location_ids:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.findByAddress_Output_200_locations_location_ids
	rep-type java.lang.String
variable this.locationId
	var-kind field locationId
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.provider
	var-kind field provider
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.findByAddress_Output_400:::CLASS
ppt-type class

ppt main.findByAddress_Output_400:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.findByAddress_Output_400
	rep-type java.lang.String
variable this.supermodelIoLogisticsPUDOApiProblemDetail
	var-kind variable
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.supermodelIoLogisticsPUDOApiProblemDetail.status
	var-kind field status
	enclosing-var this.supermodelIoLogisticsPUDOApiProblemDetail
	dec-type double
	rep-type double
variable this.supermodelIoLogisticsPUDOApiProblemDetail.title
	var-kind field title
	enclosing-var this.supermodelIoLogisticsPUDOApiProblemDetail
	dec-type java.lang.String
	rep-type java.lang.String
variable this.supermodelIoLogisticsPUDOApiProblemDetail.detail
	var-kind field detail
	enclosing-var this.supermodelIoLogisticsPUDOApiProblemDetail
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.find-by-address:::CLASS
ppt-type class

ppt main.find-by-address.findByAddress(main.findByAddress_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.findByAddress_Input
	rep-type java.lang.String
variable input.countryCode
	var-kind field countryCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.addressLocality
	var-kind field addressLocality
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.postalCode
	var-kind field postalCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.streetAddress
	var-kind field streetAddress
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.providerType
	var-kind field providerType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locationType
	var-kind field locationType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.serviceType
	var-kind field serviceType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type double
	rep-type double
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type double
	rep-type double

ppt main.find-by-address.findByAddress(main.findByAddress_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.findByAddress_Input
	rep-type java.lang.String
variable input.countryCode
	var-kind field countryCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.addressLocality
	var-kind field addressLocality
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.postalCode
	var-kind field postalCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.streetAddress
	var-kind field streetAddress
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.providerType
	var-kind field providerType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locationType
	var-kind field locationType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.serviceType
	var-kind field serviceType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type double
	rep-type double
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type double
	rep-type double
variable return
	var-kind return
	dec-type main.findByAddress_Output_200
	rep-type java.lang.String
variable return.locations
	var-kind field locations
	enclosing-var return
	dec-type main.locations[]
	rep-type java.lang.String
variable return.locations[..]
	var-kind array
	enclosing-var return.locations
	array 1
	dec-type main.locations[]
	rep-type java.lang.String[]

ppt main.find-by-address.findByAddress_locations(main.findByAddress_locations_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.findByAddress_Input
	rep-type java.lang.String
variable input.countryCode
	var-kind field countryCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.addressLocality
	var-kind field addressLocality
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.postalCode
	var-kind field postalCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.streetAddress
	var-kind field streetAddress
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.providerType
	var-kind field providerType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locationType
	var-kind field locationType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.serviceType
	var-kind field serviceType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type double
	rep-type double
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type double
	rep-type double
variable return
	var-kind return
	dec-type main.findByAddress_Output_200_locations
	rep-type java.lang.String
variable return.url
	var-kind field url
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location
	var-kind return
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.ids
	var-kind field ids
	enclosing-var return.location
	dec-type main.ids[]
	rep-type java.lang.String
variable return.location.ids[..]
	var-kind array
	enclosing-var return.location.ids
	array 1
	dec-type main.ids[]
	rep-type java.lang.String[]
variable return.location.keyword
	var-kind field keyword
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.keywordId
	var-kind field keywordId
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.location.type
	var-kind field type
	enclosing-var return.location
	dec-type java.lang.String
	rep-type java.lang.String
variable return.name
	var-kind field name
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.distance
	var-kind field distance
	enclosing-var return
	dec-type double
	rep-type double

ppt main.find-by-address.findByAddress_locations_location_ids(main.findByAddress_locations_location_ids_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.findByAddress_Input
	rep-type java.lang.String
variable input.countryCode
	var-kind field countryCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.addressLocality
	var-kind field addressLocality
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.postalCode
	var-kind field postalCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.streetAddress
	var-kind field streetAddress
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.providerType
	var-kind field providerType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locationType
	var-kind field locationType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.serviceType
	var-kind field serviceType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type double
	rep-type double
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type double
	rep-type double
variable return
	var-kind return
	dec-type main.findByAddress_Output_200_locations_location_ids
	rep-type java.lang.String
variable return.locationId
	var-kind field locationId
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.provider
	var-kind field provider
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.find-by-address.findByAddress(main.findByAddress_Input):::EXIT4
ppt-type subexit
variable input
	var-kind variable
	dec-type main.findByAddress_Input
	rep-type java.lang.String
variable input.countryCode
	var-kind field countryCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.addressLocality
	var-kind field addressLocality
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.postalCode
	var-kind field postalCode
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.streetAddress
	var-kind field streetAddress
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.providerType
	var-kind field providerType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.locationType
	var-kind field locationType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.serviceType
	var-kind field serviceType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.radius
	var-kind field radius
	enclosing-var input
	dec-type double
	rep-type double
variable input.limit
	var-kind field limit
	enclosing-var input
	dec-type double
	rep-type double
variable return
	var-kind return
	dec-type main.findByAddress_Output_400
	rep-type java.lang.String
variable return.supermodelIoLogisticsPUDOApiProblemDetail
	var-kind return
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.supermodelIoLogisticsPUDOApiProblemDetail.status
	var-kind field status
	enclosing-var return.supermodelIoLogisticsPUDOApiProblemDetail
	dec-type double
	rep-type double
variable return.supermodelIoLogisticsPUDOApiProblemDetail.title
	var-kind field title
	enclosing-var return.supermodelIoLogisticsPUDOApiProblemDetail
	dec-type java.lang.String
	rep-type java.lang.String
variable return.supermodelIoLogisticsPUDOApiProblemDetail.detail
	var-kind field detail
	enclosing-var return.supermodelIoLogisticsPUDOApiProblemDetail
	dec-type java.lang.String
	rep-type java.lang.String

