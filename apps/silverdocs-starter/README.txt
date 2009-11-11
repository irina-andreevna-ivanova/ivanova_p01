This application provides a starter pack for creating your own version of silverdocs.
Keep in mind that the application was created for Firefox. It might work ok on Chrome, Opera or Safari.
It DOESN'T work on IE.   

The following files and folders are provided:

FILES
    - index.html: the main start page of the application. Defines the two FRAMES of the application (topbar and the content)
    - topbar.html: defined the top bar
    - contents.html: the HTML code for the content
    - credits.txt: credits for the authors of the libraries used by the application

FOLDERS
    - common: contains the images (icons, backgrounds, etc), the various plugins used by the application,
      the JS scripts and the CSS file for customizing the look and feel of the application
    - docs: contains documents (in most cases TXT files) written by you, where you can keep notes, code 
      snippets that you found useful, and any other text or image content that you want to store. You can
      then reference these resources in the modules
    - modules: contains the HTML modules that are being rendered on the main page. These modules contain anything
      you want, since they are simple HTML pages. However, in 99% of the cases you will place links and search
      forms on these small items.
   
JavaScript files (located in common/scripts)
You will be interested in 2 JS files:
    - sd-moddef.js: here you define your modules. For each definition you must provide a logical name (which
      you will then use for defining the layout of the page) and the name of the small HTML page that contains
      the module content
    - sd-layoutdef.js: here you define the layout of the page, the order of the modules on the page as well as
      the number of the columns, and the repartition of modules on each column.
      
Things to keep in mind:
    - make sure that the logical name of the module is unique
    - make sure that you use the same logical name when defining the module in sd-moddef.js, when placing the
      module on the page, in sd-layoutdef.js, and when implementing the content of the module, in the actual
      HTML document.
      
For convenience, a few modules and a sample doc have been provided.

To start creating a module, use module-x.html, which provides a simple prototype.

TIP:
    - if you use silverdocs on multiple machines, you can either:
        - put your application on a hosting server (the application has only HTML, JS and CSS files, so
          any free hosting provider will do the trick)
        - checkin your application on a SVN server, and then keep your checkouts up-to-date on all the machines
          where you need the application 

The LOCALS:
    - on the main page, there is a module called Locals. Here you can add links that will be stored locally,
      on the current computer. Any synchronization of the application sources will not publish the local links
      (the links are stored on a cookie, on your computer)
      WARNING: if you clean your cookies in the browser, the local links will also be deleted.

The QJ
    - right next to the Google button on the top bar there are 2 buttons: QJ and a GO button (the one with
      the green arrow). QJ comes from Quick Jump, and you can assign a link to the QJ feature, and then quickly
      jump to this link whenever you want. Drag and drop a link in the Google search box, then click on QJ.
      The link will be stored locally. Then whenever you want, just click the green arrow.               
      
Have fun!
Bogdan Mocanu
http://bogdanmocanu.wordpress.com