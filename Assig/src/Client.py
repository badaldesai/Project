'''
Created on Jul 9, 2014

@author: Vipul Kapoor 
         6486894 
         Concordia University
'''

import socket
import sys

class clientInterface():
    
    def displayOptions(self):
        print "Python DB Menu" 
        print "1. Find customer"
        print "2. Add customer"
        print "3. Delete customer"
        print "4. Update customer age"
        print "5. Update customer address"
        print "6. Update customer phone"
        print "7. Print report"
        print "8. Exit"
   
HOST, PORT = "localhost", 9998
data = "".join(sys.argv[1:])

# Create a socket (SOCK_STREAM means a TCP socket)
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    # Connect to server and send data
    sock.connect((HOST, PORT))
    print "Client Online..."
    print "Client Connected to Server..."
    
    l_clientInterface = clientInterface()
    
    while True:
   
        l_clientInterface.displayOptions()
        choise = str(raw_input("Select: "))

        if choise == "1":
            customer_name = str(raw_input("Enter the Name of the Customer: \n"))
            data_packet = str(choise) + ":" + customer_name
            sock.sendall(data_packet)
            # Receive data from the server and shut down
            received = sock.recv(1024)
            print "Received: \n{}".format(received)
            print "\n//////////////////////////////////////////"

            
        elif choise == "2":
            customer_name           = str(raw_input("Enter the Name of the Customer: \n"))
            customer_age            = str(raw_input("Enter the Age of the Customer: \n"))
            customer_address        = str(raw_input("Enter the Address of the Customer: \n"))
            customer_phone_number   = str(raw_input("Enter the Phone Number of the Customer: \n"))
            data_packet = str(choise) + ":" + customer_name + ":" + customer_age + ":" + customer_address + ":" + customer_phone_number    
            sock.sendall(data_packet)
            # Receive data from the server and shut down
            received = sock.recv(1024)
            print "Received: \n{}".format(received)
            print "\n//////////////////////////////////////////"
            
        elif choise == "3":
            customer_name = str(raw_input("Enter the Name of the Customer: \n"))
            data_packet = str(choise) + ":" + customer_name
            sock.sendall(data_packet)
            # Receive data from the server and shut down
            received = sock.recv(1024)
            print "Received: \n{}".format(received)
            print "\n//////////////////////////////////////////"   
               
        elif (choise == "4") or (choise == "5") or (choise == "6"):
            customer_name       = str(raw_input("Enter the Name of the Customer: \n"))
            customer_feild_data = str(raw_input("Enter the new data to the Feild of the Customer you wish to append: \n"))
            data_packet = str(choise) + ":" + customer_name + ":" + customer_feild_data
            sock.sendall(data_packet)
            # Receive data from the server and shut down
            received = sock.recv(1024)
            print "Received: \n{}".format(received)
            print "\n//////////////////////////////////////////"
            
        elif choise == "7":
            data_packet = str(choise)
            sock.sendall(data_packet)
            # Receive data from the server and shut down
            received = sock.recv(1024)
            print "Received: \n{}".format(received)
            print "\n//////////////////////////////////////////"
            
        elif choise == "8":
            break
        
        else:
            print "Wrong Input. Please try again."
                        
        
finally:
    print "Client Socket Shutting Down."
    sock.close()
