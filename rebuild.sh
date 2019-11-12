cd ./DockerSBServise3_Age/ && gradle clean build && docker build . -t service-3-age:v1 &
cd ./DockerSBServise2_LastName/ && gradle clean build && docker build . -t service-2-lastname:v1 &
cd ./DockerSBServise1_FirstName/ && gradle clean build && docker build . -t service-1-firsname:v1