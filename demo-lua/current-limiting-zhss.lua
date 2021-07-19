-- 全局限流+业务限流

-- 全局限流，假设可以做为写死的，也可以是动态可配置的，redis
-- 全局限流，每秒最多可以放过去多少流量假设是可以配置的，写入到redis里，你只要定时的从redis里
-- 读取全局限流的QPS数量就可以了
-- 每隔一段时间，就自动去查询redis读取就可以了
globalLimiting = 10000

-- 业务限流，读取redis里面当前的秒杀场次，把场次里的每个商品的限购数量读取出来
-- 这个秒杀场次里每个商品最多可以抢购多少件，最多对每个商品的抢购请求放过去数量 * 1.1
currentSessionId = 101

currentSessionProductLimiting = {}
currentSessionProductLimiting[518] = 1000
currentSessionProductLimiting[629] = 10000
currentSessionProductLimiting[745] = 200

-- 现在在OpenResty里，过来了一个请求
-- 先做全局限流，每秒最多可以放过去1w个请求，先做全局的限流
-- 定义一个变量，变量可以存放当前这一秒放过去的请求数量
-- 正常的思路，获取当前的时间戳，判断是否属于当前这一秒，对当前这一秒的数量进行累加
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
        -- 在OpenResty里都是支持提取HTTP请求的请求参数，请求信息的
        -- 提取出来你的本次抢购的秒杀商品的id
        local productId = 518
        local productRequests = currentProductRequests[productId]

        if(productRequests == nil or productRequests == 0)
        then
            -- 进行HTTP请求的放行，在OpenResty里如何放行HTTP请求，都讲过的
            currentProductRequests[productId] = 1
            currentRequests = currentRequests + 1
        else
            local productLimiting = currentSessionProductLimiting[productId]

            if(productRequests <= productLimiting * 1.1)
            then
                -- 进行HTTP请求的放行，在OpenResty里如何放行HTTP请求，都讲过的
                currentProductRequests[productId] = productRequests + 1
                currentRequests = currentRequests + 1
            else
                -- 对秒杀商品的放行过去的请求数量已经超过了限购数量的1.1倍了
                -- 此时进行业务限流，返回响应给客户端，说明抢购失败
            end
        end
    else
        -- 这一秒内的请求数量超过了1w，进行全局限流
        -- 在OpenResty里讲过如何返回一些响应，返回一个预定义的响应给客户端就可以了
        -- 他就知道被限流了，通知用户说抢购失败了
    end
else
    currentTime = timestamp
    -- 把新的一秒里第一个请求直接放行HTTP请求到后端的抢购系统去
    currentRequests = 1
end

