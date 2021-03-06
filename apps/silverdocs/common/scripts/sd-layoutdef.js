/* *********************************************************************** */
var SPOT_MODULEID = 0;
var SPOT_NAME     = 1;
var SPOT_COLOR    = 2;

var SDSpots = new Array();
var columnIndex = -1;
var spotIndex;

/* *********************************************************************** */
spotIndex = -1;
SDSpots[ ++columnIndex ] = new Array();

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.locallinks";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Locals";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.general.knowledge";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Knowledge";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.general.various";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Misc";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.general.browsers.firefox";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Mozilla Firefox";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.admin.servers";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Admin - Servers";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.linux";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Linux";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.algorithms";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Algorithms";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.books.notes";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Book notes";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.ide.eclipse";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "IDE: Eclipse";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.ide.netbeans";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "IDE: Netbeans";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.design.modelling.uml";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "UML";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.design.patterns";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Patterns, Refactoring, XP";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.computer.network";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Network Protocols";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.security";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Security";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.general.techbag";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Technology bag";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.shortcuts";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Shortcuts";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

/* *********************************************************************** */
spotIndex = -1;
SDSpots[ ++columnIndex ] = new Array();

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.db.jdbc";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Database &amp; JDBC";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.db.sql";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "SQL";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.db.hibernate";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Hibernate";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.caching";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Caching";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.json";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "JSON";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.xml";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "XML &amp; XSD &amp; DTD";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.xsl";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "XSL &amp; XSLT &amp; XSL-FO";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.regex";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Regular Expressions";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.web.html";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "HTML";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.web.javascript";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "JavaScript";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.web.css";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "CSS";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.computer.ascii";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "ASCII";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.soa";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Service Oriented Arch";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.webservices";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Web Services";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

/* *********************************************************************** */
spotIndex = -1;
SDSpots[ ++columnIndex ] = new Array();

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.java";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Java";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.java.more";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Java (more)";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.j2se";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "J2SE";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_red";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.j2me";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "J2ME";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.j2ee";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "J2EE";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.jms";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "JMS";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.various";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Java various";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.ejb";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "EJB";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.apache.maven";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Apache Maven";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_blue";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.apache.commons";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Apache Commons";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.apache.various";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Apache Projects";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.j2ee.jsf";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "JSF &amp; Apache MyFaces";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.spring";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Spring";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_green";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.testing";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Testing &amp; JUnit";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.versioncontrol";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Version Control Systems";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.samples";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Samples";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_COLOR    ] = "back_gr_gray";

/* *********************************************************************** */
function spotSortFunction( spot1, spot2 ) {
	return ( spot1[ SPOT_NAME ] < spot2[ SPOT_NAME ] ) ? -1 : 1;
}

function getSpotByModuleId( moduleId ) {
	for( columnIndex = 0; columnIndex < SDSpots.length; columnIndex++ ) {
		for( rowIndex = 0; rowIndex < SDSpots[ columnIndex ].length; rowIndex++ ) {
			if ( SDSpots[ columnIndex ][ rowIndex ][ SPOT_MODULEID ] == moduleId ) {
				return SDSpots[ columnIndex ][ rowIndex ];
			}
		}
	}

	alert( "Cannot find the SPOT for module ID: " + moduleId );
	return null;
}

function getSpotsListOrderedByName() {
	var unifiedArray = new Array();
	for( columnIndex = 0; columnIndex < SDSpots.length; columnIndex++ ) {
		unifiedArray = unifiedArray.concat( SDSpots[ columnIndex ] );
	}

	unifiedArray.sort( spotSortFunction );
	return unifiedArray;
}
