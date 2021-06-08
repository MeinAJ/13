---------------------------------------------------------------------------------------------
local redis = require 'redis'
local host = "192.168.2.52"
local port = 6379
client = redis.connect(host, port)

redis.call = function(cmd, ...) 
    return assert(loadstring('return client:'.. string.lower(cmd) ..'(...)'))(...)
end

-- 获取table的长度
function table_leng(t)
  local leng=0
  for k, v in pairs(t) do
    leng=leng+1
  end
  return leng;
end

---------------------------------------------------------------------------------------------
local bucket = redis.call('hgetall', 'redis:bucket:limit')
local num = table_leng(bucket)
print(num)

redis.call('hset','redis:bucket:limit','lastRefillTime',123)

bucket = redis.call('hgetall', 'redis:bucket:limit')
local num1 = table_leng(bucket)
print(num1)