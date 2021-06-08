local redis = require 'redis'
local host = "192.168.2.52"
local port = 6379
client = redis.connect(host, port)

redis.call = function(cmd, ...)
    return assert(loadstring('return client:' .. string.lower(cmd) .. '(...)'))(...)
end

function table_leng(t)
    local leng = 0
    for k, v in pairs(t) do
        leng = leng + 1
    end
    return leng;
end

local key = "redis:bucket:limit:plus"
local tokensRemaining = "tokensRemaining"
local bucket = redis.call("hgetall", key)
local remainTokens

if table_leng(bucket) == 0 then
    redis.call("hset", key, tokensRemaining, 2000)
    redis.call("pexpire", key, 10000)
    remainTokens = 2000
else
remainTokens = tonumber(bucket[1])
end

if remainTokens == 0
then
    redis.call("hset", key, tokensRemaining, remainTokens)
    return 0
else
redis.call("hset", key, tokensRemaining, remainTokens - 1)
end