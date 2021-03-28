# = Handling POST =

# == Imports: ==
import json

# == Create, request and send POST ==

"""
When a post is requested without any given data, we request it from the user.
We request:
    - path to where the file to be written is,
    - if we want, query in the URI,
    - text or form to be written.
We check if we are sending a form or a text and act accordingly in [[post.py#section-4]] and [[post.py#section-6]].
If we request a query (not usual!) we put this in the header in [[post.py#section-4]] and [[post.py#section-8]].
Now we set the request and send it using the [[client.py]]'s socket.
"""
def post_data(client, res = ""):
    if(res == "" and res == ""):
        res = input("[POST] new path to send to (e.g.: folder/file.txt): ")
        qry = input("[POST] form to query (else just ENTER) [{key: input}, {key: input}]: ")
        txt = input("[POST] string or form [{key: input}, {key: input}] to send: ")
    
    content_type = "text/plain"
    if('[{' in txt):
        data = handle_form(txt)
        content_type = "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW"
        txt = parse_table_to_text(data)
    if(qry != ""):
        data = handle_form(qry)
        res  = handle_query(res, data)
        
    
    request  = ('POST /{} HTTP/1.1\r\n').format(res).encode('utf-8')
    request += 'Host: {}\r\n'.format(client.addr).encode('utf-8')
    request += 'Content-Type: {}\r\n'.format(content_type).encode('utf-8')
    request += 'Content-Length: {}\r\n'.format(len(txt)).encode('utf-8')
    request += b'Connection: keep-alive\r\n'
    request += b'\r\n'
    request += txt.encode('utf-8')
    print("[POST] post request: \r\n{}".format(request.decode('utf-8')))
    
    client.sock.send(request)

# == Handling a form ==

"""
When the user wants to send a form, we parse this and use JSON to create a dictionary.
Then we return the dictionary.
"""
def handle_form(data):
    cnt = 0
    temp = ""
    for char in data:
        if char == '{':
            temp += char + "\""
        elif char == ':' or char == '}':
            temp += "\"" + char
            if char == ':':
                temp += "\""
        else:
            temp += char
            
        cnt += 1
    data = json.loads(temp)
    
    return data

# == Form dictionary to header content ==

"""
After generating the form dictionary in [[post.py#section-4]], we need to put this inside the headers content.
Using the WebKitFormBoundary to send the form correctly. 
"""
def parse_table_to_text(data):
    helper_data = ""
    for arr in data:
        for key in arr:    
            helper_data += "----WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"{}\"\r\n\r\n{}\r\n".format(key, arr[key])
            
    helper_data += "----WebKitFormBoundary7MA4YWxkTrZu0gW"
    
    return helper_data
    
# == Querying the URI ==

"""
Eventhough this is not standard, this allows scaling for non-idempotent actions. It allows us to limit the scope of the current request.(Later on to remove only certain things etc.).
"""
def handle_query(res, data):
    url_string = "{}?{}".format(res, parse_url_string_form(data))
    return url_string
    

# == Query dictionary to string ==

"""    
When a query is requested and parsed to a dictionary, we need to set the query of the URI.
We do this by adding each element (key, input pair) to the url_string.
Then we return this String where it will be used to built the new URI in the header of the request.
"""
def parse_url_string_form(data):
    url_string = ""
    for arr in data:
        for key in arr:
            key = key.strip()
            arr[key] = arr[key].strip()
            
            if "=" in url_string:
                url_string += "&{}={}".format(key, arr[key])
            else:
                url_string += "{}={}".format(key, arr[key])
    return url_string  
    
    
                
            
            
                
                
            
        
    
    

def main():
    print("[POST]")
if __name__ == "__main__":
    main()