# demo-api-service

Demo service to use / try different directives, also can act as startup akka-http project with
setting up all different parts like, route trait(s), configurations, basic test setup and integration with docker.

## Run

`sbt run`

## Package:

in sbt console:

`universal:packageZipTarball`

## Dockerize

First create the tarball (see above)

Then build the image (in the projects folder):

`docker build -t demoapi . `

Finally run the image with:

` docker run -d -P --name demoservice demoapi:latest `

Enter
 
`docker ps `

to find out on which localport your application is mapped.





