# = Handling requests =

# == Imports: ==

import socket, traceback, os, datetime

HOST = ''
PORT = 51235
CLRF = '\r\n'

# == Handling invalid requests ==

"""
For any invalid request, this will be thrown and the exact error code can be added.
"""

class InvalidRequest(Exception):
	pass

# == HTTP request object ==
class Request(object):
    
	# === Initialize the request ===
	
	"""
	From the raw stream of data we try to get all the information we need in a clean manner. We do this by using the parse_request method [[request.py#section-6]]
	"""
    	
	def __init__(self, raw_request):
		self._raw_request = raw_request
		
		self._method, self._path, self._protocol, self._headers, self._data, self._content_type = self.parse_request()
	
	# === Parse the request ===
 
	"""
	According to the required HTTP command, we parse the header and following data in such a way that we can know what to send back, which file and with what (error) code.
	"""
 
	def parse_request(self):
		print(self._raw_request)
		temp = [i.strip() for i in self._raw_request.splitlines()]
		
		method, path, protocol = [i.strip() for i in temp[0].split()]
		
		headers = {}
		helping = {}
  
		data = ""
  
		"""
		For GET and HEAD request, we split the headers and find the requested data but for the HEAD we do not go any further. For the GET we also read the data of the requested file.
		If the file does not exist, we raise an error.
		For PUT and POST we almost do the same. See if the file exists, see if the data is already there, if not we create a file and write it. For PUT we overwrite it when there is something else, for POST we append it.
		If these do not work, there is a server malfunction and we send this
		"""
  
		if (b'GET' == method and path != b'//') or (b'HEAD' == method and path != b'//'):
			for i in temp[1:-1]:
				if i == b'':
					key = b'Content:'
				else:
					key = i.split(b':')[0]
					value = i.split(b':')[1]
					helping[key.strip()] = value.strip()

			headers = helping
   
			if path == b'/':
				path = b'/index.html'

			content_type = "text/html"
			
			path = "serverpackage/web" + path.decode("UTF-8")
			print("[SERVING] web page: {}".format(path))
			if 'web/images' in path:
				content_type = "img"
			try:
				f = open(path, 'rb')
				if method == b"GET":
					print("[READING] data...")
					data = f.read()
					f.close()
			except:
					raise InvalidRequest('404 File does not exist.')
		elif(b'PUT' == method):
			print("[SERVING] PUT")
			text = False
			for i in temp[1:-1]:
				print(i)
				if i == b'':
					text = True
				else:
					if text == False:
						key = i.split(b':')[0]
						value = i.split(b':')[1]
						helping[key.strip()] = value.strip()
					else:
						key = b'Content:'
						data = i
						

			headers = helping
			print(headers)
			print(path)
			path = path.replace(b"/", b"\\")
			path = path.decode("UTF-8")
			print("[SERVING] PUT: {}".format(path))
			content_type = "bytes"
   
		elif(b'POST' == method):
			print("[SERVING] POST")
			text = False
			for i in temp[1:-1]:
				print(i)
				if i == b'':
					text = True
				else:
					if text == False:
						key = i.split(b':')[0]
						value = i.split(b':')[1]
						helping[key.strip()] = value.strip()
					else:
						key = b'Content:'
						data = i
						

			headers = helping
			print(headers)
			print(path)
			path = path.replace(b"/", b"\\")
			path = path.decode("UTF-8")
			print("[SERVING] POST: {}".format(path))
			content_type = "bytes"

		
		else:
			raise InvalidRequest('500 Could not handle request.')
		
		return method, path, protocol, headers, data, content_type

	def __repr__(self):
		return repr({'method': self._method, 'path': self._path, 'protocol': self._protocol, 'headers': self._headers, 'data': self._data, 'content_type': self._content_type})