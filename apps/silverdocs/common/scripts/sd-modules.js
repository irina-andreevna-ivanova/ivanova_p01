/* *********************************************************************** */
var CONTENT_MODULE_CONTENTDIV = "sd.module.content";
var BOOKMARKS_MODULE_ID = "sd.panel.bookmarks";
var APPLICATIONS_MODULE_ID = "sd.panel.applications";
var SEARCH_MODULE_ID = "sd.panel.search";
/* *********************************************************************** */
function onModuleLoad(spotname) {
	var moduleContent = document.getElementById(CONTENT_MODULE_CONTENTDIV).innerHTML;

	if (spotname == BOOKMARKS_MODULE_ID) {
		if (parent.processBookmarksContent) {
			parent.processBookmarksContent(moduleContent);
		} else {
			alert("Error loading bookmarks panel!");
		}
    } else if (spotname == APPLICATIONS_MODULE_ID) {
        if (parent.processApplicationsContent) {
            parent.processApplicationsContent(moduleContent);
        } else {
            alert("Error loading applications panel!");
        }
    } else if (spotname == SEARCH_MODULE_ID) {
        if (parent.processSearchContent) {
            parent.processSearchContent(moduleContent);
        } else {
            alert("Error loading search panel!");
        }
    } else {
		if (parent.processModuleContent) {
			parent.processModuleContent(spotname, moduleContent);
		} else {
			alert("Error loading module!");
		}
	}
}
