
redis.call("set","key001","123")
local value = redis.call("get","key001")
print(value)