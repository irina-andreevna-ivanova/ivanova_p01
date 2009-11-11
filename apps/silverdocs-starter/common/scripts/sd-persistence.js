var APPSTATE_COOKIE_NAME = "sdcookie";
var APPSTATE_CURRENT_READ_URL = null;

var PersistenceLoadingFunctions = new Array();
PersistenceLoadingFunctions[ 0 ] = loadModuleStatesFromString; 
PersistenceLoadingFunctions[ 1 ] = loadQuickJumpURLFromString; 
PersistenceLoadingFunctions[ 2 ] = loadLocalLinksFromString; 

var PersistenceSavingFunctions = new Array();
PersistenceSavingFunctions[ 0 ] = saveModuleStatesToString; 
PersistenceSavingFunctions[ 1 ] = saveQuickJumpURLToString; 
PersistenceSavingFunctions[ 2 ] = saveLocalLinksToString; 

//------------------------------------------------------------------------------------------------------

function loadAppStateFromCookie() {
	var cookieStr = getCookieValue( APPSTATE_COOKIE_NAME );
	if ( cookieStr == false || cookieStr == null || cookieStr.length == 0 ) {
		return;
	}
	
	for( var index = 0; index < PersistenceLoadingFunctions.length; index++ ) {
		var pos = cookieStr.indexOf( "`" );
		if ( pos > -1 ) {
			var encodedDataStr = cookieStr.substr( 0, pos );
			cookieStr = cookieStr.substr( pos + 1, cookieStr.length );
			
			if ( encodedDataStr != null && encodedDataStr.length > 0 ) {
				var dataStr = decode64( encodedDataStr );
				PersistenceLoadingFunctions[ index ]( dataStr );
			}
		}
	}
}

function saveAppStateToCookie() {
	var cookieStr = "";
	
	for( var index = 0; index < PersistenceSavingFunctions.length; index++ ) {
		var dataStr = PersistenceSavingFunctions[ index ]();
		if ( dataStr != null && dataStr.length > 0 ) {
			var encodedDataStr = encode64( dataStr );
			cookieStr = cookieStr + encodedDataStr + "`";
		} else {
			cookieStr = cookieStr + "`";
		}
	}
	
	createPermanentCookie( APPSTATE_COOKIE_NAME , cookieStr );
}

// Modules ------------------------------------------------------------------------------------------------------

function loadModuleStatesFromString( dataStr ) {
	if ( dataStr == null || dataStr == "" ) {
		// no cookie set (or no cookies allowed)
		return;
	} 

	while( (dataStr.length > 0) && (!stopLoadingModules) ) {
		pos = dataStr.indexOf( "!" );
		if ( pos > -1 ) {
			moduleID = dataStr.substr( 0, pos );
			module = getModuleByID( moduleID );
			if ( (module != null) && (!stopLoadingModules) ) {
				dataStr = dataStr.substr( pos + 1, dataStr.length );
				pos = dataStr.indexOf( "!" );
				if ( pos > -1 ) {
					state = dataStr.substr( 0, pos );
					dataStr = dataStr.substr( pos + 1, dataStr.length );
					module[ MODULE_VISIBLE ] = ( state == "1" );

					pos = dataStr.indexOf( "!" );
					if ( pos > -1 ) {
						state = dataStr.substr( 0, pos );
						dataStr = dataStr.substr( pos + 1, dataStr.length );
						module[ MODULE_PINNED ] = ( state == "1" );
					} else {
						dataStr = "";
					}
				} else {
					dataStr = "";
				}
			}
		} else {
			dataStr = "";
		}
	}
}
function saveModuleStatesToString() {
	cookieStr = "";
	for( index = 0; index < SDModules.length; index++ ) {
		module = SDModules[ index ];
		cookieStr = cookieStr + module[ MODULE_ID ] + "!" + ( module[ MODULE_VISIBLE ] ? "1":"0" ) + "!" + ( module[ MODULE_PINNED ] ? "1":"0" ) + "!"; 
	}
	
	return cookieStr;
}

// Local links -------------------------------------------------------------------------------------------------

function loadLocalLinksFromString( cookieStr ) {
	if ( cookieStr == null || cookieStr == "" ) {
		// no cookie set (or no cookies allowed)
		return;
	} 
	
	while( cookieStr.length > 0 ) {
		var pos = cookieStr.indexOf( "`" );
		if ( pos > -1 ) {
			var name = cookieStr.substr( 0, pos );
			cookieStr = cookieStr.substr( pos + 1, cookieStr.length );
			pos = cookieStr.indexOf( "`" );
			
			if ( pos > -1 ) {
				var url = cookieStr.substr( 0, pos );
				cookieStr = cookieStr.substr( pos + 1, cookieStr.length );
				addLocalLinkWithParams( name, url );
			} else {
				cookieStr = "";
			}
		} else {
			cookieStr = "";
		}
	}
}
function saveLocalLinksToString() {
	var cookieStr = "";
	for( var index = 0; index < LOCALLINKS.length; index++ ) {
		var link = LOCALLINKS[ index ];
		cookieStr += link.name + "`" + link.url + "`";		
	}
	return cookieStr;
}

// Other things -------------------------------------------------------------------------------------------------

function loadQuickJumpURLFromString( str ) {
	APPSTATE_CURRENT_READ_URL = str;
}
function saveQuickJumpURLToString() {
	return APPSTATE_CURRENT_READ_URL;
}
