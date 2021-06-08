local cnt = tonumber(redis.call("incr", KEYS[1]))
print(cnt)
if (cnt == 1) then
    redis.call("pexpire", KEYS[1], tonumber(ARGV[2]))
elseif (cnt > tonumber(ARGV[1])) then
    return -1
end
return cnt