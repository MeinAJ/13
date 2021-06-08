local redis = require 'redis'
local host = "192.168.2.52"
local port = 6379
client = redis.connect(host, port)

redis.call = function(cmd, ...) 
    return assert(loadstring('return client:'.. string.lower(cmd) ..'(...)'))(...)
end

redis.call("set","key001","123")
local value = redis.call("get","key001")
print(value)