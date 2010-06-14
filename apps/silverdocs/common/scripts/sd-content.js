/* *********************************************************************** */
var CONTENT_LOADFRAME   = "sd.loadFrame";
var CONTENT_CONTENTDIV  = "sd.contentDiv";
var CONTENT_INDEXDIV    = "sd.indexDiv";
var CONTENT_BOOKMARKDIV = "sd.bookmarkDiv"; 
var CONTENT_APPLICATIONSDIV = "sd.applicationsDiv"; 
var CONTENT_SEARCHDIV = "sd.searchDiv"; 
var CONTENT_INDEXITEMS  = 3;
var CONTENT_SPOTDIVS_PREFIX = "spot.";
var CONTENT_SPOTHEADERPINICON_PREFIX = "spot.header.icon.pin.";

var CONTENT_SPOT_INVISIBLE = "<table class='spot_div_invisible_table' cellpadding='0px' cellspacing='0px'><tr><td></td></tr></table>";
var CONTENT_SPOT_LOADING = "<table class='spot_div_loading_table' cellpadding='0px' cellspacing='0px'><tr><td>Loading...</td></tr></table>";

/* *********************************************************************** */
function getSpotDivByModuleID( moduleID ) {
	return document.getElementById( CONTENT_SPOTDIVS_PREFIX + moduleID );
}
function getSpotHeaderPinIconByModuleID( moduleID ) {
	return document.getElementById( CONTENT_SPOTHEADERPINICON_PREFIX + moduleID );
}

function onPageLoad() {
    // 0. generate the page content (that is, the spots)
    generatePageContent();

	// 1. load the module states from the cookie, along with the other app state
    loadAppStateFromCookie();

	// 2. process all modules, and load those that are marked as visible
	processAllModules();
	
	// 3. update app state
	updateApplicationState();
	
	// 3. add hover events for all tables with class="spot_header"
	/*
	var elems = document.getElementsByTagName( "table" );
	for( index = 0; index < elems.length; index++ ) {
		elem = elems[ index ];
		
		if ( elems[ index ].className == "spot_header" ) {
			tdelem = elem.getElementsByTagName( "tr" )[ 0 ].getElementsByTagName( "td" )[ 1 ];
			tdelem.onmouseover = onSpotHeaderMouseOver;
			tdelem.onmouseout = onSpotHeaderMouseOut;
		}
	}
	*/
}

function generatePageContent() {
	// 1. generate the big table with modules
	var contentDiv = document.getElementById( CONTENT_CONTENTDIV );
	columnLength = Math.floor( 100 / SDSpots.length );
	
	var contentStr = "";
	contentStr += '<table cellpadding="0px" cellspacing="0px" width="100%" style="padding-left: 1px; padding-right: 5px;"><tr><td>';
	contentStr += '<table cellpadding="0" cellspacing="0" width="100%">';
	contentStr += '    <tbody><tr>';
	contentStr += '        <td valign="top" width="' + columnLength + '%">';
	
	for( colIndex = 0; colIndex < SDSpots.length; colIndex++ ) {
		spotColumn = SDSpots[ colIndex ];

		for( rowIndex = 0; rowIndex < spotColumn.length; rowIndex++ ) {
			spot = spotColumn[ rowIndex ];
			contentStr += '            <table class="spot_header ' + spot[ SPOT_COLOR ] + '" style="width:100%;"><tbody><tr><td class="spot_header_icon_unpinned" id="spot.header.icon.pin.' + spot[ SPOT_MODULEID ] + '" onClick="onSpotHeaderPinIconClick(\'' + spot[ SPOT_MODULEID ] + '\');"></td><td onClick="onSpotHeaderClick(\'' + spot[ SPOT_MODULEID ] + '\');">' + spot[ SPOT_NAME ] + '</td></tr></tbody></table>';
			contentStr += '            <div id="spot.' + spot[ SPOT_MODULEID ] + '" class="spot_div"></div>';
		} 

		if ( colIndex < SDSpots.length - 1 ) {
			contentStr += '</td>';
			contentStr += '<td style="width: 1px; font-size: 2px;"></td>';
			contentStr += '<td style="padding-left: 1px;" valign="top" width="' + columnLength + '%">';
		}
	}

	contentStr += '        </td>';
	contentStr += '<td style="width: 0px; font-size: 2px; background-color: #FFFFFF;">&nbsp;</td>';
	contentStr += '</tr>';
	contentStr += '</tbody></table>';
	contentStr += '</td></tr></table>';
	
	contentDiv.innerHTML = contentStr;
	
	// 2. generate the index list
	var indexDiv = document.getElementById( CONTENT_INDEXDIV );
	var spotsList = getSpotsListOrderedByName();
	
	contentStr = "";
	contentStr += "<table cellpadding='0px' cellspacing='0px' class='table_indexList'><tr>";
	contentStr += "<td class='table_indexList_cell'>";

/*
	contentStr += "<table cellpadding='0px' cellspacing='3px'><tr>";
	contentStr += "<td valign='top'>";
	for( spotIndex = 0; spotIndex < spotsList.length; spotIndex++ ) {
		contentStr += "<a href='' onClick='onSpotHeaderClick(\"" + spotsList[ spotIndex ][ SPOT_MODULEID ] + "\");return false;'>" + spotsList[ spotIndex ][ SPOT_NAME ] + "</a><br/>";
		
		if ( ( spotIndex + 1 ) % CONTENT_INDEXITEMS == 0 ) {
			contentStr += "</td><td style='width:1px; background-color: #A0A0A0;'></td><td valign='top'>";
		}	
	}
	contentStr += "</td></tr></table>";
*/
	for( spotIndex = 0; spotIndex < spotsList.length; spotIndex++ ) {
		contentStr += "<a id='spotIndexRef_" + spotsList[ spotIndex ][ SPOT_MODULEID ] + "' href='' onClick='onSpotHeaderClick(\"" + spotsList[ spotIndex ][ SPOT_MODULEID ] + "\");return false;' >" + spotsList[ spotIndex ][ SPOT_NAME ] + "</a><br/>";
	}
	contentStr += "</td></tr></table>";
	
	indexDiv.innerHTML = contentStr; 
}

function onPageUnload() {
	saveAppStateToCookie();
}
function onBookmarkBarClick() {
	var bookmarkDiv = document.getElementById( CONTENT_BOOKMARKDIV );

	if ( SDBookmarks[ MODULE_VISIBLE ] ) {
		SDBookmarks[ MODULE_VISIBLE ] = false;
		bookmarkDiv.innerHTML = "";
		bookmarkDiv.className = "bookmarkDiv_notVisible";
	} else {
		SDBookmarks[ MODULE_VISIBLE ] = true;
		bookmarkDiv.innerHTML = CONTENT_SPOT_LOADING;
		bookmarkDiv.className = "bookmarkDiv_visible";

		var loadFrame = document.getElementById( CONTENT_LOADFRAME );
		loadFrame.src = SDBookmarks[ MODULE_FILENAME ];
	} 
}
function onApplicationsBarClick() {
	var applicationsDiv = document.getElementById( CONTENT_APPLICATIONSDIV );

    if ( SDApplications[ MODULE_VISIBLE ] ) {
        SDApplications[ MODULE_VISIBLE ] = false;
        applicationsDiv.innerHTML = "";
        applicationsDiv.className = "applicationsDiv_notVisible";
    } else {
        SDApplications[ MODULE_VISIBLE ] = true;
        applicationsDiv.innerHTML = CONTENT_SPOT_LOADING;
        applicationsDiv.className = "applicationsDiv_visible";

        var loadFrame = document.getElementById( CONTENT_LOADFRAME );
        loadFrame.src = SDApplications[ MODULE_FILENAME ];
    }
}
function onSearchBarClick() {
	var searchDiv = document.getElementById( CONTENT_SEARCHDIV );

    if ( SDSearch[ MODULE_VISIBLE ] ) {
        SDSearch[ MODULE_VISIBLE ] = false;
        searchDiv.innerHTML = "";
        searchDiv.className = "searchDiv_notVisible";
    } else {
        SDSearch[ MODULE_VISIBLE ] = true;
        searchDiv.innerHTML = CONTENT_SPOT_LOADING;
        searchDiv.className = "searchDiv_visible";

        var loadFrame = document.getElementById( CONTENT_LOADFRAME );
        loadFrame.src = SDSearch[ MODULE_FILENAME ];
    }
}
function onSpotHeaderClick( moduleID ) {
	var module = getModuleByID( moduleID );
	
	if ( module[ MODULE_VISIBLE ] ) {
		module[ MODULE_VISIBLE ] = false;
	} else {
		closeAllModulesUnpinnedOnTheColumn( moduleID );
		module[ MODULE_VISIBLE ] = true;
	}
	module[ MODULE_PROCESSED ] = false;
	processAllModules();	
}
function onSpotHeaderPinIconClick( moduleID ) {
	var module = getModuleByID( moduleID );
	module[ MODULE_PINNED ] = !module[ MODULE_PINNED ];
	processModule( module );
}

function processAllModules() {
	for( index = 0; index < SDModules.length; index++ ) {
		if ( !SDModules[ index ][ MODULE_PROCESSED ] ) {
			processModule( SDModules[ index ] );
			break;
		}
	} 
}

function processModule( module ) {
	var spotDiv = getSpotDivByModuleID( module[ MODULE_ID ] );
	var spotHeaderPinIconTD = getSpotHeaderPinIconByModuleID( module[ MODULE_ID ] );

	if ( !module[ MODULE_PROCESSED ] ) { 
		if ( module[ MODULE_VISIBLE ] ) {
            //document.getElementById( "spotIndexRef_" + module[ MODULE_ID ] ).className = "a_index_opened";
			spotDiv.innerHTML = CONTENT_SPOT_LOADING;
            var loadFrame = document.getElementById( CONTENT_LOADFRAME );
			loadFrame.src = module[ MODULE_FILENAME ];
		} else { 
            //document.getElementById( "spotIndexRef_" + module[ MODULE_ID ] ).className = "a_index_closed";
			spotDiv.innerHTML = CONTENT_SPOT_INVISIBLE;
			module[ MODULE_VISIBLE ] = false;
			module[ MODULE_PROCESSED ] = true;
			processAllModules();
		}
	}
	
	if ( module[ MODULE_PINNED ] ) {
		spotHeaderPinIconTD.className = "spot_header_icon_pinned";
	} else {
		spotHeaderPinIconTD.className = "spot_header_icon_unpinned";
	}
}

function processModuleContent( moduleID, content ) {
	var module = getModuleByID( moduleID );
	getSpotDivByModuleID( moduleID ).innerHTML = content;
	
	// this is really bad since it hardcodes the module ID here as well as the module itself...
	//if ( moduleID == "sd.general.search" ) {
		//document.getElementById( "sd.general.search.google.search.input" ).focus();		
	//}
	
	module[ MODULE_VISIBLE   ] = true;
	module[ MODULE_PROCESSED ] = true;
	module[ MODULE_TIMESTAMP ] = new Date().getTime();
	processAllModules();
}
function processBookmarksContent( content ) {
	var bookmarkDiv = document.getElementById( CONTENT_BOOKMARKDIV );
	bookmarkDiv.innerHTML = content;
}
function processApplicationsContent( content ) {
	var applicationsDiv = document.getElementById( CONTENT_APPLICATIONSDIV );
	applicationsDiv.innerHTML = content;
}
function processSearchContent( content ) {
	var searchDiv = document.getElementById( CONTENT_SEARCHDIV );
	searchDiv.innerHTML = content;
}
function closeAllModulesUnpinnedOnTheColumn( activeModuleID ) {
	var colIndex = -1;
	var rowIndex = -1;
	
	// 1. first search for the module in the spot array
	for( cIndex = 0; cIndex < SDSpots.length && colIndex == -1; cIndex++ ) {
		for( rIndex = 0; rIndex < SDSpots[ cIndex ].length; rIndex++ ) {
			if ( SDSpots[ cIndex ][ rIndex ][ SPOT_MODULEID ] == activeModuleID ) {
				colIndex = cIndex;
				rowIndex = rIndex;
				break;
			}
		}  
	}
	
	// 2. close all modules from the same column, so that only the most
	// MODULES_MAXVISIBLEPERCOLUMN recent opened modules remain opened
	while ( true ) {
		modulesCount = 0;
		minTimestamp = new Date().getTime();// any other time will be lower than current time
		minModule = null;
		
		for( rIndex = 0; rIndex < SDSpots[ colIndex ].length; rIndex++ ) {
			if ( rIndex != rowIndex ) {
				module = getModuleByID( SDSpots[ colIndex ][ rIndex ][ SPOT_MODULEID ] );
				if ( !module[ MODULE_PINNED ] && module[ MODULE_VISIBLE ] ) {
					modulesCount++;
					if ( minTimestamp >= module[ MODULE_TIMESTAMP ] ) {
						minTimestamp = module[ MODULE_TIMESTAMP ];
						minModule = module;
					} 
				}
			}
		}
		
		if ( modulesCount > MODULES_MAXVISIBLEPERCOLUMN - 1 ) {
			minModule[ MODULE_VISIBLE   ] = false;
			minModule[ MODULE_PROCESSED ] = false;
		} else {
			break;
		}
	}
}

function setAllModulesStates( state ) {
	for( index = 0; index < SDModules.length; index++ ) {
		module = SDModules[ index ];
		if ( module[ MODULE_VISIBLE ] != state ) {
			module[ MODULE_VISIBLE ] = state;
			module[ MODULE_PROCESSED ] = false;
		}
	}
	
	processAllModules();	
}

function setAllModulesVisible() {
	setAllModulesStates( true );
}

function setAllModulesInvisible() {
	setAllModulesStates( false );
}

function setAllUnpinnedModulesInvisible() {
    for( index = 0; index < SDModules.length; index++ ) {
        module = SDModules[ index ];
        if ( module[ MODULE_VISIBLE ] && !module[ MODULE_PINNED ] ) {
            module[ MODULE_VISIBLE ] = false;
            module[ MODULE_PROCESSED ] = false;
        }
    }

    processAllModules();
}

function openModuleWindow( file, width, height ) {
	left = (screen.width - width) / 2;
	top = (screen.height - height) / 2 - 20;

	window.open( "modules/" + file, "", "scrollbars=yes,width=" + width + ",height=" + height + ",left=" + left + ",top=" + top );
	return false;
}