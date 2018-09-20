#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include <conio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <syslog.h>
#include <unistd.h>
//#include <capture.h>

int main(){
	int server_socket, client_socket, read_size;
	char client_message[2000]; 
	printf("test");
	
	//create socket for server
	server_socket = socket(AF_INET, SOCK_STREAM,0);
	if(server_socket == -1){
		syslog(LOG_ERR,"could not create socket");	
	}
	syslog(LOG_INFO, "socket successfully created");
	
	//Define server adress
	struct sockaddr_in server_adress;
	server_adress.sin_family = AF_INET;
	server_adress.sin_port = htons(9002);
	server_adress.sin_addr.s_addr = INADDR_ANY;

	//bind and check if bind went well, if fail break
	if(bind(server_socket, (struct sockaddr*) &server_adress, sizeof(server_adress)) < 0){
		syslog(LOG_ERR, "Could not bind socket");		
		return 1;
	}
	syslog(LOG_INFO, "socket successfully binded");
	
	//5 in queue
	listen(server_socket, 5);
	
	//accept connection from incomming client (check argument 2 and three, has something to do with 	lengt of accepted in?)
	client_socket = accept(server_socket, NULL, NULL);
	if(client_socket < 0){
		syslog(LOG_ERR, "accept fail");
	}
	//Recive message form client
	while((read_size = recv(client_socket, client_message, 2000,0)) > 0){
		printf("the client sent the data: %s", client_message);		
		write(client_socket,client_message,strlen(client_message));

	}
	if(read_size == 0){
		printf("the end of connection");		
	}
	else if(read_size == -1){
		printf("ERROR!!");
	}

	return 0;
}
