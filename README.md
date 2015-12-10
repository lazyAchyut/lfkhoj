# lfkhoj

##Building
------------------------

*  Ensure you have JDK 8 (or newer) installed

> java -version

*  IDE Eclipse is used

*  If you already have Maven 3.2.5 (or newer) installed you can use it directly

> mvn install

Using Eclipse
-------------
1. Install the latest version of eclipse
2. Make sure Xmx in eclipse.ini is at least 1280M, and it's using Java 8
3. Launch eclipse and install the Jetty plugin
   (get it from: http://www.eclipse.org/
   or install "Maven Integration for Eclipse" from the Eclipse Marketplace)
4. In eclipse preferences Java->Code Style, import the cleanup, templates, and
   formatter configs in [ide-configs/eclipse](https://github.com/lfkhoj) in the lfkhoj repository.
5. Use import on the root pom, which will pull in all modules
6. Make sure you use the depedency of Jersey Bundle.(get it from: http://www.mvnrepository.com/


Using Hipchat
-------------
1.You can easily build your own ad-hoc Hip Chat integrations from your own custom applications
Steps  
  *  Log into hipchat.com
  *  go to Rooms>Integration
  *  Find new then click on “Build your own ”
2. Configure  your slash command
  *  creating a REST endpoint in your application you can invoke a message to be sent back to Hip Chat
  *  You create a slash command 
  *  Your teammates type your command in chat which posts to your service URL
  *  Your integration goes off and gets the relevant data
  *  Your integration sends a message back in chat
3.  For more information, refer (https://www.hipchat.com/docs/apiv2)


