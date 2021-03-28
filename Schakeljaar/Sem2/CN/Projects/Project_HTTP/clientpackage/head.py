# = Handling headers =

# == Imports: ==
import socket
import os

# == Get the header ==

"""
After a request has been send on a socket, this method will receive the header of the reply.
The header is put in a dictionary for simple handling later on.
"""
def get_head(client):
    print("[HEAD]...")
    data = b''
    while b'\r\n\r\n' not in data:
        data += client.sock.recv(1)
       
    header = data[:-4].decode()
    # The if statement is here to fix a bug with a Google reply that a lot of people seemed to have.
    if header[0:1] == '\n':
        header = header[1:]
    print(header.splitlines()[1:])
    
    header = header.splitlines()[1:]
    if "HTTP" in header[0]:
        header = header[1:]
    headers = dict([i.split(': ') for i in header])
    print("[HEAD] head is:\r\n{}".format(header))
    return headers

def head_data(client):
    print("[HEAD]")
        
    request  = (client.command + ' / HTTP/1.1\r\n').encode('utf-8')
    request += 'Host: {}:{}\r\n'.format(client.addr, client.port).encode('utf-8')
    request += b'\r\n'
    request += b'\r\n'
    
    print("[HEAD] requesting: \r\n{}".format(request.decode()))

    client.sock.send(request)
    
    headers = get_head(client)
    
def main():
    print("[HEAD]")
if __name__ == "__main__":
    main()