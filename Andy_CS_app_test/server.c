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

typedef struct connectionInfo
{
    int descriptor;
    int width;
    int height;
    int frequency;
} connectionInfo;

void resetClient(connectionInfo *client)
{
    *client = (connectionInfo) { -1, -1, -1, -1 };
}

int main(int argc, char *argv[])
{        
    int maxClients = 30;
    connectionInfo clients[maxClients];
    for (int i = 0; i < maxClients; i++)   
    {   
        resetClient(&clients[i]);
    }   
         
    int masterSocket;
    if((masterSocket = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {   
        perror("Creation of master socket failed");   
        exit(EXIT_FAILURE);   
    }   
     

    int optval = true;
    if(setsockopt(masterSocket, SOL_SOCKET, SO_REUSEADDR, (char*)&optval, sizeof(optval)) < 0)
    {   
        perror("Failed to set master socket to reduce wait time.");
        exit(EXIT_FAILURE);
    }   
     
    struct sockaddr_in address;
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

    int addressSize = sizeof(address);
    puts("Waiting for connections...");

    while(true)
    {   
        fd_set descriptors;
        FD_ZERO(&descriptors);

        FD_SET(masterSocket, &descriptors);
        int highestSocketDescriptor = masterSocket;

        for (int i = 0 ; i < maxClients ; i++)
        {   
            if(clients[i].descriptor > -1)
                FD_SET(clients[i].descriptor, &descriptors);
                 
            if(clients[i].descriptor > highestSocketDescriptor)
                 highestSocketDescriptor = clients[i].descriptor;
        }
     
        int activity = select(highestSocketDescriptor + 1, &descriptors, NULL, NULL, NULL);
        if ((activity < 0) && (errno!=EINTR))
        {   
            printf("Error selecting activity");
        }
             
        if (FD_ISSET(masterSocket, &descriptors))
        {     
            int newSocket;
            if ((newSocket = accept(masterSocket, (struct sockaddr*)&address, (socklen_t*)&addressSize)) < 0)
            {   
                perror("Error accepting new connection");
            }   
            printf("New connection, socket descriptor is %d, ip is: %s, port: %d\n", newSocket, inet_ntoa(address.sin_addr), ntohs(address.sin_port));
           
            char *message = "You have reached the server!\n";
            if(send(newSocket, message, strlen(message), 0) != strlen(message))
            {
                perror("Error in sending whole message");
            }

            bool foundSpace = false;
            for (int i = 0; i < maxClients; i++)
            {
                if(clients[i].descriptor == -1)
                {
                    clients[i].descriptor = newSocket;
                    printf("Adding to list of sockets as %d\n" , i);
                    foundSpace = true;
                    break;
                }
                //Possible TODO expand number of clients possible.
            }   
            if (foundSpace == false)
            {
                //Possible TODO no space message?
                close(newSocket);
            }
        }   

        for (int i = 0; i < maxClients; i++)
        {   
            if (FD_ISSET(clients[i].descriptor, &descriptors))
            {       
                int charactersRead;
                char buffer[1025];
                if ((charactersRead = read(clients[i].descriptor, buffer, 1024)) <= 0)
                {   
                    //Socket disconnected, closed descriptor with nothing to read, or failed.
                    getpeername(clients[i].descriptor, (struct sockaddr*)&address, (socklen_t*)&addressSize);
                    printf("Disconnected, ip: %s, port: %d \n", inet_ntoa(address.sin_addr), ntohs(address.sin_port));
                    close(clients[i].descriptor);
                    resetClient(&clients[i]);
                }
                else
                {
                    buffer[charactersRead] = '\0';

                    char* next = buffer;
                    while(*next != '\0' && *next != '\n')
                    {
                        printf("Address: %p, value: %hhx \n", next, *next);
                        if (*next == 'w')
                            clients[i].width = strtol(next + 1, &next, 10);
                        else if (*next == 'h')
                            clients[i].height = strtol(next + 1, &next, 10);
                        else if (*next == 'f')
                            clients[i].frequency = strtol(next + 1, &next, 10);
                    }

                    printf("Width: %d, Height: %d, Frequency: %d \n", clients[i].width, clients[i].height, clients[i].frequency);

                    //buffer[0] = '!';
                    buffer[charactersRead] = '\0';
                    send(clients[i].descriptor, buffer, strlen(buffer), 0);
                }   
            }   
        }   
    }   

    return 0;
}   