<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>About</h2>
<div>OpenMp3Player is a web application for playing mp3 files.</div><br><br>
<h4>What is it?</h4>
<div>You probably have a computer loaded with mp3 files and connected to loudspeakers. This 
is your entertainment center where you need to install OpenMp3Player. Run it and control music player 
with any smart device that supports http. That is practically any smart device even 
your dishwasher if it is connected to the internet (local network is enough). The users report that 
it comes extremely handy to change the music without getting out of the bed or armchair.</div>
<h4>Functionality</h4>
<div>The application comes as <strong>war</strong> file and must be deployed 
on <strong>web server</strong> that supports Java Servlet API (in my case 
I used Apache Tomcat). To deploy the application drop the OpenMp3Player.war 
into webapps directory inside Tomcat home directory. Once deployed, the application 
is reachable with web browser on <a href="http://hostname:8080/OpenMp3Player">
http://hostname:8080/OpenMp3Player</a>.<br>
GUI contains buttons to handle playing of mp3 files, a dropdown menu to select playlists and 
loaded mp3 files (from the selected playlist).<br>
<strong>Play</strong> button starts playing the first song from the playlist<br>
<strong>Previous</strong> starts playing previous song from the playlist.<br>
<strong>Next</strong> starts playing next song from the playlist.<br>
<strong>Stop</strong> stops playing.<br>
<strong>Playlists dropdown menu</strong> - select playlist. Confirm selection by 
clicking on <strong>OK</strong> buton.<br>
<strong>List of files</strong> - a list of mp3 files as defined in the xml file. Each 
mp3 file has little play button (&gt;).<br>
When a song is played out, next song from the playlist will 
automatically be played.<br>
<br>
<strong>Playlists</strong><br>
Playlists are defined in file playlists.xml. Each playlist has defined name 
and source of mp3 files. The source can either be a path to directory where 
mp3 files are stored or a reference to another xml file which holds paths to 
mp3 files (in any directory). In first case all mp3 files from defined directory 
are loaded. Currently the playlists can only be modified manually in text editor.<br>
<br>
<br>
Backup playlists before OpenMp3Player is updated to newer version.
</div>
<br>
<br>
<h2>Licencing</h2>
<p>OpenMp3Player uses the following open source components.</p>
<ul>
<li><strong>Apache Tomcat server 7.0.57</strong> (<a href="http://tomcat.apache.org">http://tomcat.apache.org</a>) - web server</li>
<li><strong>JSF 1.2</strong> (<a href="https://javaserverfaces.java.net">https://javaserverfaces.java.net</a>) - mojarra JSF implementation</li>
<li><strong>JLayer 1.0.1</strong> (<a href="http://www.javazoom.net">http://www.javazoom.net</a>, 
<a href="http://sourceforge.net/projects/javalayer">http://sourceforge.net/projects/javalayer</a>) - mp3 player implementation</li>
<li><strong>jID3lib 0.5.4</strong> (<a href="http://javamusictag.sourceforge.net">http://javamusictag.sourceforge.net</a>) 
- read ID3 tags</li>
<li><strong>Icons from IconArchive.com</strong> (<a href="http://www.iconarchive.com">
http://www.iconarchive.com</a>) - icons that are allowed for non-commercial 
usage (which OpenMp3Player is)</li>
<li><strong>simple-logger</strong> (<a href="http://www.matjazcerkvenik.si/projects/simplelogger">
http://www.matjazcerkvenik.si/projects/simplelogger</a>) - logger utility</li>
</ul>
<br>
<br>
<h2>History</h2>
<div>

<h3>2.0.0</h3>
<p>october 2014</p>
<ul>
<li>redesign to JSF</li>
<li>ID3 Tag support</li>
<li>playlist management (add/delete)</li>
<li>playing queue</li>
<li>custom tags</li>
<li>embedded Apache Tomcat</li>
</ul>
<br>

<h3>1.4.0</h3>
<p>april 2014</p>
<ul>
<li>volume up/down</li>
<li>repeat song</li>
<li>settings window</li>
<li>migration towards HTML5</li>
<li>CLI: added volume control, show information</li>
<li>bug fixes</li>
</ul>
<br>

<h3>1.3.1</h3>
<p>march 2014</p>
<ul>
<li>GUI improvements</li>
<li>buttons replaced by icons</li>
<li>dropdown menu in player gui (containing playlist editor)</li>
<li>alert (javascript) if playlist name is empty (when saving queue)</li>
<li>popup box on button clicks</li>
<li>changed styling to black and white</li>
<li>bug fixes</li>
</ul>
<br>

<h3>1.3.0</h3>
<p>march 2014</p>
<ul>
<li>SAX parser replaced with JAXB (requires Java 6)</li>
<li>moved playlists from Mng to PlistMng</li>
<li>two types of playlists - one for playing songs and one for browsing</li>
<li>playlist editor</li>
<li>CLI interface</li>
<li>queue</li>
<li>save queue (to playlist xml)</li>
<li>empty queue</li>
<li>delete playlist (xml file)</li>
<li>added properties for configuration</li>
<li>added logger</li>
<li>minor bug fixes</li>
</ul>
<br>

<h3>1.2.0</h3>
<p>january 2014</p>
<ul>
<li>Renamed to OpenMp3Player</li>
<li>toggle Play/Stop button</li>
<li>if player is not playing 'Stopped' is displayed</li>
<li>added version</li>
<li>corrected bug to play on Windows</li>
<li>NPE was thrown in watchdog when shutting down Tomcat</li>
</ul>
<br>

<h3>1.1.1</h3>
<p>january 2014</p>
<ul>
<li>changed form-input-type buttons with button-element buttons</li>
<li>dropdown menu automatic selection</li>
<li>refresh button to refresh currently playing song</li>
<li>more styles</li>
</ul>
<br>

<h3>1.1.0</h3>
<p>january 2014</p>
<ul>
<li>Play, Stop, Next, Prev buttons enchanced with AJAX functionality to avoid 
reloading page</li>
<li>added jquery</li>
</ul>
<br>

<h3>1.0.0</h3>
<p>january 2014</p>
<ul>
<li>Play, Stop, Next, Prev buttons</li>
<li>Show currently playing song</li>
<li>Select playlist</li>
<li>Show songs in current playlist</li>
<li>Each song in playlist has play button</li>
<li>Playlists manually editable</li>
</ul>
<br>

</div>
<h2>Plans for future</h2>
<div>
- playlist management<br>
- merge playlists<br>
- youtube playlist
- pause button<br>
- sorting (by song name, artist, tag...)<br>
- warning if file does not exist<br>
- upload file<br>
- migrate to Jetty<br>
- migrate to JBOSS<br>
- SOAP interface<br>
- REST interface<br>
- FTP download of files<br>
</div>
