#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/time.h>
#include <stdbool.h>
     
#define PORT 9009
     
int main(int argc, char *argv[])
{   
    int optval = true;
    int masterSocket;
    int addressSize;
    int newSocket;
    int maxClients = 30;
    int clientSockets[maxClients];
    int activity;
    int charactersRead;
    int socketDescriptor;
    int highestSocketDescriptor;
    struct sockaddr_in address;

    char buffer[1025];
         
    fd_set descriptors;
         
    char *message = "You have reached the server!\n";
     
    for (int i = 0; i < maxClients; i++)   
    {   
        clientSockets[i] = -1;   
    }   
         
    if((masterSocket = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {   
        perror("Creation of master socket failed");   
        exit(EXIT_FAILURE);   
    }   
     
    if(setsockopt(masterSocket, SOL_SOCKET, SO_REUSEADDR, (char*)&optval, sizeof(optval)) < 0)
    {   
        perror("Failed to set master socket to reduce wait time.");
        exit(EXIT_FAILURE);
    }   
     
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
         
    if (bind(masterSocket, (struct sockaddr*)&address, sizeof(address)) < 0)   
    {   
        perror("Failed to bind socket");   
        exit(EXIT_FAILURE);   
    }   
    printf("Listener on port %d \n", PORT);   
         
    if (listen(masterSocket, 3) < 0)
    {
        perror("listening failed");
        exit(EXIT_FAILURE);
    }

    addressSize = sizeof(address);
    puts("Waiting for connections...");

    while(true)
    {   
        FD_ZERO(&descriptors);

        FD_SET(masterSocket, &descriptors);
        highestSocketDescriptor = masterSocket;

        for (int i = 0 ; i < maxClients ; i++)
        {   
            socketDescriptor = clientSockets[i];

            if(socketDescriptor > -1)
                FD_SET(socketDescriptor, &descriptors);
                 
            if(socketDescriptor > highestSocketDescriptor)
                 highestSocketDescriptor = socketDescriptor;
        }
     
        activity = select(highestSocketDescriptor + 1, &descriptors, NULL, NULL, NULL);
        if ((activity < 0) && (errno!=EINTR))
        {   
            printf("Error selecting activity");
        }
             
        if (FD_ISSET(masterSocket, &descriptors))
        {   
            if ((newSocket = accept(masterSocket, (struct sockaddr*)&address, (socklen_t*)&addressSize)) < 0)
            {   
                perror("Error accepting new connection");
            }   
             
            printf("New connection, socket descriptor is %d, ip is: %s, port: %d\n", newSocket, inet_ntoa(address.sin_addr), ntohs(address.sin_port));
           
            if(send(newSocket, message, strlen(message), 0) != strlen(message))
            {
                perror("Error in sending whole message");
            }

            bool foundSpace = false;
            for (int i = 0; i < maxClients; i++)
            {
                if(clientSockets[i] == -1)
                {
                    clientSockets[i] = newSocket;
                    printf("Adding to list of sockets as %d\n" , i);
                    foundSpace = true;
                    break;
                }
                //TODO expand number of clients possible.
            }   
            if (foundSpace == false)
            {
                //TODO no space message?
                close(newSocket);
            }
        }   

        for (int i = 0; i < maxClients; i++)
        {   
            socketDescriptor = clientSockets[i];

            if (FD_ISSET(socketDescriptor, &descriptors))
            {   
                if ((charactersRead = read(socketDescriptor, buffer, 1024)) <= 0)
                {   
                    //Socket disconnected, closed descriptor with nothing to read.
                    getpeername(socketDescriptor, (struct sockaddr*)&address, (socklen_t*)&addressSize);
                    printf("Disconnected, ip: %s, port: %d \n", inet_ntoa(address.sin_addr), ntohs(address.sin_port));
                    close(socketDescriptor);
                    clientSockets[i] = -1;
                }
                else
                {
                    buffer[charactersRead] = '\0';
                    send(socketDescriptor, buffer, strlen(buffer), 0);
                }   
            }   
        }   
    }   

    return 0;
}   