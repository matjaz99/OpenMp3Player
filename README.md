## OpenMp3Player


Release 3 is still in development phase. It features JSF 2.0.

Last stable release: 2.2.0


### What is it?

OpenMp3Player is a web application for playing mp3 files. It runs on Apache Tomcat 7 server which comes 
embedded with the application (deployment is not required).


### Starting

On Windows just double click the start.bat.

On Linux or OS X you might first need to change file permissions of all .sh files 
in OpenMp3Player directory. You can do this with single command (recursively):
'$ find OpenMp3Player-2.x.x -name \*.sh -exec chmod 755 {} +'

Then start OpenMp3Player on Linux or OS X by executing start.sh script:
$ ./start.sh


To stop execute stop.sh or stop.bat script.

With web browser go to: <a href="http://hostname:8080/OpenMp3Player">
http://hostname:8080/OpenMp3Player</a>


### Upgrade

Currently it is possible only manually:
copy 'playlist' and 'config' directories from old version to new version and 
restart OpenMp3Player (stop/start)


### For developers

Developed in Eclipse Kepler. JDK 1.6 is needed. Deploy on Apache Tomcat 7.
Add VM args to set custom OpenMp3Player home directory when starting the project in 
Apache Tomcat runtime configuration (Eclipse):
-Domp3p.home="/path/to/OpenMp3Player"


### Git branches:

master - JSP and servlet technology

v2 - JSF 1.2

v3 - JSF 2.1


### History
-------------------
3.0.0-alpha.03
february 2015

- ajax on putToQueue button
- empty queue on save
- player and currently-playing as one component
- PlayerBean is now ApplicationScoped bean (behaves as a singleton) and manages single instance of Mp3Player
- Watchdog moved to Mp3Player
- passive playlist separated from Mp3Player and moved to session scoped PlaylistBean.PlaylistMng
- removed binding to datatables (not needed in jsf 2)


3.0.0-alpha.02
january 2015

- ajax on some buttons (play, stop, prev, next, volume, refresh, repeat)
- o:icon composition
- o:tag component
- o:tags component and renderer
- managed beans annotations
- component and renderer annotations
- validator annotations
- cleared faces-config - using only faces annotations


3.0.0-alpha.01
january 2015

- JSF 2.0 (mojarra 2.1.29)
- changed jsp into xhtml
- cleared faces-config - using managed beans annotations


2.2.0
january 2015

- creating custom tags moved to settings
- colors dropdown menu for creating new tags
- dark text on bright tags
- use color definition from tags.xml
- delete tag button
- validators moved from io to web package
- TagValidator for duplicate tags
- DirectoryValidator to check a directory before adding to playlists
- input fields are required, error message is displayed
- english and spanish resource files prepared (not completely supported yet)
- Colors class moved to resources
- list of allowed background colors (only bright colors)
- labels implementation


2.1.1
january 2015

- added missing *.bat files


2.1.0
january 2015

- settings and help buttons moved to title bar
- repeat button moved to currently-playing bar
- volume and playlists button moved to title bar
- reduced icon size to 48 px
- new icons for volume control
- custom tag o:icon to replace h:graphicImage
- shutdown listener (interrupt watchdog, close log file)



2.0.0
december 2014

- use JSF instead of JSP technology and see if it still works
- ID3 Tag support
- playlist management (add/delete)
- custom tags
- embedded Tomcat for easier deployment




1.4.0
may 2014

- volume up/down (Linux and OSX only)
- repeat song
- settings window
- migration towards HTML5
- CLI: volume control, show information, repeat
- bug fixes



1.3.1
march 2014

- GUI improvements
- buttons replaced by icons
- dropdown menu in player gui (containing playlist editor)
- alert (javascript) if playlist name is empty (when saving queue)
- popup box on button clicks
- changed styling to black and white
- bug fixes



1.3.0
march 2014

- SAX parser replaced with JAXB (requires Java 6)
- moved playlists from Mng to PlistMng
- two types of playlists - one for playing songs and one for browsing
- playlist editor
- CLI interface
- queue
- save queue (to playlist xml)
- empty queue
- delete playlist (xml file)
- added properties for configuration
- added logger
- minor bug fixes



1.2.0
january 2014

- toggle Play/Stop button
- if player is not playing 'Stopped' is displayed
- added version
- corrected bug to play on Windows
- NPE was thrown in watchdog when shutting down Tomcat



1.1.1
january 2014

- changed form-input-type buttons with button-element buttons
- dropdown menu automatic selection
- refresh button to refresh currently playing song
- more styling



1.1.0
january 2014

- Play, Stop, Next, Prev buttons enchanced with AJAX functionality to avoid reloading page
- added jquery



1.0.0
january 2014

- Play, Stop, Next, Prev buttons
- Show currently playing song
- Select playlist
- Show songs in current playlist
- Each song in playlist has play button
- Playlists manually editable

