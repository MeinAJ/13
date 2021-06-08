function table_leng(t)
    local leng=0
    for k, v in pairs(t) do
        leng=leng+1
    end
    return leng;
end

local key = "redis:bucket:limit:plus"-- key
local maxTokens = tonumber("2000") -- 最高200并发
local resetBucketInterval = tonumber("10000")-- 1000ms重置令牌时间

local bucket = redis.call('hgetall', key)
local currentTokens

-- 若当前桶未初始化,先初始化令牌桶
if table_leng(bucket) == 0
then
    -- 初始桶内令牌
    currentTokens = maxTokens
    -- 初始化令牌桶的过期时间
    redis.call('pexpire', key, resetBucketInterval)
end

-- 如果当前令牌 == 0 ,更新桶内令牌, 返回 0
if currentTokens == 0
then
    redis.call('hset', key, 'tokensRemaining', currentTokens)
    return 0
else
    -- 如果当前令牌 大于 0, 更新当前桶内的(令牌 -1) , 再返回当前桶内令牌数
    redis.call('hset', key, 'tokensRemaining', currentTokens - 1)
    return currentTokens
end