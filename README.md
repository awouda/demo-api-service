# demo-api-service

Demo service to use / try different directives, also can act as startup akka-http project with
setting up all different parts like, route trait(s), configurations, basic test setup and integration with docker.


## Revolver support

No need to restart thanks to the revolver plugin, just enter _within_ 
the sbt console:

`~reStart`

## Publish as docker image using sbt docker commands

Build your app as usual, when ready for dockerizing:

`sbt docker:publishLocal`

When the image is created, you can run the image with:

`docker run -dit -p 9023:9023 --name demoapiSBTdocker demo-api-service:0.1-SNAPSHOT`

## Package to docker manual


### First create archive tarball
in sbt console:

`universal:packageZipTarball`

### Then create the dockerile manually

Then build the image (in the projects folder):

`docker build -t demoapi . `

Finally run the image with:

` docker run -d -P --name demoservice demoapi:latest `

Enter
 
`docker ps `

to find out on which localport your application is mapped.
As you can see, the docker task within sbt is slightly easier. 
In the build.sbt you can see how you can set environment vars.
If not all possibilities in the docker sbt config 
(http://www.scala-sbt.org/sbt-native-packager/formats/docker.html#configuration)
are enough for you, you could revert to the manual method. 


