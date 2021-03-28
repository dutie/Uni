# = Client class =

"""
This file represents the server. Requires address of host, port, clrf and threads that will be added on startup.
It receives requests and handles them. It controls how web users access hosted files. 
We are using an HTTP/1.1 server. It understands URLs and HTTP commands. The server can be accessed through the ip-address,
unfortunately, i did not implement domain names of the html files it stores,
and it delivers the content of these hosted websites to the end user's device.
"""

# == Imports: ==

import socket
import os
import sys
import threading
import traceback, datetime
import request


class Server:
    # == Initialize ==
    def __init__(self, addr="192.168.56.1", port=5050, clrf ="\r\n"):
        self.addr    = (addr, port)
        self.port    = port
        self.CLRF    = clrf
        self.threads = []
        
    # == Set up server ==
    
    """
    Try to bind to a socket to listen to incomming requests, problem may be: 
    malformed url or no internet connection
    
    Post:

        - **if** try succeeds: connect,
        - **else** throw new error
    """
        
    def setup_server(self):
        self.SERVER = socket.gethostbyname(socket.gethostname())
        self.addr   = (self.SERVER, self.port)
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            self.server.bind(self.addr)
            self.server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        except socket.error:
            sys.exit('Failed to bind to server.')
            
    # == Start listening ==
    
    """
    Create a thread for each incomming connection and name it clientsock with its address: clientaddress.
    Then the thread is added to the server if we needed to access it later on.
    Start the thread and go to the handle_client [[server.py#section-5]] 
    """
    
    def start_up(self):
        self.server.listen()
        print(f"[LISTENING] Server is listening on {self.server}")
        while True:
            clientsock = ""
            clientaddress = ""
            try:
                clientsock, clientaddress = self.server.accept()
                print("[CLIENT SOCK] ", clientsock)
            except KeyboardInterrupt:
                raise
            except:
                traceback.print_exc()
           
            thread = threading.Thread(target=self.handle_client, args=(clientsock, clientaddress))
            self.threads.append(thread)
            thread.start()
            print(f"[ACTIVE CONNECTIONS] {threading.activeCount() - 5}")
    # == Handle incomming requests ==
    
    """
    Try to receave the header and create the actual request in [[request.py]].
    Then, according to the HTTP command, resolve the query.
    if: 
    
    - GET: find the required file and send it to client with the appropriate code. If it does not exist send the appropriate code (404).
    - PUT: see if file exists, if it does and the data is not the same, overwrite it. If it does but the data is the same, send this with the appropriate code (204). Else create the new file.
    - POST: see if file exists, if it does and the text is different, append. Else create a new file when the file does not exist. Return appropriate code to client (200, 201, 204).
    - Other: InvalidRequest will be prompted and asked to retry (400, 500).
    """
    
    def handle_client(self, clientsock, clientaddress):
        print(f"[NEW CONNECTION] {clientaddress} connected.")

        connected = True
        while connected:
            try:
                request_ = clientsock.recv(1024)
                request_ = request.Request(request_)
                data     = request_._data
                status   = ""
                    
                if request_._method != b"GET" and request_._method != b"HEAD":
                    
                    length_ = int(request_._headers[b"Content-Length"])
                    act_length = len(data) + 4
                    while act_length < length_:
                        data += clientsock.recv(length_)
                        
                    if request_._method == b"PUT":
                        path = os.getcwd()
                        name = request_._path
                        path_to_file = name[2:name.find(name.split('\\')[-1])]
                        if not os.path.isfile(path + '\\get\\files' + name):
                            status = "201 CREATED"
                        else:
                            status = "204 NO CONTENT"
                        name = name.replace("\\", "/")
                        name = name.split("/")[-1]
                        f = open("get/files/{}".format(name), "wb")
                        f.write(data)
                        f.close()
                    if request_._method == b"POST":
                        path = os.getcwd()
                        name = request_._path
                        path_to_file = name[2:name.find(name.split('\\')[-1])]
                        if not os.path.isfile(path + '\\get\\files' + name):
                            status = "201 CREATED"
                            name = name.replace("\\", "/")
                            name = name.split("/")[-1]
                            f = open("get/files/{}".format(name), "wb")
                            f.write(data)
                            f.close()
                        else:
                            string_ = open("get/files/{}".format(name), "rb").read()
                            if string_ == data:
                                status = "304 Not Modified"
                            else:                            
                                status = "200 OK"
                                
                                name = name.replace("\\", "/")
                                name = name.split("/")[-1]
                                f = open("get/files/{}".format(name), "ab")
                                f.write(data)
                                f.close()
                        
                else:
                    status = "200 OK"
                data = request_parser(request_, status)
                clientsock.send(data)
                    
                print("[SENDING] {}".format(data))
                    
            except(KeyboardInterrupt, SystemExit):
                connected = False
                break
            
            except request.InvalidRequest as e:
                msg  = ('HTTP/1.1 400 Bad Request{}'.format(self.CLRF)).encode('UTF-8')
                msg += ('Content-Type: text/html{}'.format(self.CLRF*2)).encode('UTF-8')
                msg +=('<h1>Invalid Request: {}</h1>'.format(e)).encode('UTF-8')
                clientsock.send(msg)
            except:
                traceback.print_exc()
                break
            


            print(f"[{clientaddress}]")
        clientsock.close()

# == Setting up the answer to send to the client ==

"""
This will parse the result of the query of the client into an answer.
Here the client can act accordingly to the answer.
"""

def request_parser(request_, status):
    content_length = len(request_._data)
    datetime_      = datetime.datetime.now().strftime("%d/%m/%Y %H:%M:%S")
    
    header  = ('HTTP/1.1 {}{}'.format(status,'\r\n')).encode('UTF-8')
    header += b'Accept-Ranges: bytes \r\n'
    header += 'Content-Type: {} \r\n'.format(request_._content_type).encode('UTF-8')
    header += 'Content-Length: {}\r\n'.format(content_length).encode('UTF-8')
    header += 'Date: {}\r\n'.format(datetime_).encode('UTF-8')
    header += b'\r\n'
    header += request_._data
    header += b'\r\n'
    return header