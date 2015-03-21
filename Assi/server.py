#!/usr/bin/python

#Created on Jul 13, 2014
#author: Hiren
# Server program
#Hirenkumar Tarsadiya
#Id 6742904


from socket import socket

fread = open("sam.txt") #open file
dictnry = { } #default dictionary
while 1:
    line=fread.readline().strip();  #reading file line by line and removing additional spaces
    if not line:
        break
    lst=line.split("|");
    
    if lst[0]!="":
        dictnry[lst[0].lower().strip()]=[lst[1].strip(),lst[2].strip(),lst[3].strip()]

#print dictnry
fread.close(); #closing file object
print "server started..."

# Set the socket parameters

host = "localhost"
port = 9999
buf = 5000
addr = (host,port)
# Create socket and bind to address

UDPSock = socket(AF_INET,SOCK_DGRAM)
UDPSock.bind(addr)
# Receive messages

while 1:
    receivedInfo= UDPSock.recvfrom(buf) #receive data from client in data,address format
    addr=receivedInfo[1]
    received_data=receivedInfo[0].split(":"); #store data in received_data variable as list
    
    if(received_data[0] == "1"):     
        if dictnry.has_key(received_data[1]): #check whether dictionary contains key or not 
            tmplst=dictnry[received_data[1]] 
            reply=received_data[1]+"|"+tmplst[0]+"|"+tmplst[1]+"|"+tmplst[2]
            reply=str(reply)
        else:
            reply="customer not found"
        UDPSock.sendto(reply,addr)  #send reply back to client
    elif(received_data[0]=="2"):
        if dictnry.has_key(received_data[1]):   #check whether dictionary contains key or not
            reply="data already exist"
        else:
            dictnry[received_data[1]]=[received_data[2],received_data[3],received_data[4]]
            reply="new record added successfully"
        UDPSock.sendto(reply,addr)  #send reply back to client
    elif(received_data[0]=="3"):
        if dictnry.has_key(received_data[1]):  #check whether dictionary contains key or not
            del dictnry[received_data[1]]     #delete entry from dictionary
            reply="customer's entry deleted"
        else:
            reply="customer does not exist"
        UDPSock.sendto(reply,addr)   #send reply back to client
    elif(received_data[0]=="4"):
        if dictnry.has_key(received_data[1]):   #check whether dictionary contains key or not
            l=dictnry.get(received_data[1])
            dictnry[received_data[1]]=[received_data[2],l[1],l[2]]  #update new arrived data
            reply="Age updated successfully"
        else:
            reply="customer not found"
        UDPSock.sendto(reply,addr)        
    elif(received_data[0]=="5"):
        if dictnry.has_key(received_data[1]):
            l=dictnry.get(received_data[1])
            dictnry[received_data[1]]=[l[0],received_data[2],l[2]]  
            reply="Address updated successfully"
        else:
            reply="customer not found"
        UDPSock.sendto(reply,addr)    #send reply back to client    
    elif(received_data[0]=="6"):
        if dictnry.has_key(received_data[1]):
            l=dictnry.get(received_data[1])
            dictnry[received_data[1]]=[l[0],l[1],received_data[2]]  
            reply="Phone number updated successfully"
        else:
            reply="customer not found"
        UDPSock.sendto(reply,addr)        #send reply back to client

    elif(received_data[0]=="7"):
       
        for keys in sorted(dictnry):   #sort dictionary by its key 
            l=dictnry[keys]
            reply=keys+"|"+l[0]+"|"+l[1]+"|"+l[2]
            UDPSock.sendto(reply,addr)    
        reply="stop"      #send stop when all contents are sent
        UDPSock.sendto(reply,addr)   #send reply back to client
#           
    


UDPSock.close()  #close socket
