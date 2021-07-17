-- 全局限流+业务限流

--1.全局限流,限流数可以定死,也可以通过从redis中获取配置,能够配置是比较灵活的
global_limiting = 10000
--2.业务限流,每个秒杀批次中,都有一批商品,这些商品都有库存大小,直接从redis中获取,这里只写一个批次的业务限流
current_session_id=100
current_session_products={}
current_session_products[100]=1000
current_session_products[101]=2000
current_session_products[102]=3000


