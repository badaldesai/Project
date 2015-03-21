'''
Created on Jul 11, 2014

@author: rahul
'''
import socket   #for sockets
import re
import sys  #for exit
#Database menu to display the user
print ('python DB Menu')
print ('1.Find Customer')
print ('2.Add Customer')
print ('3.Delete Customer')
print ('4.Update Customer Age')
print ('5.Update Customer Address')
print ('6.Update Customer Phone')
print ('7.Print Report')
print ('8.Exit')
try:
#socket creation using UDP
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
except socket.error:
    print 'Failed to create socket'
    sys.exit()
host = 'localhost';
port = 9999;
#infinite loop to communicate with server untill user himself exits the loop
while(1) :
    user_input=raw_input("Enter the choice ")
    try :
        if user_input=='1':# find customer
            choice=raw_input('Enter the customer name which you want to find')#get input from user
            temp_str='1'+'|'+choice.lower()#concatenete the string and userchoice
            s.sendto(temp_str, (host, port))#send the concatenate string to server
            recv_in=s.recvfrom(1024)#receive the response form the server
            get_list=[]#list to store individual word
            get_list_split=recv_in[0].split('|')#split the string which received from the server 
            for each_temp_str_split in get_list_split:
                get_list.append(each_temp_str_split)#store the individual string to the list
            #Print all the information 
            if get_list[0]!='0':
                print 'Name: ',get_list[0]
                print 'Age: ',get_list[1]
                print 'Street: ',get_list[2]
                print 'PhoneNumber: ',get_list[3]
            else:
                print get_list[1]
                continue
        elif user_input=='2':#add customer to the server side dictionary
            print ('Enter all the customer Details which you want to add')
            user_name_input=raw_input("Enter the name of the customer: ")#get name
            if re.match("^[A-Za-z]*$", user_name_input) or re.match("^[A-Za-z\s]*$", user_name_input):#name only include alphabets and/or space
                name=user_name_input.lower()
            else:
                print ('you have entered wrong name')
                continue
            user_age_input=raw_input("Enter the age of the customer: ")#get age
            if re.match("^[0-9]*$", user_age_input):
                age=user_age_input
            else:
                print ('you have entered wrong age')
                continue
            user_street_input=raw_input("Enter the street of the customer: ")#get address
            if re.match("^[A-Za-z0-9\s]*$", user_street_input):
                street=user_street_input
            else:
                print ('you have entered wrong street')
                continue
            user_phone_number_input=raw_input("Enter the phone number of the customer: ")#get phone number
            if re.match("^[A-Za-z0-9-\s]*$", user_phone_number_input):
                num=user_phone_number_input
            else:
                print ('you have entered wrong phone number')
                continue
            user_send='2'+'|'+name+'|'+age+'|'+street+'|'+num#take all info and concatenate as a string
            s.sendto(user_send, (host, port))
            con=s.recvfrom(1024)
            print con[0]
        elif user_input=='3':#delete customer
            del_input=raw_input('Enter the customer name which you want to delete: ')
            user_del_send='3'+'|'+del_input.lower()
            s.sendto(user_del_send,(host,port))
            con=s.recvfrom(1024)
            print con[0]
        elif user_input=='4':#update the age
            update_name_age=raw_input('Enter the customer name whose you want to update the age: ')#take the name of the customer as an identity whose info wants to change
            update_age=raw_input('Enter the new age: ')
            update_age_string='4'+'|'+update_name_age.lower()+'|'+update_age
            s.sendto(update_age_string,(host,port))
            con=s.recvfrom(1024)
            print con[0]
        elif user_input=='5':#update the address
            update_name_address=raw_input('Enter the customer name whose you want to update the address: ')
            update_address=raw_input('Enter the new address: ')
            update_address_string='5'+'|'+update_name_address.lower()+'|'+update_address
            s.sendto(update_address_string,(host,port))
            con=s.recvfrom(1024)
            print con[0]
        elif user_input=='6':#update the phone number
            update_name_phone=raw_input('Enter the customer name whose you want to update the phone number: ')
            update_phone=raw_input('Enter the new phone number: ')
            update_phone_string='6'+'|'+update_name_phone.lower()+'|'+update_phone
            s.sendto(update_phone_string,(host,port))
            con=s.recvfrom(1024)
            print con[0]
        elif user_input=='7':#print the dictionary on the client side from the server side
            show_dic='7'+'|'+'show full dictionary'
            s.sendto(show_dic,(host,port))#sent the request to the server side
            con=s.recvfrom(500000)#receive the dictionary from server
            first_split=con[0].split('$')
            for first in first_split:#take one customer at a time and split its attribute
                if first!='':
                    split_list=[]
                    second_split=first.split('|')
                    for second in second_split:#split the individual attribute 
                        split_list.append(second)
                    print 'Name :'+split_list[0]+' Age :'+split_list[1]+' Address :'+split_list[2]+' PhoneNumber :'+split_list[3]
        elif user_input=='8':#shut down the client
            print 'GoodBye'
            s.close()#closed the socket
            break
        else:
            print ('Invalid choice')
    except socket.error, msg:
        print 'Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
        sys.exit()