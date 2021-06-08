----------------------------------------------------------------------------------------
local redis = require 'redis'
local host = "192.168.2.52"
local port = 6379
client = redis.connect(host, port)
redis.call = function(cmd, ...)
    return assert(loadstring('return client:' .. string.lower(cmd) .. '(...)'))(...)
end

----------------------------------------------------------------------------------------

function tableLen(table)
    local leng = 0
    for k, v in pairs(table) do
        leng = leng + 1
    end
    return leng;
end

local key = "redis:bucket:limit:plus"
local tokensRemaining = "tokensRemaining"
local bucket = redis.call("hgetall", key)
local remainTokens
print("元数据长度" .. tableLen(bucket))

if tableLen(bucket) == 0 then
    redis.call("hset", key, tokensRemaining, 10)
    redis.call("expire", key, 15)
    remainTokens = 20
else
    remainTokens = tonumber(bucket[tokensRemaining])
end

if remainTokens == 0 then
    redis.call("hset", key, tokensRemaining, remainTokens)
    print("剩余tokens=" .. remainTokens)
    return 0
else
    remainTokens = remainTokens - 1;
    redis.call("hset", key, tokensRemaining, remainTokens)
    print("剩余tokens=" .. remainTokens)
    return remainTokens
end