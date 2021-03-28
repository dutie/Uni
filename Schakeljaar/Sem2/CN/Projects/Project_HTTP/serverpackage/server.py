
import socket
import os
import sys
import threading
import traceback
import request


class Server:

    def __init__(self, addr="192.168.56.1", port=5050, clrf ="\r\n"):
        self.addr    = (addr, port)
        self.port    = port
        self.CLRF    = clrf
        self.threads = []
        
    def setup_server(self):
        self.SERVER = socket.gethostbyname(socket.gethostname())
        self.addr   = (self.SERVER, self.port)
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            self.server.bind(self.addr)
            self.server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        except socket.error:
            sys.exit('Failed to bind to server.')
            
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
            print(f"[ACTIVE CONNECTIONS] {threading.activeCount() - 1}")
            
    def handle_client(self, clientsock, clientaddress):
        print(f"[NEW CONNECTION] {clientaddress} connected.")

        connected = True
        while connected:
            try:
                request_ = clientsock.recv(1024)
                request_ = request.Request(request_)
                # clientsock.send(repr(request_).encode('UTF-8')).
                print("request: {}".format(request_))
                data = repr(request_)
                data = data[data.find("data")+9:]
                print("Data:", data)
                if data != "":
                    msg  = ('HTTP/1.1 200 OK{}'.format(self.CLRF)).encode('UTF-8')
                    msg += ('Content-Type: text/html{}'.format(self.CLRF*2)).encode('UTF-8')
                    msg +=(data).encode('UTF-8')
                clientsock.send(msg)
            except(KeyboardInterrupt, SystemExit):
                raise
            except request.InvalidRequest as e:
                msg  = ('HTTP/1.1 400 Bad Request{}'.format(self.CLRF)).encode('UTF-8')
                msg += ('Content-Type: text/html{}'.format(self.CLRF*2)).encode('UTF-8')
                msg +=('<h1>Invalid Request: {}</h1>'.format(e)).encode('UTF-8')
                clientsock.send(msg)
            except:
                traceback.print_exc()
            
            try:
                clientsock.close()
                connected = False
            except KeyboardInterrupt:
                raise
            except:
                traceback.print_exc()

            print(f"[{clientaddress}] {request_}")