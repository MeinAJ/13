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
        currentRequests = currentRequests + 1
        print("currentRequests=" .. currentRequests)
        print("通过全局限流")
        print("nowTime=" .. nowTime)
        ngx.say("hello world " .. currentRequests);
    else
        print("操作太频繁,请稍后再试!")
    end

else
    currentRequests = 1
    lastTime = nowTime
end
