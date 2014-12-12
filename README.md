OpenMp3Player
===================

New version 2 is still in development phase. It will bring many new features, such as:
- JSF 1.2 will be used (JSP based)
- tags and labels

Last stable release: 1.4.0


What is it?
-------------------
OpenMp3Player is a web application for playing mp3 files. It runs on Apache Tomcat server.


Deploy
-------------------
Before deploying make sure that Apache Tomcat server is running (run $TOMCAT_HOME/bin/startup.sh).

Unzip and put OpenMp3Player.war into $TOMCAT_HOME/webapps directory.

With web browser go to: <a href="http://hostname:8080/OpenMp3Player">
http://hostname:8080/OpenMp3Player</a>


For developers
-------------------
Developed on tomcat 7. To start add VM arg to tomcat:
-Domp3p.home="/path/to/OpenMp3Player"

Change file permissions to 755 for all *.sh files in OpenMp3Player directory (recursively):
$ find OpenMp3Player-2.0.0-beta.10.tomcat -name \*.sh -exec chmod 755 {} +


History
-------------------

2.0.0
zzz 2014

- use JSF instead of JSP technology and see if it still works




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

