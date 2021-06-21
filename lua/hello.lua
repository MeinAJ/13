----------------------------------------------------------------------------------------
local redis = require 'redis'
local host = "192.168.2.52"
local port = 6379
client = redis.connect(host, port)
redis.call = function(cmd, ...)
    return assert(load('return client:' .. string.lower(cmd) .. '(...)'))(...)
    -- return assert(loadstring('return client:' .. string.lower(cmd) .. '(...)'))(...)
end

----------------------------------------------------------------------------------------

local key = "place"
local jingdu = 106.485617
local weidu = 29.521523
local field = "aj"
local jingdu1 = 106.480407
local weodu1 = 129.53444
local field1 = "dalao"

redis.call("geoadd",key,jingdu,weidu,field)