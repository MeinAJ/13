----------------------------------------------------------------------------------------
local redis = require 'redis'
local host = "127.0.0.1"
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
local count = "200"
local pexpire = "1000"
local zero = 0

local bucket = redis.call("hgetall", key)
local remainTokens

if tableLen(bucket) == 0 then
    redis.call("hset", key, tokensRemaining, tonumber(count))
    redis.call("pexpire", key, tonumber(pexpire))
    remainTokens = tonumber(count)
else
    remainTokens = tonumber(bucket[tokensRemaining])
end

if remainTokens == zero then
    redis.call("hset", key, tokensRemaining, remainTokens)
    return zero
else
    remainTokens = remainTokens - 1;
    redis.call("hset", key, tokensRemaining, remainTokens)
    return remainTokens
end