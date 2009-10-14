var trs = document.getElementsByTagName( "tr" );
for( index = 0; index < trs.length; index++ ) {
	currentTR = trs[ index ];
	if ( index % 2 == 0 ) {
		currentTR.className = "table_row_even";
	} else {
		currentTR.className = "table_row_odd";
	}
}