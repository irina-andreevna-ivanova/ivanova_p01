/* *********************************************************************** */
var CONTENT_MODULE_CONTENTDIV = "sd.module.content";
var BOOKMARKS_MODULE_ID = "sd.bookmarks";
/* *********************************************************************** */
function onModuleLoad(spotname) {
	var moduleContent = document.getElementById(CONTENT_MODULE_CONTENTDIV).innerHTML;

	if (spotname == BOOKMARKS_MODULE_ID) {
		if (parent.processBookmarksContent) {
			parent.processBookmarksContent(moduleContent);
		} else {
			alert("Error loading bookmarks!");
		}
	} else {
		if (parent.processModuleContent) {
			parent.processModuleContent(spotname, moduleContent);
		} else {
			alert("Error loading module!");
		}
	}
}

