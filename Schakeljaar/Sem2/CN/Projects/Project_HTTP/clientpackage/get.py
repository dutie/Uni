# = Handling GET =

# == Imports: ==

import time
import socket
import os

# == Content-Length: x ==

"""
After receiving the header with 'Content-Length: x' in it, we process this.
We try to receive the full length on the client's socket from [[client.py]], if this does not work, we check on the remaining length until it does.
Then we return the data.
"""
def  get_content(length, client):
    data = b''
    
    data += client.sock.recv(length)
    while len(data) < length:
        data += client.sock.recv(length - len(data))
        
    return data

def get_content_it(length, client):
    data = b''
    while True:
        data += client.sock.recv(length)
        while len(data) < length:
            time.sleep(0.3)
            data += client.sock.recv(length - len(data))
            if length < 1: break
        if length < 1: break
        
    return data

# == Transfer-Encoding: chunked ==

"""
After receiving the header with 'chunked' in it, we process this.
The function receives, on the client's socket, a chunk and checks for the length of the nex.
Also, since there are some failures, we check if the length matches that of the received data.
Then we return the data.
"""
def get_chunked(client):
    helper_data = b''
    data = b''
    next_length = 0
    first = True
    
    if first:
        next_length = int(client.sock.recv(4), 16)
        first = False
        
    while True:
        helper_data = client.sock.recv(next_length)
        
        while len(helper_data) < next_length:
            time.sleep(0.3)
            helper_data += client.sock.recv(next_length - len(helper_data))

        data += helper_data
        helper_data = b''
        while b'\r\n' not in helper_data:
            helper_data += client.sock.recv(1)
            
        next_length = int(client.sock.recv(4), 16)
        
                
        if(next_length == 0):
            break
        
    return data

# == Receiving the actual data ==

"""
After the header from [[head.py]] has been received, we look for the type of length of the file:

- **Content-Length**: x or in [[get.py#section-1]],
- **Transfer-Encoding**: Chunked [[get.py#section-2]],

and handle both accordingly.
Then we return the received data.
With the returned data, we will parse it to HTML in [[parser_.py]]
"""
def get_data(headers, client):
    data = b''
    
    content_length = int(headers.get('Content-Length', 0))
    chunked = (headers.get('Transfer-Encoding', 0))
    
    if chunked == 'chunked':
        print("[GET] Transfer-Encoding: chunked")
        data += get_chunked(client)
    
    else:
        print("[GET] Content-Length: ", content_length)
        data += get_content(content_length, client)

    return data

# == Setting up the GET request ==

"""
To setup the GET request, we form the request based on our Client [[client.py]] and send it.
"""
def setup(client):
    print("[GET]")
    
    request  = (client.command + ' / HTTP/1.1\r\n').encode('utf-8')
    request += 'Host: {}\r\n'.format(client.addr).encode('utf-8')
    request += b'\r\n\r\n'
        
    print("[GET] requesting: \r\n{}".format(request.decode('UTF-8')))
    client.sock.send(request)
    

def main():
    print("[GET]")
if __name__ == "__main__":
    main()