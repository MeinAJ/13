-- 计数器限流
-- key      string  限流的key
-- count    string  限流数
-- pexpire  string  单位时间(毫秒)

local key = "redis:bucket:limit:plus"
local count = "200"
local pexpire = "1000"

local cnt = tonumber(redis.call("incr", key))
if (cnt == 1) then
    -- cnt 值为1说明之前不存在该值, 因此需要设置其过期时间
    redis.call("pexpire", key, tonumber(pexpire))
elseif (cnt > tonumber(count)) then
    return -1
end
return cnt