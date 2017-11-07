# excelprice
1. opens file #fileName for editing
2. inserts in row #top:
  - hyperlinks in column #codeCol
  - images in column #imgCol

POST /upload<br/>
{<br/>
  "fileName":"\\\\192.168.100.4\\share\\price.xlsx",<br/>
	"codeCol":5,<br/>
	"imgCol":4,<br/>
	"hrefs":<br/>
	[<br/>
	{"href":"http://infromation-page-1", "imgHref":"https://image-1.png", "top":22},<br/>
	{"href":"http://infromation-page-2", "imgHref":"https://image-2.png", "top":23}<br/>
	]<br/>
}<br/>
