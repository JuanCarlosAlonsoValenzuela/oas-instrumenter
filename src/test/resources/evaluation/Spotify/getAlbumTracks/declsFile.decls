decl-version 2.0
var-comparability implicit

ppt main.getAlbumTracks_Input:::CLASS
ppt-type class

ppt main.getAlbumTracks_Input:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable this.id
	var-kind field id
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
variable this.market
	var-kind field market
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.getAlbumTracks_Output_200:::CLASS
ppt-type class

ppt main.getAlbumTracks_Output_200:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getAlbumTracks_Output_200
	rep-type java.lang.String
variable this.href
	var-kind field href
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.items
	var-kind field items
	enclosing-var this
	dec-type main.items[]
	rep-type java.lang.String
variable this.items[..]
	var-kind array
	enclosing-var this.items
	array 1
	dec-type main.items[]
	rep-type java.lang.String[]
variable this.limit
	var-kind field limit
	enclosing-var this
	dec-type int
	rep-type int
variable this.next
	var-kind field next
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.offset
	var-kind field offset
	enclosing-var this
	dec-type int
	rep-type int
variable this.previous
	var-kind field previous
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.total
	var-kind field total
	enclosing-var this
	dec-type int
	rep-type int

ppt main.getAlbumTracks_Output_200_items:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getAlbumTracks_Output_200_items
	rep-type java.lang.String
variable this.artists
	var-kind field artists
	enclosing-var this
	dec-type main.artists[]
	rep-type java.lang.String
variable this.artists[..]
	var-kind array
	enclosing-var this.artists
	array 1
	dec-type main.artists[]
	rep-type java.lang.String[]
variable this.available_markets
	var-kind field available_markets
	enclosing-var this
	dec-type java.lang.String[]
	rep-type java.lang.String
variable this.available_markets[..]
	var-kind array
	enclosing-var this.available_markets
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable this.disc_number
	var-kind field disc_number
	enclosing-var this
	dec-type int
	rep-type int
variable this.duration_ms
	var-kind field duration_ms
	enclosing-var this
	dec-type int
	rep-type int
variable this.explicit
	var-kind field explicit
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.external_urls
	var-kind variable
	enclosing-var this
	dec-type main.getAlbumTracks_Output_200_items_external_urls
	rep-type java.lang.String
variable this.external_urls.spotify
	var-kind field spotify
	enclosing-var this.external_urls
	dec-type java.lang.String
	rep-type java.lang.String
variable this.href
	var-kind field href
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.id
	var-kind field id
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.is_local
	var-kind field is_local
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.is_playable
	var-kind field is_playable
	enclosing-var this
	dec-type boolean
	rep-type boolean
variable this.linked_from
	var-kind variable
	enclosing-var this
	dec-type main.getAlbumTracks_Output_200_items_linked_from
	rep-type java.lang.String
variable this.linked_from.external_urls
	var-kind variable
	enclosing-var this.linked_from
	dec-type main.getAlbumTracks_Output_200_items_external_urls
	rep-type java.lang.String
variable this.linked_from.external_urls.spotify
	var-kind field spotify
	enclosing-var this.linked_from.external_urls
	dec-type java.lang.String
	rep-type java.lang.String
variable this.linked_from.href
	var-kind field href
	enclosing-var this.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable this.linked_from.id
	var-kind field id
	enclosing-var this.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable this.linked_from.type
	var-kind field type
	enclosing-var this.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable this.linked_from.uri
	var-kind field uri
	enclosing-var this.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable this.name
	var-kind field name
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.preview_url
	var-kind field preview_url
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.restrictions
	var-kind variable
	enclosing-var this
	dec-type main.getAlbumTracks_Output_200_items_restrictions
	rep-type java.lang.String
variable this.restrictions.reason
	var-kind field reason
	enclosing-var this.restrictions
	dec-type java.lang.String
	rep-type java.lang.String
variable this.track_number
	var-kind field track_number
	enclosing-var this
	dec-type int
	rep-type int
variable this.type
	var-kind field type
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.uri
	var-kind field uri
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.getAlbumTracks_Output_200_items_artists:::OBJECT
ppt-type object
variable this
	var-kind variable
	dec-type main.getAlbumTracks_Output_200_items_artists
	rep-type java.lang.String
variable this.external_urls
	var-kind variable
	enclosing-var this
	dec-type main.getAlbumTracks_Output_200_items_artists_external_urls
	rep-type java.lang.String
variable this.external_urls.spotify
	var-kind field spotify
	enclosing-var this.external_urls
	dec-type java.lang.String
	rep-type java.lang.String
variable this.href
	var-kind field href
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
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
variable this.type
	var-kind field type
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String
variable this.uri
	var-kind field uri
	enclosing-var this
	dec-type java.lang.String
	rep-type java.lang.String


ppt main.albums{id}tracks:::CLASS
ppt-type class

ppt main.albums{id}tracks.getAlbumTracks_200(main.getAlbumTracks_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable input.id
	var-kind field id
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
variable input.market
	var-kind field market
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.albums{id}tracks.getAlbumTracks_200_items(main.getAlbumTracks_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable input.id
	var-kind field id
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
variable input.market
	var-kind field market
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.albums{id}tracks.getAlbumTracks_200_items_artists(main.getAlbumTracks_Input):::ENTER
ppt-type enter
variable input
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable input.id
	var-kind field id
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
variable input.market
	var-kind field market
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.albums{id}tracks.getAlbumTracks_200(main.getAlbumTracks_Input):::EXIT1
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable input.id
	var-kind field id
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
variable input.market
	var-kind field market
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getAlbumTracks_Output_200
	rep-type java.lang.String
variable return.href
	var-kind field href
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.items
	var-kind field items
	enclosing-var return
	dec-type main.items[]
	rep-type java.lang.String
variable return.items[..]
	var-kind array
	enclosing-var return.items
	array 1
	dec-type main.items[]
	rep-type java.lang.String[]
variable return.limit
	var-kind field limit
	enclosing-var return
	dec-type int
	rep-type int
variable return.next
	var-kind field next
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.offset
	var-kind field offset
	enclosing-var return
	dec-type int
	rep-type int
variable return.previous
	var-kind field previous
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.total
	var-kind field total
	enclosing-var return
	dec-type int
	rep-type int

ppt main.albums{id}tracks.getAlbumTracks_200_items(main.getAlbumTracks_Input):::EXIT2
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable input.id
	var-kind field id
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
variable input.market
	var-kind field market
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getAlbumTracks_Output_200_items
	rep-type java.lang.String
variable return.artists
	var-kind field artists
	enclosing-var return
	dec-type main.artists[]
	rep-type java.lang.String
variable return.artists[..]
	var-kind array
	enclosing-var return.artists
	array 1
	dec-type main.artists[]
	rep-type java.lang.String[]
variable return.available_markets
	var-kind field available_markets
	enclosing-var return
	dec-type java.lang.String[]
	rep-type java.lang.String
variable return.available_markets[..]
	var-kind array
	enclosing-var return.available_markets
	array 1
	dec-type java.lang.String[]
	rep-type java.lang.String[]
variable return.disc_number
	var-kind field disc_number
	enclosing-var return
	dec-type int
	rep-type int
variable return.duration_ms
	var-kind field duration_ms
	enclosing-var return
	dec-type int
	rep-type int
variable return.explicit
	var-kind field explicit
	enclosing-var return
	dec-type boolean
	rep-type boolean
variable return.external_urls
	var-kind return
	enclosing-var return
	dec-type main.getAlbumTracks_Output_200_items_external_urls
	rep-type java.lang.String
variable return.external_urls.spotify
	var-kind field spotify
	enclosing-var return.external_urls
	dec-type java.lang.String
	rep-type java.lang.String
variable return.href
	var-kind field href
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.id
	var-kind field id
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.is_local
	var-kind field is_local
	enclosing-var return
	dec-type boolean
	rep-type boolean
variable return.is_playable
	var-kind field is_playable
	enclosing-var return
	dec-type boolean
	rep-type boolean
variable return.linked_from
	var-kind return
	enclosing-var return
	dec-type main.getAlbumTracks_Output_200_items_linked_from
	rep-type java.lang.String
variable return.linked_from.external_urls
	var-kind return
	enclosing-var return.linked_from
	dec-type main.getAlbumTracks_Output_200_items_external_urls
	rep-type java.lang.String
variable return.linked_from.external_urls.spotify
	var-kind field spotify
	enclosing-var return.linked_from.external_urls
	dec-type java.lang.String
	rep-type java.lang.String
variable return.linked_from.href
	var-kind field href
	enclosing-var return.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable return.linked_from.id
	var-kind field id
	enclosing-var return.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable return.linked_from.type
	var-kind field type
	enclosing-var return.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable return.linked_from.uri
	var-kind field uri
	enclosing-var return.linked_from
	dec-type java.lang.String
	rep-type java.lang.String
variable return.name
	var-kind field name
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.preview_url
	var-kind field preview_url
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.restrictions
	var-kind return
	enclosing-var return
	dec-type main.getAlbumTracks_Output_200_items_restrictions
	rep-type java.lang.String
variable return.restrictions.reason
	var-kind field reason
	enclosing-var return.restrictions
	dec-type java.lang.String
	rep-type java.lang.String
variable return.track_number
	var-kind field track_number
	enclosing-var return
	dec-type int
	rep-type int
variable return.type
	var-kind field type
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.uri
	var-kind field uri
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

ppt main.albums{id}tracks.getAlbumTracks_200_items_artists(main.getAlbumTracks_Input):::EXIT3
ppt-type subexit
variable input
	var-kind variable
	dec-type main.getAlbumTracks_Input
	rep-type java.lang.String
variable input.id
	var-kind field id
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
variable input.market
	var-kind field market
	enclosing-var input
	dec-type java.lang.String
	rep-type java.lang.String
variable return
	var-kind return
	dec-type main.getAlbumTracks_Output_200_items_artists
	rep-type java.lang.String
variable return.external_urls
	var-kind return
	enclosing-var return
	dec-type main.getAlbumTracks_Output_200_items_artists_external_urls
	rep-type java.lang.String
variable return.external_urls.spotify
	var-kind field spotify
	enclosing-var return.external_urls
	dec-type java.lang.String
	rep-type java.lang.String
variable return.href
	var-kind field href
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
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
variable return.type
	var-kind field type
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String
variable return.uri
	var-kind field uri
	enclosing-var return
	dec-type java.lang.String
	rep-type java.lang.String

