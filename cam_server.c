#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <syslog.h>

int main(){
	int server_socket, client_socket, read_size;
	char client_message[2000]; 
	
	//create socket for server
	server_socket = socket(AF_INET, SOCK_STREAM,0);
	if(server_socket == -1){
		sylog(LOG_ERR,"could not create socket");	
	}
	syslog(LOG_INFO, "socket successfully created");
	
	//Define server adress
	struct sockaddr_in server_adress;
	server_address.sin_family = AF_INET;
	server_address.sin_port = htons(9000);
	server_address.sin_addr.s_addr = INADDR_ANY;

	//bind and check if bind went well, if fail break
	if(bind(server_socket, (struct sockaddr*) &servere_address, sizeof(server_address)) < 0){
		syslog(LOG_ERR, "Could not bind socket");		
		return 1;
	}
	syslog(LOG_INFO, "socket successfully binded");
	
	//5 in queue
	listen(server_socket, 5);
	
	//accept connection from incomming client (check argument 2 and three, has something to do with 	lengt of accepted in?)
	client_socket = accept(server_socket, NULL, NULL);
	if(client_cocket < 0){
		syslog(LOG_ERR, "accept fail");
	}
	//Recive message form client
	while((read_size = recv(client_socket, client_message, 2000,0)) > 0){
		write(client_socket,client_message,strlen(client_message));
	}

	if(read_size == 0){
			
	}
	else if(read_size == -1){
			
	}

	return 0;
}
