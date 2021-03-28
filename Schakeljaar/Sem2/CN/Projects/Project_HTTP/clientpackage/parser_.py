# = Handling parsing =

# == Imports: ==
import socket
from bs4 import BeautifulSoup
import os, time
import get, head

# == Create HTML, find embedded objects and store ==

"""
This function will be called after all the data is received from [[head.py]] and [[get.py]], and the client is attained from [[client.py]].
It will turn raw data into HTML using *BeautifulSoup*.
From this soup, it will search for embedded objects and parse these in [[parser_.py#section-4]].
Then everything is saved correctly.
In the end the client's socket is closed.
"""
def set_html_file(client, data):
    html = data
    soup = BeautifulSoup(html, 'html.parser')
    
    images = get_images(client,soup)
    
    print("Address: ", client.addr)
    name = client.addr.split('.')[1]
    index = "index.html"
    
    path = os.getcwd()
    if not os.path.exists(path + '\\get\\home\\'+name):
        os.mkdir(path + '\\get\\home\\'+name)
        os.mkdir(path + '\\get\\home\\'+name+'\\images')
    
    if(images != [] and images != [()]):
        for (src,image) in images:
            print(src)
            print("IMG: ", str(image))
            f = open('get/home/{}/images{}'.format(name, src), "wb")
            f.write(image)
    
    
    
    f = open('get/home/{}/{}'.format(name, index), "w")
    
    soup = soup.prettify()
    f.write(str(soup))
    f.close()
    
    client.sock.close()
# == Getting embedded objects ==

"""
This function is called when an HTML is made and ready to find objects.
We look for the tag: 'img' to find images. Then we save the source of the images to generate a request in [[parser_.py#section-6]].
When we get the request, we send it, get the headers in [[head.py]] and get the data in [[get.py]].
In the end, we format the source in the HTML to match the actual location on the Client.
Finally, we return the source and reply.
"""    
def get_images(client, data):

    print('[GET] getting images...')
    
    images = []
    reply = b''
    for imgtag in data.find_all('img'):
        src = imgtag.get('src')
        
        src = src.replace(" ", "_")
        src = src.replace("%20", "_")
        request = get_from_src(src, client.addr, client)
        
        client.sock.send(request)
        headers = head.get_head(client)
        reply   = get.get_data(headers, client)
        
        src = src.split("/")[-1]
        
        if(src.startswith("/") == False):
            src = "/{}".format(str(src))
        url = "{}{}".format(str(client.addr), str(src))
        
        imgtag['src'] = "./images{}".format(src)

        print('Request: ', str(request))
    
        images.append((src, reply))
       
    return images

# == Generate request for objects ==

"""
After an embedded object is found and the path to the folder is found, we create a request to get this object from the server.
Then we return the request.
"""
def get_from_src(src, host, client):
    print(src, host)
    if src[0] == "/":
        src = src[1:]
    print("[GET] getting object...")
                    
    request  = ("GET" + ' /{} HTTP/1.1\r\n').format(src).encode('utf-8')
    request += 'Host: {}\r\n'.format(client.addr).encode('utf-8')
    request += b'\r\n\r\n'
    print("[GET] image request: ", request)
    return request
    
def main():        
    print("Parser.")
    
if __name__ == "__main__":
    main()