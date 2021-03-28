# = Start the server =

# == Imports: ==

import server
import request
import traceback

# == Main function ==

# Performs startup operation of the server in [[server.py]]
server = server.Server()        
server.setup_server()
server.start_up()