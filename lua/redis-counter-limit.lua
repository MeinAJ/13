---------------------------------------------------------------------------------------------
local redis = require 'redis'
local host = "192.168.2.52"
local port = 6379
client = redis.connect(host, port)

redis.call = function(cmd, ...) 
    return assert(loadstring('return client:'.. string.lower(cmd) ..'(...)'))(...)
end
---------------------------------------------------------------------------------------------


local cnt = tonumber(redis.call("incr", "lock:key"))
if (cnt == 1) then
    redis.call("pexpire", "lock:key", tonumber("60"))
elseif (cnt > tonumber(ARGV[1])) then
    return -1
end
print(cnt)
return cnt