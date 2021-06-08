local cnt = tonumber(redis.call("incr", KEYS[1]))
if (cnt == 1) then
    -- cnt 值为1说明之前不存在该值, 因此需要设置其过期时间
    redis.call("pexpire", KEYS[1], tonumber(ARGV[2]))
elseif (cnt > tonumber(ARGV[1])) then
    return -1
end
return cnt