decl-version 2.0
var-comparability implicit

ppt main.getMultiHotelOffers_Input:::CLASS
ppt-type class

ppt main.getMultiHotelOffers_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable this.hotelIds
	var-kind field hotelIds
	enclosing-var this
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.hotelIds[..]
	var-kind array
	enclosing-var this.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.adults
	var-kind field adults
	enclosing-var this
	dec-type int
	rep-type int
variable this.checkInDate
	var-kind field checkInDate
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.checkOutDate
	var-kind field checkOutDate
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.roomQuantity
	var-kind field roomQuantity
	enclosing-var this
	dec-type int
	rep-type int
variable this.priceRange
	var-kind field priceRange
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.currency
	var-kind field currency
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.boardType
	var-kind field boardType
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.includeClosed
	var-kind field includeClosed
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.lang
	var-kind field lang
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.getMultiHotelOffers_Output_200:::CLASS
ppt-type class

ppt main.getMultiHotelOffers_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200
	rep-type java.lang.String
variable this.dictionaries
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_dictionaries
	rep-type java.lang.String
variable this.dictionaries.currencyConversionLookupRates
	var-kind variable
	enclosing-var this.dictionaries
	dec-type main.getMultiHotelOffers_Output_200_currencyConversionLookupRates
	rep-type java.lang.String
variable this.data
	var-kind field data
	enclosing-var this
	dec-type main.data[]
	rep-type java.lang.String
variable this.data[..]
	var-kind array
	enclosing-var this.data
	array 1
	dec-type main.data[]
	rep-type java.lang.String[]

ppt main.getMultiHotelOffers_Output_200_data:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data
	rep-type java.lang.String
variable this.type
	var-kind field type
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.available
	var-kind field available
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.self
	var-kind field self
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.offers
	var-kind field offers
	enclosing-var this
	dec-type main.offers[]
	rep-type java.lang.String
variable this.offers[..]
	var-kind array
	enclosing-var this.offers
	array 1
	dec-type main.offers[]
	rep-type java.lang.String[]
variable this.hotel
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_hotel
	rep-type java.lang.String
variable this.hotel.hotelId
	var-kind field hotelId
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable this.hotel.chainCode
	var-kind field chainCode
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable this.hotel.brandCode
	var-kind field brandCode
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable this.hotel.dupeId
	var-kind field dupeId
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable this.hotel.name
	var-kind field name
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable this.hotel.cityCode
	var-kind field cityCode
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable this.hotel.latitude
	var-kind field latitude
	enclosing-var this.hotel
	dec-type double
	rep-type double
variable this.hotel.longitude
	var-kind field longitude
	enclosing-var this.hotel
	dec-type double
	rep-type double
variable this.hotel.type
	var-kind field type
	enclosing-var this.hotel
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getMultiHotelOffers_Output_200_data_offers_price_taxes:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_taxes
	rep-type java.lang.String
variable this.amount
	var-kind field amount
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.currency
	var-kind field currency
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.code
	var-kind field code
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.percentage
	var-kind field percentage
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.included
	var-kind field included
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.description
	var-kind field description
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.pricingFrequency
	var-kind field pricingFrequency
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.pricingMode
	var-kind field pricingMode
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getMultiHotelOffers_Output_200_data_offers_price_markups:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_markups
	rep-type java.lang.String
variable this.amount
	var-kind field amount
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getMultiHotelOffers_Output_200_data_offers_price_variations_average_markups:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_variations_average_markups
	rep-type java.lang.String
variable this.amount
	var-kind field amount
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getMultiHotelOffers_Output_200_data_offers_price_variations_changes_markups:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_variations_changes_markups
	rep-type java.lang.String
variable this.amount
	var-kind field amount
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getMultiHotelOffers_Output_200_data_offers:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data_offers
	rep-type java.lang.String
variable this.type
	var-kind field type
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.id
	var-kind field id
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.checkInDate
	var-kind field checkInDate
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.checkOutDate
	var-kind field checkOutDate
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.roomQuantity
	var-kind field roomQuantity
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.rateCode
	var-kind field rateCode
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.rateFamilyEstimated
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_rateFamilyEstimated
	rep-type java.lang.String
variable this.rateFamilyEstimated.code
	var-kind field code
	enclosing-var this.rateFamilyEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable this.rateFamilyEstimated.type
	var-kind field type
	enclosing-var this.rateFamilyEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable this.category
	var-kind field category
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.description
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.description.text
	var-kind field text
	enclosing-var this.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.description.lang
	var-kind field lang
	enclosing-var this.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.commission
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_commission
	rep-type java.lang.String
variable this.commission.percentage
	var-kind field percentage
	enclosing-var this.commission
	dec-type java.lang.String
	rep-type java.lang.String
variable this.commission.amount
	var-kind field amount
	enclosing-var this.commission
	dec-type java.lang.String
	rep-type java.lang.String
variable this.commission.description
	var-kind variable
	enclosing-var this.commission
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.commission.description.text
	var-kind field text
	enclosing-var this.commission.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.commission.description.lang
	var-kind field lang
	enclosing-var this.commission.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.boardType
	var-kind field boardType
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.room
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_room
	rep-type java.lang.String
variable this.room.type
	var-kind field type
	enclosing-var this.room
	dec-type java.lang.String
	rep-type java.lang.String
variable this.room.typeEstimated
	var-kind variable
	enclosing-var this.room
	dec-type main.getMultiHotelOffers_Output_200_data_offers_typeEstimated
	rep-type java.lang.String
variable this.room.typeEstimated.category
	var-kind field category
	enclosing-var this.room.typeEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable this.room.typeEstimated.beds
	var-kind field beds
	enclosing-var this.room.typeEstimated
	dec-type int
	rep-type int
variable this.room.typeEstimated.bedType
	var-kind field bedType
	enclosing-var this.room.typeEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable this.room.description
	var-kind variable
	enclosing-var this.room
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.room.description.text
	var-kind field text
	enclosing-var this.room.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.room.description.lang
	var-kind field lang
	enclosing-var this.room.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.guests
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_guests
	rep-type java.lang.String
variable this.guests.adults
	var-kind field adults
	enclosing-var this.guests
	dec-type int
	rep-type int
variable this.guests.childAges
	var-kind field childAges
	enclosing-var this.guests
	dec-type int[]
	rep-type java.lang.String
variable this.guests.childAges[..]
	var-kind array
	enclosing-var this.guests.childAges
	array 1
	dec-type int[]
	rep-type int[]
variable this.price
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price
	rep-type java.lang.String
variable this.price.currency
	var-kind field currency
	enclosing-var this.price
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.sellingTotal
	var-kind field sellingTotal
	enclosing-var this.price
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.total
	var-kind field total
	enclosing-var this.price
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.base
	var-kind field base
	enclosing-var this.price
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.taxes
	var-kind field taxes
	enclosing-var this.price
	dec-type main.taxes[]
	rep-type java.lang.String
variable this.price.taxes[..]
	var-kind array
	enclosing-var this.price.taxes
	array 1
	dec-type main.taxes[]
	rep-type java.lang.String[]
variable this.price.markups
	var-kind field markups
	enclosing-var this.price
	dec-type main.markups[]
	rep-type java.lang.String
variable this.price.markups[..]
	var-kind array
	enclosing-var this.price.markups
	array 1
	dec-type main.markups[]
	rep-type java.lang.String[]
variable this.price.variations
	var-kind variable
	enclosing-var this.price
	dec-type main.getMultiHotelOffers_Output_200_data_offers_variations
	rep-type java.lang.String
variable this.price.variations.average
	var-kind variable
	enclosing-var this.price.variations
	dec-type main.getMultiHotelOffers_Output_200_data_offers_average
	rep-type java.lang.String
variable this.price.variations.average.currency
	var-kind field currency
	enclosing-var this.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.variations.average.sellingTotal
	var-kind field sellingTotal
	enclosing-var this.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.variations.average.total
	var-kind field total
	enclosing-var this.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.variations.average.base
	var-kind field base
	enclosing-var this.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable this.price.variations.average.markups
	var-kind field markups
	enclosing-var this.price.variations.average
	dec-type main.markups[]
	rep-type java.lang.String
variable this.price.variations.average.markups[..]
	var-kind array
	enclosing-var this.price.variations.average.markups
	array 1
	dec-type main.markups[]
	rep-type java.lang.String[]
variable this.price.variations.changes
	var-kind field changes
	enclosing-var this.price.variations
	dec-type main.changes[]
	rep-type java.lang.String
variable this.price.variations.changes[..]
	var-kind array
	enclosing-var this.price.variations.changes
	array 1
	dec-type main.changes[]
	rep-type java.lang.String[]
variable this.policies
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_200_data_offers_policies
	rep-type java.lang.String
variable this.policies.paymentType
	var-kind field paymentType
	enclosing-var this.policies
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.guarantee
	var-kind variable
	enclosing-var this.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_guarantee
	rep-type java.lang.String
variable this.policies.guarantee.description
	var-kind variable
	enclosing-var this.policies.guarantee
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.policies.guarantee.description.text
	var-kind field text
	enclosing-var this.policies.guarantee.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.guarantee.description.lang
	var-kind field lang
	enclosing-var this.policies.guarantee.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.guarantee.acceptedPayments
	var-kind variable
	enclosing-var this.policies.guarantee
	dec-type main.getMultiHotelOffers_Output_200_data_offers_acceptedPayments
	rep-type java.lang.String
variable this.policies.guarantee.acceptedPayments.creditCards
	var-kind field creditCards
	enclosing-var this.policies.guarantee.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.policies.guarantee.acceptedPayments.creditCards[..]
	var-kind array
	enclosing-var this.policies.guarantee.acceptedPayments.creditCards
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.policies.guarantee.acceptedPayments.methods
	var-kind field methods
	enclosing-var this.policies.guarantee.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.policies.guarantee.acceptedPayments.methods[..]
	var-kind array
	enclosing-var this.policies.guarantee.acceptedPayments.methods
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.policies.deposit
	var-kind variable
	enclosing-var this.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_deposit
	rep-type java.lang.String
variable this.policies.deposit.amount
	var-kind field amount
	enclosing-var this.policies.deposit
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.deposit.deadline
	var-kind field deadline
	enclosing-var this.policies.deposit
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.deposit.description
	var-kind variable
	enclosing-var this.policies.deposit
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.policies.deposit.description.text
	var-kind field text
	enclosing-var this.policies.deposit.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.deposit.description.lang
	var-kind field lang
	enclosing-var this.policies.deposit.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.deposit.acceptedPayments
	var-kind variable
	enclosing-var this.policies.deposit
	dec-type main.getMultiHotelOffers_Output_200_data_offers_acceptedPayments
	rep-type java.lang.String
variable this.policies.deposit.acceptedPayments.creditCards
	var-kind field creditCards
	enclosing-var this.policies.deposit.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.policies.deposit.acceptedPayments.creditCards[..]
	var-kind array
	enclosing-var this.policies.deposit.acceptedPayments.creditCards
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.policies.deposit.acceptedPayments.methods
	var-kind field methods
	enclosing-var this.policies.deposit.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.policies.deposit.acceptedPayments.methods[..]
	var-kind array
	enclosing-var this.policies.deposit.acceptedPayments.methods
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.policies.prepay
	var-kind variable
	enclosing-var this.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_prepay
	rep-type java.lang.String
variable this.policies.prepay.amount
	var-kind field amount
	enclosing-var this.policies.prepay
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.prepay.deadline
	var-kind field deadline
	enclosing-var this.policies.prepay
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.prepay.description
	var-kind variable
	enclosing-var this.policies.prepay
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.policies.prepay.description.text
	var-kind field text
	enclosing-var this.policies.prepay.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.prepay.description.lang
	var-kind field lang
	enclosing-var this.policies.prepay.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.prepay.acceptedPayments
	var-kind variable
	enclosing-var this.policies.prepay
	dec-type main.getMultiHotelOffers_Output_200_data_offers_acceptedPayments
	rep-type java.lang.String
variable this.policies.prepay.acceptedPayments.creditCards
	var-kind field creditCards
	enclosing-var this.policies.prepay.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.policies.prepay.acceptedPayments.creditCards[..]
	var-kind array
	enclosing-var this.policies.prepay.acceptedPayments.creditCards
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.policies.prepay.acceptedPayments.methods
	var-kind field methods
	enclosing-var this.policies.prepay.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.policies.prepay.acceptedPayments.methods[..]
	var-kind array
	enclosing-var this.policies.prepay.acceptedPayments.methods
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.policies.holdTime
	var-kind variable
	enclosing-var this.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_holdTime
	rep-type java.lang.String
variable this.policies.holdTime.deadline
	var-kind field deadline
	enclosing-var this.policies.holdTime
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.cancellation
	var-kind variable
	enclosing-var this.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_cancellation
	rep-type java.lang.String
variable this.policies.cancellation.type
	var-kind field type
	enclosing-var this.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.cancellation.amount
	var-kind field amount
	enclosing-var this.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.cancellation.numberOfNights
	var-kind field numberOfNights
	enclosing-var this.policies.cancellation
	dec-type int
	rep-type int
variable this.policies.cancellation.percentage
	var-kind field percentage
	enclosing-var this.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.cancellation.deadline
	var-kind field deadline
	enclosing-var this.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.cancellation.description
	var-kind variable
	enclosing-var this.policies.cancellation
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable this.policies.cancellation.description.text
	var-kind field text
	enclosing-var this.policies.cancellation.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.cancellation.description.lang
	var-kind field lang
	enclosing-var this.policies.cancellation.description
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.checkInOut
	var-kind variable
	enclosing-var this.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_checkInOut
	rep-type java.lang.String
variable this.policies.checkInOut.checkIn
	var-kind field checkIn
	enclosing-var this.policies.checkInOut
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.checkInOut.checkInDescription
	var-kind variable
	enclosing-var this.policies.checkInOut
	dec-type main.getMultiHotelOffers_Output_200_data_offers_checkInDescription
	rep-type java.lang.String
variable this.policies.checkInOut.checkInDescription.text
	var-kind field text
	enclosing-var this.policies.checkInOut.checkInDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.checkInOut.checkInDescription.lang
	var-kind field lang
	enclosing-var this.policies.checkInOut.checkInDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.checkInOut.checkOut
	var-kind field checkOut
	enclosing-var this.policies.checkInOut
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.checkInOut.checkOutDescription
	var-kind variable
	enclosing-var this.policies.checkInOut
	dec-type main.getMultiHotelOffers_Output_200_data_offers_checkOutDescription
	rep-type java.lang.String
variable this.policies.checkInOut.checkOutDescription.text
	var-kind field text
	enclosing-var this.policies.checkInOut.checkOutDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable this.policies.checkInOut.checkOutDescription.lang
	var-kind field lang
	enclosing-var this.policies.checkInOut.checkOutDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable this.self
	var-kind field self
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.cancelPolicyHash
	var-kind field cancelPolicyHash
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getMultiHotelOffers_Output_200_data_offers_price_variations_changes:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_variations_changes
	rep-type java.lang.String
variable this.startDate
	var-kind field startDate
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.endDate
	var-kind field endDate
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.currency
	var-kind field currency
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.sellingTotal
	var-kind field sellingTotal
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.total
	var-kind field total
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.base
	var-kind field base
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.markups
	var-kind field markups
	enclosing-var this
	dec-type main.markups[]
	rep-type java.lang.String
variable this.markups[..]
	var-kind array
	enclosing-var this.markups
	array 1
	dec-type main.markups[]
	rep-type java.lang.String[]


ppt main.getMultiHotelOffers_Output_400:::CLASS
ppt-type class

ppt main.getMultiHotelOffers_Output_400:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_400
	rep-type java.lang.String
variable this.errors
	var-kind field errors
	enclosing-var this
	dec-type main.errors[]
	rep-type java.lang.String
variable this.errors[..]
	var-kind array
	enclosing-var this.errors
	array 1
	dec-type main.errors[]
	rep-type java.lang.String[]

ppt main.getMultiHotelOffers_Output_400_errors:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_400_errors
	rep-type java.lang.String
variable this.status
	var-kind field status
	enclosing-var this
	dec-type int
	rep-type int
variable this.code
	var-kind field code
	enclosing-var this
	dec-type int
	rep-type int
variable this.title
	var-kind field title
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.detail
	var-kind field detail
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.source
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_400_errors_source
	rep-type java.lang.String
variable this.source.parameter
	var-kind field parameter
	enclosing-var this.source
	dec-type java.lang.String
	rep-type java.lang.String
variable this.source.pointer
	var-kind field pointer
	enclosing-var this.source
	dec-type java.lang.String
	rep-type java.lang.String
variable this.source.example
	var-kind field example
	enclosing-var this.source
	dec-type java.lang.String
	rep-type java.lang.String
variable this.documentation
	var-kind field documentation
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.getMultiHotelOffers_Output_500:::CLASS
ppt-type class

ppt main.getMultiHotelOffers_Output_500:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_500
	rep-type java.lang.String
variable this.errors
	var-kind field errors
	enclosing-var this
	dec-type main.errors[]
	rep-type java.lang.String
variable this.errors[..]
	var-kind array
	enclosing-var this.errors
	array 1
	dec-type main.errors[]
	rep-type java.lang.String[]

ppt main.getMultiHotelOffers_Output_500_errors:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getMultiHotelOffers_Output_500_errors
	rep-type java.lang.String
variable this.status
	var-kind field status
	enclosing-var this
	dec-type int
	rep-type int
variable this.code
	var-kind field code
	enclosing-var this
	dec-type int
	rep-type int
variable this.title
	var-kind field title
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.detail
	var-kind field detail
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.source
	var-kind variable
	enclosing-var this
	dec-type main.getMultiHotelOffers_Output_500_errors_source
	rep-type java.lang.String
variable this.source.parameter
	var-kind field parameter
	enclosing-var this.source
	dec-type java.lang.String
	rep-type java.lang.String
variable this.source.pointer
	var-kind field pointer
	enclosing-var this.source
	dec-type java.lang.String
	rep-type java.lang.String
variable this.source.example
	var-kind field example
	enclosing-var this.source
	dec-type java.lang.String
	rep-type java.lang.String
variable this.documentation
	var-kind field documentation
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.shoppinghotel-offers:::CLASS
ppt-type class

ppt main.shoppinghotel-offers.getMultiHotelOffers_200(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_taxes(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_markups(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_variations_average_markups(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_variations_changes_markups(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_variations_changes(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_400(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_400_errors(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_500(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_500_errors(main.getMultiHotelOffers_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200(main.getMultiHotelOffers_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200
	rep-type java.lang.String
variable return.dictionaries
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_dictionaries
	rep-type java.lang.String
variable return.dictionaries.currencyConversionLookupRates
	var-kind return
	enclosing-var return.dictionaries
	dec-type main.getMultiHotelOffers_Output_200_currencyConversionLookupRates
	rep-type java.lang.String
variable return.data
	var-kind field data
	enclosing-var return
	dec-type main.data[]
	rep-type java.lang.String
variable return.data[..]
	var-kind array
	enclosing-var return.data
	array 1
	dec-type main.data[]
	rep-type java.lang.String[]

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data(main.getMultiHotelOffers_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data
	rep-type java.lang.String
variable return.type
	var-kind field type
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.available
	var-kind field available
	enclosing-var return
	dec-type boolean
	rep-type boolean
variable return.self
	var-kind field self
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.offers
	var-kind field offers
	enclosing-var return
	dec-type main.offers[]
	rep-type java.lang.String
variable return.offers[..]
	var-kind array
	enclosing-var return.offers
	array 1
	dec-type main.offers[]
	rep-type java.lang.String[]
variable return.hotel
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_hotel
	rep-type java.lang.String
variable return.hotel.hotelId
	var-kind field hotelId
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable return.hotel.chainCode
	var-kind field chainCode
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable return.hotel.brandCode
	var-kind field brandCode
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable return.hotel.dupeId
	var-kind field dupeId
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable return.hotel.name
	var-kind field name
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable return.hotel.cityCode
	var-kind field cityCode
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String
variable return.hotel.latitude
	var-kind field latitude
	enclosing-var return.hotel
	dec-type double
	rep-type double
variable return.hotel.longitude
	var-kind field longitude
	enclosing-var return.hotel
	dec-type double
	rep-type double
variable return.hotel.type
	var-kind field type
	enclosing-var return.hotel
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_taxes(main.getMultiHotelOffers_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_taxes
	rep-type java.lang.String
variable return.amount
	var-kind field amount
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.currency
	var-kind field currency
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.code
	var-kind field code
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.percentage
	var-kind field percentage
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.included
	var-kind field included
	enclosing-var return
	dec-type boolean
	rep-type boolean
variable return.description
	var-kind field description
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.pricingFrequency
	var-kind field pricingFrequency
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.pricingMode
	var-kind field pricingMode
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_markups(main.getMultiHotelOffers_Input):::EXIT4
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_markups
	rep-type java.lang.String
variable return.amount
	var-kind field amount
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_variations_average_markups(main.getMultiHotelOffers_Input):::EXIT5
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_variations_average_markups
	rep-type java.lang.String
variable return.amount
	var-kind field amount
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_variations_changes_markups(main.getMultiHotelOffers_Input):::EXIT6
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_variations_changes_markups
	rep-type java.lang.String
variable return.amount
	var-kind field amount
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers(main.getMultiHotelOffers_Input):::EXIT7
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data_offers
	rep-type java.lang.String
variable return.type
	var-kind field type
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.id
	var-kind field id
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.checkInDate
	var-kind field checkInDate
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.checkOutDate
	var-kind field checkOutDate
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.roomQuantity
	var-kind field roomQuantity
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.rateCode
	var-kind field rateCode
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.rateFamilyEstimated
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_rateFamilyEstimated
	rep-type java.lang.String
variable return.rateFamilyEstimated.code
	var-kind field code
	enclosing-var return.rateFamilyEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable return.rateFamilyEstimated.type
	var-kind field type
	enclosing-var return.rateFamilyEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable return.category
	var-kind field category
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.description
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.description.text
	var-kind field text
	enclosing-var return.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.description.lang
	var-kind field lang
	enclosing-var return.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.commission
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_commission
	rep-type java.lang.String
variable return.commission.percentage
	var-kind field percentage
	enclosing-var return.commission
	dec-type java.lang.String
	rep-type java.lang.String
variable return.commission.amount
	var-kind field amount
	enclosing-var return.commission
	dec-type java.lang.String
	rep-type java.lang.String
variable return.commission.description
	var-kind return
	enclosing-var return.commission
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.commission.description.text
	var-kind field text
	enclosing-var return.commission.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.commission.description.lang
	var-kind field lang
	enclosing-var return.commission.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.boardType
	var-kind field boardType
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.room
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_room
	rep-type java.lang.String
variable return.room.type
	var-kind field type
	enclosing-var return.room
	dec-type java.lang.String
	rep-type java.lang.String
variable return.room.typeEstimated
	var-kind return
	enclosing-var return.room
	dec-type main.getMultiHotelOffers_Output_200_data_offers_typeEstimated
	rep-type java.lang.String
variable return.room.typeEstimated.category
	var-kind field category
	enclosing-var return.room.typeEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable return.room.typeEstimated.beds
	var-kind field beds
	enclosing-var return.room.typeEstimated
	dec-type int
	rep-type int
variable return.room.typeEstimated.bedType
	var-kind field bedType
	enclosing-var return.room.typeEstimated
	dec-type java.lang.String
	rep-type java.lang.String
variable return.room.description
	var-kind return
	enclosing-var return.room
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.room.description.text
	var-kind field text
	enclosing-var return.room.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.room.description.lang
	var-kind field lang
	enclosing-var return.room.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.guests
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_guests
	rep-type java.lang.String
variable return.guests.adults
	var-kind field adults
	enclosing-var return.guests
	dec-type int
	rep-type int
variable return.guests.childAges
	var-kind field childAges
	enclosing-var return.guests
	dec-type int[]
	rep-type java.lang.String
variable return.guests.childAges[..]
	var-kind array
	enclosing-var return.guests.childAges
	array 1
	dec-type int[]
	rep-type int[]
variable return.price
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price
	rep-type java.lang.String
variable return.price.currency
	var-kind field currency
	enclosing-var return.price
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.sellingTotal
	var-kind field sellingTotal
	enclosing-var return.price
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.total
	var-kind field total
	enclosing-var return.price
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.base
	var-kind field base
	enclosing-var return.price
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.taxes
	var-kind field taxes
	enclosing-var return.price
	dec-type main.taxes[]
	rep-type java.lang.String
variable return.price.taxes[..]
	var-kind array
	enclosing-var return.price.taxes
	array 1
	dec-type main.taxes[]
	rep-type java.lang.String[]
variable return.price.markups
	var-kind field markups
	enclosing-var return.price
	dec-type main.markups[]
	rep-type java.lang.String
variable return.price.markups[..]
	var-kind array
	enclosing-var return.price.markups
	array 1
	dec-type main.markups[]
	rep-type java.lang.String[]
variable return.price.variations
	var-kind return
	enclosing-var return.price
	dec-type main.getMultiHotelOffers_Output_200_data_offers_variations
	rep-type java.lang.String
variable return.price.variations.average
	var-kind return
	enclosing-var return.price.variations
	dec-type main.getMultiHotelOffers_Output_200_data_offers_average
	rep-type java.lang.String
variable return.price.variations.average.currency
	var-kind field currency
	enclosing-var return.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.variations.average.sellingTotal
	var-kind field sellingTotal
	enclosing-var return.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.variations.average.total
	var-kind field total
	enclosing-var return.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.variations.average.base
	var-kind field base
	enclosing-var return.price.variations.average
	dec-type java.lang.String
	rep-type java.lang.String
variable return.price.variations.average.markups
	var-kind field markups
	enclosing-var return.price.variations.average
	dec-type main.markups[]
	rep-type java.lang.String
variable return.price.variations.average.markups[..]
	var-kind array
	enclosing-var return.price.variations.average.markups
	array 1
	dec-type main.markups[]
	rep-type java.lang.String[]
variable return.price.variations.changes
	var-kind field changes
	enclosing-var return.price.variations
	dec-type main.changes[]
	rep-type java.lang.String
variable return.price.variations.changes[..]
	var-kind array
	enclosing-var return.price.variations.changes
	array 1
	dec-type main.changes[]
	rep-type java.lang.String[]
variable return.policies
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_policies
	rep-type java.lang.String
variable return.policies.paymentType
	var-kind field paymentType
	enclosing-var return.policies
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.guarantee
	var-kind return
	enclosing-var return.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_guarantee
	rep-type java.lang.String
variable return.policies.guarantee.description
	var-kind return
	enclosing-var return.policies.guarantee
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.policies.guarantee.description.text
	var-kind field text
	enclosing-var return.policies.guarantee.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.guarantee.description.lang
	var-kind field lang
	enclosing-var return.policies.guarantee.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.guarantee.acceptedPayments
	var-kind return
	enclosing-var return.policies.guarantee
	dec-type main.getMultiHotelOffers_Output_200_data_offers_acceptedPayments
	rep-type java.lang.String
variable return.policies.guarantee.acceptedPayments.creditCards
	var-kind field creditCards
	enclosing-var return.policies.guarantee.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.policies.guarantee.acceptedPayments.creditCards[..]
	var-kind array
	enclosing-var return.policies.guarantee.acceptedPayments.creditCards
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.policies.guarantee.acceptedPayments.methods
	var-kind field methods
	enclosing-var return.policies.guarantee.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.policies.guarantee.acceptedPayments.methods[..]
	var-kind array
	enclosing-var return.policies.guarantee.acceptedPayments.methods
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.policies.deposit
	var-kind return
	enclosing-var return.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_deposit
	rep-type java.lang.String
variable return.policies.deposit.amount
	var-kind field amount
	enclosing-var return.policies.deposit
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.deposit.deadline
	var-kind field deadline
	enclosing-var return.policies.deposit
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.deposit.description
	var-kind return
	enclosing-var return.policies.deposit
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.policies.deposit.description.text
	var-kind field text
	enclosing-var return.policies.deposit.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.deposit.description.lang
	var-kind field lang
	enclosing-var return.policies.deposit.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.deposit.acceptedPayments
	var-kind return
	enclosing-var return.policies.deposit
	dec-type main.getMultiHotelOffers_Output_200_data_offers_acceptedPayments
	rep-type java.lang.String
variable return.policies.deposit.acceptedPayments.creditCards
	var-kind field creditCards
	enclosing-var return.policies.deposit.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.policies.deposit.acceptedPayments.creditCards[..]
	var-kind array
	enclosing-var return.policies.deposit.acceptedPayments.creditCards
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.policies.deposit.acceptedPayments.methods
	var-kind field methods
	enclosing-var return.policies.deposit.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.policies.deposit.acceptedPayments.methods[..]
	var-kind array
	enclosing-var return.policies.deposit.acceptedPayments.methods
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.policies.prepay
	var-kind return
	enclosing-var return.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_prepay
	rep-type java.lang.String
variable return.policies.prepay.amount
	var-kind field amount
	enclosing-var return.policies.prepay
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.prepay.deadline
	var-kind field deadline
	enclosing-var return.policies.prepay
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.prepay.description
	var-kind return
	enclosing-var return.policies.prepay
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.policies.prepay.description.text
	var-kind field text
	enclosing-var return.policies.prepay.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.prepay.description.lang
	var-kind field lang
	enclosing-var return.policies.prepay.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.prepay.acceptedPayments
	var-kind return
	enclosing-var return.policies.prepay
	dec-type main.getMultiHotelOffers_Output_200_data_offers_acceptedPayments
	rep-type java.lang.String
variable return.policies.prepay.acceptedPayments.creditCards
	var-kind field creditCards
	enclosing-var return.policies.prepay.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.policies.prepay.acceptedPayments.creditCards[..]
	var-kind array
	enclosing-var return.policies.prepay.acceptedPayments.creditCards
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.policies.prepay.acceptedPayments.methods
	var-kind field methods
	enclosing-var return.policies.prepay.acceptedPayments
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.policies.prepay.acceptedPayments.methods[..]
	var-kind array
	enclosing-var return.policies.prepay.acceptedPayments.methods
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.policies.holdTime
	var-kind return
	enclosing-var return.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_holdTime
	rep-type java.lang.String
variable return.policies.holdTime.deadline
	var-kind field deadline
	enclosing-var return.policies.holdTime
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.cancellation
	var-kind return
	enclosing-var return.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_cancellation
	rep-type java.lang.String
variable return.policies.cancellation.type
	var-kind field type
	enclosing-var return.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.cancellation.amount
	var-kind field amount
	enclosing-var return.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.cancellation.numberOfNights
	var-kind field numberOfNights
	enclosing-var return.policies.cancellation
	dec-type int
	rep-type int
variable return.policies.cancellation.percentage
	var-kind field percentage
	enclosing-var return.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.cancellation.deadline
	var-kind field deadline
	enclosing-var return.policies.cancellation
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.cancellation.description
	var-kind return
	enclosing-var return.policies.cancellation
	dec-type main.getMultiHotelOffers_Output_200_data_offers_description
	rep-type java.lang.String
variable return.policies.cancellation.description.text
	var-kind field text
	enclosing-var return.policies.cancellation.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.cancellation.description.lang
	var-kind field lang
	enclosing-var return.policies.cancellation.description
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.checkInOut
	var-kind return
	enclosing-var return.policies
	dec-type main.getMultiHotelOffers_Output_200_data_offers_checkInOut
	rep-type java.lang.String
variable return.policies.checkInOut.checkIn
	var-kind field checkIn
	enclosing-var return.policies.checkInOut
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.checkInOut.checkInDescription
	var-kind return
	enclosing-var return.policies.checkInOut
	dec-type main.getMultiHotelOffers_Output_200_data_offers_checkInDescription
	rep-type java.lang.String
variable return.policies.checkInOut.checkInDescription.text
	var-kind field text
	enclosing-var return.policies.checkInOut.checkInDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.checkInOut.checkInDescription.lang
	var-kind field lang
	enclosing-var return.policies.checkInOut.checkInDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.checkInOut.checkOut
	var-kind field checkOut
	enclosing-var return.policies.checkInOut
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.checkInOut.checkOutDescription
	var-kind return
	enclosing-var return.policies.checkInOut
	dec-type main.getMultiHotelOffers_Output_200_data_offers_checkOutDescription
	rep-type java.lang.String
variable return.policies.checkInOut.checkOutDescription.text
	var-kind field text
	enclosing-var return.policies.checkInOut.checkOutDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable return.policies.checkInOut.checkOutDescription.lang
	var-kind field lang
	enclosing-var return.policies.checkInOut.checkOutDescription
	dec-type java.lang.String
	rep-type java.lang.String
variable return.self
	var-kind field self
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.cancelPolicyHash
	var-kind field cancelPolicyHash
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_200_data_offers_price_variations_changes(main.getMultiHotelOffers_Input):::EXIT8
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_200_data_offers_price_variations_changes
	rep-type java.lang.String
variable return.startDate
	var-kind field startDate
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.endDate
	var-kind field endDate
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.currency
	var-kind field currency
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.sellingTotal
	var-kind field sellingTotal
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.total
	var-kind field total
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.base
	var-kind field base
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.markups
	var-kind field markups
	enclosing-var return
	dec-type main.markups[]
	rep-type java.lang.String
variable return.markups[..]
	var-kind array
	enclosing-var return.markups
	array 1
	dec-type main.markups[]
	rep-type java.lang.String[]

ppt main.shoppinghotel-offers.getMultiHotelOffers_400(main.getMultiHotelOffers_Input):::EXIT9
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_400
	rep-type java.lang.String
variable return.errors
	var-kind field errors
	enclosing-var return
	dec-type main.errors[]
	rep-type java.lang.String
variable return.errors[..]
	var-kind array
	enclosing-var return.errors
	array 1
	dec-type main.errors[]
	rep-type java.lang.String[]

ppt main.shoppinghotel-offers.getMultiHotelOffers_400_errors(main.getMultiHotelOffers_Input):::EXIT10
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_400_errors
	rep-type java.lang.String
variable return.status
	var-kind field status
	enclosing-var return
	dec-type int
	rep-type int
variable return.code
	var-kind field code
	enclosing-var return
	dec-type int
	rep-type int
variable return.title
	var-kind field title
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.detail
	var-kind field detail
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.source
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_400_errors_source
	rep-type java.lang.String
variable return.source.parameter
	var-kind field parameter
	enclosing-var return.source
	dec-type java.lang.String
	rep-type java.lang.String
variable return.source.pointer
	var-kind field pointer
	enclosing-var return.source
	dec-type java.lang.String
	rep-type java.lang.String
variable return.source.example
	var-kind field example
	enclosing-var return.source
	dec-type java.lang.String
	rep-type java.lang.String
variable return.documentation
	var-kind field documentation
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.shoppinghotel-offers.getMultiHotelOffers_500(main.getMultiHotelOffers_Input):::EXIT11
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_500
	rep-type java.lang.String
variable return.errors
	var-kind field errors
	enclosing-var return
	dec-type main.errors[]
	rep-type java.lang.String
variable return.errors[..]
	var-kind array
	enclosing-var return.errors
	array 1
	dec-type main.errors[]
	rep-type java.lang.String[]

ppt main.shoppinghotel-offers.getMultiHotelOffers_500_errors(main.getMultiHotelOffers_Input):::EXIT12
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getMultiHotelOffers_Input
	rep-type java.lang.String
variable input.hotelIds
	var-kind field hotelIds
	enclosing-var input
	dec-type java.lang.String[]
	rep-type java.lang.String
variable input.hotelIds[..]
	var-kind array
	enclosing-var input.hotelIds
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable input.adults
	var-kind field adults
	enclosing-var input
	dec-type int
	rep-type int
variable input.checkInDate
	var-kind field checkInDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.checkOutDate
	var-kind field checkOutDate
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.countryOfResidence
	var-kind field countryOfResidence
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.roomQuantity
	var-kind field roomQuantity
	enclosing-var input
	dec-type int
	rep-type int
variable input.priceRange
	var-kind field priceRange
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.currency
	var-kind field currency
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.paymentPolicy
	var-kind field paymentPolicy
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.boardType
	var-kind field boardType
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable input.includeClosed
	var-kind field includeClosed
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.bestRateOnly
	var-kind field bestRateOnly
	enclosing-var input
	dec-type boolean
	rep-type boolean
variable input.lang
	var-kind field lang
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getMultiHotelOffers_Output_500_errors
	rep-type java.lang.String
variable return.status
	var-kind field status
	enclosing-var return
	dec-type int
	rep-type int
variable return.code
	var-kind field code
	enclosing-var return
	dec-type int
	rep-type int
variable return.title
	var-kind field title
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.detail
	var-kind field detail
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.source
	var-kind return
	enclosing-var return
	dec-type main.getMultiHotelOffers_Output_500_errors_source
	rep-type java.lang.String
variable return.source.parameter
	var-kind field parameter
	enclosing-var return.source
	dec-type java.lang.String
	rep-type java.lang.String
variable return.source.pointer
	var-kind field pointer
	enclosing-var return.source
	dec-type java.lang.String
	rep-type java.lang.String
variable return.source.example
	var-kind field example
	enclosing-var return.source
	dec-type java.lang.String
	rep-type java.lang.String
variable return.documentation
	var-kind field documentation
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

