/* *********************************************************************** */
var MODULES_MAXVISIBLEPERCOLUMN = 3;
var MODULES_DIRECTORY           = "modules/";
var MODULES_LOCAL_DIRECTORY     = "file:///C:/";
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
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.general.knowledge";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-general-knowledge.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.books.notes";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-books-notes.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.locallinks";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-locallinks.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.ide.eclipse";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-ide-eclipse.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.caching";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-caching.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.algorithms";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-algorithms.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.design.modelling.uml";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-design-modelling-uml.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.computer.http";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-computer-http.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.computer.ascii";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-computer-ascii.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.computer.various";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-computer-various.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.design.patterns";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-design-patterns.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.general.various";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-general-various.html";
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
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.jms";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-jms.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.db.jdbc";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-db-jdbc.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.db.sql";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-db-sql.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.db.hibernate";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-db-hibernate.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.testing";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-testing.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.json";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-json.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.xml";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-xml.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.xsddtd";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-xsddtd.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.xsl";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-xsl.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.caching";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-caching.html";
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
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.java.more";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-java-more.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.j2me";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-j2me.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.j2ee";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-j2ee.html";
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
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.ejb";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-ejb.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.apache.maven";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-apache-maven.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.apache.commons";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-apache-commons.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.apache.various";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-apache-various.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.j2ee.jsf";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-j2ee-jsf.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.java.spring";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-java-spring.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.web.html";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-web-html.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.regex";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-regex.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.versioncontrol";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-versioncontrol.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.linux";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-linux.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.general.browsers.firefox";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-general-browsers-firefox.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.soa";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-soa.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.security";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-security.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.dev.webservices";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-dev-webservices.html";
SDModules[ moduleIndex ][ MODULE_VISIBLE   ] = false;
SDModules[ moduleIndex ][ MODULE_PINNED    ] = false;
initModule( SDModules[ moduleIndex ] );

SDModules[ ++moduleIndex ] = new Array();
SDModules[ moduleIndex ][ MODULE_ID        ] = "sd.admin.servers";
SDModules[ moduleIndex ][ MODULE_FILENAME  ] = MODULES_DIRECTORY + "module-admin-servers.html";
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
