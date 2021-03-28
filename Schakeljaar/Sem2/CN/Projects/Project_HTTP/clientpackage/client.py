# = Client class =

"""
This file represents the client. Requires address of host, port and HTTP request command.
It is the middleman of the project that will divide the work over the different parts of a request.
"""

# == Imports ==
import socket
from bs4 import BeautifulSoup
import os, sys

import get, head, post, put, parser_


class Client:
    # == Initialize ==
    def __init__(self, addr="www.google.com", port=80, command="GET"):
        self.addr    = addr
        self.port    = port
        self.command = command
        self.server  = (addr, port)
        
    # == Set up client socket ==
    
    """
    Try to create a socket, problem may be: 
    malformed url or no internet connection
    
    Post:

        - **if** try succeeds: connect,
        - **else** throw new error
    """
    def setup_socket(self):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            self.sock.connect(self.server)
        except socket.error:
            sys.exit('Failed to create socket')
        
            
    """
    According to the HTTP request command, try to request, 
    handle and store everything correctly.
    """
    
    def send_msg(self):
        data =''
        # === Handling GET ===
        
        """
            Go to file: [[get.py]].
        """
        if self.command == "GET"  :
            get.setup(self)
            headers = head.get_head(self)
            data = get.get_data(headers, self)
            parser_.set_html_file(self, data)
        # === Handling PUT ===
        
        """
            Go to file: [[put.py]].
        """
        if self.command == "PUT"  : 
            put.put_data(self)
            headers = head.get_head(self)
        # === Handling POST === 
        
        """
            Go to file: [[post.py]].
        """
        if self.command == "POST" : 
            post.post_data(self)
            headers = head.get_head(self)
        # === Handling HEAD ===    
        
        """
            Go to file: [[head.py]].
        """
        if self.command == "HEAD" : 
            head.head_data(self)                    
                    
        return data

    
def main():        
    data = b''
    
if __name__ == "__main__":
    main()