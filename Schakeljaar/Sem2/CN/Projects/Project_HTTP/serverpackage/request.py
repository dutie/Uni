import socket, traceback, os

HOST = ''
PORT = 51235
CLRF = '\r\n'
content_dir = "serverpackage/web"

class InvalidRequest(Exception):
	pass

class Request(object):
	"A simple http request object"
	
	def __init__(self, raw_request):
		self._raw_request = raw_request
		
		self._method, self._path, self._protocol, self._headers = self.parse_request()
	
	def parse_request(self):
		"Turn basic request headers in something we can use"
		temp = [i.strip() for i in self._raw_request.splitlines()]
		
		# if temp[0].find("HTTP") == -1:
		# 	raise InvalidRequest('Incorrect Protocol')
		
		# Figure out our request method, path, and which version of HTTP we're using
		method, path, protocol = [i.strip() for i in temp[0].split()]
		
		# Create the headers, but only if we have a GET reqeust
		headers = {}
		helping = {}#tbd
		if b'GET' == method and path != b'//':
			for i in temp[1:-1]:
				if i == b'':
					key = b'Content:'
				else:
					key = i.split(b':')[0]
					value = i.split(b':')[1]
					helping[key.strip()] = value.strip()
			# for k, v in [i.split(b':', 1) for i in temp[1:-1]]:
			# 	headers[k.strip()] = v.strip()
			headers = helping
		else:
			raise InvalidRequest('Only accepts correct requests')
		
		return method, path, protocol, headers

	def __repr__(self):
     
		self._data = ""
		if self._method == b'GET' or self._method == b'HEAD':
			if self._path == b'/':
				self._path = b'/index.html'
    
			
			path = content_dir + self._path.decode("UTF-8")
			print("[SERVING] web page: {}".format(path))
   
		try:
			f = open(path, 'rb')
			if self._method == b"GET": # Read only for GET
				print("[READING] data...")
				self._data = f.read()
				f.close()
		except:
				print("File does not exist.")
		return repr({'method': self._method, 'path': self._path, 'protocol': self._protocol, 'headers': self._headers, 'data': self._data})