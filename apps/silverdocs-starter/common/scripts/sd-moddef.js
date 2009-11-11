/* *********************************************************************** */
/* This file contains the definition of the modules. For each module, you 
 * have to provide the logical name (can be any string you want, as long as
 * you don't use a string twice), the name of the HTML file that contains 
 * the module content, and some flags regarding the initial state and the 
 * pinned state or the module.
 * 
 * The layout of the modules is defined in sd-layoutdef.js                 */
/* *********************************************************************** */
var MODULES_MAXVISIBLEPERCOLUMN = 3;
var MODULES_DIRECTORY           = "modules/";
var MODULE_ID        = 0;
var MODULE_FILENAME  = 1;
var MODULE_VISIBLE   = 2;
var MODULE_PROCESSED = 3;
var MODULE_PINNED    = 4;
var MODULE_TIMESTAMP = 5;

var SDBookmarks = new Array();
SDBookmarks[ MODULE_ID       ] = "sd.bookmarks";
SDBookmarks[ MODULE_FILENAME ] = MODULES_DIRECTORY + "module-bookmarks.html";
SDBookmarks[ MODULE_VISIBLE  ] = false;

var SDModules = new Array();
var moduleIndex = -1;
var stopLoadingModules = false;

function initModule( module ) {
	module[ MODULE_PROCESSED ] = false;
	module[ MODULE_TIMESTAMP ] = 0;
}

/* *********************************************************************************** */
SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.general.search";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-general-search.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = true;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = true;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.locallinks";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-locallinks.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.j2se";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-j2se.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.java";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-java.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.web.css";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-web-css.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.web.javascript";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-web-javascript.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.web.html";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-web-html.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

/* *********************************************************************** */
var warningAlreadyShown = false;

function getModuleByID( moduleID ) {
	for( index = 0; index < SDModules.length; index++ ) {
		if ( SDModules[ index ][ MODULE_ID ] == moduleID ) {
			return SDModules[ index ];
		}
	}

	if ( !warningAlreadyShown ) {
		alert( "Cannot find module with ID: " + moduleID );
		alert( "This page will not be loaded anymore! Please clear your cookies, and then try to reload the page!" );
		stopLoadingModules = true;
		warningAlreadyShown = true;
	}
	return null;
}
