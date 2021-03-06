-- 全局限流+业务限流
globalLimiting = 1000

if lastTime == nil then
    lastTime = os.date("%Y-%m-%d %H:%M")
end
print("time" .. lastTime)

if currentRequests == nil then
    currentRequests = 0
end
print("currentRequests" .. currentRequests)

local nowTime = os.date("%Y-%m-%d %H:%M")

if nowTime == lastTime then
    if currentRequests < globalLimiting then
        -- 还未达到限流数
        -- 全局限流通过
        -- 还要通过业务限流
        currentRequests = currentRequests + 1
        print("currentRequests=" .. currentRequests)
        print("通过全局限流")
        print("nowTime=" .. nowTime)
    else
        -- 已经达到限流数
        -- 不放行,直接返回错误码
        print("操作太频繁,请稍后再试!")
    end

else
    -- 不是上一秒的数据,所有数据复位
    currentRequests = 1
    lastTime = nowTime
end
