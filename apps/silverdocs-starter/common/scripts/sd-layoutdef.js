/* *********************************************************************** */
/* This file contains the composition of the page layout. Each group of 
 * segments below represents a column on the page. The order of the segments
 * is the order of the items on each column.                               
 *
 * The modules are defined in sd-moddef.js                                 */
/* *********************************************************************** */
var SPOT_MODULEID = 0;
var SPOT_NAME     = 1;

var SDSpots = new Array();
var columnIndex = -1;
var spotIndex;

/* *********************************************************************** */
spotIndex = -1;
SDSpots[ ++columnIndex ] = new Array();

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.locallinks";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Locals";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.general.search";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Search";

/* *********************************************************************** */
spotIndex = -1;
SDSpots[ ++columnIndex ] = new Array();
SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.web.html";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "HTML";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.web.javascript";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "JavaScript";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.web.css";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "CSS";

/* *********************************************************************** */
spotIndex = -1;
SDSpots[ ++columnIndex ] = new Array();

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.java";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "Java";

SDSpots[ columnIndex ][ ++spotIndex ] = new Array();
SDSpots[ columnIndex ][ spotIndex ][ SPOT_MODULEID ] = "sd.dev.java.j2se";
SDSpots[ columnIndex ][ spotIndex ][ SPOT_NAME     ] = "J2SE";

/* *********************************************************************** */
function spotSortFunction( spot1, spot2 ) {
	return ( spot1[ SPOT_NAME ] < spot2[ SPOT_NAME ] ) ? -1 : 1;
}

function getSpotsListOrderedByName() {
	var unifiedArray = new Array();
	for( columnIndex = 0; columnIndex < SDSpots.length; columnIndex++ ) {
		unifiedArray = unifiedArray.concat( SDSpots[ columnIndex ] );
	}

	unifiedArray.sort( spotSortFunction );
	return unifiedArray;
}
