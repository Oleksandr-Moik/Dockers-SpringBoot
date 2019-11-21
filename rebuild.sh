cd ./DockerSBServise3_Age/ && ./gradlew clean build && docker build . -t service-3-age:v1 &
cd ./DockerSBServise2_LastName/ && ./gradlew clean build && docker build . -t service-2-lastname:v1 &
cd ./DockerSBServise1_FirstName/ && ./gradlew clean build && docker build . -t service-1-firsname:v1 & exit