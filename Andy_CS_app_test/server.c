#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>

#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main()
{
    char serverMessage[256] = "You have reached the server!";
    int serverSocket = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in serverAddress;
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_port = htons(9009);
    serverAddress.sin_addr.s_addr = INADDR_ANY;

    bind(serverSocket, (struct sockaddr*) &serverAddress, sizeof(serverAddress));

    listen(serverSocket, 5); // TODO - fix 5 to something larger? Queue for connections number?

    int clientSocket = accept(serverSocket, NULL, NULL);

    send(clientSocket, serverMessage, strlen(serverMessage), 0);

    close(serverSocket);

    return 0;
}
