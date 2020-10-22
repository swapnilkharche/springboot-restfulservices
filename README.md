# springboot-restfulservices
#Docker Commands

#Create Network 

docker network create -d bridge springboot-angular-bridge-network
 
 #Run docker image

docker run -p 5000:5000 --name=springbootapp --network=springboot-angular-bridge-network swapnil145/springboot-restfulservices:v1.0.5
