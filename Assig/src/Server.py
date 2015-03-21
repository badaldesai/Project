'''
Created on Jul 9, 2014

@author: Vipul Kapoor 
         6486894 
         Concordia University
'''

import SocketServer
import os
from collections import deque
import collections

class MyDataBase():

    d = {}
    DatabaseLoad_Status = False
    
    def loadDataBase(self):
        # trying to read data from file
        if os.access("data.txt", os.R_OK):
            with open('data.txt','r') as f:
                global d
                global DatabaseLoad_Status
                
                d = {}
                Name = ''
                for line in f:
                    queue = deque(line.split("|"))
                    i = 0
                    for s in queue:
                        if i == 0:
                            Name = s
                            d[Name] = {}
                        elif i == 1:
                            d[Name]['Age'] = s
                        elif i == 2:
                            d[Name]['Address'] = s
                        elif i == 3:
                            d[Name]['Phone_number'] = s
                        i = i + 1
                        
                print "Database Loaded at Server"
                
                DatabaseLoad_Status = True  
        else:
            print "Cannot Open data.txt"
            print "Database Failed to Load"
            DatabaseLoad_Status = False


    def findCustumer(self, p_name):
        print "Client Trying to find details of Customer: " + p_name
        
        if DatabaseLoad_Status == False:
            return "Database Loading Failed @ Server. Cannot perform any operation"
        
        if p_name in d:
            return "Name: " + str(p_name) + "\nAge: "+ str(d[p_name]['Age']) + "\nAddress: " + str(d[p_name]['Address']) + "\nPhone_Number: " + str(d[p_name]['Phone_number']) 
        else:
            return "Customer Not Found"
        
    def addCustomer(self, p_name, p_age, p_address, p_phone_number):
        print "Client Trying to add details of Customer: " + p_name
        
        if DatabaseLoad_Status == False:
            return "Database Loading Failed @ Server. Cannot perform any operation"
        
        if p_name in d:
            return "Customer Already Exists in Records"
        else:
            d[p_name] = {}
            d[p_name]['Age'] = p_age
            d[p_name]['Address'] = p_address
            d[p_name]['Phone_number'] = p_phone_number 
            return "New Customer Details Added into DataBase"

    def deleteCustomer(self, p_name):
        print "Client Trying to delete details of Customer: " + p_name
        
        if DatabaseLoad_Status == False:
            return "Database Loading Failed @ Server. Cannot perform any operation"
        
        if p_name in d:
            del d[p_name]
            return "Customer Details delete from DataBase"
        else:
            return "Customer does not Exist. Cannot Delete"
        
    def updateOptions(self, p_name, p_feild, p_new_feild_data):
        print "Client Trying to Append details of Customer: " + p_name
        
        if DatabaseLoad_Status == False:
            return "Database Loading Failed @ Server. Cannot perform any operation"
        
        
        if p_name in d: 
            d[p_name][p_feild] = p_new_feild_data
            return "Customer details Updated"
        else:
            return "Customer Does Not Exist. Cannot Append data"
        
    def printReport(self):
        print "Client Trying to Get details of All Customers: "
        
        if DatabaseLoad_Status == False:
            return "Database Loading Failed @ Server. Cannot perform any operation"
        
        od = collections.OrderedDict(sorted(d.items()))
        all = "\n"
        for Name in od:
            all = all + "Name: " + str(Name) + "\t Age: "+ str(d[Name]['Age']) + "\t Address: " + str(d[Name]['Address']) + "\t Phone_Number: " + str(d[Name]['Phone_number']) + "\n"
        return all
        
    def printDictionary(self):
        print d


class MyTCPHandler(SocketServer.BaseRequestHandler):
    """
    The RequestHandler class for our server.

    It is instantiated once per connection to the server, and must
    override the handle() method to implement communication to the
    client.
    """               
    def handle(self):
        # self.request is the TCP socket connected to the client
        while 1:
            self.data = self.request.recv(1024).strip()
            
            if not self.data:
                break
            
            print "{} wrote:".format(self.client_address[0])
            print self.data
        
            l_clientData_Packet = str(self.data)
            client_request_data = l_clientData_Packet.split(":")
       
            l_db = MyDataBase()
            #If client sends option 1
            if client_request_data[0] == "1":
                result = l_db.findCustumer(client_request_data[1])
                      
            #If client sends option 2
            elif client_request_data[0] == "2":
                result = l_db.addCustomer(client_request_data[1], client_request_data[2], client_request_data[3], client_request_data[4])

            #If client sends option 3
            elif client_request_data[0] == "3":
                result = l_db.deleteCustomer(client_request_data[1])
            
            elif (client_request_data[0] == "4") or (client_request_data[0] == "5") or (client_request_data[0] == "6"):
                if client_request_data[0] == "4":
                    result = l_db.updateOptions(client_request_data[1], "Age",client_request_data[2])
                elif client_request_data[0] == "5":
                    result = l_db.updateOptions(client_request_data[1], "Address",client_request_data[2])
                elif client_request_data[0] == "6":
                    result = l_db.updateOptions(client_request_data[1], "Phone_number",client_request_data[2])
                

            elif client_request_data[0] == "7":
                result = l_db.printReport()
            
            print result
            print "-----------------------------------"
            self.request.sendall(result)
  


if __name__ == "__main__":
    HOST, PORT = "localhost", 9998

    # Building DataBase
    my_DataBase  = MyDataBase()
    my_DataBase.loadDataBase()

    print "Server Online..."
    # Create the server, binding to localhost on port 9999
    server = SocketServer.TCPServer((HOST, PORT), MyTCPHandler)

    # Activate the server; this will keep running until you
    # interrupt the program with Ctrl-C
    server.serve_forever()

