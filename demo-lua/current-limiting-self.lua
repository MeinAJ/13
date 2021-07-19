globalLimiting = 10000
currentSessionId = 101

currentSessionProductLimiting = {}
currentSessionProductLimiting[518] = 1000
currentSessionProductLimiting[629] = 10000
currentSessionProductLimiting[745] = 200

currentTime = nil
currentRequests = 0
currentProductRequests = {}

timestamp = os.date("%Y-%m-%d %H:%M:%S")

if(currentTime == nil)
then
    currentTime = timestamp
end

if(timestamp == currentTime)
then
    if(currentRequests <= globalLimiting)
    then
        local productId = 518
        local productRequests = currentProductRequests[productId]

        if(productRequests == nil or productRequests == 0)
        then
            currentProductRequests[productId] = 1
            currentRequests = currentRequests + 1
        else
            local productLimiting = currentSessionProductLimiting[productId]

            if(productRequests <= productLimiting * 1.1)
            then
                currentProductRequests[productId] = productRequests + 1
                currentRequests = currentRequests + 1
                ngx.say("hello world " .. currentRequests);
            else
            end
        end
    else
    end
else
    currentTime = timestamp
    currentRequests = 1
end

