To run dockerized jenkins with docker CLI (needed to run jenkins pipeline that uses docker)

- build new docker image from Dockerfile (```docker image build -t custom-jenkins-docker .```)
- run the images with ``` docker run -p 8080:8080 -p 50000:50000 --privileged -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home custom-jenkins-docker ```