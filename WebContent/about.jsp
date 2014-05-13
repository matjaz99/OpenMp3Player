<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About</title>
</head>
<body>
<div>
<h4>About</h4>
<div>OpenMp3Player is a web application for playing mp3 files.</div><br><br>
<h4>The idea</h4>
<div>I wanted to have some sort of remote controller to control 
music on my computer without getting out of the bed.</div>
<h4>Design</h4>
<div>The application will run on desktop machine, 
because all my MP3 files are stored there; I just need some remote handler of 
these files and smart phone is always at the reach of the hand. 
I need graphical user interface to put buttons on 
(play, stop...). I chose HTML, because it is 
easy to create GUI and most important - 
it is supported on all (smart) devices and all operating systems. I use Java on 
daily basis, so Java seems a reasonable choice to drive the application. 
Consequently Apache Tomcat was chosen as a web server, and it has good support 
for Java servlet technology.<br>
To play mp3 files I found JLayer library on the internet.<br>
Anyting else? Yes, some mechanism that manages mp3 files, 
ie. playlists. Playlist is a list of files from the file system and 
XML seems a logical choice to hold playlist data.<br>
That's all, let's put these components to work.</div>
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
If playlists are modified Tomcat must be restarted to take effect.<br>
Backup playlists before OpenMp3Player is updated to newer version.
</div>
<h4>Plans for future</h4>
<div>- control sound volume<br>
- better playlist management<br>
- pause button<br>
- ID3 Tag support<br>
- warning if file does not exist<br>
- queue - add any song to queue to be played next (virtual playlist)<br>
- AJAX support to avoid reloading the whole page every time<br>
- migrate to Jetty<br></div>
</div>
</body>
</html>