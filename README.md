# excelprice

_upload file as binary:_<br>
POST /upload?file&ext=xlsx

_insert #href as hyperlinks in row #top column #codeCol<br/>
insert #imgHref as images in row #top column #imgCol:<br/>_
POST /upload?description<br/>
{<br/>
  "fileName":"/upload/7042777731750008522.xlsx",<br/>
	"codeCol":5,<br/>
	"imgCol":4,<br/>
	"hrefs":<br/>
	[<br/>
	{"href":"http://infromation-page-1", "imgHref":"https://image-1.png", "top":22},<br/>
	{"href":"http://infromation-page-2", "imgHref":"https://image-2.png", "top":23}<br/>
	]<br/>
}<br/>

_get list of uploaded files:_<br/>
GET /upload?list

_download uploaded file:_<br/>
GET /upload?file&name=/upload/391369502987938486.xlsx

_delete uploaded files:_<br/>
DELETE /upload?list
[
"/upload/7042777731750008522.xlsx",
"/upload/391369502987938486.xlsx"
]
