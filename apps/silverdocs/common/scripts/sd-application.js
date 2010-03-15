var LOCALLINKS = new Array();
var LOCALLINKS_LAST_ID = 1;

/* *********************************************************************** */
function updateApplicationState() {
	// no code here
}

function navigateWithBlankPage(url) {
	var theForm = document.getElementById("blankPageSubmitForm");
	theForm.action = url;
	theForm.submit();
}

/* Local links ********************************************************* */
function LocalLink() {
	this.id = LOCALLINKS_LAST_ID;
	LOCALLINKS_LAST_ID++;
	this.url = null;
	this.name = null;
}

function refreshLocalLinks() {
	var htmlContent = "";
	htmlContent += "<table class='table_locallinks'>";

	for( index = 0; index < LOCALLINKS.length; index++ ) {
		var link = LOCALLINKS[ index ];
		htmlContent += "<tr class='{class}'>".replace(/{class}/, index % 2 == 0 ? "table_locallinks_row_even" : "table_locallinks_row_odd" );

		htmlContent += "<td class='table_locallinks_cell_name'>";
		htmlContent += "<a href='{url}' target='_blank'>{name}</a>".replace(/{url}/,link.url).replace(/{name}/,link.name);
		htmlContent += "</td>";

		htmlContent += "<td class='table_locallinks_cell_actions'>";
		htmlContent += "<a href='#' onclick='deleteLocalLink({id}); refreshLocalLinks(); return false;'><img src='common/images/icon-delete.png'</a>".replace(/{id}/,link.id);
		htmlContent += "</td>";

		htmlContent += "</tr>";
	}

	htmlContent += "<tr>";

	htmlContent += "<td>&nbsp</td>";

	htmlContent += "<td class='table_locallinks_cell_add'>";
	htmlContent += "<a href='#' onclick='addLocalLink(); refreshLocalLinks(); return false;'><img src='common/images/icon-add.png'</a>";
	htmlContent += "</td>";

	htmlContent += "</tr>";

	htmlContent += "</table>";

	document.getElementById( 'moduleLocalLinksHolder' ).innerHTML = htmlContent;
}

function deleteLocalLink( id ) {
	indexToRemove = -1;

	for( index = 0; index < LOCALLINKS.length; index++ ) {
		if ( LOCALLINKS[ index ].id == id ) {
			indexToRemove = index;
			break;
		}
	}

	if ( indexToRemove < 0 ) {
		return;
	}

	if( !confirm( "Are you sure you want to delete this link?\nName: " + LOCALLINKS[ indexToRemove ].name + "\nURL: " + LOCALLINKS[ indexToRemove ].url ) ) {
		return;
	}

	for( index = indexToRemove; index < LOCALLINKS.length - 1; index++ ) {
		LOCALLINKS[ index ] = LOCALLINKS[ index + 1 ];
	}

	LOCALLINKS.pop();
}

function addLocalLink() {
	var url = prompt( "URL:" );
	if ( url == null ) {
		return;
	}

	var name = prompt( "Name:", url );
	if ( name == null ) {
		return;
	}

	addLocalLinkWithParams( name, url );
}

function addLocalLinkWithParams( name, url ) {
	newLink = new LocalLink();
	newLink.name = name;
	newLink.url = url;
	newLink.id = LOCALLINKS_LAST_ID;
	LOCALLINKS_LAST_ID++;

	LOCALLINKS[ LOCALLINKS.length ] = newLink;
}

/* Google Search ********************************************************* */
function searchOnGoogle(text) {
	document.getElementById("hformGoogleQ").value = text;
	document.forms.hformGoogle.submit();
}
function searchOnGoogleLucky(text) {
	document.getElementById("hformGoogleQLucky").value = text;
	document.forms.hformGoogleLucky.submit();
}

/* Quick Jump ********************************************************* */

function setQuickJump() {
	var googleSearchField = parent.frames['topbar'].window.document.getElementById("googleSearchField");

	if ( googleSearchField.value == "" ) {
		if ( APPSTATE_CURRENT_READ_URL == null || APPSTATE_CURRENT_READ_URL == "" ) {
			alert("QuickJump set to NOTHING");
		} else {
			alert("QuickJump set to\n" + APPSTATE_CURRENT_READ_URL);
		}
	} else {
		APPSTATE_CURRENT_READ_URL = googleSearchField.value;
		googleSearchField.value = "";

		alert("QuickJump set to\n" + APPSTATE_CURRENT_READ_URL);
		googleSearchField.focus();
	}
}

function goQuickJump() {
	if (APPSTATE_CURRENT_READ_URL == null || APPSTATE_CURRENT_READ_URL == "") {
		alert( "No URL in QuickJump" );
	} else {
		navigateWithBlankPage(APPSTATE_CURRENT_READ_URL);
	}
}

function doTip( tip ) {
	Tip( tip );
}

function undoTip() {
	UnTip();
}

function openWindow( url, title, width, height ) {
    window.open(url, title, 'width=' + width + 'px,height=' + height + 'px,left=' + ((screen.width-width)/2) + 'px,top=' + ((screen.height-height)/2) + 'px');
}