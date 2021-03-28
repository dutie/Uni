# = Start the client =

# == Imports: ==
import socket
from bs4 import BeautifulSoup
import os
import sys

import client

# == Main function ==

"""
Function that will ask for input and send data to create a Client in [[client.py]].
"""
def main():
    # Requests necessary data to start the HTTP request.
    print("Start typing the command. E.g., GET www.google.com:80 HTTP/1.1")
    req = input(">")
    command, addr, http = req.split(' ')
    addr, port = addr.split(':',1)
    port = int(port)
    print(command, addr, port)
    
    """
        **If** the query is send starting with HTTP:// 
        we will trim this to make further code easier.
    """
    
    if 'http://' in addr or 'HTTP://' in addr:
        addr = addr.replace('http://', '')
        addr = addr.replace('HTTP://', '')
     
    """    
    From the client.py we will:
            - make a new client that has an address, port and the correct request command,
            - setup the socket for this request,
            - send the request and handle it correctly.
    """
    client_ = client.Client(addr, port, command)
    client_.setup_socket()
    client_.send_msg()

# == Main ==

"""
Called upon when file is requested.
Opens main function.
"""
if __name__ == "__main__":
    main()