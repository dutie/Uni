# = Handling PUT =

# == Create, request and send PUT ==

"""
When there is no data given when calling the function, we will request it from the user.
We request:
    - path to where we will write,
    - text that we will write.
Then we create the request and include a 'Connection: close' in the header.
The connection is then send using [[client.py]]'s socket.
The [[client.py#section-10]] and [[head.py#section-2]] handle the response.
"""
def put_data(client, res = ""):
    if(res == "" and res == ""):
        res = input("[PUT] new path to send to (e.g.: folder/file.txt): ")
        txt = input("[PUT] string to send: ")
    
    
    request  = ('PUT /{} HTTP/1.1\r\n').format(res).encode('utf-8')
    request += 'Host: {}\r\n'.format(client.addr).encode('utf-8')
    request += 'Content-Type: text/plain\r\n'.encode('utf-8')
    request += 'Content-Length: {}\r\n'.format(len(txt)).encode('utf-8')
    request += b'Connection: close\r\n'
    request += b'\r\n'
    request += txt.encode('utf-8')
    print("[PUT] put request: \r\n{}".format(request.decode('utf-8')))
    
    client.sock.send(request)
    

def main():
    print("[PUT]")
if __name__ == "__main__":
    main()