#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>

int main(){
	
	//create socket int and store socket
	int network_socket;
	network_socket = socket(AF_INET, SOCK_STREAM, 0);
	
	//specify an adress for the socket
	struct sockaddr_in server_address;
	//type of adress
	server_address.sin_family = AF_INET;
	server_address.sin_port = htons(9002);
	server_address.sin_addr.s_addr = inet_addr("192.168.20.250");
	
	int connection_status = connect(network_socket, (struct sockaddr *) &server_address, sizeof(server_address));
	//check connectionstatus	
	if(connection_status == -1){
		printf("There was an error making the connection");
	}
	char *hello_message = "helo frosdaasdasdaasm clien";
	send(network_socket, hello_message, strlen(hello_message),0);
	char server_response[256];
	//recive data
	recv(network_socket, &server_response, sizeof(server_response),0);

	//print server response
	printf("the server sent the data: %s", server_response);
	
	//close socket
	close(network_socket);	

	return 0;	
}
