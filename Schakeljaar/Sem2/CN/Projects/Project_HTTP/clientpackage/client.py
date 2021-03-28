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
    def __init__(self, addr="www.google.com", port=80, command="GET", path = ""):
        self.addr    = addr
        self.port    = port
        self.command = command
        self.server  = (addr, port)
        self.path    = "/"+path
        
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
            data, content_type = get.get_data(headers, self)
            if content_type == "html":
                parser_.set_html_file(self, data)
            else:
                parser_.set_object(self, data)
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
    
    def send_end(self):
        request  = ('HEAD / HTTP/1.1\r\n').encode('utf-8')
        request += b'Connection: close'
        request += 'Host: {}:{}\r\n'.format(self.addr, self.port).encode('utf-8')
        request += b'\r\n'
        request += b'\r\n'
        

    
def main():        
    data = b''
    
if __name__ == "__main__":
    main()