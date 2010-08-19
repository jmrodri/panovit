import xmlrpclib
import time

routeId = "%07luP" % (int(time.time()) % 10000000)
client = xmlrpclib.Server("http://www.pandora.com/radio/xmlrpc/v27?rid=%s&method=authenticateListener" % routeId)
client.listener.authenticateListener(int(time.time()), "jmrodri@gmail.com", "luvLiz69")

