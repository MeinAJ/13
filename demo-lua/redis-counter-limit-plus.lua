-- 计数器限流
-- key      string  限流的key
-- count    string  限流数
-- pexpire  string  单位时间(毫秒)

local key = KEYS[1] -- "redis:bucket:limit:plus"
local count = ARGV[1] -- "200"
local pexpire = ARGV[2] --"1000"

local remainTokens = tonumber(redis.call("incr", key))
if (remainTokens == 1) then
    redis.call("pexpire", key, tonumber(pexpire))
elseif (remainTokens > tonumber(count)) then
    return -1
end
return remainTokens